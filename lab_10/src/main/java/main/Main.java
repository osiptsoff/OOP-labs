package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import gui.*;
import org.apache.log4j.PropertyConfigurator;

public class Main {
	public static int[] lastIds = {0, 0, 0, 0, 0};
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
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("log4j.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PropertyConfigurator.configure(props);
		
		var fr = new Hulk(700, 500);
		fr.setVisible(true);
	}
}
