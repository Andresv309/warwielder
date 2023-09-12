import { getAllPets } from "../data/pets.js";
import { htmlRenderer } from "../utils/renderer.js"
import { petsImagesEndpoint } from "../constants/endpoints.js";

const petsContainer = document.getElementById("petsContainer")
let selectedPetId = null;

function setSelectedPetId(e, id) {
	e.preventDefault()
	
	const allHeartButtons = petsContainer.querySelectorAll('.heart')
	const heartElement = e.target.querySelector('.heart')
	
	console.log(allHeartButtons)
	allHeartButtons.forEach((button) => {
	    if (button !== heartElement) {
	      button.classList.remove("is-active");
	    }
	})
		
	if (heartElement.classList.contains("is-active")) {
		heartElement.classList.remove("is-active");
		selectedPetId = null;
	} else {
		heartElement.classList.add("is-active");
		selectedPetId = id;
	}

	console.log('Selected Pet ID:', selectedPetId);
}

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


//	freePets.forEach((pet) => {
//    	const { id } = pet;
//		const petElement = document.createElement('div');
//    	petElement.className = 'swiper-slide';
//    	petElement.innerHTML = petsTemplateComponent(pet)
//
//	    // Add the click event listener to the button
//	    const button = petElement.querySelector('.button-heart');
//	    console.log(button)
//	    button.addEventListener('click', (event) => {
//	      	setSelectedPetId(event, id);
//	    });
//
//		petsContainer.appendChild(petElement)
//  });

	htmlRenderer(petsContainer, freePets, petsTemplateComponent, (content, currentValue) => {
	  // Add event listeners or perform custom actions here
	  console.log({content, currentValue})
	  console.log(content.innerHTML)
	  const button = content.querySelector('.button-heart');
	  console.log(button);
	  button.addEventListener('click', (event) => {
	    setSelectedPetId(event, currentValue.id);
	  });
	});

//	htmlRenderer(petsContainer, freePets, petsTemplateComponent);





}


function getPetId () {
	return selectedPetId
}


export { renderPets, getPetId }
