<%@ include file="header.jsp"%>
<%@ include file="navigation.jsp"%>

<div class="container">

	<form:form method="POST" modelAttribute="billForm" class="form-signin">
		<h2 class="form-signin-heading">Bill</h2>
		<form:hidden path="id" />
		<spring:bind path="month">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="number" path="month" class="form-control"
					placeholder="month" autofocus="true"></form:input>
				<form:errors path="month"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="amount">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="number" path="amount" class="form-control"
					placeholder="amount"></form:input>
				<form:errors path="amount"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="status">
				Paid:
                <form:checkbox path="status" />
			<form:errors path="status"></form:errors>
		</spring:bind>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	</form:form>

</div>
<%@ include file="footer.jsp"%>