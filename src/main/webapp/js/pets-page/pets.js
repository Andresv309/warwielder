import { htmlRenderer } from "../utils/renderer.js"
import { getAllPets } from "../data/pets.js";
import { petsImagesEndpoint } from "../constants/endpoints.js";

const petsContainer = document.getElementById("cardsContainer")


function petsTemplateComponent(pet) {
	const { img, name, rarity} = pet
 	const imgPath = petsImagesEndpoint + "/" + img;
 	
 	
 	let rarityColor;
  
	  switch (rarity) {
	    case "LEGENDARY":
	      rarityColor = "#fea444";
	      break;
	    case "EPIC":
	      rarityColor = "#ff0534";
	      break;
	    case "RARE":
	      rarityColor = "#ee4cdb ";
	      break;
	    case "COMMON":
	      rarityColor = "#01c4e7";
	      break;
	    case "FREE":
	      rarityColor = "#2ef8a0";
	      break;
	    default:
	      rarityColor = "#2ef8a0";
	  }
 
	 
	  return `<div class="petContainer">
	            <img class="petFrame" width="240px" alt=""
	              src="public/petFrame.webp">
	            <div class="petContainerImage" style="filter: drop-shadow(0px 4px 30px ${rarityColor});">
	              <img class="petImage" alt="${name}" src="${imgPath}">
	            </div>
	          </div>`;
}


async function renderPetsPage() {
	const pets = await getAllPets()
	htmlRenderer(petsContainer, pets, petsTemplateComponent)
}


export { renderPetsPage }
