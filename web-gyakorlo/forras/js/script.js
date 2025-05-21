function validateForm() {
    const vezeteknev = document.getElementById("vnev").value;
    const utonev = document.getElementById("unev").value;
    const email = document.getElementById("email").value;
    const telefon = document.getElementById("tel").value;
    const megjegyzes = document.getElementById("megjegyzes").value;


    if (vezeteknev == "" || utonev == "" || email == "" || telefon == "" || megjegyzes == "") {
        alert("Kérem töltse ki az összes mezőt!");
        return false;
    }else{
        alert("Köszönjük, hogy megkeresett minket, hamarosan felvesszük Önnel a kapcsolatot!");
    }
    
    return true;
}

const copyright = document.getElementById("copyright")

copyright.appendChild(document.createTextNode(new Date().getFullYear()))

window.onload = () =>{
    console.log("Oldal betöltve!")
}

