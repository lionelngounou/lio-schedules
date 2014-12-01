modules = {
	
	core_rs {
		dependsOn 'jquery'
		//resource url:'css/main.css'
		dependsOn 'bootstrap'
		resource url:'css/bootstrap_flatly.css'
		resource url:'css/datepicker.css'
		//resource url:'css/errors.css'
		//resource url:'css/mobile.css'
		
		resource url:'js/bootstrap-datepicker.js'
		resource url:'js/application.js'
	}
	
    /*
	application {
        resource url:'js/application.js'
    }
	*/
   
}