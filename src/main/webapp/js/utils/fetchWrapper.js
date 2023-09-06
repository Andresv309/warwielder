

/**
 * Wraps the Fetch API to simplify making HTTP requests and handling responses.
 *
 * @param {string} url - The URL to send the HTTP request to.
 * @param {string} method - The HTTP request method (default is 'GET').
 * @param {object} body - The data to send in the request body (optional).
 *
 * @returns {Promise} A Promise that resolves to the parsed JSON response or null on error.
 */

async function fetchWrapper(url, method = 'GET', body) {
	try {
		// Define request headers with a default content type of JSON.
		const headers  = {
			"content-Type": "application/json"
		} 
		
		// Configure the request options.
		const options =  {
			method,
			headers,
			body: body ? JSON.stringify(body) : undefined 
			
		}
		
		// Send the HTTP request and await the response.
		const response  = await fetch(url,options)
		
		try{
			// Try to parse the response body as JSON and return it.
			const data = await response.json()
			return data
		} catch(error) {
			console.log("Error parsing JSON:", error)
			return null
		}

	} catch (error){
		console.error("Fetch Error:", error)
		return null
	}
} 



export { fetchWrapper }
