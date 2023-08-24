const element = document.getElementById('skin');
const height = element.clientHeight;
const width = element.clientWidth;

element.style.transformStyle = 'preserve-3d';

element.addEventListener('mousemove', (event) => {
	const { layerX, layerY } = event;

	const yRotation = ((layerX - width / 2) / width) * 20;
	const xRotation = ((layerY - height / 2) / height) * 20;

	const string = `
		perspective(400px)
		scale(1.1)
		rotateX(${xRotation}deg)
		rotateY(${yRotation}deg)
	`;

	element.style.webkitTransform = string; 
 
//	console.log({ yRotation, xRotation });
});