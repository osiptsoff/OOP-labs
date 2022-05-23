package gui;

import classes.*;

/**
 * @author ������� ������, ������ 0305
 * <p>�����-����� ��������, ������������ GUI</p>
 */
class Constants {
	/**
	 * ����� ���������, � �������� ����� �������������� GUI.
	 */
	public static final String[] Entities = {
			"������",
			"���������",
			"������",
			"�������������",
			"�������",
			"������ ������"
	};
	
	/**
	 * ������ ���������, � �������� ������� ����������� ��������.
	 */
	public static final int[][] relatedEntities = {
			{1}, 
			{0},
			{0, 4},
			{4},
			{3},
			{}
	};
	
	/**
	 * �������� ����� ���������. ������ �������� ����� ����� ��� �� ������, ��� � �����, � ������� ��� ����������.
	 */
	public static final String[][] FieldsNames = {
			{"ID", "�����", "�������������", "��� �������", "������", "��������"},
			{"ID", "���", "�������", "����� ��������", "������"},
			{"ID", "������", "�������", "���� �������"},
			{"ID", "��������", "�������"},
			{"ID", "���", "�������", "����� ��������", "�������������"},
			{}
			
	};
	
	/**
	 * ���� �� ������ csv, ���� ����������� � ������ ����������� �������
	 */
	public static final String[] xmlPaths = {
			"cars.xml",
			"owners.xml",
			"reports.xml",
			"specs.xml",
			"workers.xml"
	};
	
	/**
	 * ��������� � ��������� ����� ������� ����� ��������� ������� ������� ����.
	 */
	interface Constructor{
		Object Instantiate(int num);
	}
	public static final Constructor[] Constructors = {
			(int num) -> { return new Car(num); },
			(int num) -> { return new Owner(num); },
			(int num) -> { return new Report(num); },
			(int num) -> { return new Speciality(num); },
			(int num) -> { return new Worker(num); },
	};
	
	/**
	 * ����������� ������ ���������: ��� ������ � �������� �������� ����� ������
	 */
	private Constants() {};
}


