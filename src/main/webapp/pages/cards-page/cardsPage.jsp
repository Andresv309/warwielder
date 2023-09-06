<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/cardsPageStyles.css">

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/cardsPage.js" defer></script> --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/paralax.js" defer></script> --%>
<script type="module" src="${pageContext.request.contextPath}/pages/cards-page/main.js"></script>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Security-Policy" content="script-src 'self';" />
<title>WarWielder | Todas las cartas</title>
</head>
<body>

	<header class="root">
		<div class="headerContainer">
			<%@ include file="../../components/NavBar.jsp"%>
		</div>
	</header>


	<div class="rootContainer">
		<div class="backgroundContainer"></div>
		<div class="mainContainer">

			<div class="contentContainer">
				<div>
					<img class="backgroundSection" alt="bang"
						src="public/headerCardPage.jpg">
				</div>

				<div class="title">
					<img class="logoCards" alt="logoCards" src="public/CardsLogo.png">
					<h2>UNA BATALLA EN CARTAS</h2>
					<span>"Despliega la Magia, Convoca a tus Guerreros ¡Que
						Comience la Batalla!"</span>
				</div>

				<div class="bodyContainer">
					<div id="cardsContainer" class="cardsContainer"> 
						<img width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png"> <img
							width="200px" alt="bang" src="public/card.png">
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer class="footerContainer">
		<div>
			<img width="100%" class="" alt="PieceOfWood" src="public/piePage.png">
		</div>
		<%@include file="../../components/Footer.jsp"%>
	</footer>
</body>
</html>