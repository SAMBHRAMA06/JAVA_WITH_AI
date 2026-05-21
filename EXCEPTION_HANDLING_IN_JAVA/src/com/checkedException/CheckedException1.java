package com.checkedException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CheckedException1 {

	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("abc.txt");
		pw.write("Hellow World!!>This is Java Programming");
		pw.close();
		System.out.println("Successfull");
	}
}
