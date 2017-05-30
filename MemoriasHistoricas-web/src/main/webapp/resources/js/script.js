/*PAGINADOR*/
var contador = 0;
var paraCronometro;
/**BLOCK_GENERIC**/
var $myNewElemen = $('<div id = "ajaxBlock" class="black_overlayError"><div class="mhWait"><div class="wBall" id="wBall_1"><div class="wInnerBall"></div></div><div class="wBall" id="wBall_2"><div class="wInnerBall"></div></div><div class="wBall" id="wBall_3"><div class="wInnerBall"></div></div><div class="wBall" id="wBall_4"><div class="wInnerBall"></div></div><div class="wBall" id="wBall_5"><div class="wInnerBall"></div></div></div></div>');
function delBlock() {
    if ($("#ajaxBlock").length >= 1) {
        $("#ajaxBlock").remove();
    }
}
function divBlock() {
    delBlock();
    $($myNewElemen).css("background-color", '#0F0254');
    $('body').append($myNewElemen);
    $("#ajaxBlock").show();
}

/**CONTROL DE AJAX**/
var open = window.XMLHttpRequest.prototype.open,
        send = window.XMLHttpRequest.prototype.send,
        onReadyStateChange;
function openReplacement(method, url, async, user, password) {
    return open.apply(this, arguments);
}


function sendReplacement(data) {
    divBlock();
    if (this.onreadystatechange) {
        this._onreadystatechange = this.onreadystatechange;
    }
    this.onreadystatechange = onReadyStateChangeReplacement;

    return send.apply(this, arguments);
}

window.XMLHttpRequest.prototype.open = openReplacement;
window.XMLHttpRequest.prototype.send = sendReplacement;

function addZero(i) {
    v = "0" + i;
    v = v.substr(v.length - 2);
    return v;
}


function myFunction() {
    var d = new Date();
    var cro = (new Date() - ini);
    d.setTime(cro);
    var h = addZero(d.getHours() - 19);
    var m = addZero(d.getMinutes());
    var s = addZero(d.getSeconds());

    document.getElementById("formMemoriasHistoricas").value = h + ":" + m + ":" + s;
    setTimeout("myFunction()", 1000);
}
function myProces()
{
    document.getElementById('procesImg').style.display = 'block';
}

function cronometro() {
    contador = parseInt(document.getElementById("formMemoriasHistoricas").value);
    var d = new Date(contador);
    var h = addZero(d.getHours() - 19);
    var m = addZero(d.getMinutes());
    var s = addZero(d.getSeconds());
    var tiempo = h + ":" + m + ":" + s;
    document.getElementById("formMemoriasHistoricas").value = tiempo;
    contador += 1000;
    document.getElementById("formMemoriasHistoricas").value = contador;
    paraCronometro = setTimeout("cronometro()", 1000);
}

function detenerCronometro() {
    clearTimeout(paraCronometro);
}

(function ($) {
    $(document).ready(function () {
        $('nav > ul > li > a').click(function () {
            $('nav li').removeClass('active');
            $(this).closest('li').addClass('active');
            var checkElement = $(this).next();
            if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                $(this).closest('li').removeClass('active');
                checkElement.slideUp('normal');
            }
            if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                $('nav ul ul:visible').slideUp('normal');
                checkElement.slideDown('normal');
            }
            if ($(this).closest('li').find('ul').children().length == 0) {
                return true;
            } else {
                return false;
            }
        });
        $('nav > ul > li > ul > li > a').click(function () {
            $('nav li ul li').removeClass('active');
            $(this).closest('li').addClass('active');
            var checkElement = $(this).next();
            if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                $(this).closest('li').removeClass('active');
                checkElement.slideUp('normal');
            }
            if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                $('nav ul ul ul:visible').slideUp('normal');
                checkElement.slideDown('normal');
            }
            if ($(this).closest('li').find('ul').children().length == 0) {
                return true;
            } else {
                return false;
            }
        });
    });

})($);

window.addEventListener("beforeunload", function (e) {
    divBlock();
});

function initDialog() {
    debugger;
    $("#toolTipPanel").dialog({
        autoOpen: true,
        title: "Registro de usuario",
        resizable : false,
        width: 550,
        heigth: 400,
        modal: true,
        buttons: {
            Cancelar: function () {
                $(this).dialog("close");
            }
        }
    });
}