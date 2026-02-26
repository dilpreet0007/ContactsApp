package com.Main;
import java.util.*;

import com.userregistration.UserBuilder;
import com.userregistration.UserRegistration;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter your Name: ");
		String name = sc.nextLine();
		System.out.print("Enter your UserName: ");
		String userName = sc.nextLine();
		System.out.print("Enter your Password: ");
		String password = sc.nextLine();
		System.out.print("Enter your Email: ");
		String email = sc.nextLine();
		System.out.print("Enter your Registration type Free/Premium: ");
		String type = sc.nextLine();
		
		UserRegistration user = new UserBuilder().setName(name)
				.setUserName(userName)
				.setEmail(email)
				.setPassword(password).setType(type).create();
		
		System.out.print(user.getName());

	}

}
