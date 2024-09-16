package com.ajwalker.module;



import com.ajwalker.controller.UserController;
import com.ajwalker.entity.User;
import com.ajwalker.repository.UserRepository;

import java.util.Optional;
import java.util.Scanner;

public class LoginMenu {
	private Scanner scanner = new Scanner(System.in);
	private UserController userController = UserController.getInstance();
	
	public Optional<User> loginModule(){
		return loginMenu();
	}
	
	private Optional<User> loginMenu() {
		System.out.print("Enter your username: ");
		String username = scanner.nextLine();
		Optional<User> user = userController.findByUsername(username);
		if(user.isEmpty()){
			System.out.println("Invalid username!");
			return Optional.empty();
		}
		System.out.print("Enter your password: ");
		String password = scanner.nextLine();
		if (checkUser(password, user)){
			//TODO: başka bir menüye aktarılsın!
			System.out.println("Login successful!");
			return user;
		}
		else{
			System.out.println("Incorrect username or password!");
			return Optional.empty();
		}
	}
	
	private boolean checkUser(String password, Optional<User> user) {
		return user.get().getPassword().equals(password);
	}
	
	private Optional<String> findByUsername() {
		System.out.println("Enter username: ");
		String username = scanner.nextLine();
		if (existsUsername(username)) return Optional.of(username);
		else return Optional.empty();
	}
	
	private boolean existsUsername(String username) {
		return false;
	}
}