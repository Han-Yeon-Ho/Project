const clock = document.querySelector("h2#clock");



function getClock(){
    const date= new Date();
    const secText= String(date.getSeconds()).padStart(2,"0");
    const hourText=String( date.getHours()).padStart(2,"0");
    const minutesText= String(date.getMinutes()).padStart(2,"0");
    clock.innerText=`${hourText}:${minutesText}:${secText}`;


}
getClock();
setInterval(getClock,1000);


