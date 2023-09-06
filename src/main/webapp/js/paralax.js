
function paralaxEffect (htmlElement) {
	const height = htmlElement.clientHeight;
	const width = htmlElement.clientWidth;
	
	htmlElement.style.transformStyle = 'preserve-3d';
	
	htmlElement.addEventListener('mousemove', (event) => {
	    const { layerX, layerY } = event;
	
	    const yRotation = ((layerX - width / 2) / width) * 20;
	    const xRotation = ((layerY - height / 2) / height) * 20;
	
	    const effect = `
	        perspective(400px)
	        scale(1.1)
	        rotateX(${xRotation}deg)
	        rotateY(${yRotation}deg)
	    `;
	
	    htmlElement.style.transform = effect; 
	});
}

export { paralaxEffect }
