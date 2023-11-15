package Tests;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import components.Client;
import components.Account;
import components.SavingsAccount;
import components.CurrentAccount;


public class Main {
	
	public static void main(String[] args) {
		//1.1.2 Creation of Main class for tests
		int HowManyClients = 5;
		ArrayList<Client> ClientsList = CreateClientsList(HowManyClients);
		DisplayClients(ClientsList);
		
		//1.2.3 Creation of the tablea account
		ArrayList<Account> AccountsList = generateAccounts(ClientsList);
		displayAccounts(AccountsList);
		
		
		//1.3.1 Creation of Hashtable
		HashMap<Integer, Account> hashAccount = generateHashAccount(AccountsList);
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
	
	//1.2.3 Creation of the tablea account
	private static ArrayList<Account> generateAccounts(ArrayList<Client> ClientsList) {
		ArrayList<Account> AccountsList = new ArrayList<>();
		for (Client client : ClientsList) {
			AccountsList.add(new SavingsAccount("Saving account client" + client.getClientNumber(), client));
			AccountsList.add(new CurrentAccount("Current account client" + client.getClientNumber(), client));
		}
		return AccountsList;
	}
	
	private static void displayAccounts(ArrayList<Account> AccountsList) {
		AccountsList.forEach(System.out::println);
	}
	
	
	//1.3.1 Managing of Hashtable and printing them, sorted by balance's value
	private static HashMap<Integer, Account> generateHashAccount(ArrayList<Account> AccountsList) {
		HashMap<Integer, Account> hashtable = new HashMap<>();

		for (Account account : AccountsList) {
			hashtable.put(account.getAccountNumber(), account);
		}

		return hashtable;
	}

	
	private static void displaySortedHash(HashMap<Integer, Account> hashAccount) {
		List<Entry<Integer, Account>> sorted = hashAccount.entrySet().stream().sort(Map.Entry.comparingByValue()).toList();

		for (Entry<Integer, Account> entry : sorted) {
			System.out.println(entry.getValue());
		}

	}

}
