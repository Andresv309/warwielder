
/**
 * Renders HTML content within an HTML parent element using a template function.
 * 
 * @param {HTMLElement} htmlParentElement - The HTML element where the content will be rendered.
 * @param {Array} ChildrenArray - An array of data items to be passed to the template function.
 * @param {Function} htmlTemplateComponent - A function that generates HTML content for each data item.
 * @param {Function} customCallback - A callback function for adding event listeners or custom code.
 */
function htmlRenderer(htmlParentElement, ChildrenArray, htmlTemplateComponent, customCallback) {
  // Create a new DocumentFragment to hold the rendered content
  const fragment = document.createDocumentFragment();

  // Iterate through the ChildrenArray and append the generated content to the fragment
  ChildrenArray.forEach((currentValue) => {
    const content = document.createRange().createContextualFragment(htmlTemplateComponent(currentValue));
    console.log({content, html: htmlTemplateComponent(currentValue)})

    // Call the custom callback function with the content as an argument
    if (typeof customCallback === 'function') {
	  customCallback(content, currentValue);
    }
    fragment.appendChild(content);
  });

  // Clear the existing content of the htmlParentElement
  while (htmlParentElement.firstChild) {
    htmlParentElement.removeChild(htmlParentElement.firstChild);
  }

  // Append the DocumentFragment to the htmlParentElement
  htmlParentElement.appendChild(fragment);
}

export { htmlRenderer };