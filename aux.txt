// PV:IFCOND(pv:hasFeature('AirPressure'))
function applyPressure() {
	var measureText = document.getElementById("p_measure");
	var pointer = document.getElementById("p_point");
	
	applyTachoValue(minPres, maxPres, measureText, pointer);
	//line 1 added
	//line 2 added
	//line 3 added
	//line 4 added
	//line 4 added
	return false;
}
// PV:ENDCOND

// PV:IFCOND(pv:hasFeature('WindSpeed'))
var windMeasure = 0;
function applyWindSpeed() {
	var measureText = document.getElementById("w_measure");
	windMeasure = measureText.value;
	var pointer = document.getElementById("w_point");
	
	applyTachoValue(minWind, maxWind, measureText, pointer);
	setWarnings();
		//line 1 added
	//line 2 added
	return false;}
// PV:ENDCOND
//Lineas al final!