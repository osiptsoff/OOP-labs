package gui;

import classes.*;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс-набор констант, используемых GUI</p>
 */
final class Constants {
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
	 * Названия таблиц в БД.
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
	 * Классы сущностей. Используются для получения данных из таблиц в БД.
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
	 * Номера сущностей, с которыми связана определённая сущность.
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
			{"ID", "Машина", "Рабочий", "День ремонта", "Месяц ремонта", "Год ремонта"},
			{"ID", "Название", "Рабочие"},
			{"ID", "Имя", "Фамилия", "Номер телефона", "Специальности"},
			{}
			
	};
	
	/**
	 * Обращаясь к элементам этого массива можно создавать объекты нужного типа.
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
	 * Конструктор сделан приватным: нет смысла в создании объектов этого класса
	 */
	private Constants() {};
}


