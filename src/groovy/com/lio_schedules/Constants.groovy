package com.lio_schedules

import org.joda.time.*
import org.joda.time.format.*

/**
 * @author lionel.ngounou
 */
class Constants {

	final static defaultDateFormat = "dd/MM/yyyy"
	final static defaultTimeFormat = "HH:mm:ss"
	final static defaultDateTimeFormat = "$defaultDateFormat $defaultTimeFormat"
	final static dateTimeFormatter = DateTimeFormat.forPattern(defaultDateTimeFormat)
	final static dateFormatter = DateTimeFormat.forPattern(defaultDateFormat)	
}

