/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function createElement(category) {
    var nameCategory = category.categoria;
    
    if(nameCategory !== undefined)
        return $("<li>").attr("class", "verticalnav").append($("<a>").attr("href", "notizie.html?category="+nameCategory).html(nameCategory));
}

function createUser(user) {
    var id = user.id; 
    var nome = user.name;
    var cognome = user.surname;
    var immagine = $("<img>").attr("src", user.img).attr("height", 20).attr("width", 20).attr("alt", "immagine profilo");
    
    if(id !== undefined && nome !== undefined && cognome !== undefined && immagine !== undefined)
        return $("<li>").attr("class", "verticalnav").append($("<a>").attr("href", "notizie.html?author="+id).html(nome+" "+cognome).append(immagine));

}

function stateSuccess(data) {
    var catDiv = $("ul.list");
    var authorDiv = $("ul.author");
    
    $(catDiv).empty();
    $(authorDiv).empty();
    
    for(var instance in data) {
        $(catDiv).append(createElement(data[instance]));
        $(authorDiv).append(createUser(data[instance]));
    }
}

function stateFailure(data, state) {
    console.log(state);
}

$(document).ready(function() {
    var ricerca = document.getElementById("search");
    
    //istruzioni per agganciare event handlers
    ricerca.addEventListener("keyup", function(event) {
        $.ajax({
            url: "filter.json",
            data: {
                q: "search",
                toSearch: event.target.value
            },
            dataType: 'json',
            success: function (data, state) {stateSuccess(data);},
            error: function (data, state) {stateFailure(data, state);}
            });

    });
});