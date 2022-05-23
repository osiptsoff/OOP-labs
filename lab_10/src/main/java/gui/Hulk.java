package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

import main.Main;

/**
 * @author ������� ������, ������ 0305
 * <p>����� ��������� ���� ����������.</p>
 */
public class Hulk {
	protected int wid, hght, currentEntity;
	
	protected JFrame mainPart;
	
	protected JFrame selectionFrame;
	protected JTextField sfAddWhat;
	protected JToolBar sfToolbar;
	protected JButton sfButton;
	protected int sfEntity;
	
	protected JButton helpButton;
	protected JButton addButton;
	protected JButton removeButton;
	protected JButton alterButton;
	protected JButton downloadButton;
	protected JButton saveButton;
	protected JButton searchButton;
	protected JButton[] entities;
	
	protected ClosedTable[] tables;
	protected JScrollPane[] scrolls;
	
	protected JTextField searchwhat;
	
	protected JToolBar workingtb;
	protected JToolBar entitiestb;
	protected JToolBar searchtb;
	
	protected ArrayList<JComboBox<String>> fieldsToSearch;
	
	protected void sfPrepare() {
		selectionFrame.remove(scrolls[sfEntity]);
		selectionFrame.remove(scrolls[4]);
		if (Constants.relatedEntities[currentEntity].length == 2) {
			selectionFrame.add(scrolls[Constants.relatedEntities[currentEntity][0]], BorderLayout.WEST);
			selectionFrame.add(scrolls[Constants.relatedEntities[currentEntity][1]], BorderLayout.EAST);
			sfButton.setEnabled(false);
		} else {
			selectionFrame.add(sfToolbar, BorderLayout.SOUTH);
			selectionFrame.add(scrolls[Constants.relatedEntities[currentEntity][0]], BorderLayout.CENTER);
			sfButton.setEnabled(true);
		}
		sfToolbar.remove(fieldsToSearch.get(sfEntity));
		sfEntity = Constants.relatedEntities[currentEntity][0];
		sfToolbar.add(fieldsToSearch.get(sfEntity), BorderLayout.WEST);
		selectionFrame.revalidate();
		sfToolbar.revalidate();
	}
	
	/**
	 * @param _wid ����� ��������� ���� ����������.
	 * @param _hght ������ ��������� ���� ����������.
	 */
	public Hulk(int _wid, int _hght) {
		wid = _wid;
		hght = _hght;
		currentEntity = Constants.Entities.length - 1;
		
		/**
		 * <p>�������� ��������� ������, ������������ � ��� ����������� � ���������</p>
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
		searchButton.setToolTipText("����� ������, � �������� ������� ���������� �������� �����");
		
		/**
		 * <p>���� ����� ��������� ������ ��� ������</p>
		 */
		searchwhat = new JTextField();
		searchwhat.setToolTipText("������� �����");
		searchwhat.setText("������� �����");
		
		/**
		 * <p>������ ��� �������� ����� ���������</p>
		 */
		entitiestb = new JToolBar();
		entitiestb.setFloatable(false);
		entitiestb.setLayout(new GridLayout(Constants.Entities.length, 1));
		
		/**
		 * <p>� ���� ����� ��������� ����������� ��������: ������ ��� �������� ����� ��������� � 
		 * ���� ���������, �� ������� ����� ����� �����. �������� ������� �� ������ Constants</p>
		 */
		entities = new JButton[Constants.Entities.length];
		fieldsToSearch = new ArrayList<JComboBox<String>>();
		tables = new ClosedTable[Constants.FieldsNames.length];
		scrolls = new JScrollPane[Constants.FieldsNames.length];
		for(int i = 0; i < entities.length; ++i) {
			tables[i] = new ClosedTable(Constants.FieldsNames[i]);
			scrolls[i] = new JScrollPane(tables[i]);
			
			entities[i] = new JButton(Constants.Entities[i]);
			fieldsToSearch.add(new JComboBox<String>(Constants.FieldsNames[i]));
			entitiestb.add(entities[i]);
		}
		
		/**
		 * <p>�������� � ���������� ������� ��� ������ ����� � ��������</p>
		 */
		searchtb = new JToolBar();
		searchtb.setLayout(new BorderLayout());
		searchtb.setFloatable(false);
		searchtb.add(searchwhat, BorderLayout.CENTER);
		searchtb.add(searchButton, BorderLayout.EAST);
		searchtb.add(fieldsToSearch.get(fieldsToSearch.size() - 1), BorderLayout.WEST);
		
		/**
		 * <p>�������� � ���������� "��������" �������, �� ������� ����� ������ ��� ���������� ��������
		 * �������� � ���������</p>
		 */
		workingtb = new JToolBar();
		workingtb.setFloatable(false);
		workingtb.add(downloadButton);
		workingtb.add(saveButton);
		workingtb.add(helpButton);
		workingtb.add(alterButton);
		workingtb.add(addButton);
		workingtb.add(removeButton);
		
		/**
		 * <p>������ ���� ����������</p>
		 */
		mainPart = new JFrame("������� ������������");
		mainPart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPart.setSize(wid,hght);
		mainPart.setLocationRelativeTo(null);
		mainPart.setLayout(new BorderLayout());
		mainPart.add(workingtb, BorderLayout.NORTH);
		mainPart.add(searchtb, BorderLayout.SOUTH);
		mainPart.add(entitiestb, BorderLayout.WEST);
		mainPart.add(scrolls[scrolls.length - 1], BorderLayout.CENTER);
		
		
		sfButton = new JButton("��������");
		sfButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int colIndex = tables[sfEntity].getColumnIndex(fieldsToSearch.get(sfEntity).getSelectedItem().toString());
				var selectionModel = tables[sfEntity].getSelectionModel();
				
				for(int i = 0; i < tables[sfEntity].rowCount; ++i)
					if(tables[sfEntity].getValueAt(i, colIndex).toString().equals(sfAddWhat.getText()))
						selectionModel.addSelectionInterval(i, i);
			}
		});
		
		sfEntity = 1;
		sfToolbar = new JToolBar();
		sfToolbar.setLayout(new BorderLayout());
		sfToolbar.setFloatable(false);
		sfAddWhat = new JTextField("��� �������� � ���������");
		sfToolbar.add(sfAddWhat, BorderLayout.CENTER);
		sfToolbar.add(sfButton, BorderLayout.EAST);
		
		selectionFrame = new JFrame("������� �����");
		selectionFrame.setAutoRequestFocus(false);
		selectionFrame.setSize(wid / 2,hght / 2);
		selectionFrame.setLocation(mainPart.getLocation());
		selectionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		selectionFrame.setLayout(new BorderLayout());
		
		animate();
	}
 	
	private void animate() {
		for(int i = 0; i < entities.length; ++i) {
			final int _i = i;
			entities[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchtb.remove(fieldsToSearch.get(currentEntity));
					searchtb.add(fieldsToSearch.get(_i), BorderLayout.WEST);
					searchtb.revalidate();
					searchtb.repaint();
					mainPart.remove(scrolls[currentEntity]);
					mainPart.add(scrolls[_i], BorderLayout.CENTER);
					mainPart.revalidate();
					entities[currentEntity].setEnabled(true);
					currentEntity = _i;
					entities[currentEntity].setEnabled(false);
				}
			});
		}
		
		addButton.addActionListener(new AddButtonListener(this));
		alterButton.addActionListener(new EditButtonListener(this));
		
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var selectedRows = tables[currentEntity].getSelectedRows();
				
				if(selectedRows.length > 0 && (e == null || JOptionPane.showConfirmDialog(mainPart, "�� ������������� ������ ������� ��������� ������?") == JOptionPane.OK_OPTION))
					for(int row = selectedRows.length - 1; row >= 0; --row) {
						tables[currentEntity].deleteRow(selectedRows[row]);
						Main.megaList.get(currentEntity).remove(selectedRows[row]);
					}
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentEntity == Constants.Entities.length - 1)
					return;
				
				tables[currentEntity].clearSelection();
				
				int colIndex = tables[currentEntity].getColumnIndex(fieldsToSearch.get(currentEntity).getSelectedItem().toString());
				var selectionModel = tables[currentEntity].getSelectionModel();
				
				for(int i = 0; i < tables[currentEntity].rowCount; ++i)
					if(tables[currentEntity].getValueAt(i, colIndex).toString().equals(searchwhat.getText()))
						selectionModel.addSelectionInterval(i, i);
				if(tables[currentEntity].getSelectedRows().length > 0)
					JOptionPane.showMessageDialog(mainPart, "����� �������� �������.");
				else
					JOptionPane.showMessageDialog(mainPart, "������ �� �������.");
			}
		});
		
		searchwhat.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					searchButton.getActionListeners()[0].actionPerformed(null);
			}
			
		});
		
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainPart, "����������� ��������� ��� ������ � ����� �����������."
						+ "\n��������: ������� ������ 0305, ������� ������."
						+ "\n\n����������: ������ �� ��������������� ������, ��������� ��������� ������, ������ Enter. �������� �������� Escape."
						+ "\n��������: ������� ������ ������ �������, ������ �� ��������������� ������."
						+ "\n��������������: ������� ������ ������, ������ �� ��������������� ������. �������� ������������� ������, ������ Enter. �������� �������� Escape."
						+ "\n�����: � ���������� ������ ������� �������� �������, �� ������� ������ ������, � ���� ������� ��������, ������� ������ ����� � ���� �������. ����� ������� ��� ���������� ������.");
			}
			
		});
		saveButton.addActionListener(new ExportButtonListener(this));
		downloadButton.addActionListener(new ImportButtonListener(this));
	}
	/**
	 * <p>������ ���� �������.</p>
	 */
	public void setVisible(boolean visible) {
		mainPart.setVisible(visible);
	}
	
	/**
	 * <p>���������� ��� ������������ ��� ������.</p>
	 */
	public void setContentEnabled(boolean enabled) {
		for(var comp : workingtb.getComponents())
			comp.setEnabled(enabled);
		for(var comp : searchtb.getComponents())
			comp.setEnabled(enabled);
		for(var comp : entitiestb.getComponents())
			comp.setEnabled(enabled);
		if(enabled)
			entities[currentEntity].setEnabled(false);
	}
}
