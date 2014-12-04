package com.lio_schedules.util

import grails.test.mixin.*
import spock.lang.*
import com.lio_schedules.*
import static com.lio_schedules.Utils.*

class UtilsSpec extends Specification {

	static boolean hasDateFields(dt, year, month, day){
		dt.year==year && dt.monthOfYear==month && dt.dayOfMonth==day 
	}

	static boolean hasTimeFields(dt, hour, minute){
		dt.hourOfDay == hour && dt.minuteOfHour == minute
	}

	void 'get localDateTime from params' (){
		given:  
			def p = [date:'10/10/2010', hour: 10, minute:10]
			def dt = null

		when: 'there are all date time parts'
			dt = toLocalDateTime p
		then: 
			dt != null 
			hasDateFields(dt, 2010, 10, 10)
			hasTimeFields(dt, 10, 10)
		
		when: 'there are not all date time parts'
			p.hour = null
			dt = toLocalDateTime p
		then: 
			dt == null 

		when: 'there are invalid date time parts'
			p.hour = 133
			dt = toLocalDateTime p
		then: 
			dt == null 
	}

	void 'get localDate from formated date string' (){
		given:  
			def dateStr = '10/10/2010'
			def dt = null 

		when: 'there are all date parts'
			dt = toLocalDate dateStr
		then: 
			dt != null 
			hasDateFields(dt, 2010, 10, 10)
		
		when: 'there are not all date parts'
			dateStr = '10//2010'
			dt = toLocalDate dateStr
		then: 
			dt == null 

		when: 'there are invalid date parts'
			dateStr = '10/we/2010'
			dt = toLocalDate dateStr
		then: 
			dt == null 
	}

	@Unroll
	void 'test date[#date] string is valid [#result] format according to date format in constants'(){
		expect: 
			isValidDateFormat(dateStr) == result
		where:  
			dateStr		 || result
			'10/10/2010' || true
			'10/16/2010' || false
			'10-10/2010' || false
			'10/10-2010' || false
			'10-10-2010' || false
			'10.10.2010' || false
	}

	@Unroll
	void 'test [date:#date, hour:#hour, minute:#minute] isCompleteDateTime #result' (){
		expect: 
			isCompleteDateTime(toLocalDate(date), hour, minute) == result
		where:  
			date 		 | hour | minute || result
			'10/10/2010' | 10   | 10     || true
			null         | 10   | 10     || false
			'10/10/2010' | null | 10     || false
			'10/10/2010' | 10   | null   || false
			null         | null | null   || false
	}

	@Unroll
	void 'test [date:#date, hour:#hour, minute:#minute] isEmptyDateTime #result' (){
		expect: 
			isEmptyDateTime(toLocalDate(date), hour, minute) == result
		where:  
			date 		 | hour | minute || result
			'10/10/2010' | 10   | 10     || false
			null         | 10   | 10     || false
			'10/10/2010' | null | 10     || false
			'10/10/2010' | 10   | null   || false
			null         | null | null   || true
	}

	@Unroll
	void 'test [date:#date, hour:#hour, minute:#minute] isEmptyOrValidDateTime #result' (){
		expect: 
			isEmptyOrValidDateTime(date, hour, minute) == result
		where:  
			date 		 | hour | minute || result
			'10/10/2010' | 10   | 10     || true
			null         | null | null   || true
			'10/1a/2010' | 10   | 10     || false
			null         | 10   | 10     || false
			'10/10/2010' | null | 10     || false
			'10/10/2010' | 10   | null   || false
			'10/10/2010' | 10   | 70     || false
			'10/10/2010' | 70   | 10     || false
	}

	@Unroll
	void 'test first date of the week for [#date] is [#result]' (){
		expect: 
			getFirstDateOfTheWeek(toLocalDate(date)) == toLocalDate(result)
		where:  
			date 		 || result
			'04/12/2014' || '01/12/2014'
			'08/12/2014' || '08/12/2014'
			'21/12/2014' || '15/12/2014'
			'01/01/2015' || '29/12/2014'
	}

	@Unroll
	void 'test last date of the week for [#date] is [#result]' (){
		expect: 
			getLastDateOfTheWeek(toLocalDate(date)) == toLocalDate(result)
		where:  
			date 		 || result
			'04/12/2014' || '07/12/2014'
			'08/12/2014' || '14/12/2014'
			'21/12/2014' || '21/12/2014'
			'31/12/2014' || '04/01/2015'
	}

	@Unroll
	void 'test first date of the month for [#date] is [#result]' (){
		expect: 
			getFirstDateOfTheMonth(toLocalDate(date)) == toLocalDate(result)
		where:  
			date 		 || result
			'04/12/2014' || '01/12/2014'
			'01/12/2014' || '01/12/2014'
			'31/12/2014' || '01/12/2014'
			'29/02/2012' || '01/02/2012'
			'28/02/2014' || '01/02/2014'
	}

	@Unroll
	void 'test last date of the month for [#date] is [#result]' (){
		expect: 
			getLastDateOfTheMonth(toLocalDate(date)) == toLocalDate(result)
		where:  
			date 		 || result
			'04/12/2014' || '31/12/2014'
			'01/12/2014' || '31/12/2014'
			'31/12/2014' || '31/12/2014'
			'29/02/2012' || '29/02/2012'
			'28/02/2014' || '28/02/2014'
	}

}