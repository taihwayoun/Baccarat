package com.thyoun.casino;

import java.util.stream.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class BaccaratPlay {
	protected Card banker[] = new Card[3];
	protected Card player[] = new Card[3];
	private final int numDeck = 8;
	private Deck deck;
	private final int cutPos = 30;
	
	public BaccaratPlay() throws Exception{
		this.deck = new Deck(numDeck);
		deck.shuffle();
		deck.burn();
	}
	
	public int getNumDeck() {
		return numDeck;
	}

	public void getNewShoe() throws Exception {
		deck = new Deck(numDeck);
		deck.shuffle();
		deck.burn();
	}
	
	public boolean isPastCutPos() {
		return deck.getNumCards() < cutPos;
	}
	
	public int[] playSimulation(long numShoe) throws Exception {
		int bw=0, pw=0, tie=0, total=0,bankernat=0, playernat=0,three89=0,any89=0,badbeat=0,any87=0,panda=0,dragon=0, ppair=0,bpair=0;
		int res[] = new int[14];
		for (long k=0; k<numShoe; k++) {
			while (!isPastCutPos()) {
				playOneRound();
				if (isBankerNatural()) ++bankernat;
				if (isPlayerNatural()) ++playernat;
				if (isThree8And9()) ++three89;
				if (isAny8And9()) ++any89;
				if (isBadBeat()) ++badbeat;
				if (isAny8And7()) ++any87;
				if (isPanda()) ++panda;
				if (isDragon()) ++dragon;
				if (isPlayerPair()) ++ppair;
				if (isBankerPair()) ++bpair;
				if (getPlayResult()==PlayResult.BANKER) ++bw;
				else if (getPlayResult()==PlayResult.PLAYER) ++pw;
				else ++tie;
				total++;
			}
			getNewShoe();
		}
		res[0]=bw;  //bankwin,playwin,tie,total,bankernatural,playernatural,three89,any89,badbeat,any87,panda,dragon
		res[1]=pw;
		res[2]=tie;
		res[3]=total;
		res[4] = bankernat;
		res[5] = playernat;
		res[6] = three89;
		res[7] = any89;
		res[8] = badbeat;
		res[9] = any87;
		res[10] = panda;
		res[11] = dragon;
		res[12] = ppair;
		res[13] = bpair;
		return res; 
	}
	
	public int playOneRound() {
		if (deck.getNumCards() < cutPos) return -1;
		Arrays.fill(player, null);
		Arrays.fill(banker, null);
		player[0]=deck.getCard(0);
 		banker[0]=deck.getCard(0);
		player[1]=deck.getCard(0);
		banker[1]=deck.getCard(0);
		
		//rules on the third card
		if (getPlayerVal()==8 || getPlayerVal()==9 || getBankerVal()==8 || getBankerVal()==9)
			return 2;
		else if	(getBankerVal()>=6 && getPlayerVal()>=6)
			return 1;
		else {
			int p = getPlayerVal(), b=getBankerVal();
			if (b==7 && p < 6)
				player[2]=deck.getCard(0);
			else if (p >= 6 && b < 6)
				banker[2]=deck.getCard(0);
			else {
				player[2]=deck.getCard(0);
				int p2=player[2].getValue();
				if (b<3) banker[2]=deck.getCard(0);
				else if (b==3  && p2!=8) banker[2]=deck.getCard(0);
				else if (b==4 && p2>= 2 && p2<=7) banker[2]=deck.getCard(0);
				else if (b==5 && p2>=4 && p2<=7) banker[2]=deck.getCard(0);
				else if (b==6 && p2==6 || p2==7) banker[2]=deck.getCard(0);
			}
			return 0;
		}
		
	}
	
	public List<String> playOneShoe(){
		List<String> oneShoe = new ArrayList<>();
		deck.burn();
		while (deck.getNumCards()>cutPos) {
			playOneRound();
			oneShoe.add(this.toString());
		}
		return oneShoe;
	}
	
	private boolean continuePlay() {
		if (getPlayerVal()==8 ||
				getPlayerVal()==9 ||
				getBankerVal()==8 ||
				getPlayerVal()==9 ||
				getBankerVal()==7 && getPlayerVal()==7)
			return false;
		else
			return true;
	}
	
	private int getVal(boolean b) { //true is the banker and false is the player
		Card tem[]= new Card[3];
		tem= b? banker: player;
		int retVal = 0;
		for(int i=0; i<tem.length; i++)
			retVal += Objects.isNull(tem[i])? 0:tem[i].getValue();
		return retVal%10;
	}
	
	public int getBankerVal() {
		return getVal(true);
	}
	
	public int getPlayerVal() {
		return getVal(false);
	}
	
	public PlayResult getPlayResult() {
		if (getBankerVal()>getPlayerVal()) return PlayResult.BANKER;
		else if (getBankerVal()<getPlayerVal()) return PlayResult.PLAYER;
		else return PlayResult.TIE;
	}

	public PlayResultColor getPlayResultColor() {
		if (getBankerVal()>getPlayerVal()) return PlayResultColor.RED;
		else if (getBankerVal()<getPlayerVal()) return PlayResultColor.BLUE;
		else return PlayResultColor.GREEN;
	}
	
	public boolean isBankerNatural() {
		return Objects.isNull(banker[2]) && getBankerVal()==8 || getBankerVal()==9;  
	}

	public boolean isPlayerNatural() {
		return Objects.isNull(player[2]) && getBankerVal()==8 || getBankerVal()==9;  
	}
	
	public boolean isThree8And9() {
		boolean isThreeCards = !isBankerNatural() && !isPlayerNatural();
		return isThreeCards && ((getBankerVal()==8 && getPlayerVal()==9) || (getBankerVal()==9 && getPlayerVal()==8));
	}
	
	public boolean isAny8And9() {
		return (Objects.isNull(banker[2]) && Objects.isNull(player[2])) && (getBankerVal()==8 && getPlayerVal()==9)||(getBankerVal()==9 && getBankerVal()==8);
	}

	public boolean isBadBeat() {
		return (Math.abs(getBankerVal() - getPlayerVal())==1)? true: false;
	}
	
	public boolean isAny8And7() {
		return (getBankerVal()==8 && getPlayerVal()==7) || (getBankerVal()==7 && getPlayerVal()==8);
	}
	
	public boolean isPanda() {
		return !Objects.isNull(player[2]) && getPlayerVal()==8 && getBankerVal() <8;
	}
	
	public boolean isDragon() {
		return getPlayResult()==PlayResult.BANKER && !Objects.isNull(banker[2]) && getBankerVal()==7;
	}

	public boolean isPlayerPair() {
		return player[0].getName() == player[1].getName();
	}

	public boolean isBankerPair() {
		return banker[0].getName() == banker[1].getName();
	}
	
	public String getShoeStatus() {
		return "Decks: " + numDeck + ", Card in Shoe: " + deck.getNumCards() + ", Cut Position: " + cutPos;
	}

	public String toShortString() {
		String str="";
		str+= banker[0].toString() + "--" +  banker[1].toString() + " ";
		if (!Objects.isNull(banker[2])) str +=  " : " + banker[2].toString();
		str+= " / "+player[0].toString() + "--" + player[1].toString() + " ";
		if (!Objects.isNull(player[2])) str +=  " : " + player[2].toString();
		return str;
		
	}
	
	@Override
	public String toString() {
		String str="";
		str= "<div class='w3-cell-row' style='width:80%'><div class='w3-card-4 w3-cell' id='banker' style=\"padding-right: 20px;width:50%\">"+
				"<div class='w3-container  w3-red'\"><h3>Banker</h3></div><div class='w3-container'>   <img src='/cards/" +
				banker[0].toMinString() + ".bmp'><img src='/cards/" + banker[1].toMinString() +".bmp'>";
		if (!Objects.isNull(banker[2])) str +=  " : <img class='card3' src='/cards/" + banker[2].toMinString() +".bmp'>";
		str += "</div></div>";
		str+= "<div class='w3-card-4 w3-cell' style=\"width:50%\"> <div class='w3-container w3-blue' id='player'> <h3>Player</h3></div><div class='w3-container'>   <img src='/cards/" 
				+ player[0].toMinString() + ".bmp'><img src='/cards/" + player[1].toMinString() +".bmp'>";
		if (!Objects.isNull(player[2])) str +=  " : <img class='card3' src='/cards/" + player[2].toMinString() +".bmp'>";
		str += "</div></div></div>";
		return str;
	}
	
}
