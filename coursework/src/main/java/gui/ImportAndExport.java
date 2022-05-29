package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.*;
import javax.swing.JOptionPane;

import classes.Speciality;
import classes.TableFriendly;
import main.Main;

class ImportButtonListener implements ActionListener {
	Hulk hulk;
	
	public ImportButtonListener(Hulk _hulk) { hulk = _hulk; }
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(hulk.mainPart, "Вы действительно хотите загрузить данные из базы?"
				+ "\nОткрытые в приложении таблицы будут перезаписаны.") != JOptionPane.OK_OPTION) 
			return;
		var emf = Persistence.createEntityManagerFactory("test");
		var em = emf.createEntityManager();
		
		for(int i = 0, j; i < Constants.EntitiesClasses.length; ++i) {
			var res = em.createNativeQuery("SELECT * FROM " + Constants.SQLtablesNames[i], Constants.EntitiesClasses[i]).getResultList();
			Main.megaList.set(i, (ArrayList<Object>) res);
			var lst = Main.megaList.get(i);
			var table = hulk.tables[i];
			hulk.entities[i].getActionListeners()[0].actionPerformed(null);
			
			table.selectAll();
			hulk.removeButton.getActionListeners()[0].actionPerformed(null);
			
			j = 0;
			for(var obj : lst) {
				table.addRow();
				table.setRow(((TableFriendly)obj).toRow(), j++);
			}
			if(!lst.isEmpty())
				Main.lastIds[hulk.currentEntity] = Integer.parseInt(Integer.toString(((TableFriendly)lst.get(table.rowCount - 1)).GetId()).substring(1)) + 1;
		}
		
		int j;
		int m;
		for(var spec : Main.megaList.get(3)) {
			j = 0;
			var wkrs = ((Speciality)spec).GetWorkers();
			for(var wkr : wkrs) {
				m = Main.megaList.get(4).lastIndexOf(wkr);
				if(m > -1)
					wkrs.set(j, wkr);
				++j;
			}
		}
			
		
		em.close();
		emf.close();
		JOptionPane.showMessageDialog(hulk.mainPart, "Чтение выполнено успешно.");
	} 
}

class ExportButtonListener implements ActionListener {
	private Hulk hulk;
	
	public ExportButtonListener(Hulk _hulk) { hulk = _hulk;}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(JOptionPane.showConfirmDialog(hulk.mainPart, "Вы действительно хотите сохранить изменения?\n"
				+ "Все данные во всех таблицах будут перезаписаны.") != JOptionPane.OK_OPTION)
			return;
		
		var emf = Persistence.createEntityManagerFactory("test");
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		for(var name : Constants.SQLtablesNames)
			em.createNativeQuery("TRUNCATE TABLE " + name).executeUpdate();
		
		for (var list : Main.megaList)
			for (var obj : list)
					em.persist(obj);

		em.getTransaction().commit();
		em.close();
		emf.close();
		JOptionPane.showMessageDialog(hulk.mainPart, "Успешно.");
	}
}