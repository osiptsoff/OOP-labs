package main;

import java.util.ArrayList;

import gui.*;

public class Main {
	public static int[] lastIds = {0, 0, 0, 0, 0};
	/* 0 - список машин
	 * 1 - список владельцев
	 * 2 - список отчётов
	 * 3 - список специальностей
	 * 4 - список рабочих
	 */
	public static final ArrayList<ArrayList<Object>> megaList = new ArrayList<ArrayList<Object>>();
	static {
		for(int i = 0; i < 5; ++i)
			megaList.add(new ArrayList<Object>());
	}
	
	public static void main(String[] args) {	
		var fr = new Hulk(700, 500);
		fr.setVisible(true);
	}
}
