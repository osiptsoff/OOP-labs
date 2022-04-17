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
	 * �������� ����� ���������. ������ �������� ����� ����� ��� �� ������, ��� � �����, � ������� ��� ����������.
	 */
	public static final String[][] FieldsNames = {
			{"ID", "�����", "�������������", "��� �������", "������"},
			{"ID", "���", "�������", "����� ��������"},
			{"ID", "ID ������", "ID ��������", "���� �������"},
			{"ID", "��������"},
			{"ID", "���", "�������", "����� ��������"},
			{}
			
	};
	
	/**
	 * ���� �� ������ csv, ���� ����������� � ������ ����������� �������
	 */
	public static final String[] csvPaths = {
			"cars.csv",
			"owners.csv",
			"reports.csv",
			"specs.csv",
			"workers.csv"
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
	 * ���� ����������� ������������ ��� ���������� � ���������� ������ � ��������� �����.
	 */
	public static String separator = "\\|\\|";
	
	/**
	 * ����������� ������ ���������: ��� ������ � �������� �������� ����� ������
	 */
	private Constants() {};
}


