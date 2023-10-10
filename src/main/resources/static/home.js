const liElements = document.querySelectorAll("div.menu-element");

liElements.forEach(function (element){
    element.addEventListener("mouseover", function (){
        element.classList.add("mouseover-home");
    })

    element.addEventListener("mouseout", function (){
        element.classList.remove("mouseover-home");
    })
})