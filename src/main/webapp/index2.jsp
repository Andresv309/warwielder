<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="styles/style.css">


<script src="https://cdn.tailwindcss.com"></script>

<meta charset="ISO-8859-1">
<title>WarWielder | Home page</title>
</head>
<body>

	<div class="rootContainer">
		<div class="backgroundContainer">
		
		</div>
		<div class="mainContainer">
			<div class="headerContainer">
				<header class="header">
					<div class="Logo">
						<img width="180px" height="60px" alt="logo" src="public/logo1.png">
					</div>

					<nav class="nav">
						<ul>
							<li><a href="#">Como Jugar</a></li>
							<li><a href="cartas.jsp">Cartas</a></li>
							<li><a href="#">Mascotas</a></li>
							<li><a href="#">Git Hub</a></li>
						</ul>
					</nav>

					<div class="">
						<button class="Btn"></button>
					</div>
				</header>
			</div>

			<div class="contentContainer">
				<video class="videoViking" width="47%" src="public/viking.webm"
					autoplay="autoplay" muted="muted" loop>
				</video>
				
				<div class="welcome">
					<img width="400px" height="70px" class="card"  alt="bang" src="public/homepage_cardbacks_row_01.avif">
					<img width="450px" height="70px" class="card2"  alt="bang" src="public/homepage_cardbacks_row_02.avif">
					<h2>Te damos la bienvenida a WarWielder</h2>
					<img class="w-[500px] h-[80px] mb-10"  alt="bang" src="public/bang.avif">
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>