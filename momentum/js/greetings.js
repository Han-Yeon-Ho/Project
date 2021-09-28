

const loginForm =document.getElementById("login-form");
const loginInput = loginForm.querySelector("input");
const greetingText=document.querySelector("#greeting");

const HIDDEN_CLASSNAME="hidden";
const USERNAME_KEY="username";
function onLoginSubmit(event){
    event.preventDefault();
    const username = loginInput.value;
    localStorage.setItem(USERNAME_KEY,username);

    loginForm.classList.add(HIDDEN_CLASSNAME);
    // greetingText.innerText="Hello🎈 "+username;
    paintGreetins(username);
    console.log(username);
   
}
function paintGreetins(username){
    greetingText.innerText=`Hello🎈 ${username}`;
    greetingText.classList.remove(HIDDEN_CLASSNAME);

}
//loginButton.addEventListener("click", onLoginBtnClick);



const savedUsername = localStorage.getItem(USERNAME_KEY);
if(savedUsername === null){
    //show form     
    loginForm.classList.remove(HIDDEN_CLASSNAME);
    loginForm.addEventListener("submit",onLoginSubmit);    

}else{
    paintGreetins(savedUsername);
}
