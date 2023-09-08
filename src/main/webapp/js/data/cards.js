import { cardsEndpoint } from "../constants/endpoints.js"
import { fetchWrapper } from '../utils/fetchWrapper.js'

async function getAllCards(){
	const cardsData = await fetchWrapper(cardsEndpoint)
	return cardsData.data	
}


export { getAllCards }