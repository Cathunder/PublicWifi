let myPosBtn = document.getElementById("myPosBtn");
let nearBtn = document.getElementById("nearWIFIInfoBtn");
let lat = document.getElementById("latInput");
let lnt = document.getElementById("lntInput");

myPosBtn.addEventListener("click", function() {
    navigator.geolocation.getCurrentPosition(success, error);
});

function success(position) {
    lat.value = position.coords.latitude;
    lnt.value = position.coords.longitude;
}

function error(error) {
    console.log(error.message);
    alert("위치 정보를 확인할 수 없습니다.");
}

nearBtn.addEventListener("click", function() {
    window.location = "http://localhost:8080/?lat=" + lat.value + "&lnt=" + lnt.value;
});