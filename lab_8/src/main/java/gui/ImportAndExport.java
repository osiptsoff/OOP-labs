package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

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
		
	public ExportButtonListener(Hulk _hulk) { hulk = _hulk; }
	@Override
	public void actionPerformed(ActionEvent e) {
		table = hulk.tables[hulk.currentEntity];
		try {
			var pw = new PdfWriter("out.pdf");
			var pd = new PdfDocument(pw, new DocumentProperties());
			var pf = PdfFontFactory.createFont(FontProgramFactory.createFont("src/main/resources/fonts/ARIALUNI.TTF"));
			pd.setCloseWriter(true);
			pd.setDefaultPageSize(PageSize.A4);
			
			pd.addNewPage(PageSize.A4);
			var pp = pd.getLastPage();
			var pc = new PdfCanvas(pp, true);

			pc.setFontAndSize(pf, 12);
			
			var ps = pp.getPageSize();
			var downOffset = 20;
			var rightOffset = ps.getWidth() / table.getColumnCount();
			var startPos = ps.getTop() - downOffset;
			
			pc.moveText(2, startPos + 5);
			for(int i = 0; i < table.getColumnCount(); ++i) {
				pc.rectangle(0 + rightOffset * i, startPos, rightOffset, downOffset);
				pc.showText(Constants.FieldsNames[hulk.currentEntity][i]);
				pc.moveText(rightOffset, 0);
			}
			
			for(int row = 1; row <= table.rowCount; ++row) {
				pc.moveText(-ps.getWidth(), -downOffset);
				for(int col = 0; col < table.getColumnCount(); ++col) {
					pc.rectangle(0 + rightOffset * col, startPos - downOffset * row, rightOffset, downOffset);
					pc.showText(table.getValueAt(row - 1, col).toString());
					pc.moveText(rightOffset, 0);
				}
			}
			pc.stroke();
			
			pd.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
};
		
