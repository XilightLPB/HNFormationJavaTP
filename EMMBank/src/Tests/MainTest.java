package Tests;
import java.util.ArrayList;

import components.Client;


public class MainTest {
	
	public static void main(String[] args) {
		//1.1.2 Creation of Main class for tests
		int HowManyClients = 5;
		ArrayList<Client> ClientsList = CreateClientsList(HowManyClients);
		DisplayClients(ClientsList);
	}
	
	
	//1.1.2 Creation of Main class for tests
	private static ArrayList<Client> CreateClientsList(int HowMany){
		ArrayList<Client> res = new ArrayList<Client>();
		
		for (int i = 0; i<HowMany; i++) {
			res.add(new Client("name" + i, "firstname" + i));
		}
		return res;
	}
	
	
	private static void DisplayClients(ArrayList<Client> ClientsList) {
		ClientsList.forEach(System.out::println);
	}
	
	


}
