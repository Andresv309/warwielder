import { createSwiper } from "../libs/swiper.js";

const nextStepButton = document.getElementById('nextStep');
const submitButton = document.getElementById('submitForm');
const step1Section = document.getElementById('step1');
const step2Section = document.getElementById('step2');

const heartButton  = document.querySelector('.button-heart')
//const heartElement = heartButton.querySelector('.heart')


function init() {
	createSwiper({ 
		swiperHtmlElement:'.mySwiper', 
		swiperPaginationHtmlElement:'.swiper-pagination'
	});	
	
	step2Section.style.display = 'none';
	
	nextStepButton.addEventListener('click', function(event) {
		event.preventDefault();
		// Aquí podrías realizar validaciones y procesar los datos del primer paso
	
		// Oculta el primer paso y muestra el segundo paso
		step1Section.style.display = 'none';
		step2Section.style.display = 'block';
		step2Section.style.visibility = 'visible';
	});
	
	
	submitButton.addEventListener('click', function(event) {
		event.preventDefault();
	
		// Aquí podrías realizar validaciones y enviar los datos del segundo paso
		
		
		// Simplemente para este ejemplo, podrías redirigir al usuario a una página de éxito
		window.location.href = 'registro_exitoso.jsp';
	});
	
	
	heartButton.addEventListener('click', (e) => {	
		e.preventDefault()
	
	    if (heartElement.classList.contains("is-active")) {
		    heartElement.classList.remove("is-active");
		} else {
		    heartElement.classList.add("is-active");
		}
	})

} 

export {init}