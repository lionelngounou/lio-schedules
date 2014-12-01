<g:each var="schedule" in="${scheduleList}">
	<div class="container well ">
		<a href="${createLink(action: 'edit', id: schedule.id)}">
			<span class="label label-success">${schedule.title} 
			<g:if test="${schedule.start == schedule.end}">
				(${joda.format(pattern:'E dd MMM yyyy [hh:mm]', value: schedule.start)})
			</g:if>
			<g:else>
				(${joda.format(pattern:'E dd MMM yyyy [hh:mm]', value: schedule?.start)} -
				${joda.format(pattern:'E dd MMM yyyy [hh:mm]', value: schedule?.end)})
			</g:else>
		</a>
	   <div class="">${schedule.description}</div>
	</div>
</g:each>