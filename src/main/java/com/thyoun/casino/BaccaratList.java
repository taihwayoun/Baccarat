package com.thyoun.casino;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;

public class BaccaratList{
	private List<BaccaratPlay> playRecords = new ArrayList<>();
	private List<PlayResultColor> listAllColors = new ArrayList<>();
	private List<PlayResultColor> listWithoutGreen = new ArrayList<>();
	//private List<PlayResultColor> bigRoad = new ArrayList<>();
	private List<PlayResultColor> bigEyeBoy = new ArrayList<>();
	private List<PlayResultColor> smallRoad = new ArrayList<>();
	private List<PlayResultColor> cockRoachPig = new ArrayList<>();
	private PlayResultColor prediction[] = new PlayResultColor[6];
	//private List<PlayResultColor> beadPlate = new ArrayList<>();
	private List<BaccaratPlay> top = new ArrayList<>();
	
	public BaccaratList(){
		super();
	}
	public BaccaratList(BaccaratPlay bp) {
		addToPlayRecords(bp);
	}

	public List<PlayResultColor> getBeadPlate(){
		return listAllColors;
	}
	
	public List<PlayResultColor> getBigRoad(){
		return listWithoutGreen;
	}
	
	public List<PlayResultColor> getBigEyeBoy(){
		return bigEyeBoy;
	}
	public List<PlayResultColor> getSmallRoad(){
		return smallRoad;
	}
	public List<PlayResultColor> getCockRoachPig(){
		return cockRoachPig;
	}
	
	public boolean addToPlayRecords(BaccaratPlay b) {
		playRecords.add(b);
		listAllColors.add(b.getPlayResultColor());
		if (b.getPlayResultColor()!=PlayResultColor.GREEN) {
			listWithoutGreen.add(b.getPlayResultColor());
			buildScoreboard();
		}
		return true;
	}
	
	public void clearBoard() {
		playRecords.clear();
		listAllColors.clear();
		listWithoutGreen.clear();
		bigEyeBoy.clear();
		smallRoad.clear();
		cockRoachPig.clear();
	}
	
	private void buildScoreboard() {
		buildBoard(1);
		buildBoard(2);
		buildBoard(3);
	}

	public List<Integer> getLengthList(){
		List<Integer> ret = new ArrayList<>();
		ret.add(Integer.valueOf(1));
		int j=1;
		for (int i=0; i<listWithoutGreen.size()-1;i++){
			if (listWithoutGreen.size()>1 && listWithoutGreen.get(i)!=listWithoutGreen.get(i+1)) {
				j=1;
				ret.add(Integer.valueOf(j));
			} else {
				Integer temp = ret.size()-1;
				ret.set(temp, ++j);
			}
		}
		return ret;
	}

	public PlayResultColor[] getPrediction() {
		List<Integer> lengthList=getLengthList();
		List<Integer> temLenList = new ArrayList();
		int len = lengthList.size();

		if (len>=2) {
			temLenList.add(lengthList.get(len-1));
			temLenList.add(lengthList.get(len-2));
		}
		if (len>=3)
			temLenList.add(lengthList.get(len-3));
		if (len >= 4)
			temLenList.add(lengthList.get(len-4));
		Collections.reverse(temLenList);
		Integer lastValR =lengthList.get(len-1);
		Integer lastValP = lengthList.get(len-1);
		int clen = listWithoutGreen.size();
		if (clen ==0) return null;
		PlayResultColor lastColor = listWithoutGreen.get(clen-1);
		
		PlayResultColor nextColors[] = new PlayResultColor[6];
		
		if (lastColor==PlayResultColor.BLUE)
		{  //blue and then red
			PlayResultColor temp[] = new PlayResultColor[3];
			//Arrays.fill(temp, PlayResultColor.NONE);
			if (temLenList.size() >= 2)
				temLenList.set(temLenList.size()-1, temLenList.get(temLenList.size()-1) + 1);
			temp = getColors(temLenList);
			nextColors[0]=temp[0];
			nextColors[1]=temp[1];
			nextColors[2]=temp[2];
			if (temLenList.size() >= 2)
				temLenList.remove(temLenList.size()-1);
			temLenList.add(1);
			temLenList.add(1);
			temp = getColors(temLenList);
			nextColors[3]=temp[0];
			nextColors[4]=temp[1];
			nextColors[5]=temp[2];
		}
		else
		{
			PlayResultColor temp[] = new PlayResultColor[3];
			temLenList.add(1);
			temp = getColors(temLenList);
			nextColors[0]=temp[0];
			nextColors[1]=temp[1];
			nextColors[2]=temp[2];
			if (temLenList.size() >= 2) {
				temLenList.remove(temLenList.size()-1);
				temLenList.set(temLenList.size()-1, temLenList.get(temLenList.size()-1) + 1);
			}
			temp = getColors(temLenList);
			nextColors[3]=temp[0];
			nextColors[4]=temp[1];
			nextColors[5]=temp[2];
			
		}
		//case next blue
		//case next red
		return nextColors;
	}
	
	private PlayResultColor[] getColors(List<Integer> list){
		PlayResultColor[] colors = new PlayResultColor[3];
		Arrays.fill(colors, PlayResultColor.NONE);
		int len = list.size();
		if (len==0) return colors;
		Integer lastVal =list.get(len-1);
		
		
		if (len >= 2) {
			if (lastVal==1) {
				Integer pivot = list.get(len-2);
				if (len >= 3) {
					 colors[0] = pivot == list.get(len-3)? PlayResultColor.SRED: PlayResultColor.SBLUE;
				}
				if (len >= 4) {
					 colors[1] = pivot == list.get(len-4)? PlayResultColor.FRED: PlayResultColor.FBLUE;
				}
				if (len >= 5) {
					 colors[2] = pivot == list.get(len-5)? PlayResultColor.CRED: PlayResultColor.CBLUE;
				}
			}
			else {
				Integer pivot = list.get(len-1);
				if (len >= 2) {
					 colors[0] = (pivot - list.get(len-2)) == 1? PlayResultColor.SBLUE: PlayResultColor.SRED;
				}
				if (len >= 3) {
					 colors[1] = (pivot - list.get(len-3)) == 1? PlayResultColor.FBLUE: PlayResultColor.FRED;
				}
				if (len >= 4) {
					 colors[2] = (pivot - list.get(len-4)) == 1? PlayResultColor.CBLUE: PlayResultColor.CRED;
				}
				
			}
		}
		
		return colors;
	}
	
	private void buildBoard(int n) {

		List<Integer> lengthList=getLengthList();
		int len = lengthList.size();
		Integer lastVal =lengthList.get(len-1);
		
		List<PlayResultColor> board=new ArrayList<>();
		if (n==1) board = bigEyeBoy;
		else if (n==2) board = smallRoad;
		else if (n==3) board = cockRoachPig;
		
		if (len >= 2) {
			if (lastVal==1 && len > n+1) {
				Integer pivot = lengthList.get(len-2);
				if (pivot==lengthList.get(len-2-n)) {
					if (n==3) board.add(PlayResultColor.CRED);
					else if (n==2) board.add(PlayResultColor.FRED);
					else board.add(PlayResultColor.SRED);
				}
				else {
					if (n==3) board.add(PlayResultColor.CBLUE);
					else if (n==2) board.add(PlayResultColor.FBLUE);
					else board.add(PlayResultColor.SBLUE);
				}
			}
			else if (lastVal >= 2 && len>n) {
				Integer pivot = lengthList.get(len-1);
				if (pivot-lengthList.get(len-1-n)==1) {
					if (n==3) board.add(PlayResultColor.CBLUE);
					else if (n==2) board.add(PlayResultColor.FBLUE);
					else board.add(PlayResultColor.SBLUE);
				}
				else {
					if (n==3) board.add(PlayResultColor.CRED);
					else if (n==2) board.add(PlayResultColor.FRED);
					else board.add(PlayResultColor.SRED);
				}
					
			}
		}
	}

}

