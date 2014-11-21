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

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'start', 'error')} required control-group">
	<label for="start" class="control-label">
		<g:message code="schedule.start.label" default="On/From" class="input-xxlarge"/>
		<span class="required-indicator">*</span>
	</label>	
	<div class="controls">
		<joda:dateTimePicker name="start" required="" class="input-xlarge" years="${2013..2020}" value="${scheduleInstance?.start}" />
        <span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'end', 'error')} control-group">
	<label for="end" class="control-label">
		<g:message code="schedule.end.label" default="To" class="input-xxlarge"/>
	</label>	
	<div class="controls">
		<joda:dateTimePicker name="end" class="input-xlarge" years="${2013..2020}" noSelection="['':'-']" 
			value="${scheduleInstance?.end}" default="none" />
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

