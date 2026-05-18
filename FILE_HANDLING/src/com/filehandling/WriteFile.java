package com.filehandling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	public static void main(String[] args) throws IOException {
		FileWriter fileWriter = new FileWriter("sample.txt");
		fileWriter.write("Hello world");
		fileWriter.write("this is java programming");
		fileWriter.write("\n");
		fileWriter.write("this is the end");
		fileWriter.close();
		System.out.println("Completed");

		BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("sample1.txt"));
		bufferWriter.write("Hello world");
		bufferWriter.write("this is java programming");
		bufferWriter.write("\n");
		bufferWriter.write("this is the end");
		bufferWriter.close();
		System.out.println("Completed");
	}

}
