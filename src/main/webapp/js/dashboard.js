const liElements = document.querySelectorAll("li.menu-list");

liElements.forEach(function (element){
    element.addEventListener("mouseover", function (){
        element.classList.add("mouseover");
    })

    element.addEventListener("mouseout", function (){
        element.classList.remove("mouseover");
    })
})