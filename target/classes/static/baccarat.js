/* RED,GREEN,BLUE,SBLUE,SRED
 * bead,small,big,cock,eye, lens
 * message
 */

let forecast = document.getElementById("forecast");
let t = forecast.children[0].offsetTop;
for (let i=2; i<forecast.children.length; i++){
	forecast.children[i].style.position="absolute";
	if (i<5){
		
		forecast.children[i].style.left = 35;
		forecast.children[i].style.top = t + 150 + 20 * (i-1);
	} else {
		forecast.children[i].style.left = 65;
		forecast.children[i].style.top = t + 150+ 20 * (i-4);
	}
}

const dm=20;
const sdm=13;

$(()=>{
	
	/*for (let i=0; i<pred.length; i++){
		if (i<3) $("#foreblue").append(pred[i]);
		else $("#forered").append(pred[i]);
	}
	
	/*for (let i=0; i<pred.length; i++){
		if (i < 3){
			$("#foreblue").children[i].style.left = 5;
			$("#foreblue").children[i].style.top = 20 * (i+1);
		}else{
			$("#forered").children[i-3].style.left = 25;
			$("#forered").children[i-3].style.top = 20 * (i-2);
		}
	}
	*/
	
	if (choice != "NONE"){
		let m =$("#msg").html();
		m += "<h4>Round "+ round + "</h4>";
		if (winner=="TIE")
			m += "The two sides tie.";
		else
			m += "<p>" + winner + " wins.<br>" + choice; 
		if (winner==choice){
			m += ": right choice";
			//let mark = $("#chinatown").children.length-1;
			//$("#chinatown").children[mark].html("&check");
			++wins;
			}
		else if (choice=="PASS")
			m += " taken.";
		else
			m += ": wrong choice";
		let percent;
		if (bets==0)
			percent=0;
		else
			percent = 100*wins/bets;
		m += "<br>Win percentage: " + percent.toFixed(3) + "% (" + wins + " out of " +bets+ ")" ;
		m += "<br>" + infoStr;
		$("#msg").html(m);
	}

	$( "#newhand" ).click(function(){
		$("#newform").submit(function(){
			if ($("input:checked").val()==undefined){
				window.alert("Please check on a bet option.");
				event.preventDefault();
			}
			else {
				if ($("input:checked").val()=="BANKER" || $("input:checked").val()=="PLAYER") ++bets;
				$("#bets").val(bets);
				$("#shoe").val("next");
				$("#wins").val(wins);
			}
		});
	});
	
	$( "#newshoe" ).click(function() {
		$("#shoe").val("new");
		$("#wins").val(0);
		$("#bets").val(0);
		$("newform").submit();
	});
	
	$("input[type=checkbox]").click(function(){
		for (x of $("input:checked"))
			x.checked = false;
		this.checked = true;
	})
	
	if (big.length>0){
		for (let i=0; i<big.length; i++){
			$("#big_road").append(big[i]);
			placement($("#big_road").children(), dm);
		}
	}
	if (eye.length>0){
		for (let i=0; i<eye.length; i++){
			$("#big_eye_boy").append(eye[i]);
			placement($("#big_eye_boy").children(), sdm);
		}
	}
	if (small.length>0){
		for (let i=0; i<small.length; i++){
			$("#small_road").append(small[i]);
			placement($("#small_road").children(), sdm);
		}
	}
	if (cock.length>0){
		for (let i=0; i<cock.length; i++){
			$("#cockroach_pig").append(cock[i]);
			placement($("#cockroach_pig").children(), sdm);
		}
	}
	if (bead.length>0){
		for (let i=0; i<bead.length; i++){
			$("#china_town").append(bead[i]);
		}
		for (let i=0; i<$("#china_town").children().length; i++){
			let tp = dm * (i%6) + 1;
			let lt = dm * Math.floor(i/6) + 1;
			$("#china_town").children()[i].style.left = lt;
			$("#china_town").children()[i].style.top = tp;									
		}
	}
});

function placement(obj, m){
	if (obj.length==0 || obj==undefined) return;
	if (obj.length==1) {
		obj[0].style.left=1;
		obj[0].style.top=1;
		return;
		}
	let ult = obj[obj.length - 1];
	let penult = obj[obj.length - 2];
	let x = parseInt(penult.style.left);
	let y = parseInt(penult.style.top);
	if (ult.className!=penult.className) {
		ult.style.top = 1;
		ult.style.left = x + m;
	} else {
		ult.style.top = y + m;
		ult.style.left = x;
	}
}

function buildMessage(){
	return;
}
