package main;

import java.util.ArrayList;

import gui.*;

public class Main {
	public static int lastId = 0;
	public static ArrayList<Object> carList = new ArrayList<Object>();
	public static ArrayList<Object> ownerList = new ArrayList<Object>();
	public static ArrayList<Object> reportList = new ArrayList<Object>();
	public static ArrayList<Object> specList = new ArrayList<Object>();
	public static ArrayList<Object> workerList = new ArrayList<Object>();
	
	public static final ArrayList<ArrayList<Object>> megaList = new ArrayList<ArrayList<Object>>();
	static {
		megaList.add(carList);
		megaList.add(ownerList);
		megaList.add(reportList);
		megaList.add(specList);
		megaList.add(workerList);
	}
	
	public static void main(String[] args) {
		var fr = new Hulk(700, 500);
		fr.setVisible(true);
	}
}
