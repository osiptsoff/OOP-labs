package main;

import java.util.ArrayList;

import gui.*;

public class Main {
	public static int[] lastIds = {0, 0, 0, 0, 0};
	/* 0 - ������ �����
	 * 1 - ������ ����������
	 * 2 - ������ �������
	 * 3 - ������ ��������������
	 * 4 - ������ �������
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
