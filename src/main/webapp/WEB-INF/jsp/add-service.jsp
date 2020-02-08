<%@ include file="header.jsp"%>
<%@ include file="navigation.jsp"%>
<div class="container">

	<form:form method="POST" modelAttribute="service" class="form-signin">
		<h2 class="form-signin-heading">Add service</h2>
		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" path="name" class="form-control"
					placeholder="name" autofocus="true"></form:input>
				<form:errors path="name"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="externId">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="number" path="externId" class="form-control"
					placeholder="externId"></form:input>
				<form:errors path="externId"></form:errors>
			</div>
		</spring:bind>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	</form:form>

</div>
</body>
</html>
<%@ include file="footer.jsp"%>