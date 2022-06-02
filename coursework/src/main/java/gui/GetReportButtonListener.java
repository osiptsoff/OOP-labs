package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import classes.Report;

final class GetReportButtonListener implements ActionListener {
	Hulk hulk;
	
	public GetReportButtonListener(Hulk _hulk) { hulk = _hulk; }
	
	@Override
	public void actionPerformed (ActionEvent event)
	{
		Document document = new Document(PageSize.A4, 10, 10, 20, 20);
		PdfPTable t = new PdfPTable(4);
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream("report.pdf"));
		} 
		catch (Exception e) { 
			JOptionPane.showMessageDialog(hulk.mainPart, "������ ������� ��� �������� �����.");
			return;
		}

		BaseFont bf;
		try {
			bf = BaseFont.createFont("src\\main\\resources\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(hulk.mainPart, "���������� ����� �����.");
			return;
		}
		Font font = new Font(bf, 10);
		
		for(var obj : hulk.currentEntityShownRows) {
			var report = (Report)obj;
			t.addCell(new Phrase("ID : " + report.GetId()));
			
			var data = report.getWorkerInfo().replace("'", "").split(" ");
			t.addCell(new Phrase("�������\n"
					+ "ID : " + data[0] + "\n��� : " + data[1]
					+ "\n������� : " + data[2] + "\n����� �������� : " + data[3], font));
			
			data = report.getCarInfo().replace("'", "").split(" ");
			t.addCell(new Phrase("������\n"
					+ "ID : " + data[0] + "\n����� : " + data[1]
					+ "\n������������� : " + data[2] + "\n��� ������� : " + data[3]
					+ "\n������ : " + data[4], font));
			
			t.addCell(new Phrase("���� ������� : " + report.getDay() + "." + report.getMonth() + "." + report.getYear(), font));
		}
		
		if(hulk.currentEntityShownRows.isEmpty()) {
			t.addCell(new Phrase("�� ����", font));
			t.addCell(new Phrase("�����", font));
			t.addCell(new Phrase("�� ���", font));
			t.addCell(new Phrase("������", font));
		}
			
		
		document.open();
		try {
		document.add(t);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(hulk.mainPart, "������ ��� ������ ���������.");
			return;
		}
		
		document.close();
		JOptionPane.showMessageDialog(hulk.mainPart, "����� ������� ������.\n�� ��������� � �������� ����� ����������.");
	}
}
