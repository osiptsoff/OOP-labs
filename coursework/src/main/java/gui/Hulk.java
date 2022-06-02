package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import classes.TableFriendly;

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
	protected JButton filterButton;
	protected JButton revertButton;
	protected JButton relinfoButton;
	protected JButton getReportButton;
	protected JButton[] entities;
	
	protected ClosedTable[] tables;
	protected JScrollPane[] scrolls;
	protected ArrayList<TableFriendly> currentEntityShownRows;
	
	protected JTextField searchwhat;
	
	protected JToolBar workingtb;
	protected JToolBar entitiestb;
	protected JToolBar searchtb;
	protected JToolBar searchSubtbParams;
	protected JToolBar searchSubtbCommands;
	
	protected ArrayList<JComboBox<String>> fieldsToSearch;
	protected JComboBox<String> searchModes;
	
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
	/**
	 * @param _wid
	 * @param _hght
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
		addButton.setToolTipText("�������� � ��������� ����� ������");
		removeButton = new JButton(new ImageIcon("src/main/resources/icons/edit_remove_2851.png"));
		removeButton.setToolTipText("������� ��������� ������");
		alterButton = new JButton(new ImageIcon("src/main/resources/icons/pencil_5373.png"));
		alterButton.setToolTipText("�������� ��������� ������");
		downloadButton = new JButton(new ImageIcon("src/main/resources/icons/down_1680.png"));
		downloadButton.setToolTipText("������� ������ �� ���� (������� � ���������� �������������)");
		saveButton = new JButton(new ImageIcon("src/main/resources/icons/filesave_8536.png"));
		saveButton.setToolTipText("��������� ������ � ���� (������ ������ �������������)");
		filterButton = new JButton("������");
		filterButton.setToolTipText("������� ������ ������, � �������� ������� ���������� �������� �����");
		revertButton = new JButton("������");
		revertButton.setToolTipText("�������� ����������");
		relinfoButton = new JButton("���������� � ������");
		relinfoButton.setToolTipText("�������� ��������� ���������� � ������ ������� � ��������� ������");
		getReportButton = new JButton("�����");
		getReportButton.setToolTipText("�������� � ������� pdf ��������� ������ � ������ �������");
		getReportButton.setEnabled(false);
		
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
		searchModes = new JComboBox<String>(new String[]{"��������", "���������"});
		tables = new ClosedTable[Constants.FieldsNames.length];
		scrolls = new JScrollPane[Constants.FieldsNames.length];
		currentEntityShownRows = new ArrayList<TableFriendly>();
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
		searchSubtbCommands = new JToolBar();
		searchSubtbCommands.setFloatable(false);
		searchSubtbCommands.setLayout(new BorderLayout());
		searchSubtbCommands.add(filterButton, BorderLayout.WEST);
		searchSubtbCommands.add(revertButton, BorderLayout.EAST);
		searchSubtbParams = new JToolBar();
		searchSubtbParams.setFloatable(false);
		searchSubtbParams.setLayout(new BorderLayout());
		searchSubtbParams.add(fieldsToSearch.get(fieldsToSearch.size() - 1), BorderLayout.WEST);
		searchSubtbParams.add(searchModes, BorderLayout.EAST);
		searchtb.setLayout(new BorderLayout());
		searchtb.setFloatable(false);
		searchtb.add(searchwhat, BorderLayout.CENTER);
		searchtb.add(searchSubtbCommands, BorderLayout.EAST);
		searchtb.add(searchSubtbParams, BorderLayout.WEST);
		
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
		workingtb.add(relinfoButton);
		workingtb.add(getReportButton);
		
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
				@SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent e) {
					searchSubtbParams.remove(fieldsToSearch.get(currentEntity));
					searchSubtbParams.add(fieldsToSearch.get(_i), BorderLayout.WEST);
					searchSubtbParams.revalidate();
					searchSubtbParams.repaint();
					mainPart.remove(scrolls[currentEntity]);
					mainPart.add(scrolls[_i], BorderLayout.CENTER);
					mainPart.revalidate();
					revertButton.getActionListeners()[0].actionPerformed(null);
					entities[currentEntity].setEnabled(true);
					currentEntity = _i;
					
					getReportButton.setEnabled(currentEntity == 2);
					if(currentEntity != Constants.Entities.length - 1)
						currentEntityShownRows = (ArrayList<TableFriendly>) Main.megaList.get(currentEntity).clone();
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
				
				if(selectedRows.length > 0 && (JOptionPane.showConfirmDialog(mainPart, "�� ������������� ������ ������� ��������� ������?") == JOptionPane.OK_OPTION))
					for(int row = selectedRows.length - 1; row >= 0; --row) {
						tables[currentEntity].deleteRow(selectedRows[row]);
						TableFriendly obj = currentEntityShownRows.get(selectedRows[row]);
						currentEntityShownRows.remove(obj);
						obj.cascadeRemove();
							
						revalidateTables();
					}
			}
		});
		
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentEntity == Constants.Entities.length - 1)
					return;
				
				tables[currentEntity].selectAll();
				
				int colIndex = tables[currentEntity].getColumnIndex(fieldsToSearch.get(currentEntity).getSelectedItem().toString());
				var selectionModel = tables[currentEntity].getSelectionModel();
				
				for(int i = 0; i < tables[currentEntity].rowCount; ++i)
					if(searchModes.getSelectedItem().toString().equals("��������")) {
						if(tables[currentEntity].getValueAt(i, colIndex).toString().contains(searchwhat.getText()))
							selectionModel.removeSelectionInterval(i, i); 
					} else if(tables[currentEntity].getValueAt(i, colIndex).toString().equals(searchwhat.getText()))
						selectionModel.removeSelectionInterval(i, i);

				var selection = tables[currentEntity].getSelectedRows();	
				for(int i = selection.length - 1; i >= 0; --i)
					currentEntityShownRows.remove(selection[i]);
				
				tables[currentEntity].clear();
				for(var obj : currentEntityShownRows) {
					tables[currentEntity].addRow();
					tables[currentEntity].setRow(obj.toRow(), tables[currentEntity].rowCount - 1);
				}
					
				JOptionPane.showMessageDialog(mainPart, "���������� ���������.");
			}
		});
		
		revertButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				if(currentEntity == Constants.Entities.length - 1)
					return;
				
				currentEntityShownRows = (ArrayList<TableFriendly>) Main.megaList.get(currentEntity).clone();
				tables[currentEntity].clear();
				for(var obj : currentEntityShownRows) {
					tables[currentEntity].addRow();
					tables[currentEntity].setRow(obj.toRow(), tables[currentEntity].rowCount - 1);
				}
			}
			
		});
		
		searchwhat.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					filterButton.getActionListeners()[0].actionPerformed(null);
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
		relinfoButton.addActionListener(new RelinfoButtonListener(this));
		getReportButton.addActionListener(new GetReportButtonListener(this));
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
		for(var comp : searchSubtbParams.getComponents())
			comp.setEnabled(enabled);
		for(var comp : searchSubtbCommands.getComponents())
			comp.setEnabled(enabled);
		for(var comp : entitiestb.getComponents())
			comp.setEnabled(enabled);
		
		getReportButton.setEnabled(enabled && currentEntity == 2);
		if(enabled)
			entities[currentEntity].setEnabled(false);
	}
	
	public void revalidateTables() {
		for(int i = 0; i < entities.length - 1; ++i) {
			var lst = i == currentEntity ? currentEntityShownRows : Main.megaList.get(i);
			tables[i].clear();
			for (var o : lst) {
				tables[i].addRow();
				tables[i].setRow(o.toRow(), tables[i].rowCount - 1);
			}
		}
	}
}
