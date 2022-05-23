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
	 * Названия полей сущностей. Списки названий полей имеют тот же индекс, что и класс, в котором они содержатся.
	 */
	public static final String[][] FieldsNames = {
			{"ID", "Бренд", "Неисправность", "Год выпуска", "Пробег"},
			{"ID", "Имя", "Фамилия", "Номер телефона"},
			{"ID", "ID машины", "ID рабочего", "Дата ремонта"},
			{"ID", "Название"},
			{"ID", "Имя", "Фамилия", "Номер телефона"},
			{}
			
	};
	
	/**
	 * Пути до файлов csv, куда сохраняются и откуда загружаются таблицы
	 */
	public static final String[] csvPaths = {
			"cars.csv",
			"owners.csv",
			"reports.csv",
			"specs.csv",
			"workers.csv"
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
	 * Этот разделитель используется при считывании и сохранении таблиц в текстовые файлы.
	 */
	public static String separator = "\\|\\|";
	
	/**
	 * Конструктор сделан приватным: нет смысла в создании объектов этого класса
	 */
	private Constants() {};
}


