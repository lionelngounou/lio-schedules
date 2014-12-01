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

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'startDate', 'error')} required control-group">
	<label for="startDate" class="control-label">
		<g:message code="schedule.start.label" default="On/From" class="input-xxlarge"/>
		<span class="required-indicator">*</span>
	</label>	
	<div class="controls">
		<g:textField class="date-picker" name="startDate" data-date-format="dd/mm/yyyy" required="" 
			value="${joda.format(pattern:'dd/mm/yyyy', value: scheduleInstance?.start)}" />
		<g:select name="startHour" from="${0..23}" class="span1" required="" value="${scheduleInstance?.startHour}" optionValue="${{it.toString().padLeft(2,'0')}}" />
		<g:select name="startMinute" from="${0..59}" class="span1" required="" value="${scheduleInstance?.startMinute}" optionValue="${{it.toString().padLeft(2,'0')}}" />
		<span class="help-inline hidden">Please correct the error</span>
    </div>
</div>

<div class="fieldcontain ${hasErrors(bean: scheduleInstance, field: 'endDate', 'error')} control-group">
	<label for="endDate" class="control-label">
		<g:message code="schedule.end.label" default="To" class="input-xxlarge"/>
	</label>	
	<div class="controls">
		<g:textField class="date-picker" name="endDate" data-date-format="dd/mm/yyyy" 
			value="${joda.format(pattern:'dd/mm/yyyy', value: scheduleInstance?.end)}" />
		<g:select name="endHour" from="${0..23}" class="span1" value="${scheduleInstance?.endHour}" 
			noSelection="['':'']" optionValue="${{it.toString().padLeft(2,'0')}}" />
		<g:select name="endMinute" from="${0..59}" class="span1" value="${scheduleInstance?.endMinute}" 
			noSelection="['':'']" optionValue="${{it.toString().padLeft(2,'0')}}" />
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
