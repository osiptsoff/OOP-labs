package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс основного окна приложения.</p>
 */
public class Hulk {
	int wid, hght;
	
	private JFrame mainPart;
	
	private JButton helpButton;
	private JButton addButton;
	private JButton removeButton;
	private JButton alterButton;
	private JButton downloadButton;
	private JButton saveButton;
	private JButton searchButton;
	private JButton[] entities;
	
	private JScrollPane scroll;
	
	private JTextField searchwhat;
	
	private JToolBar workingtb;
	private JToolBar entitiestb;
	private JToolBar searchtb;
	
	private JTable table;
	
	private JComboBox<String>[] fieldsToSearch;
	
	/**
	 * @param _wid Длина основного окна приложения.
	 * @param _hght Ширина основного окна приложения.
	 */
	public Hulk(int _wid, int _hght) {
		wid = _wid;
		hght = _hght;
		
		/**
		 * Создание одиночных кнопок, приклепление к ним изображений и подсказок
		 */
		helpButton = new JButton(new ImageIcon("src/main/resources/icons/unknown_4913.png"));
		helpButton.setToolTipText("Справка");
		addButton = new JButton(new ImageIcon("src/main/resources/icons/edit_add_9236.png"));
		addButton.setToolTipText("Создать и добавить объект в список");
		removeButton = new JButton(new ImageIcon("src/main/resources/icons/edit_remove_2851.png"));
		removeButton.setToolTipText("Удалить объект из списка");
		alterButton = new JButton(new ImageIcon("src/main/resources/icons/pencil_5373.png"));
		alterButton.setToolTipText("Выбрать строку и изменить её");
		downloadButton = new JButton(new ImageIcon("src/main/resources/icons/down_1680.png"));
		downloadButton.setToolTipText("Скачать данные из базы (таблица не сохранится автоматически)");
		saveButton = new JButton(new ImageIcon("src/main/resources/icons/filesave_8536.png"));
		saveButton.setToolTipText("Сохранить таблицу в базу данных (старые данные перезапишутся)");
		searchButton = new JButton("Найти");
		
		/**
		 * Сюда будет вводиться запрос для поиска
		 */
		searchwhat = new JTextField();
		
		/**
		 * Тулбар для перехода между таблицами
		 */
		entitiestb = new JToolBar();
		entitiestb.setFloatable(false);
		entitiestb.setLayout(new GridLayout(Constants.Entities.length, 1));
		
		/**
		 * В этом цикле создаются неодиночные элементы: кнопки для перехода между таблицами и 
		 * поля сущностей, по которым можно вести поиск. Названия берутся из класса Constants
		 */
		entities = new JButton[Constants.Entities.length];
		fieldsToSearch = new JComboBox[Constants.Entities.length];
		for(int i = 0; i < entities.length; ++i) {
			entities[i] = new JButton(Constants.Entities[i]);
			fieldsToSearch[i] = new JComboBox<String>(Constants.FieldsNames[i]);
			entitiestb.add(entities[i]);
		}
		
		/**
		 * Создание и заполнение тулбара для поиска строк в таблицах
		 */
		searchtb = new JToolBar();
		searchtb.setFloatable(false);
		searchtb.add(fieldsToSearch[0]);
		searchtb.add(searchwhat);
		searchtb.add(searchButton);
			
		/**
		 * Создание и заполнение "рабочего" тулбара, на котором будут кнопки для выполнение основных
		 * действий с таблицами
		 */
		workingtb = new JToolBar();
		//workingtb.setLayout(new GroupLayout(workingtb));
		workingtb.setFloatable(false);
		workingtb.add(downloadButton);
		workingtb.add(saveButton);
		workingtb.add(helpButton);
		workingtb.add(alterButton);
		workingtb.add(addButton);
		workingtb.add(removeButton);
		
		
		table = new JTable(new DefaultTableModel(Constants.SampleCars, Constants.FieldsNames[0]));
		scroll = new JScrollPane(table);
		
		/**
		 * Сборка окна приложения
		 */
		mainPart = new JFrame("Станция обслуживания");
		mainPart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPart.setSize(wid,hght);
		mainPart.setLocationRelativeTo(null);
		mainPart.setLayout(new BorderLayout());
		mainPart.add(workingtb, BorderLayout.NORTH);
		mainPart.add(searchtb, BorderLayout.SOUTH);
		mainPart.add(entitiestb, BorderLayout.WEST);
		mainPart.add(scroll, BorderLayout.CENTER);
	}
 	
	/**
	 * <p>Делает окно видимым.</p>
	 */
	public void Show() {
		mainPart.setVisible(true);
	}
}
