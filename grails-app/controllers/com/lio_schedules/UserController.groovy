package com.lio_schedules

import org.springframework.dao.DataIntegrityViolationException

import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.web.context.request.RequestContextHolder as RCH
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails
//import org.springframework.security.web.authentication.AuthenticationProcessingFilter
import grails.plugin.springsecurity.SpringSecurityUtils

class UserController {

    def springSecurityService
    def authenticationManager
    def userService 

    static allowedMethods = [register: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    private void authenticateAfterRegistration(final String email){
        def user = User.findWhere(email: email)
        if(!user) 
            return //or throw user not found exception
        if(user.password==springSecurityService.encodePassword(user.password))
            return //or throw auth failure exception
        def request = RCH.currentRequestAttributes().currentRequest
        def response = RCH.currentRequestAttributes().currentResponse  

        //get User Details
        def userDetails = springSecurityService.userDetailsService.loadUserByUsername(email)

        //Build Token
        def authentication = new UsernamePasswordAuthenticationToken(userDetails, user.password)
        authentication.details = new WebAuthenticationDetails(request)
        SCH.context.authentication = authentication

        //Do the authentication adn store in session (otherwise the user is only logged in for this request)
        request.session.setAttribute("SPRING_SECURITY_LAST_USERNAME", user.email)
        authenticationManager.authenticate( authentication )

        // optional remember me
        //rememberMeServices.loginSuccess request, response, authentication
    }

    def register(UserRegistrationCommand command) {
		withForm {
            if(command.hasErrors()){
                flash.message = "Invalid registration form."
                render view: "registrationPage", model: [user: command]
                return
            }
            println "registration form is valid"
            userService.createUser command.asUser()
            springSecurityService.reauthenticate command.email
            if (springSecurityService.isLoggedIn()) {
                redirect(controller: "schedule")
                //redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
                return
            }
		}.invalidToken {
            flash.message = "Try register again"
            render view: "registrationPage", model: [user: command]
		}
    }
	
	def showRegistrationPage() {
        println "All users: ${User.list()}"
        if (springSecurityService.isLoggedIn()) {
            redirect(controller: "schedule")
            return
        }
        render view: "registrationPage", model: [user: flash.user]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
}
