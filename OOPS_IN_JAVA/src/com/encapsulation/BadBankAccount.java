package com.encapsulation;

class BankAccount {
	public String accountHolderName;
	public int accountNumber;
	public int balance;

	@Override
	public String toString() {
		return "BankAccount [accountHolderName=" + accountHolderName + ", accountNumber=" + accountNumber + ", balance="
				+ balance + "]";
	}

}

public class BadBankAccount {
	public static void main(String[] args) {
		BankAccount b1 = new BankAccount();
		b1.accountHolderName = "Sam";
		b1.accountNumber = -12345;
		b1.balance = -123265;
		System.out.println(b1);
	}

}
