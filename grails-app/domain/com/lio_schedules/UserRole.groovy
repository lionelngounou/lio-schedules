package com.lio_schedules

/** @author lionel.ngounou*/
@groovy.transform.EqualsAndHashCode(includes="user, role")
@groovy.transform.ToString(includes="user, role")
class UserRole implements Serializable{
	
	private static final long serialVersionUID = -450669891
	User user
	Role role
	
	static mapping = {
		id composite: ['role', 'user']
		version false
	}
	
	static constraints = {
      role validator: { Role r, UserRole ur ->
         if (ur.user == null) return
         boolean existing = false
         //existing = UserRole.exists(ur.user.id, r.id)
         
         UserRole.withNewSession {session ->
         	//session.clear()
            existing = UserRole.exists(ur.user.id, r.id)
         }
         
         if (existing) return 'userRole.exists'
      }
   }
	
	static UserRole get(long userId, long roleId) {
		UserRole.where{ user==User.load(userId) && role==Role.load(roleId) }.get()
	}
	
	static UserRole create(User user, Role role, boolean flush = false) {
		new UserRole(user: user, role: role).save(flush: flush, insert: true)
	}
	
	static boolean exists(long userId, long roleId) {
		UserRole.createCriteria().count{
			eq "user.id", userId
			eq "role.id", roleId 
		} > 0 
		//avoid within nested sessions: UserRole.where{ user==User.load(userId) && role==Role.load(roleId) }.exists()
	}
	
	static boolean remove(User u, Role r) {
		if(u == null || r == null) return false
		UserRole.where{ user == User.load(u.id) && role == Role.load(r.id) }.deleteAll() > 0
	}
	
	static void removeAll(User u) {
		if(u == null) return
		UserRole.where{user == User.load(u.id)}.deleteAll()
	}
	
	static void removeAll(Role r) {
		if(r == null) return
		UserRole.where{role == Role.load(r.id)}.deleteAll()
	}
}

