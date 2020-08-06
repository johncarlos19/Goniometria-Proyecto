$('.toggle').click(function(){
    $('.formulario').animate({
        height: "toggle",
        'padding-top': 'toggle',
        'padding-bottom': 'toggle',
        opacity: 'toggle'

    }, "slow");});
$('.toggle').click(function(){
    var elemento = 'inici';
    var ele = 'Iniciar Sesion';
    var new_id = 'vol';
    try {
        if (document.getElementById('inici').id === elemento){
            document.getElementById('inici').innerText = ele;
            document.getElementById('inici').id = new_id;

        }
    }
    catch(err) {
        document.getElementById(new_id).innerText = 'Crear Cuenta';
        document.getElementById(new_id).id = elemento;
    }


});
