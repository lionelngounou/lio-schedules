<%@ page import="com.lio_schedules.Schedule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="baseLayout">
		<g:set var="entityName" value="${message(code: 'schedule.label', default: 'Schedule')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="create-schedule" class="content" role="main">
			<g:render template="scheduleNav" />
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${scheduleInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${scheduleInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form action="save" class="form-horizontal well">
				<fieldset>
					<legend>Create schedule</legend>
					<g:render template="form"/>
					
					<div class="form-actions">
						<g:submitButton name="save" class="btn btn-primary"
							value="${message(code: 'default.button.save.label', default: 'Save')}" />
							&nbsp;&nbsp;
						<g:link action="list">Cancel</g:link>
					</div>
		  
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
