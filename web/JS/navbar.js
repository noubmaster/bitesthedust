var currentPath = window.location.pathname;
var currentPge = window.location.href;
var block = document.getElementById("block");
var block2 = document.getElementById("block2");
var loginIfBlock = document.getElementById("loginIfBlock");
if (currentPath === "/memes/faces/ui/navbar.xhtml" ||
        currentPath === "/memes/faces/ui/navbar.xhtml#" ||
        currentPath === "/memes/faces/cadastroArtistaTeste.xhtml" ||
        currentPath === "/memes/faces/cadastroArtistaTeste.xhtml#") {
    var tipo = document.getElementById("f1:tipoUser").value;
    console.log(tipo);
    if (tipo === "0") {
        block.style.display = "none";
        jQuery.ajax({
            url: currentPath,
            success: function (result) {
                html = jQuery('<div/>').html(result).find("div#block2");
            }
        });
        loginIfBlock.style.display = "inline";
    } else {
        loginIfBlock.style.display = "none";
        block.style.display = "inline";
    }
}
;
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
        login.style.display = "inline";
        optionsUser.style.display = "none";
        sop.style.display = "none";

    });
    document.getElementById('f1:registra').click();
    location.reload();
}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    var login = document.getElementById("login");
    var optionsUser = document.getElementById("optionsUser");
    var sop = document.getElementById("sop");
    var email = document.getElementById('f:email');
    var idR = document.getElementById('f:idR');
    var idR2 = document.getElementById('f1:idR');
    var perfil = document.getElementById('f:perfil');
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
    if (profile.getId() !== null) {
        login.style.display = "none";
        optionsUser.style.display = "inline";
        sop.style.display = "inline";
        console.log('ID: ' + profile.getId());
        email.value = profile.getEmail();
        idR.value = profile.getId();
        idR2.value = profile.getId();
        perfil.value = profile.getImageUrl();
        document.getElementById('f:registra').click();
        document.getElementById('f1:registra').click();
    }
    $('#myModal').modal('hide');
}