<%@ page import="com.lio_schedules.Schedule" %>
<%@ page import="org.joda.time.*" %>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'title', 'error')} required control-group">
	<label for="title" class="control-label">
		<g:message code="schedule.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls ">
		<g:textField name="title" maxlength="100"  required="" value="${scheduleInstance?.title}" class="input-xxlarge"/>
        <span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'fromDateTime', 'error')} required control-group">
	<label for="fromDateTime" class="control-label">
		<g:message code="schedule.fromDateTime.label" default="On/From" class="input-xxlarge"/>
		<span class="required-indicator">*</span>
	</label>	
	<div class="controls">
		<joda:dateTimePicker name="fromDateTime" required="" class="input-xlarge" years="${2013..2020}" value="${scheduleInstance?.fromDateTime}" />
        <span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'toDateTime', 'error')} control-group">
	<label for="toDateTime" class="control-label">
		<g:message code="schedule.toDateTime.label" default="To" class="input-xxlarge"/>
	</label>	
	<div class="controls">
		<joda:dateTimePicker name="toDateTime" class="input-xlarge" years="${2013..2020}" noSelection="['':'-']" 
			value="${scheduleInstance?.toDateTime}" default="none" />
        <span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'description', 'error')} control-group">
	<label for="description" class="control-label">
		<g:message code="schedule.description.label" default="Description" />
	</label>
	<div class="controls">
		<g:textArea name="description" maxlength="100" value="${scheduleInstance?.description}" class="input-xxlarge" 
			rows="5" cols="60" />
        <span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

