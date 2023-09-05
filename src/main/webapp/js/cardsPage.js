const imagesEndpoint = "/api/v1/assets/cards" 
const basePath = "http://localhost:8080/warwielder"
const imagePath = basePath + imagesEndpoint

//let imagesArray = []
const cardsContainer = document.getElementById("cardsContainer")
//
//for(let i = 1; i <= 32; i++ ){
//  const imageRoute = imagesPath + `card_${i}.png`
//  imagesArray.push(imageRoute)
//}


function htmlRenderer (htmlElement, htmlChildrenArray, htmlComponent) {
  htmlElement.innerHTML = htmlChildrenArray.reduce((accumulator, currentValue) => accumulator + htmlComponent(currentValue), "")
}


function cardTemplateComponent(imgPath) {
	
  return `<img class="card" width="240px" alt="bang" src=${imgPath}>`
}


// cardsContainer.innerHTML = imagesArray.reduce((accumulator, currentValue) => accumulator + cardTemplateComponent(currentValue), "")



function renderCards(cards) {
	const cardsImages =  cards.map(card => imagePath + "/" + card.img)
	htmlRenderer(cardsContainer, cardsImages, cardTemplateComponent)
}



async function fetchCards(){
	const cardsPath = basePath + "/api/v1/cards?offset=0&limit=20"

	
//	const res = await fetch(cardsPath)
//	const cardsData = await res.json()
	const cardsData = await fetchWrapper(cardsPath)

	console.log(cardsData) 
	return cardsData.data	
} 


async function main() {
	const cards =  await fetchCards()
    renderCards(cards)
} 


main();
