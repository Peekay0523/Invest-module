document.onreadystatechange = function () {

	if (document.readyState !== "complete") {
	   document.querySelector("app-wrapper").style.visibility = "hidden";
	   document.getElementById("loading_indicator").style.visibility = "visible";
	  
	} else {
	   setTimeout(() => {
		  document.getElementById("loading_indicator").style.display ="none";
		  document.querySelector("app-wrapper").style.visibility = "visible";
	   }, 1500)
	}
 };
