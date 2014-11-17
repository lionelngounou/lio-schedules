package com.lio_schedules

/**@author lionel.ngounou
 */
@groovy.transform.ToString(includes="id, authority")
class Role {
	
	String authority
	
	static mapping = {
		cache true
	}
	
	static constraints = {
		authority blank: false, unique: true
	}
}

