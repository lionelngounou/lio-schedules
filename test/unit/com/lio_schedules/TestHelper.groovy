package com.lio_schedules

/**
 * @author lionel.ngounou
 */
class TestHelper {
	
	static final String name="testName", password="testPassword", email = "email@test.it"; 
	
	static getUser = {
		new User(name: name, password: password)
	}
	
	static getValidUser = {
		new User(name:name, password:password, email:email)
	}
	
	static getWord = {length->
		def w = "${length}char"
		def ln = w.length()
		ln==length? w :	ln>length? ("w" * length) : w.padRight(length, '0')
	}
	
	static validateDomain = {domain, String field, String value ->
		domain[field] = value
		domain.validate()
	}
	
	static checkError = {domain, String field, error ->
		domain.errors[field] == error
	}
}

