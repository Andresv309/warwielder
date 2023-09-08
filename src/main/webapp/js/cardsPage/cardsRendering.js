import { fetchWrapper } from "./utils/fetchWrapper.js"
import { htmlRenderer } from "./utils/renderer.js"
import { paralaxEffect } from "./paralax.js"
import { cardImagesEndpoint, cardsEndpoint } from "../constants/endpoints.js"

const cardsContainer = document.getElementById("cardsContainer")
const CARD_CLASS = "card"

function cardTemplateComponent(card) {
	const { img } = card 
	const imgPath = cardImagesEndpoint + "/" + img;
	
	return `<img class="${CARD_CLASS}" width="240px" alt="card" src=${imgPath}>`
}

function renderCards(cards) {
	htmlRenderer(cardsContainer, cards, cardTemplateComponent)
}

async function fetchCards(){
	const cardsData = await fetchWrapper(cardsEndpoint)
	return cardsData.data	
}

function addParalaxEffectToCards() {
	const htmlCardElements = document.querySelectorAll(`.${CARD_CLASS}`);
	setTimeout(() => {htmlCardElements.forEach(paralaxEffect)}, 500)
}

export { fetchCards, renderCards, addParalaxEffectToCards }
