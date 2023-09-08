import { htmlRenderer } from "../utils/renderer.js"
import { paralaxEffect } from "../paralax.js"
import { cardImagesEndpoint } from "../constants/endpoints.js"
import { getAllCards } from "../data/cards.js"


const cardsContainer = document.getElementById("cardsContainer")
const CARD_CLASS = "card"

function cardTemplateComponent(card) {
	const { img } = card 
	const imgPath = cardImagesEndpoint + "/" + img;
	
	return `<img class="${CARD_CLASS}" width="240px" alt="card" src=${imgPath}>`
}

function addParalaxEffectToCards() {
	const htmlCardElements = document.querySelectorAll(`.${CARD_CLASS}`);
	setTimeout(() => {htmlCardElements.forEach(paralaxEffect)}, 500)
}

async function renderCards() {
	const cards = await getAllCards()
	htmlRenderer(cardsContainer, cards, cardTemplateComponent)
	addParalaxEffectToCards()
}


export { renderCards }
