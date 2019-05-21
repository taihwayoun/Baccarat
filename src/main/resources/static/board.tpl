<div id="control">
	<form id="newform" method="GET" action="/Scoreboard">
	<div  class="w3-container w3-card-4"   style="margin-top:10px;padding:20px">
		<button id="newshoe" class="w3-button w3-black w3-round-large">New Shoe</button>
		<button id="newhand" class="w3-button w3-yellow w3-round-large" style="margin-left:10px">N E X T</button>
		<input type="hidden" id="shoe" name="shoe">
		<input type="hidden" id="wins" name="wins">
		<input type="hidden" id="bets" name="bets">
	</div>
	<div class="w3-container w3-card-4" id="betForm" style="margin-top:10px;padding:10px">
		<h4>Bet Choice</h4>
		<input class="w3-check" id="cbanker" type="checkbox" name="winner" value="BANKER"><label for="BANKER"> Banker </label>
		<input class="w3-check" id="cplayer" type="checkbox" name="winner" value="PLAYER"><label for="PLAYER"> Player </label>
		<input class="w3-check" id="ctie" type="checkbox" name="winner" value="TIE"><label for="TIE"> Tie </label>
		<input class="w3-check" id="ctie" type="checkbox" name="winner" value="PASS"><label for="TIE"> Pass </label>
	</div>
	</form>
	<div class="w3-container w3-card-4" id="msg">
	</div>
	<div id="forecast" class="w3-container w3-card-4" style="margin-top:10px;height:150">
		<h4>Forecast</h4>
		<script>
		for (let i=0; i<pred.length;i++){
			document.write(pred[i]);
		}
		</script>
	</div>
</div>

<div class="w3-container" style="position:relative">
	<div class="china_town"  id="china_town" style="top:10"></div>
	<div class="scoreboard"  id="big_road" style="top:170" ></div>
	<div class="scoreboard"  id="big_eye_boy" style="top:370" style="flow:left"></div>
	<span class="scoreboard"  id="small_road" style="top:500" style="display:inline"></span>
	<span class="scoreboard"  id="cockroach_pig" style="top:630" style="display:inline"></span>
</div>

<script src="/baccarat.js"></script>



