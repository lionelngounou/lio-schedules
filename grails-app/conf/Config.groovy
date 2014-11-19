// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "html" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

grails.controllers.defaultScope = 'singleton'

grails.gorm.default.constraints = {
    '*'(nullable: true, minSize:0)
	notNullOrBlank(nullable: false, blank: false)
}
grails.gorm.failOnError = true
grails.gorm.autoFlush = true

grails.gorm.default.mapping = {
	version false
	autoTimestamp false
	dynamicUpdate true
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentDateTime, class: org.joda.time.DateTime
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentLocalDate, class: org.joda.time.LocalDate
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime, class: org.joda.time.LocalDateTime
}

grails.sitemesh.default.layout = 'baseLayout'
grails.plugins.twitterbootstrap.fixtaglib = true
grails.plugins.twitterbootstrap.defaultBundle = 'bundle_bootstrap'

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}


//security 
grails.plugin.springsecurity.active = true //activate or deactivate spring security
grails.plugin.springsecurity.useSessionFixationPrevention = true

grails.plugin.springsecurity.password.algorithm = 'bcrypt'
grails.plugin.springsecurity.password.bcrypt.logrounds = 10

grails.plugin.springsecurity.userLookup.usernamePropertyName = 'email'
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.lio_schedules.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.lio_schedules.UserRole'
grails.plugin.springsecurity.userLookup.authoritiesPropertyName = 'roles'

grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [
   '/':                 ['permitAll'],
   '/home':            ['permitAll'],
   '/home.gsp':        ['permitAll'],
   '/assets/**':        ['permitAll'],
   '/**/js/**':         ['permitAll'],
   '/**/css/**':        ['permitAll'],
   '/**/images/**':     ['permitAll'],
   '/**/favicon.ico':   ['permitAll'],
   '/login/**':         ['permitAll'],
   '/logout/**':        ['permitAll'],
   '/register':			['permitAll'],
   '/user/register':     ['permitAll'],
   '/dbconsole/**':     ['permitAll'],
   '/schedule/**':		['isFullyAuthenticated()']
]

grails.plugin.springsecurity.logout.filterProcessesUrl = '/logout'

grails.plugin.springsecurity.auth.loginFormUrl = '/login'
//grails.plugin.springsecurity.auth.ajaxLoginFormUrl = '/login'

//grails.plugin.springsecurity.failureHandler.defaultFailureUrl = '/login?login_error=1'
//grails.plugin.springsecurity.failureHandler.ajaxAuthFailUrl = '/login?login_error=1'

grails.plugin.springsecurity.apf.filterProcessesUrl = '/log-in'
grails.plugin.springsecurity.apf.usernameParameter = 'email'
grails.plugin.springsecurity.apf.passwordParameter = 'password'

grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/schedule'


// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}
	
	root {
		info()
	}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
	
	info	'grails.app', 
			'com.lio_schedules'
	
	debug	'com.lio_schedules', 
			'StackTrace'
}
