package com.lio_schedules

/** @author lionel.ngounou
 */
@grails.validation.Validateable
@groovy.transform.ToString(includes="name, email, website, password, passwordRepeat")
class UserRegistrationCommand{
	
    String name, email
	String password, passwordRepeat
	String website
	
	static constraints = {
		name shared: "notNullOrBlank", size: 5..100
        email shared: "notNullOrBlank", email:true, unique: true, index: 'USER_EMAIL_IDX'
        password (shared: "notNullOrBlank", size: 6..30, 
        	validator: {pwd, user ->
				if(pwd==user.email) return "user.password.matchesEmail"
			}
		)        
		passwordRepeat (shared: "notNullOrBlank",
        	validator: {passwd2, user ->
				if(passwd2!=user.password) return "user.passwordRepeat.missMatch"
			}
		)
    }

    User asUser(){
    	new User(name:name, email:email, password:password, website:website)
    }
}

