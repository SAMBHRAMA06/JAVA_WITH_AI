package com.looping_statements;

import java.util.Scanner;

public class LoopingStatements4 {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int value = 0;

        do {
        	System.out.println("Enter value: ");
            value = sc.nextInt();
        } while (value<=0);

        System.out.println("Your entered value: "+value);
    }
}