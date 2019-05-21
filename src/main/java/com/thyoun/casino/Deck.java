package com.thyoun.casino;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private String suits[] = {"heart", "spade", "club", "diamond"};
	private String names[] = {"ace", "deuce", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};
	private int values[]=new int[names.length];
	{
		for (int i=0; i<names.length; i++)
			{
			if (i<10) values[i]= i+1;
			else values[i]=10;
			}
	}
	private int numDecks=0;
	private List<Card> cards = new ArrayList<>();
	
	public Deck(int nd) {
		List<Card> temp=new ArrayList<>();
		int j, k;
		for (j=0; j<suits.length; j++) {
			for (k=0; k<names.length; k++) {
				temp.add(new Card(suits[j], values[k], names[k]));
			}
		}
		
		while (numDecks<nd) {
			cards.addAll(temp);
			numDecks++;
		}
		//System.out.println(cards.size());
	}
	
	public int getNumCards() {
		return cards.size();
	}
	
	public Card getCard(int pos) {
		return cards.remove(pos);
	}
	
	public void burn() {
		int i = getCard(0).getValue();
		while (i>0) {
			cards.remove(0);
			--i;
		}
	}
	
	public void shuffle() throws Exception {
		if (getNumCards()==numDecks*suits.length*names.length) {
			Collections.shuffle(cards);
		} else {
			throw new Exception("Shuffle can be done only with a full stack.");
		}
	}
	
}
