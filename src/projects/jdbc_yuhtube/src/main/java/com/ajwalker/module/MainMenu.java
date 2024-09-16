package com.ajwalker.module;

import com.ajwalker.entity.User;

import java.util.Optional;
import java.util.Scanner;

public class MainMenu {
	private static Optional<User> user = Optional.empty();
	private static LoginMenu loginMenu = new LoginMenu();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void mainMenu(){
		int opt;
		do{
			if (user.isEmpty()) opt = anonymousMainMenu();
			else opt = userMainMenu();
		} while(opt != 0);
		System.out.println(opt);
	}
	public static int anonymousMainMenu(){
		System.out.println("""
				                   YuhTube
				                   Main Menu
				                   1. Login
				                   2. Register
				                   3. Watch Videos
				                   0. Exit
				                   
				                   """);
		return mainMenuOptions(choice());
	}
	
	private static int mainMenuOptions(int choice) {
		switch (choice){
			case 1:
				user = new LoginMenu().loginModule();
				break;
			case 2:
				new RegisterMenu().register();
				break;
			case 3:
				user = new VideoModule().videoModule(user);
				break;
			case 0:
				System.out.println("Have a nice day!");
				break;
				default:
					System.out.println("Invalid option!");
		}
		
		return choice;
	}
	
	public static int choice() {
		while (true) {
			try {
				System.out.print("Enter int value> ");
				int i = scanner.nextInt();
				scanner.nextLine();
				return i;
			}
			catch (Exception e) {
				System.out.println("invalid value");
				scanner.nextLine();
			}
		}
	}
	
	private static int userMainMenu() {
		System.out.println("""
				                   YuhTube
				                   Main Menu
				                   1. Logout
				                   2. Watch Videos
				                   0. Exit
				                   
				                   """);
		return userMainMenuOptions(choice());
	}
	
	private static int userMainMenuOptions(int choice) {
		switch (choice){
			case 1:
				user = Optional.empty();
				break;
			case 2:
				user = (new VideoModule()).videoModule(user);
				break;
			case 0:
				System.out.println("Have a nice day!");
				break;
			default:
				System.out.println("Invalid option!");
		}
		
		return choice;
	}
	
}