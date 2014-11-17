package com.lio_schedules.domain

import grails.test.mixin.*
import spock.lang.*
import com.lio_schedules.*
import static com.lio_schedules.TestHelper.*

@TestFor(User)
class UserUnitSpec extends Specification {
	
	static user_properties = ['name', 'email', 'password', 'website', 'dateCreated', 'lastUpdated']
	def user = getValidUser()
	
    void "properties presence"() {
		given: 'a user with properties called'
		user_properties.each{user."$it"}
		
		expect: 'no missing property exception thrown'
		true //should be ok
    }
	
	void 'a valid user has no errors'() {
		setup:
		mockForConstraintsTests User
		
		when:
		user.validate()
		
		then:
		!user.hasErrors()
	}
	
	void "user can be found by email: #testEmail"() {
		setup:
		mockDomain User
		
		when:
		user.email = testEmail
		user.save()
		
		then:
		User.findByEmail(testEmail) != null
		
		where:
		testEmail = TestHelper.email
	}
	
	@Unroll('constraint test of #field=#value has error:#error')
	void 'test user basic constraints'(){
		setup:
		mockForConstraintsTests User
		
		when: 
		validateDomain(user, field, value)
		
		then:
		checkError(user, field, error)
		
		where: 
		/*grouped by error, ordered by error, field, value asc*/
		error			||	field			|	value
		'blank'			||	'email'			|	" "
		'blank'			||	'name'			|	" "
		'blank'			||	'password'		|	" "
		'email'			||	'email'			|	'temail'
		'email'			||	'email'			|	'temail@'
		'email'			||	'email'			|	'temail@t'
		'email'			||	'email'			|	'temail@t.'
		null			||	'email'			|	TestHelper.email
		null			||	'email'			|	TestHelper.email
		null			||	'password'		|	getWord(5)
		null			||	'password'		|	getWord(15)
		'nullable'		||	'email'			|	null
		'nullable'		||	'name'			|	null
		'nullable'		||	'password'		|	null
		'size'			||	'name'			|	getWord(5-1)
		'size'			||	'name'			|	getWord(100+1)
		'size'			||	'password'		|	getWord(5-1)
		'size'			||	'password'		|	getWord(15+1)
	}
	
	void 'user email must be unique'(){
		setup:
		mockForConstraintsTests User
		
		when:
		getValidUser().save()
		def user2 = getValidUser()
		user2.validate()
			
		then: 
		checkError(user2, 'email', 'unique')
	}
}
