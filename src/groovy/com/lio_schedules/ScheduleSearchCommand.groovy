package com.lio_schedules

import org.joda.time.* 

/** @author lionel.ngounou
 */
//@grails.validation.Validateable
@groovy.transform.ToString(includes="fromDate, toDate", includeNames=true)
class ScheduleSearchCommand{
	LocalDate fromDate, toDate
	
	
}