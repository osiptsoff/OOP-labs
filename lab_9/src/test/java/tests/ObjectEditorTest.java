package tests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import classes.*;
import gui.ObjectEditor;
import gui.ClosedTable;

public class ObjectEditorTest {
private static final ClosedTable dummyTable = new ClosedTable(new String[]{"1", "2", "3", "4", "5"});
static {
	dummyTable.addRow();
	dummyTable.startEdittingRow(0);
}
	@Test
	public void testEditCar() {
		Object[] expected_1 = {0, "Lada", "Тормоз", 2012, 122343};
		Object[] expected_2 = {1, "Toyota", "Стук в салоне при езде", 1978, 965964};
		Object[] expected_3 = {2, "BMW", "Необходимость в замене масла", 2019, 4465657};
		Object[] expected_4 = {3, "Lada", "Проколота шина", 1966, 99999};
		Object[] expected_5 = {4, "Volkswagen", "Треснуло стекло", 2020, 12454};
		
		Car actual_1 = new Car(0); 
		Car actual_2 = new Car(1);
		Car actual_3 = new Car(2);
		Car actual_4 = new Car(3);
		Car actual_5 = new Car(4);
		
		try {
			ObjectEditor.editCar(actual_1, new Object[]{1, "Lada", "Тормоз", "2012", "122343"}, dummyTable);
			ObjectEditor.editCar(actual_2, new Object[]{2, "Toyota", "Стук в салоне при езде", "1978", "965964"}, dummyTable);
			ObjectEditor.editCar(actual_3, new Object[]{3, "BMW", "Необходимость в замене масла", "2019", "4465657"}, dummyTable);
			ObjectEditor.editCar(actual_4, new Object[]{4, "Lada", "Проколота шина", "1966", "99999"}, dummyTable);
			ObjectEditor.editCar(actual_5, new Object[]{5, "Volkswagen", "Треснуло стекло", "2020", "12454"}, dummyTable);
		} catch(Exception e) {}
		Assertions.assertAll(
		() -> Assertions.assertArrayEquals(expected_1, new Object[]{actual_1.GetId(), actual_1.GetBrand(), actual_1.GetProblemDescription(), actual_1.GetReleaseYear(), actual_1.GetMileage()}),
		() -> Assertions.assertArrayEquals(expected_2, new Object[]{actual_2.GetId(), actual_2.GetBrand(), actual_2.GetProblemDescription(), actual_2.GetReleaseYear(), actual_2.GetMileage()}),
		() ->Assertions.assertArrayEquals(expected_3, new Object[]{actual_3.GetId(), actual_3.GetBrand(), actual_3.GetProblemDescription(), actual_3.GetReleaseYear(), actual_3.GetMileage()}),
		() ->Assertions.assertArrayEquals(expected_4, new Object[]{actual_4.GetId(), actual_4.GetBrand(), actual_4.GetProblemDescription(), actual_4.GetReleaseYear(), actual_4.GetMileage()}),
		() ->Assertions.assertArrayEquals(expected_5, new Object[]{actual_5.GetId(), actual_5.GetBrand(), actual_5.GetProblemDescription(), actual_5.GetReleaseYear(), actual_5.GetMileage()})
		);
	}
	
	@Test
	public void testEditOwner() {
		Object[] expected_1 = {0, "Николай", "Соболев", "+79105467634"};
		Object[] expected_2 = {1, "Юрий", "Стеклов", "+79195467634"};
		Object[] expected_3 = {2, "Анатолий", "Горошин", "+79105460812"};
		Object[] expected_4 = {3, "Максим", "Коровьев", "+79101957634"};
		Object[] expected_5 = {4, "Павел", "Говорунов", "+72348780645"};
		
		Owner actual_1 = new Owner(0); 
		Owner actual_2 = new Owner(1);
		Owner actual_3 = new Owner(2);
		Owner actual_4 = new Owner(3);
		Owner actual_5 = new Owner(4);
		
		try {
			ObjectEditor.editOwner(actual_1, expected_1, dummyTable);
			ObjectEditor.editOwner(actual_2, expected_2, dummyTable);
			ObjectEditor.editOwner(actual_3, expected_3, dummyTable);
			ObjectEditor.editOwner(actual_4, expected_4, dummyTable);
			ObjectEditor.editOwner(actual_5, expected_5, dummyTable);
		} catch(Exception e) {}
		Assertions.assertAll(
		() -> Assertions.assertArrayEquals(expected_1, new Object[]{actual_1.GetId(), actual_1.GetName(), actual_1.GetLastName(), actual_1.GetPhoneNumber()}),
		() -> Assertions.assertArrayEquals(expected_2, new Object[]{actual_2.GetId(), actual_2.GetName(), actual_2.GetLastName(), actual_2.GetPhoneNumber()}),
		() -> Assertions.assertArrayEquals(expected_3, new Object[]{actual_3.GetId(), actual_3.GetName(), actual_3.GetLastName(), actual_3.GetPhoneNumber()}),
		() -> Assertions.assertArrayEquals(expected_4, new Object[]{actual_4.GetId(), actual_4.GetName(), actual_4.GetLastName(), actual_4.GetPhoneNumber()}),
		() -> Assertions.assertArrayEquals(expected_5, new Object[]{actual_5.GetId(), actual_5.GetName(), actual_5.GetLastName(), actual_5.GetPhoneNumber()})
		);
	
	}
}
