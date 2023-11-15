//1.2.1 Creation of the account class
package components;

public class Account {
	protected String label;
	protected double balance = 0;
	protected int accountNumber;
	protected static int count = 0;
	protected Client client;
	
	protected Account(String label, Client client) {
		this.label = label;
		this.client = client;
		this.accountNumber = count++;
	}
	
	public String getLabel() {
		return label;
	}
	
	public double getBalance() {
		return balance;
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	//here I will make modification later
	public void setBalance(int Amount) {
		this.balance = Amount;
	}
	
	public void setAccountNumber(int NewNumber) {
		this.accountNumber = NewNumber;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}

	
}
