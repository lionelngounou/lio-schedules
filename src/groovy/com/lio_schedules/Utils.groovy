package com.lio_schedules

import org.joda.time.*

/**
 * @author lionel.ngounou
 */
class Utils {
	
	final static toLocalDateTime = { params ->
		try {
			def dateText = "${params.date} ${params.hour}:${params.minute}:00"
			return Constants.dateTimeFormatter.parseLocalDateTime(dateText)
		}
		catch(e) {
			return null	
		}	
	}
	
	final static toLocalDate = {String date ->
		try {
			return Constants.dateFormatter.parseLocalDate(date)
		}
		catch(e) {
			return null	
		}	
	}

	static final boolean isValidDateFormat(String date ){
		try {
			return Constants.dateFormatter.parseLocalDate(date)!=null
		}
		catch(e) {
			//e.printStackTrace()
			return !(e instanceof IllegalArgumentException)	
		}	
	}

	final static boolean isCompleteDateTime (LocalDate date, hour, minute){
		(date && hour && minute)
	}

	final static boolean isEmptyDateTime (LocalDate date, hour, minute){
		(!date && !hour && !minute)
	}

	final static boolean isEmptyOrValidDateTime (String date, hour, minute){ 
		def localDate = Utils.toLocalDate(date)
		if(isEmptyDateTime(localDate, hour, minute)) 
			return true
		if((hour && Integer.valueOf(hour)>23) || (minute && Integer.valueOf(minute)>59)) 
			return false
		(isValidDateFormat(date) &&  isCompleteDateTime(localDate, hour, minute))
	}
}

