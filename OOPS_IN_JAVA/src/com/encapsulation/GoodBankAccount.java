package com.encapsulation;

class BankAccount1 {
	private String accountHolderName;
	private int accountNumber;
	private int balance;

	public BankAccount1(String accountHolderName, int accountNumber, int balance) {
		this.accountHolderName = accountHolderName;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void deposit(int amount) {
		if (amount <= 0) {
			System.out.println("Amount should be greater than zero");
			return;
		} else {
			this.balance += amount;
		}
	}

	public int withDraw(int amount) {
		if(amount <=0 || amount>balance) {
			System.out.println("Invalid amount");
			return 0;
		}else {
			this.balance-=amount;
			return amount;
		}
	}
	public void printDetails() {
		System.out.println("Holder Name: "+this.accountHolderName);
		System.out.println("Account Number: "+this.accountNumber);
		System.out.println("Balance: "+this.balance);
	}

}

public class GoodBankAccount {
	public static void main(String[] args) {
		BankAccount1 b1 = new BankAccount1("Sam", 1234, 100000);
		b1.deposit(200);
		int res = b1.withDraw(1000);
		System.out.println("withdraw amount is: "+res);
		b1.printDetails();
	}

}
