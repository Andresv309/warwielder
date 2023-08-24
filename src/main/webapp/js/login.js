document.getElementById('formLogin').addEventListener('submit', (event) => {
	event.preventDefault();
	const formData = new FormData(event.target);
	const credentials = Object.fromEntries(formData);
	console.log(credentials);

	fetch('http://localhost:8080/warwielder/api/v1/login', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(credentials)
	})
})

