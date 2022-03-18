package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author ������� ������, ������ 0305
 * <p>����� ��������� ���� ����������.</p>
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
	 * @param _wid ����� ��������� ���� ����������.
	 * @param _hght ������ ��������� ���� ����������.
	 */
	public Hulk(int _wid, int _hght) {
		wid = _wid;
		hght = _hght;
		
		/**
		 * �������� ��������� ������, ������������ � ��� ����������� � ���������
		 */
		helpButton = new JButton(new ImageIcon("src/main/resources/icons/unknown_4913.png"));
		helpButton.setToolTipText("�������");
		addButton = new JButton(new ImageIcon("src/main/resources/icons/edit_add_9236.png"));
		addButton.setToolTipText("������� � �������� ������ � ������");
		removeButton = new JButton(new ImageIcon("src/main/resources/icons/edit_remove_2851.png"));
		removeButton.setToolTipText("������� ������ �� ������");
		alterButton = new JButton(new ImageIcon("src/main/resources/icons/pencil_5373.png"));
		alterButton.setToolTipText("������� ������ � �������� �");
		downloadButton = new JButton(new ImageIcon("src/main/resources/icons/down_1680.png"));
		downloadButton.setToolTipText("������� ������ �� ���� (������� �� ���������� �������������)");
		saveButton = new JButton(new ImageIcon("src/main/resources/icons/filesave_8536.png"));
		saveButton.setToolTipText("��������� ������� � ���� ������ (������ ������ �������������)");
		searchButton = new JButton("�����");
		
		/**
		 * ���� ����� ��������� ������ ��� ������
		 */
		searchwhat = new JTextField();
		
		/**
		 * ������ ��� �������� ����� ���������
		 */
		entitiestb = new JToolBar();
		entitiestb.setFloatable(false);
		entitiestb.setLayout(new GridLayout(Constants.Entities.length, 1));
		
		/**
		 * � ���� ����� ��������� ����������� ��������: ������ ��� �������� ����� ��������� � 
		 * ���� ���������, �� ������� ����� ����� �����. �������� ������� �� ������ Constants
		 */
		entities = new JButton[Constants.Entities.length];
		fieldsToSearch = new JComboBox[Constants.Entities.length];
		for(int i = 0; i < entities.length; ++i) {
			entities[i] = new JButton(Constants.Entities[i]);
			fieldsToSearch[i] = new JComboBox<String>(Constants.FieldsNames[i]);
			entitiestb.add(entities[i]);
		}
		
		/**
		 * �������� � ���������� ������� ��� ������ ����� � ��������
		 */
		searchtb = new JToolBar();
		searchtb.setFloatable(false);
		searchtb.add(fieldsToSearch[0]);
		searchtb.add(searchwhat);
		searchtb.add(searchButton);
			
		/**
		 * �������� � ���������� "��������" �������, �� ������� ����� ������ ��� ���������� ��������
		 * �������� � ���������
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
		 * ������ ���� ����������
		 */
		mainPart = new JFrame("������� ������������");
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
	 * <p>������ ���� �������.</p>
	 */
	public void Show() {
		mainPart.setVisible(true);
	}
}
