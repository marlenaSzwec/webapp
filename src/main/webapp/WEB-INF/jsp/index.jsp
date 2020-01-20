<%@ include file="header.jsp"%>

<c:if test="${account == null}">
	<div id="buttonsContainer">
		<button id="login" class="button" onclick="location.href='login.html';">Login</button>
		<button id="register" class="button" onclick="location.href='register.html';">Register</button>
	</div>
</c:if>

<c:if test="${account != null}">
	<h1>Hello ${account.login}!</h1>
	<a href="index.html">&gt;back</a>
</c:if>

<%@ include file="footer.jsp"%>