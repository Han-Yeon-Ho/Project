
const MY_API="8fbab96c0fd2c4ace49510681f88a00c";

function onGeoOk(position){
    const lat=position.coords.latitude;
    const lng=position.coords.longitude;
    
    const url=` https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&appid=${MY_API}&units=metric`;
    console.log(url);
    fetch(url)
    .then(response => response.json())
    .then( data => {
        const weather = document.querySelector("#weather span:first-child")
        const city = document.querySelector("#weather span:last-child")
       const name=data.name
       const weat = data.weather[0].main;
       weather.innerText=name;
       city.innerText=weat+` / ${data.main.temp}🌡`;

    });
}

function OnGeoError(){
    alert("Can't find you, No Weather for you.");
}

navigator.geolocation.getCurrentPosition(onGeoOk,OnGeoError)