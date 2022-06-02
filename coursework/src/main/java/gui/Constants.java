package gui;

import classes.*;

/**
 * @author ������� ������, ������ 0305
 * <p>�����-����� ��������, ������������ GUI</p>
 */
final class Constants {
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
	 * �������� ������ � ��.
	 */
	public static final String[] SQLtablesNames = {
			"car",
			"owner",
			"report",
			"speciality",
			"worker",
			"worker_speciality"
	};
	
	/**
	 * ������ ���������. ������������ ��� ��������� ������ �� ������ � ��.
	 */
	@SuppressWarnings("rawtypes")
	public static final Class[] EntitiesClasses = {
			Car.class,
			Owner.class,
			Report.class,
			Speciality.class,
			Worker.class
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
			{"ID", "������", "�������", "���� �������", "����� �������", "��� �������"},
			{"ID", "��������", "�������"},
			{"ID", "���", "�������", "����� ��������", "�������������"},
			{}
			
	};
	
	/**
	 * ��������� � ��������� ����� ������� ����� ��������� ������� ������� ����.
	 */
	interface Constructor{
		TableFriendly Instantiate(int num);
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


