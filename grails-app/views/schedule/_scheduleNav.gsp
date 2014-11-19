
<div class="container">
	<ul class="nav nav-pills">
		<li <g:if test="${listMode=='month'}">class="active"</g:if>><g:link action="list" params="[listMode: 'month']">Month</g:link></li>
		<li <g:if test="${listMode=='week'}">class="active"</g:if>><g:link action="list" params="[listMode: 'week']">Week</g:link></li>
		<li <g:if test="${listMode=='day'}">class="active"</g:if>><g:link action="list" params="[listMode: 'day']">Day</g:link></li>
		<li class="pull-right active"><g:link action="create">+ Create</g:link></li>
	</ul>
</div>
