var deadManSwitch = false;
var pictureDomElement = document.getElementById("picture");

var checkNewImage;
checkNewImage = function(){
  var xmlhttp;
  xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function(){
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
      pictureDomElement.src = xmlhttp.responseText;
      setTimeout(checkNewImage, 10000);
      deadManSwitch = true;
    }
  };
  xmlhttp.open("GET", "current-picture-url", true);
  xmlhttp.send();
};
checkNewImage();

var deadManSwitchChecker;
deadManSwitchChecker = function(){
  if (deadManSwitch == false){
    pictureDomElement.src = "";
  } else {
    deadManSwitch = false;
  }
  setTimeout(deadManSwitchChecker, 20000);
};
deadManSwitchChecker();
