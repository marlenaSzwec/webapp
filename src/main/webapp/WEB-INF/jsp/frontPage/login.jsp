<%@ include file="../header.jsp"%>

<form action="login.html" method="POST">
	<b>Login</b>
	<input style="width: 100%; line-height: 4vh; border: 1px solid #333" type="text" name="login" />
	<br>
	<b>Password</b>
	<input style="width: 100%; line-height: 4vh; border: 1px solid #333" type="password" name="password" />
	<input class="button" style="width: 100%; font-size: 1em; padding: .4em" type="submit" value="Login" />
</form>

<a href="index.html" style="text-align: right">&gt;back</a>

<c:if test="${error != null}">
	<div id="error">
		${error}
	</div>
</c:if>

<%@ include file="../footer.jsp"%>