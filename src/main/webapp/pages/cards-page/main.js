import { fetchCards, renderCards, addParalaxEffectToCards } from '../../js/cardsPage.js';

async function main() {
	const cards =  await fetchCards()
    renderCards(cards)
    addParalaxEffectToCards()
}

main()