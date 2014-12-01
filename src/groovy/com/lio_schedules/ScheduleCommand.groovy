package com.lio_schedules

/** @author lionel.ngounou
 */
@grails.validation.Validateable
@groovy.transform.ToString(includes="id, title, description, startDate, startHour, startMinute, endDate, endHour, endMinute", 
	includeNames=true)
class ScheduleCommand{
	Long id
    String title
	String description

	String startDate, endDate
	Integer startHour, endHour, startMinute, endMinute
	
    static constraints = {
    	importFrom Schedule, exclude: ["start", "end", "user"]
		startDate nullable:false , validator: {stDate, cmd ->
			if(!Utils.isValidDateFormat(stDate))
				return "schedule.start.date.invalid"
		}
		startHour nullable:false, range: 0..23
		startMinute nullable:false, range: 0..59
		endDate nullable:true, validator: {eDate, cmd ->
			println "checking if [date:$eDate, hour:${cmd.endHour}, min:${cmd.endMinute} is empty or valid"
			if(!Utils.isEmptyOrValidDateTime(eDate, cmd.endHour, cmd.endMinute))
				return "schedule.end.invalid"
			//todo: implement end.before.start validation 
		}
		endHour nullable:true, range: 0..23
		endMinute nullable:true, range: 0..59
		description nullable:true
	}

	def getStart(){
		Utils.toLocalDateTime([date:startDate, hour:startHour, minute:startMinute])
	}

	def getEnd(){
		Utils.toLocalDateTime([date:endDate, hour:endHour, minute:endMinute])
	}

	Schedule asSchedule(){
		def schedule = new Schedule()
		populateSchedule(schedule)
		schedule 
	}

	void populateSchedule(Schedule schedule){
		schedule.properties["id", "title", "description"] = properties
		schedule.start = start
		schedule.end = end
		schedule 
	}
}