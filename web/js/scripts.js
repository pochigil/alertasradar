/*
 Creating an HTML5 enhanced responsive-ready contact form, with custom javascript feature detection
 www.toddmotto.com
 */
(function() {

    // Create input element for testing
    var inputs = document.createElement('input');

    // Create the supports object
    var supports = {};

    supports.autofocus = 'autofocus' in inputs;
    supports.required = 'required' in inputs;
    supports.placeholder = 'placeholder' in inputs;

    // Fallback for autofocus attribute
    if (!supports.autofocus) {

    }

    // Fallback for required attribute
    if (!supports.required) {

    }

    // Fallback for placeholder attribute
    if (!supports.placeholder) {

    }

    // Change text inside send button on submit
    var send = document.getElementById('login-submit');
    if (send) {
        send.onclick = function() {
            if (document.getElementById('usuario-text').value.trim().length){
                if(document.getElementById('password-text').value.trim().length){
                   this.innerHTML = 'Entrando...';
                }else{
                    alert("Password incorrecto");
                }
            }else{
                alert("Usuario incorrecto");
            }
            
        }
    }

    // Change text inside send button on submit
    var send = document.getElementById('enviar-submit');
    if (send) {
        send.onclick = function() {
            if (document.getElementById('mensaje-text').value.trim().length){
               this.innerHTML = 'Enviando...'; 
            }else{
                alert("Mensaje no puede estar vacio");
            }
                
        }
    }

})();