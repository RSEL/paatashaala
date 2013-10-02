package com.ramselabs.education.security;

import java.io.File;
import java.io.IOException;

public class Manager {
	public static void main(String[] args) {
		File f1=new File("Test.txt");
		try {
			System.out.println(f1.createNewFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
