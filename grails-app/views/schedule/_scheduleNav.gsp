<div class="container">
		<ul class="nav nav-pills">
			<li <g:if test="${listMode=='month'}">class="active"</g:if>>
				<g:link action="list" params="[listMode: 'month', forDate: search?.from]">Month</g:link>
			</li>
			<li <g:if test="${listMode=='week'}">class="active"</g:if>>
				<g:link action="list" params="[listMode: 'week', forDate: search?.from]">Week</g:link>
			</li>
			<li <g:if test="${listMode=='day'}">class="active"</g:if>>
				<g:link action="list" params="[listMode: 'day', forDate: search?.from]">Day</g:link>
			</li>
			<li class="pull-right active"><g:link action="create">+ Create</g:link></li>
		</ul>

		<g:if test="${actionName!='create'}">
			<ul class="pager">
				<li class="previous">
					<g:link action="list" params="[listMode: listMode, when: 'previous', forDate: search?.from]"><<</g:link>
				</li>
				<li class="active">
					<g:link action="list" params="[listMode: listMode, forDate: today.localMillis]">Today</g:link>
				</li>
				<li class="next">
					<g:link action="list" params="[listMode: listMode, when: 'next', forDate: search?.from]">>></g:link>
				</li>
			</ul>
		</g:if>
</div>