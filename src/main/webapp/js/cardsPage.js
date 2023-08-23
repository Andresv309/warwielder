const imagesPath = "http://localhost:8080/warwielder/api/v1/cardImages/" 
let imagesArray = []
const cardsContainer = document.getElementById("cardsContainer")

for(let i = 1; i <= 32; i++ ){
  const imageRoute = imagesPath + `card_${i}.png`
  imagesArray.push(imageRoute)
}


function htmlRenderer (htmlElement, htmlChildrenArray, htmlComponent) {
  htmlElement.innerHTML = htmlChildrenArray.reduce((accumulator, currentValue) => accumulator + htmlComponent(currentValue), "")
}


function cardTemplateComponent(imgPath) {
  return `<img width="240px" alt="bang" src=${imgPath}>`
}


// cardsContainer.innerHTML = imagesArray.reduce((accumulator, currentValue) => accumulator + cardTemplateComponent(currentValue), "")

htmlRenderer(cardsContainer, imagesArray, cardTemplateComponent)
