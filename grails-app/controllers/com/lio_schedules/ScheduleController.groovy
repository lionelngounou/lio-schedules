package com.lio_schedules

import org.springframework.dao.DataIntegrityViolationException

class ScheduleController {
	
	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def getCurrentUser(){
		springSecurityService?.currentUser
	}
	
	def getUserSchedule(Long id){
		Schedule.findByIdAndUser id, currentUser
	}
	
	def getUserSchedules(){
		 Schedule.findAllByUser currentUser
	}
	
    def index() {
		params.listMode = "week"
        redirect(action: "list", params: params)
    }

    def list(String listMode) {
		listMode = listMode ?: "day"
        [listMode: listMode, scheduleList: getUserSchedules()]
    }

	def create(){
		[scheduleInstance: flash.schedule]
	}
	
	def edit(Long id){
		def schedule = getUserSchedule id
		if (!schedule) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'schedule.label', default: 'Schedule'), id])
            redirect(action: "list")
            return
        }
		[scheduleInstance: schedule]
	}

	def save(){
		def schedule = new Schedule(params)
		schedule.user = currentUser 
		schedule.setEndIfNull()
		
        if (!schedule.save()) {
            render(view: "create", model: [scheduleInstance: schedule])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'schedule.label', default: 'Schedule'), schedule.id])
        redirect(action: "edit", id: schedule.id)
	}

	def update(Long id){
        def schedule = getUserSchedule id
        if (!schedule) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'schedule.label', default: 'Schedule'), id])
            redirect(action: "list")
            return
        }		
        
		schedule.properties = params
        schedule.setEndIfNull()
		
		if (!schedule.save()) {
            render(view: "edit", model: [scheduleInstance: schedule])
            return
        }
        
		flash.message = message(code: 'default.updated.message', args: [message(code: 'schedule.label', default: 'Schedule'), schedule.id])
        redirect(action: "edit", id: schedule.id)
	}
}
