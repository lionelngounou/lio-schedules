package com.lio_schedules

import org.springframework.dao.DataIntegrityViolationException

class ScheduleController {
	
	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
		params.listMode = "week"
        redirect(action: "list", params: params)
    }

    def list(String listMode) {
		def user = springSecurityService.currentUser
		//Schedule.findAllByUser(user)
		if(!listMode) redirect(uri: "/schedule/list/day") 
        [listMode: listMode]
    }
}
