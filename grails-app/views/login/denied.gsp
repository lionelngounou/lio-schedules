<%@ page import="com.lio_schedules.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="registerOrLoginLayout">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="springSecurity.denied.title" /></title>
	</head>
	<body>
		<div class="body">
			<div class="errors"><g:message code="springSecurity.denied.message" /></div>
		</div>
	</body>
</html>
