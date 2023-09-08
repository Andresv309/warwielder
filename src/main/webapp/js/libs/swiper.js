
async function createSwiper ({
	swiperHtmlElement,
	swiperPaginationHtmlElement
}) {
	
	return new Swiper(swiperHtmlElement, {
	  pagination: {
	    el: swiperPaginationHtmlElement,
	    dynamicBullets: true,
	  },
	});	
}

export { createSwiper }
