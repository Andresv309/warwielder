    const nextStepButton = document.getElementById('nextStep');
    const submitButton = document.getElementById('submitForm');
    const step1Section = document.getElementById('step1');
    const step2Section = document.getElementById('step2');
    const submitButtonBefore = submitButton.querySelector("::before");

    nextStepButton.addEventListener('click', function(event) {
        event.preventDefault();
        // Aquí podrías realizar validaciones y procesar los datos del primer paso

        // Oculta el primer paso y muestra el segundo paso
        step1Section.style.display = 'none';
        step2Section.style.display = 'block';
    });

    submitButton.addEventListener('click', function(event) {
        event.preventDefault();
        
        console.log(submitButtonBefore)
        
        // Aquí podrías realizar validaciones y enviar los datos del segundo paso
		submitButtonBefore.style.content = 'Empezar'; 
        // Simplemente para este ejemplo, podrías redirigir al usuario a una página de éxito
        window.location.href = 'registro_exitoso.jsp';
    });