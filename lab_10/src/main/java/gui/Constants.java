package gui;

import classes.*;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс-набор констант, используемых GUI</p>
 */
class Constants {
	/**
	 * Имена сущностей, с которыми будет контактировать GUI.
	 */
	public static final String[] Entities = {
			"Машины",
			"Владельцы",
			"Отчёты",
			"Специальности",
			"Рабочие",
			"Пустой список"
	};
	
	/**
	 * номера сущностей, с которыми связана определённая сущность.
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
	 * Названия полей сущностей. Списки названий полей имеют тот же индекс, что и класс, в котором они содержатся.
	 */
	public static final String[][] FieldsNames = {
			{"ID", "Бренд", "Неисправность", "Год выпуска", "Пробег", "Владелец"},
			{"ID", "Имя", "Фамилия", "Номер телефона", "Машины"},
			{"ID", "Машина", "Рабочий", "Дата ремонта"},
			{"ID", "Название", "Рабочие"},
			{"ID", "Имя", "Фамилия", "Номер телефона", "Специальности"},
			{}
			
	};
	
	/**
	 * Пути до файлов csv, куда сохраняются и откуда загружаются таблицы
	 */
	public static final String[] xmlPaths = {
			"cars.xml",
			"owners.xml",
			"reports.xml",
			"specs.xml",
			"workers.xml"
	};
	
	/**
	 * Обращаясь к элементам этого массива можно создавать объекты нужного типа.
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
	 * Конструктор сделан приватным: нет смысла в создании объектов этого класса
	 */
	private Constants() {};
}


