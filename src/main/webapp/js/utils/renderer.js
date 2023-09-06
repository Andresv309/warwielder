

/**
 * Renders HTML content within an HTML parent element using a template function.
 * 
 * @param {HTMLElement} htmlParentElement - The HTML element where the content will be rendered.
 * @param {Array} ChildrenArray - An array of data items to be passed to the template function.
 * @param {Function} htmlTemplateComponent - A function that generates HTML content for each data item.
 */

function htmlRenderer (htmlParentElement, ChildrenArray, htmlTemplateComponent) {
  htmlParentElement.innerHTML = ChildrenArray.reduce((accumulator, currentValue) => accumulator + htmlTemplateComponent(currentValue), "")
}

export { htmlRenderer }

/* Example of use similar to react */
/*
<div>
{
	elements.map((element) => {
		const { img } = element
		return (
			<img src={img}/>
		)
	})
}
</div>
*/