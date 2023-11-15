package Tests;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind;
import com.fasterxml.*;

import components.Client;
import components.Credit;
import components.Account;
import components.SavingsAccount;
import components.Transfert;
import components.CurrentAccount;
import components.Debit;
import components.Flow;


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		//1.1.2 Creation of Main class for tests
		int HowManyClients = 5;
		ArrayList<Client> ClientsList = CreateClientsList(HowManyClients);
		DisplayClients(ClientsList);
		
		//1.2.3 Creation of the tablea account
		ArrayList<Account> AccountsList = generateAccounts(ClientsList);
		displayAccounts(AccountsList);
		
		
		//1.3.1 Creation of Hashtable
		HashMap<Integer, Account> hashAccount = generateHashAccount(AccountsList);
		//displaySortedHash(hashAccount);
		
		
		//1.3.4 Creation of the flow array
		ArrayList<Flow> FlowsList = new ArrayList<Flow>();
		FlowsList = LoadFlowsList(AccountsList); 
		
		//1.3.5 Update accounts
		updateAccounts(hashAccount,FlowsList);
		//displaySortedHash(hashAccount);
		
		//2.1 JSON file of flows
		final String path = "../tests.json";
		//FlowsList = generateFlowsFromJSON(path);
		
	}
	
	
	//1.1.2 Creation of Main class for tests
	private static ArrayList<Client> CreateClientsList(int HowMany){
		ArrayList<Client> res = new ArrayList<Client>();
		
		for (int i = 0; i<HowMany; i++) 
		{
			res.add(new Client("name" + i, "firstname" + i));
		}
		
		return res;
	}
	
	
	private static void DisplayClients(ArrayList<Client> ClientsList) 
	{
		ClientsList.forEach(System.out::println);
	}
	
	//1.2.3 Creation of the tablea account
	private static ArrayList<Account> generateAccounts(ArrayList<Client> ClientsList) {
		ArrayList<Account> AccountsList = new ArrayList<>();
		
		for (Client client : ClientsList) 
		{
			AccountsList.add(new SavingsAccount("Saving account client" + client.getClientNumber(), client));
			AccountsList.add(new CurrentAccount("Current account client" + client.getClientNumber(), client));
		}
		
		return AccountsList;
	}
	
	private static void displayAccounts(ArrayList<Account> AccountsList) 
	{
		AccountsList.forEach(System.out::println);
	}
	
	
	//1.3.1 Managing of Hashtable and printing them, sorted by balance's value
	private static HashMap<Integer, Account> generateHashAccount(ArrayList<Account> AccountsList) {
		HashMap<Integer, Account> hashtable = new HashMap<>();

		for (Account account : AccountsList) 
		{
			hashtable.put(account.getAccountNumber(), account);
		}

		return hashtable;
	}

	
	private static void displaySortedHash(HashMap<Integer, Account> hashAccount) {
		List<Entry<Integer, Account>> sorted = hashAccount.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();

		for (Entry<Integer, Account> entry : sorted) {
			System.out.println(entry.getValue());
		}

	}
	
	//1.3.4 Creation of the flows array
	private static ArrayList<Flow> LoadFlowsList(ArrayList<Account> AccountsList) {
		ArrayList<Flow> res = null;
		res.add(new Debit("a debit of 50€ from account n°1", 50, 1));
		
		for (int i = 1; i<(AccountsList.size()+1); i++ ) {
		
			if (AccountsList.get(i) instanceof CurrentAccount) 
			{
				res.add(new Credit("a credit of 100.50€ on all current accounts in the array of accounts", 100.50, i));
			}
			
			else if (AccountsList.get(i) instanceof SavingsAccount) 
			{
				res.add(new Credit("a credit of 1500€ on all current accounts in the array of accounts", 1500, i));
			}
		}
		
		res.add(new Transfert("A transfer of 50€ from account n1 to account n2", 50, 2, 1));
		
		
		return res;
		
	}
	
	//Update accounts
	private static HashMap<Integer, Account> updateAccounts(HashMap<Integer, Account> hashAccount, ArrayList FlowsList){
		for (int i = 0; i< FlowsList.size(); i++ ) {
			Flow CurrentElement = (Flow) FlowsList.get(i);
			Account account = hashAccount.get(CurrentElement.getTargetAccountNumber());
			account.setBalance(CurrentElement);
			//checking that the account doesn't have a balance below 0
			if (account.getBalance()<0) {
				System.out.println("Account number " + i + " got his balance below 0");
			}
			
			hashAccount.put(account.getAccountNumber(), account);
			
			if (CurrentElement instanceof Transfert) 
			{
				Transfert trans = (Transfert) CurrentElement;
				Account SourceAccount = hashAccount.get(trans.getSourceAccount());
				SourceAccount.setBalance(trans);
				
				//checking that the sending account isn't now with a balance below 0
				if (SourceAccount.getBalance()<0) {
					System.out.println("Account number " + SourceAccount.getAccountNumber() + " got his balance below 0");
				}
				hashAccount.put(SourceAccount.getAccountNumber(), SourceAccount);
			}
		}
		
		return hashAccount;
	}
	
	
	//2.1 JSON file to flows
	private static ArrayList<Flow> generateFlowsFromJSON(String path) throws IOException {

		// Get JSON file from path
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		String json = new String(encoded, StandardCharsets.UTF_8);
		
		//Trying to fill the flow array from it
		ArrayList <Flow> res = new ArrayList<Flow>();
		System.out.print(json);
		
		
		
		return res;
	}

}
