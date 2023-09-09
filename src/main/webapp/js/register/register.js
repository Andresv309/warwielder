import { htmlRenderer } from "../utils/renderer.js"
import { getAllPets } from "../data/pets.js";
import { petsImagesEndpoint } from "../constants/endpoints.js";

const petsContainer = document.getElementById("petsContainer")


function petsTemplateComponent(pet) {
	const { img, name  } = pet
 	const imgPath = petsImagesEndpoint + "/" + img;
 
		 
 	return `<div class="swiper-slide">
				<div class="sliderContent">
					<div class="left-slider">
						<img width= "150px" height= "250px" class="petImage" alt="" src="${imgPath}">
					</div>
				
					<div class="right-slider">
					
						<div class="info-pet" >
							<div class="name-pet">
								<h2>${name}</h2>
							</div>
		
							<div class="hex-container">
								<div class="hex-nut">
									<div class="inner-circle"></div>
								</div>
							</div>
							
							<div class="hex-container-two">
								<div class="hex-nut">
									<div class="inner-circle"></div>
								</div>
							</div>
		
		
						</div>
					
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
																					
						
						<div class="buttonContainer">
							<button class="button-heart"><span class="heart"></span>ESCOGER MASCOTA</button>
						</div>
					</div>
				</div>
			</div>`
}


async function renderPets() {
	const pets = await getAllPets()
	const freePets = pets.filter(pet => pet.rarity === "FREE");
	htmlRenderer(petsContainer, freePets, petsTemplateComponent)
}


export { renderPets }












