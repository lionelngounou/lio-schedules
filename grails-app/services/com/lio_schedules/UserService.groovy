package com.lio_schedules

/**
 * @author lionel.ngounou
 */
class UserService {
	
	static AUTHORITIES = [ADMIN:"ROLE_ADMIN", USER:"ROLE_USER"]
	
	void createUser(User user){
		createUserWithRole(user, userRole)
	}
	
	void createAdmin(User user){
		createUserWithRole(user, adminRole)
	}
	
	void createUserWithRole(User user, Role role){
		log.info "Create user $user , with role $role"
		println "Create user $user , with role $role"
		user.save()
		UserRole.create(user, role, true)
	}
	
	def getAdminRole(){
		 Role.findByAuthority(AUTHORITIES.ADMIN)
	}
	
	def getUserRole(){
		 Role.findByAuthority(AUTHORITIES.USER)
	}
	
	void initRoles(){
		new Role(authority: AUTHORITIES.ADMIN).save()
		new Role(authority: AUTHORITIES.USER).save()
	}
	
}

