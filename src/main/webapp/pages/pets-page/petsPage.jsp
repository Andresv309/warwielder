<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/petsPageStyles.css">


<script type="module" src="${pageContext.request.contextPath}/pages/pets-page/main.js"></script>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Security-Policy" content="script-src 'self';" />
<title>WarWielder | Mascotas</title>
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
				<div class="containerBackgroundSection ">
					<img class="backgroundSection" alt="bang"
						src="public/headerPetsPage2.webp">
				</div>

				<div class="title">
					<img class="petIcon" alt="logoCards" src="public/petIcon.avif">
					<%@include file="../../components/WrappedHeader.jsp"%>
				</div>

				<div class="bodyContainer">
					<div id="cardsContainer"  class="cardsContainer">
					
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer class="footerContainer">

		<%@include file="../../components/Adds.jsp"%>
		<%@include file="../../components/Footer.jsp"%>
	</footer>
</body>
</html>