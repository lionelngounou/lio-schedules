package com.lio_schedules

import org.joda.time.* 

/**
 * @author lionel.ngounou
 */
@groovy.transform.EqualsAndHashCode(includes="id, user, title, fromDateTime, toDateTime")
@groovy.transform.ToString(includes="id, title, fromDateTime, toDateTime")
class Schedule {
	String title
	LocalDateTime fromDateTime, toDateTime
	String description
	
	static belongsTo = [user:User]
	
	static mapping = {
		version false
		autoTimestamp true
	}
	
    static constraints = {
		title shared: "notNullOrBlank", maxSize: 50
        user nullable:false, updateable: false
		fromDateTime nullable:false
		toDateTime nullable:false, validator: {val, schedule ->
				if(val.isBefore(schedule.fromDateTime))
					return "schedule.toDateTime.before.fromDateTime"
			}
		description maxSize: 100
	}
	
	transient springSecurityService
	static transients = ['springSecurityService']
	
	void setToDateTimeIfNull(){
		if(fromDateTime && !toDateTime){
			toDateTime=fromDateTime
		}
	}
	
	def beforeInsert(){
		setToDateTimeIfNull()
		user = springSecurityService?.currentUser
	}
	
	def beforeUpdate(){
		setToDateTimeIfNull()
	}
	
	boolean startsAndEndsAtTheSameTime(){
		fromDateTime==toDateTime
	}
}

