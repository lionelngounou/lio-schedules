import grails.util.Environment
import com.lio_schedules.Role
import com.lio_schedules.User
import com.lio_schedules.UserRole

class BootStrap {

	def userService 

    def init = { servletContext ->
		switch (Environment.current) {
            case Environment.DEVELOPMENT:
				initDevEnvironment(servletContext)
				break
            case Environment.TEST:
				initTestEnvironment(servletContext)
				break
            case Environment.PRODUCTION:
				initLiveEnvironment(servletContext)
				break
		}
    }

    private void initDevEnvironment(servletContext){
		servletContext.setAttribute("environment", "development")
		userService.initRoles()
		userService.createAdmin(new User(name:"admin", email:"admin@schedules.lio", password:"password"))
		userService.createUser(new User(name:"kalkul.camer", email:"lay@schedules.lio", password:"password"))
	}

    private void initTestEnvironment(servletContext){
		servletContext.setAttribute("environment", "test")
    }

    private void initLiveEnvironment(servletContext){
		servletContext.setAttribute("environment", "production")
    }

    def destroy = {
		
    }
}
