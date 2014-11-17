<%@ page import="com.lio_schedules.User" %>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} required control-group">
	<label for="name" class="control-label">
		<g:message code="user.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls ">
		<g:textField name="name" maxlength="100"  required="" value="${userInstance?.name}" class="input-xlarge"/>
        <span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

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
		<g:passwordField name="password" maxlength="15" required="" value="password" class="input-xlarge"/>
        <span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordRepeat', 'error')} required control-group">
	<label for="passwordRepeat" class="control-label">
		<g:message code="user.passwordRepeat.label" default="Repeat Password" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:passwordField name="passwordRepeat" maxlength="15" required="" value="password" class="input-xlarge"/>
        <span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'website', 'error')} control-group">
	<label for="website" class="control-label">
		<g:message code="user.website.label" default="Website" />
	</label>
	<div class="controls">
		<g:textField name="website" value="${userInstance?.website}" class="input-xlarge"/>
        <span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

