import grails.util.Environment
import com.lio_schedules.*

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
		def admin = new User(name:"admin", email:"admin@schedules.lio", password:"password")
		userService.createAdmin(admin)
		userService.createUser(new User(name:"kalkul.camer", email:"lay@schedules.lio", password:"password"))

		def date = Utils.toLocalDateTime([date:'27/10/2014', hour:10, minute:10])
		(0..70).each{
			def sDt = date.plusDays(it)
			new Schedule(user:admin, title:"schedule $it for $sDt", start:sDt, end:sDt).save()
		}
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
