package banking;

import java.util.LinkedHashMap;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts;
	
	static long accountNumber=0L;
	
	public Bank() {
		accountNumber=0L;
		accounts = new LinkedHashMap<>();
	}

	private Account getAccount(Long accountNumber) {
		return this.accounts.get(accountNumber);
		
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		accountNumber = accountNumber+1;
		CommercialAccount account = new CommercialAccount(company, accountNumber, pin, startingDeposit);
		accounts.put(accountNumber, account);
        return account.getAccountNumber();
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		accountNumber = accountNumber+1;
		ConsumerAccount account = new ConsumerAccount(person, accountNumber, pin, startingDeposit);
		accounts.put(accountNumber, account);
		return account.getAccountNumber();
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
		return this.getAccount(accountNumber).validatePin(pin);
	}

	public double getBalance(Long accountNumber) {
		return accounts.get(accountNumber).getBalance();
	}

	public void credit(Long accountNumber, double amount) {
		accounts.get(accountNumber).creditAccount(amount);
	}

	public boolean debit(Long accountNumber, double amount) {
        return accounts.get(accountNumber).debitAccount(amount);
	}
}
