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
		def scheduleList = searchForDates()
        [listMode: listMode, scheduleList: scheduleList, search: [from: params.from, to: params.to], 
        	today: params.today]
    }
	
	private def searchForDates(){
		params.today = new LocalDate()
		def when = params.when ?: "onDay"
		def listMode = params.listMode
		def forDate = params.long('forDate') ?: params.today.localMillis
		def from = null, to = null
		if("day" == listMode){
			//default = onDay
			from = new LocalDate(forDate)
			to = from.plusDays(1)
			if("previous"==when){
				to = from
				from = to.minusDays(1)
			}
			else if("next"==when){
				from = to
				to = from.plusDays(1)
			}
		}
		else if("week" == listMode){
			//default = onDay
			from = Utils.getFirstDateOfTheWeek(new LocalDate(forDate))
			to = from.plusWeeks(1)
			if("previous"==when){
				to = from
				from = to.minusWeeks(1)
			}
			else if("next"==when){
				from = to
				to = from.plusWeeks(1)
			}
		}
		else if("month" == listMode){
			//default = onDay
			def date = new LocalDate(forDate)
			from = Utils.getFirstDateOfTheMonth(date)
			to = Utils.getLastDateOfTheMonth(date).plusDays(1)
			if("previous"==when){
				to = from
				from = Utils.getFirstDateOfTheMonth(to.minusDays(1))
			}
			else if("next"==when){
				from = to
				to = Utils.getLastDateOfTheMonth(from).plusDays(1)
			}
		}
		params.from = from.localMillis //set in params
		params.to = to.localMillis //set in params
		from = new LocalDateTime(params.from)
		to = new LocalDateTime(params.to)
		println "@@@@@ list schedules between [$from] and [$to]"
		Schedule.where{user==currentUser && 
			((start>=from && start<=to) || (end>=from && end<=to))}.list()
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
	
	private def search(){
		def searchParams = params.search
		def from = new LocalDateTime(searchParams.from)
		def to = new LocalDateTime(searchParams.to)
		Schedule.where{user==currentUser && 
			((start>=from && start<=to) || (end>=from && end<=to))}.list()
	}
}
