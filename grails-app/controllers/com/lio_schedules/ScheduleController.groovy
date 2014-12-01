package com.lio_schedules

import org.springframework.dao.DataIntegrityViolationException
import org.joda.time.* 
import org.joda.time.format.*

class ScheduleController {
	
	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
		params.listMode = "week"
        redirect(action: "list", params: params)
    }

    def list(String listMode) {
		listMode = listMode ?: "day"
        [listMode: listMode, scheduleList: getUserSchedules()]
    }

	def create(){
		//[scheduleInstance: flash.schedule]
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

	def save(ScheduleCommand command){
		log.info "save schedule $command"
		if(command.hasErrors()){
			log.debug "error saving schedule : invalid command"
			render(view: "create", model: [scheduleInstance: command])
			return
		}

		def schedule = command.asSchedule()
		schedule.user = currentUser 
		schedule.setEndIfNull()
		
        if (!schedule.save()) {
			log.debug "error saving schedule : invalid schedule"
            render(view: "create", model: [scheduleInstance: schedule])
            return
        }
		log.info "schedule saved"

        flash.message = message(code: 'default.created.message', args: [message(code: 'schedule.label', default: 'Schedule'), schedule.id])
        redirect(action: "edit", id: schedule.id)
	}

	def update(Long id, ScheduleCommand command){
		log.info "update schedule #$id : $command"
        def schedule = getUserSchedule id
        if (!schedule) {
        	log.debug "schedule #$id not found"
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'schedule.label', default: 'Schedule'), id])
            redirect(action: "list")
            return
        }		

		if(command.hasErrors()){
			log.debug "error updating schedule #$id : invalid command"
			render(view: "create", model: [scheduleInstance: command])
			return
		}

        command.populateSchedule(schedule)
        schedule.setEndIfNull()
		
		if (!schedule.save()) {
			log.debug "error updating schedule #$id : invalid schedule"
            render(view: "edit", model: [scheduleInstance: schedule])
            return
        }
        log.info "schedule updated"

		flash.message = message(code: 'default.updated.message', args: [message(code: 'schedule.label', default: 'Schedule'), schedule.id])
        redirect(action: "edit", id: schedule.id)
	}


	def getCurrentUser(){
		springSecurityService?.currentUser
	}
	
	def getUserSchedule(Long id){
		Schedule.findByIdAndUser id, currentUser
	}
	
	def getUserSchedules(){
		 Schedule.findAllByUser currentUser
	}
	
}
