class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/home")
		//"/"(redirect: [controller: 'schedule', action: 'list'])
		"/schedule/list/$listMode"{
			controller = "schedule" 
			action = "list"
			constraints{
				listMode(nullable:true, blank:true, inList: ["month", "week", "day"])
			}
		}
		//"/login"(controller: "security", action: "loginPage")
		//"/forgotPassword"(controller: "login", action: "forgotPasswordPage")
		"/register"(controller: "user", action: "showRegistrationPage")
		
		"500"(view:'/error')
	}
}
