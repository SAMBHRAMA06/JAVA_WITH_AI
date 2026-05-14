package com.abstraction;

class UPI implements PaymentMethod {

	@Override
	public void paymentType() {
		System.out.println("Payments Type is UPI ");

	}

}

class CreditCard implements PaymentMethod {

	@Override
	public void paymentType() {
		System.out.println("Payments Type is CreditCard ");

	}

}

public class AbstractionUsingInterface {
	}
