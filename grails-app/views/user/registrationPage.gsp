<%@ page import="com.lio_schedules.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="registerOrLoginLayout">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.register.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="register-user" class="content" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${userInstance}">
				<div class="alert alert-dismissable alert-error">
					<ul class="errors" role="alert">
						<g:eachError bean="${userInstance}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
						</g:eachError>
					</ul>
				</div>
			</g:hasErrors>
			<g:form action="register" class="form-horizontal well" useToken="true">
				<fieldset>
					<legend>Register for your schedulez account</legend>
					<g:render template="form"/>
					
					<div class="form-actions">
						<g:submitButton name="register" class="btn btn-primary"
							value="${message(code: 'default.button.register.label', default: 'Register')}" />
					</div>
		  
				</fieldset>
				<fieldset>
					Already registered? <g:link uri ="/login">Login instead</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
