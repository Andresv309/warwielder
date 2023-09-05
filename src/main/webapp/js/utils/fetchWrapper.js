async function fetchWrapper(url, method = 'GET', body) {
	try{
		
		const headers  = {
			"content-Type": "application/json"
		} 
		
		const options =  {
			method,
			headers,
			body: body ? JSON.stringify(body) : undefined 
			
		}
		
		const response  = await fetch(url,options)
		
		
//		if(response.ok){
			try{
				const data = await response.json()
				return data
			} catch(error) {
				cosole.log("Error parsing JSON:", error)
				return null
			}
//		}else {
//			cosole.error("Fetch failed with status:", response.status)
//			return null
//		}
		
	}catch (error){
		cosole.error("Fetch Error:", error)
		return null
		
	}
} 