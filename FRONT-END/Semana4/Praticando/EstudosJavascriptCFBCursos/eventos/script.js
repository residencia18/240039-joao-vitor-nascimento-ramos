const c1 = document.querySelector("#c1")
const msg = () =>{
    alert("clicou")
}

const cursos=[...document.querySelectorAll(".curso")]

cursos.map((el) => {
    el.addEventListener("click", (evt) => {
        const et = evt.target;
        et.classList.add("destaque")
    })
})


