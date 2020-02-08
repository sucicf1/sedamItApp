<%@ include file="header.jsp"%>
<%@ include file="navigation.jsp"%>
<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/service/add">Add
			service</a>
	</div>
	<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th width="20%">Name</th>
					<th width="20%">External id</th>
					<th width="20%">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${services}" var="service">
					<tr>
						<td>${service.name}</td>
						<td>${service.externId}</td>
						<td><a type="button" class="btn btn-success"
							href="/service/${service.externId}/showBills">View bills</a> <a
							type="button" class="btn btn-warning"
							href="/service/delete/${service.externId}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<%@ include file="footer.jsp"%>
