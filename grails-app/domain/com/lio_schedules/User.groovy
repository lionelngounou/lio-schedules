package com.lio_schedules

/*@@@@@@@@@@@@@@@@@@@ MaSchedules @@@@@@@@@@@@@@@@@*/

@groovy.transform.EqualsAndHashCode(includes="id, email")
@groovy.transform.ToString(includes="id, name, email, website")
class User {
	
	String name /*could be a person or an organisation*/
	String email
	String password
	String website
	Date dateCreated
	Date lastUpdated
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static mapping = {
		table 'tblUser'
		version false
		autoTimestamp true
		password column: 'passwd'
	}
	
    static constraints = {
		name shared: "notNullOrBlank", size: 5..100
        password (shared: "notNullOrBlank", 
        	/*size: 5..15, would fail validation after password encoding */
        	validator: {pwd, user ->
				if(user.email == pwd) return "user.password.matchesEmail"
			}
		)
        email shared: "notNullOrBlank", email:true, unique: true, index: 'USER_EMAIL_IDX', updateable: false
	}
	
	transient springSecurityService
	static transients = ['springSecurityService']
	
	Set<Role> getRoles() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
	
	def beforeInsert() {
		encodePassword()
	}
	
	def beforeUpdate() {
		if(isDirty('password')) encodePassword()
	}
	
	protected void encodePassword() {
		def salt = email 
		password = springSecurityService.encodePassword(password, salt)
	}
}
