<%@ include file="header.jsp"%>
<%@ include file="navigation.jsp"%>
<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md"
			href="/bill/add/${externId}">Add bill</a>
	</div><br />
	Extern id of service of all bills is ${externId}
	<div class="panel-body">
		<table class="table table-striped" style="width:90%">
			<thead>
				<tr>
					<th width="8%">Month</th>
					<th width="8%">Amount</th>
					<th width="8%">Status</th>
					<th width="8%">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bills}" var="bill">
					<tr>
						<td>${bill.month}</td>
						<td>${bill.amount}</td>
						<td>${bill.status}</td>
						<td><a type="button" class="btn btn-success"
							href="/bill/update/${bill.id}">Update bill</a> <a type="button"
							class="btn btn-success" href="/bill/delete/${bill.id}">Delete
								bill</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<%@ include file="footer.jsp"%>