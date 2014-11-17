<%@ page import="com.lio_schedules.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="registerOrLoginLayout">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.login.label" /></title>
	</head>
	<body>
		<div id="login-user" class="content" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${userInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${userInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<form action='${postUrl}' method='POST' id='loginForm' class='form-horizontal well' autocomplete='off'>
				<fieldset>
					<legend>Login to your schedulez account</legend>
					
					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required control-group">
						<label for="email" class="control-label">
							<g:message code="user.email.label" default="Email" class="input-xlarge"/>
							<span class="required-indicator">*</span>
						</label>	
						<div class="controls">
							<g:field type="email" name="email" required="" value="${userInstance?.email}" class="input-xlarge"/>
							<span class="help-inline hidden">Please correct the error</span>
						</div>
					</div>

					<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required control-group">
						<label for="password" class="control-label">
							<g:message code="user.password.label" default="Password" />
							<span class="required-indicator">*</span>
						</label>
						<div class="controls">
							<g:passwordField name="password" maxlength="15" required="" value="${userInstance?.password}" class="input-xlarge"/>
							<span class="help-inline hidden">Please correct the error</span>
						</div>
					</div>
					
					<div class="form-actions">
						<g:submitButton name="login" class="btn btn-primary"
							value="${message(code: 'default.button.login.label', default: 'Login')}" />
						<g:link uri="/forgotPassword">forgot password?</g:link>
					</div>
		  
				</fieldset>
				<fieldset>
					Not yet registered? <g:link uri="/register">Register here</g:link>
				</fieldset>
			</form>
		</div>
	</body>
</html>
