<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/registerStyles.css">
	
	
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js" defer></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js" defer></script>
	

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Security-Policy" content="script-src 'self';" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1" />

<title>WarWielder | Registrarme</title>
</head>
<body>

	<div class="rootContainer">
		<div class="backgroundContainer"></div>
		<div class="mainContainer">
			<div class="contentContainer">
				<div class="leftContainer">
					<img id="skin" alt="skinRegister" src="public/skin_register.avif">
				</div>
				<div class="rightContainer">
					<div class="frameForm">
						<div class="logoContainer">
							<a href="index.jsp"><img width="250px" alt="logo"
								src="public/logo1.png"></a>
						</div>
						<form class="formContent">
						
							<div class="step1" id="step1">
								<div class="step">
									<div class="inputContainer">
										<label>Nombre de Usuario</label> <input name="username"
											type="text" placeholder="War_wielder">
									</div>
			
									<div class="inputContainer">
										<label>Contraseña</label> <input name="password" type="password"
											placeholder="•••••••••••••">
									</div>
			
									<div class="btnaddContainer">
										<button type="button" class="BtnComponent" id="nextStep"
											data-text="Crear Cuenta" style="height: 60px;"></button>
									</div>
			
									<div class="registerInfo">
										<span>¿Ya tienes una cuenta?<a href="login">¡Inicia
												Sesion aquí!</a></span>
									</div>
								</div>
							</div>
							
							<div class="step2" id="step2">
								<div class="stepContainer">
								
									<div class="swiperContainer">
										<div class="swiper mySwiper">
											<div class="swiper-wrapper">
												<div class="swiper-slide">
													<div class="left-slider">
														<img class="petImage" alt="" src="public/petOne.png">
													</div>
													
													<div class="right-slider">
														<div class="progressBarConteiner">
															<img width="32px" height="32px" class="petImage" alt="" src="public/icons/battle.png">
															<div class="progress-bar">
																<div class="bar"></div>
															</div>	
														</div>
														
														<div class="progressBarConteiner">
															<img width="32px" height="32px" class="petImage" alt="" src="public/icons/shield.png">
															<div class="progress-bar">
																<div class="bar"></div>
															</div>	
														</div>
													</div>
												</div>
												<div class="swiper-slide">Slide 2</div>
												<div class="swiper-slide">Slide 3</div>	
												<div class="swiper-slide">Slide 4</div>
											</div>
											<div class="swiper-pagination"></div>
										</div>
									</div>
		
									<div class="btnaddContainer">
										<button type="submit" class="BtnComponent" id="submitForm" data-text="Empezar"
											style="height: 60px;"></button>
									</div>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>