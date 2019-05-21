let bigRoad;
let smallRoad;
let bigEyeBoy;
function setup(){
	scoreboard = createCanvas(600, 200);
	scoreboard.parent('scoreboard')
}

function draw(){
	//mouseIsPressed? fill("blue"): fill("red");
	//bigRoad.background("yellow");
	//smallRoad.background("blue");
	noStroke();
	fill("red");
	ellipse(15, 15, 15, 15);
	fill("blue");
	ellipse(15, 35, 15, 15);

}