<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/registerStyles.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/paralax.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js" defer></script>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Security-Policy" content="script-src 'self';" />

<title>WarWielder | Registrarme</title>
</head>
<body>

	<div class="rootContainer">
		<div class="backgroundContainer"></div>
		<div class="mainContainer">
			<div class="contentContainer">
				<div class="leftContainer">
					<img id="skin"   alt="skinRegister" src="public/skin_register.avif"> 
				</div>
				<div class="rightContainer">
					<div class="frameForm">
						<form class="formContent" id="step1">
							<div class="logoContainer">
								<a href="index.jsp"><img width="250px" alt="logo"
									src="public/logo1.png"></a>
							</div>
							
							<div class="inputContainer">
								<label>Nombre de Usuario</label> 
								<input name="username" type="text" placeholder="War_wielder">
							</div>

							<div class="inputContainer">
								<label>Contraseña</label> <input name="password" type="password"
									placeholder="•••••••••••••">
							</div>
							
							<div class="btnaddContainer">
								<button class="BtnComponent" id="nextStep" data-text="Crear Cuenta" style="--btn-height: 60px;"></button>
							</div>
							
							<div class="registerInfo">
								<span>¿Ya tienes una cuenta?<a href="login">¡Inicia Sesion aquí!</a></span>
							</div>
						</form>
						
						
						
						
	
						
						<form class="formContent step2" id="step2">
						
							<h1>Holaaaaa</h1>
							<div class="btnaddContainer">
								<button class="BtnComponent" id="submitForm" data-text="Empezar"></button>
							</div>
						
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>