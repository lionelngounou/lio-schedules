package com.lio_schedules

import org.joda.time.* 

/**
 * @author lionel.ngounou
 */
@groovy.transform.EqualsAndHashCode(includes="id, user, title, start, end")
@groovy.transform.ToString(includes="id, title, start, end")
class Schedule {
	String title
	LocalDateTime start, end
	String description
	
	static belongsTo = [user:User]
	
	static mapping = {
		version false
		autoTimestamp true
		start column: "start_date_time"
		end column: "end_date_time"
	}
	
    static constraints = {
		title shared: "notNullOrBlank", maxSize: 50
        user nullable:false, updateable: false
		start nullable:false
		end nullable:false, validator: {val, schedule ->
				if(val.isBefore(schedule.start))
					return "schedule.end.before.start"
			}
		description maxSize: 100
	}
	
	void setEndIfNull(){
		if(start && !end) {
			setEndToStart()
		}
	}
	
	void setEndToStart(){
		end=start
	}
	
	boolean startsAndEndsAtTheSameTime(){
		start==end
	}
	
}

