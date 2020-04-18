package application;

import java.util.Date;

import modal.entities.Departament;
import modal.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Departament obj = new Departament(1, "Books");
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);

		System.out.println(seller);

	}

}
