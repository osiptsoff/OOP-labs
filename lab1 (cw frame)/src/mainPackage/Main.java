package mainPackage;

import lab1.*;

public class Main {
	public static void main(String[] args) {
		Worker person = new Worker();
		String s1 = "aINURBEK67567";
		String s2 = "BbbbBBBBBbbbb";
		String s3 = "+79318544234";
		
		System.out.println(person.SetName(s1) + " " + s1 + " " +  person.GetName());
		System.out.println(person.SetLastName(s2) + " " + s2 + " " +  person.GetLastName());
		System.out.println(person.SetPhoneNumber(s3) + " " + s3 + " " +  person.GetPhoneNumber());
	}
}
