import { petsEndpoint } from "../constants/endpoints.js"
import { fetchWrapper } from '../utils/fetchWrapper.js'

async function getAllPets(){
	const petsData = await fetchWrapper(petsEndpoint)
	return petsData.data	
}


export { getAllPets }