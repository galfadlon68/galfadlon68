/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;
	
	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}

	public BankAccount lookUp(String name){
		// create an Entry with the given name, a "dummy" accountNumber (1) and zero balance
		// This "dummy" accountNumber will be ignored when executing getData
		BankAccount lookFor = new BankAccount(name, 1, 0);
		return (BankAccount)namesTree.findData(lookFor);
	}
	
	public BankAccount lookUp(int accountNumber){
		// create an Entry with a "dummy" name, zero balance and the given accountNumber
		// This "dummy" name will be ignored when executing getData
		BankAccount lookFor = new BankAccount("dummy", accountNumber,0);
		return (BankAccount)accountNumbersTree.findData(lookFor);
	}
	
	// END OF Given code -----------------------------------
	
	// Complete the methods from here on

	// task 6a This method receives a new newAccount account and adds it to the bank management system
	public boolean add(BankAccount newAccount) {
		String accountName = newAccount.getName();
		int numberAccount = newAccount.getAccountNumber();

		// The bank's management system already have an existing account with the same name or number
		if ((lookUp(accountName) != null) | (lookUp(numberAccount) != null))
			return false;

		else {
			namesTree.insert(newAccount);
			accountNumbersTree.insert(newAccount);
			return true;
		}
	}

	// task 6b gets a accountName and deletes the relevant account in the bank management system
	public boolean delete(String name){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(name);
		// complete this:

		// The bank's management system have an existing account with the same name
		if (toRemove != null){
			accountNumbersTree.remove(toRemove);
			namesTree.remove(toRemove);
			return true;
		}
		else
			return false;
	}

	// task 6c gets a numberAccount and deletes the relevant account in the bank management system
	public boolean delete(int accountNumber){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(accountNumber);
		// complete this:

		// The bank's management system have an existing account with the same number
		if (toRemove != null){
			accountNumbersTree.remove(toRemove);
			namesTree.remove(toRemove);
			return true;
		}
		else
			return false;
	}

	// task 6d the method deposits the amount to the accountNumber that she got
	public boolean depositMoney(int amount, int accountNumber){
		BankAccount depositTo = lookUp(accountNumber);
		// The bank's management system have an existing account with the same number
		if (depositTo != null & amount >= 0) {
			depositTo.depositMoney(amount);
			return true;
		}
		else
			return false;
	}

	//task 6e This method withdrawMoney the amount from the accountNumber that she got
	public boolean withdrawMoney(int amount, int accountNumber){
		BankAccount withdrawMoneyTo = lookUp(accountNumber);
		// The bank's management system have an existing account with the same number
		if (withdrawMoneyTo != null & amount >= 0 & amount <= withdrawMoneyTo.getBalance()) {
			withdrawMoneyTo.withdrawMoney(amount);
			return true;
		}
		else
			return false;
	}
}
