<div>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<form id="logoutForm" method="POST" action="${contextPath}/logout">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<table class="table table-striped">
			<tr>
				<td width="20%">You're logged in as
					${pageContext.request.userPrincipal.name} <br /> <a
					href="/user/update">Update profile</a>
				</td>
				<td width="20%"><a href="/service">View services</a></td>
				<td width="20%"><a
					onclick="document.forms['logoutForm'].submit()">Logout</a></td>
			</tr>
		</table>
	</c:if>
</div>