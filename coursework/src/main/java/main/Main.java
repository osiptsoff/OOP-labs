package main;

import java.util.ArrayList;

import gui.*;
import classes.TableFriendly;

public class Main {
	public static int[] lastIds = {0, 0, 0, 0, 0};
	/* 0 - список машин
	 * 1 - список владельцев
	 * 2 - список отчётов
	 * 3 - список специальностей
	 * 4 - список рабочих
	 */
	public static final ArrayList<ArrayList<TableFriendly>> megaList = new ArrayList<ArrayList<TableFriendly>>();
	static {
		for(int i = 0; i < 5; ++i)
			megaList.add(new ArrayList<TableFriendly>());
	}
	
	public static void main(String[] args) {	
		var fr = new Hulk(700, 500);
		fr.setVisible(true);
	}
}
