<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" href="styles/indexStyles.css">


<script src="https://cdn.tailwindcss.com"></script>

<meta charset="ISO-8859-1">
<title>WarWielder | Bienvenido</title>
</head>
<body>

	<div class="rootContainer">
		<div class="backgroundContainer"></div>
		<div class="mainContainer">
			<div class="headerContainer">
				<%@ include file="components/NavBar.jsp"%>
			</div>

			<div class="contentContainer">
				<video class="videoViking" width="47%" src="public/viking.webm"
					autoplay="autoplay" muted="muted" loop>
				</video>

				<div class="welcome">
					<img width="400px" height="70px" class="card" alt="bang"
						src="public/homepage_cardbacks_row_01.avif"> <img
						width="450px" height="70px" class="card2" alt="bang"
						src="public/homepage_cardbacks_row_02.avif">
					<h2>Te damos la bienvenida a WarWielder</h2>
					<img class="w-[500px] h-[80px] mb-10" alt="bang"
						src="public/bang.avif">
				</div>
			</div>
		</div>
	</div>
</body>
</html>