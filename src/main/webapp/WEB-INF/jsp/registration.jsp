<%@ include file="header.jsp"%>
<%@ include file="navigation.jsp"%>

<div class="container">

	<form:form method="POST" modelAttribute="userForm" class="form-signin">
		<h2 class="form-signin-heading">Create your account</h2>
		<spring:bind path="username">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" path="username" class="form-control"
					placeholder="Username" autofocus="true"></form:input>
				<form:errors path="username"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="password" path="password" class="form-control"
					placeholder="Password"></form:input>
				<form:errors path="password"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" path="name" class="form-control"
					placeholder="Name" autofocus="true"></form:input>
				<form:errors path="name"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="surname">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" path="surname" class="form-control"
					placeholder="Surname" autofocus="true"></form:input>
				<form:errors path="surname"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" path="email" class="form-control"
					placeholder="Email" autofocus="true"></form:input>
				<form:errors path="email"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="phone">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" path="phone" class="form-control"
					placeholder="Phone" autofocus="true"></form:input>
				<form:errors path="phone"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="address">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" path="address" class="form-control"
					placeholder="Address" autofocus="true"></form:input>
				<form:errors path="address"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="addressBill">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="text" path="addressBill" class="form-control"
					placeholder="AddressBill" autofocus="true"></form:input>
				<form:errors path="addressBill"></form:errors>
			</div>
		</spring:bind>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	</form:form>

</div>
</body>
</html>
<%@ include file="footer.jsp"%>