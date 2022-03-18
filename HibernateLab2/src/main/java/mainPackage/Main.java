package mainPackage;

import javax.persistence.*;
import classes.*;

public class Main {
	public static void main(String[] args) {
		var emf = Persistence.createEntityManagerFactory("test");
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		/*var owner = new Owner();
		owner.SetName("Test");
		owner.SetLastName("Testov");
		owner.SetPhoneNumber("+454553467");
		owner.GetCarsIds().add(0);
		owner.GetCarsIds().add(7);
		owner.GetCarsIds().add(65);
		
		em.persist(owner);
		em.getTransaction().commit();
		
		System.out.println(owner.GetId());*/
		
		var owner = em.find(Owner.class, 9);
		System.out.println(owner.GetName() + " " + owner.GetLastName() + " " + owner.GetCarsIds().toString());
		
		//em.remove(owner);
		//em.getTransaction().commit();
		
		//em.close();
		//emf.close();
	}
}
