package com.checkedException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CheckedException2 {

	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("Hello.txt");
			pw.write("Hellow World!!>This is Java Programming");
			System.out.println("done");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==============");
			System.out.println(e.getMessage());
			System.out.println("==============");
			System.out.println(e);
		} finally {
			pw.close();
			System.out.println("File is closed");
		}
		System.out.println("Successfull");
	}
}
