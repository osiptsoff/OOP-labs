package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.Main;

class ImportButtonListener implements ActionListener {
	Hulk hulk;
	ClosedTable table;
	Document doc;
	
	public ImportButtonListener(Hulk _hulk) { hulk = _hulk; }
	@Override
	public void actionPerformed(ActionEvent e) {
		table = hulk.tables[hulk.currentEntity];
		if (hulk.currentEntity == Constants.Entities.length - 1 || JOptionPane.showConfirmDialog(hulk.mainPart, 
				"Вы действительно хотите загрузить вариант этой таблицы с диска?"
				+ "\nОткрытая в приложении таблица будет перезаписана.") != JOptionPane.OK_OPTION) 
			return;
		
		
		try {
			FileReader fr = new FileReader(Constants.xmlPaths[hulk.currentEntity]);
			fr.close();
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = builder.parse(new File(Constants.xmlPaths[hulk.currentEntity]));
			doc.getDocumentElement().normalize();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(hulk.mainPart, "Неудача при попытке доступа и разбора файла. Остановлено."
					+ "\nВозможно, файла не существует или его структура нарушена.");
			return;
		}
		
		table.selectAll();
		hulk.removeButton.getActionListeners()[0].actionPerformed(null);
		
		NodeList objList = doc.getElementsByTagName("object");
		 for (int i = 0; i < objList.getLength(); ++i) {
			 Node elem = objList.item(i);
			 NamedNodeMap attrs = elem.getAttributes();
			 
			 var strToPass = new String[table.getColumnCount()];
			 for(int j = 0; j < strToPass.length; ++j)
				 strToPass[j] = attrs.getNamedItem("field_" + j).getNodeValue();
			 
			 table.addRow();
			 table.startEdittingRow(table.rowCount - 1);
			 
			 var obj = Constants.Constructors[hulk.currentEntity].Instantiate(Integer.parseInt(strToPass[0]));
			 try {
			 ObjectEditor.editors[hulk.currentEntity].func(obj, strToPass, table);
			 } catch (Exception e1) {
				table.stopEditingRow();
				table.deleteRow(table.rowCount - 1);
				JOptionPane.showMessageDialog(hulk.mainPart, "Неудача при записи очередной строки. Остановлено.");
				return;
			 }
			 
			table.stopEditingRow();
			Main.megaList.get(hulk.currentEntity).add(obj);
		 }
		 
		 if(table.rowCount > 0)
			 Main.lastIds[hulk.currentEntity] = Integer.parseInt(table.getValueAt(table.rowCount - 1, 0).toString()) + 1;
		 JOptionPane.showMessageDialog(hulk.mainPart, "Чтение выполнено успешно.");
	} 
}

class ExportButtonListener implements ActionListener {
	Hulk hulk;
	ClosedTable table;
	Document doc;
		
	public ExportButtonListener(Hulk _hulk) { hulk = _hulk; }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (hulk.currentEntity == Constants.Entities.length - 1 || JOptionPane.showConfirmDialog(hulk.mainPart, 
				"Вы действительно хотите сохранить эту таблицу?"
				+ "\nСуществующий на диске вариант будет перезаписан.") != JOptionPane.OK_OPTION)
			return;
			
		
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = builder.newDocument();
			doc.setXmlStandalone(true);
		} catch (ParserConfigurationException e1) {
			JOptionPane.showMessageDialog(hulk.mainPart, "Неудача при создании файла. Остановлено.");
			return;
		}
		
		table = hulk.tables[hulk.currentEntity];
		Node objList = doc.createElement("objlist");
		doc.appendChild(objList);
		for(int i = 0; i < table.rowCount; ++i) {
			Element object = doc.createElement("object");
			Object[] row = table.getRow(i);
			objList.appendChild(object);
			
			for(int j = 0, ccount = table.getColumnCount(); j < ccount; ++j)
				object.setAttribute("field_" + j, row[j].toString());
		}
		
		try {
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			FileWriter fw = new FileWriter(Constants.xmlPaths[hulk.currentEntity]);
			trans.transform(new DOMSource(doc), new StreamResult(fw));
			fw.close();
			JOptionPane.showMessageDialog(hulk.mainPart, "Таблица успешно сохранена.");
		} catch(Exception e1) {
			JOptionPane.showMessageDialog(hulk.mainPart, "Неудача при сохранении файла. Остановлено.");
		}
	}
		

}