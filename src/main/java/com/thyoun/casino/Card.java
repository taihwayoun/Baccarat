package com.thyoun.casino;

import java.util.List;
import java.util.Arrays;
import java.lang.Comparable;

public class Card implements Comparable<Card>{

	private String suit;
	private int value;
	private String name;
	
	public Card() {
		super();
	}
	public Card(String suit, int value, String name) {
		this.suit=suit;
		this.value=value;
		this.name=name;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toMinString() {
		if (this==null) return "NoCard";
		else return suit.substring(0,1)+this.value;
	}
	
	public String toShortString() {
		if (this==null) return "NoCard";
		else return suit.substring(0,1) + "_" + name;
	}
	
	
	@Override
	public String toString() {
		if (this==null) return "No card";
		else return name + " of " + suit + "s";
	}

	public int compareTo(Card another) {
		if (this.value > another.getValue()) return 1;
		else if (this.value < another.getValue()) return -1;
		else return 0;
	}
}
