<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Test</h1>
	
	<p>${error}</p>

	<form method="post" action="./logintest">
		<div>
			<label for="username">Usuario</label>
			<input type="text" id="username" name="username" />
		</div>
		<div>
			<label for="password">Contraseña</label>
			<input type="password" id="password" name="password" />
		</div>
		<button type="submit">Enviar</button>
	</form>

</body>
</html>