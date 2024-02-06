package com.openclassrooms.paymybuddy;

import com.openclassrooms.paymybuddy.model.*;
import com.openclassrooms.paymybuddy.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@SpringBootApplication
public class PaymybuddyApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private AppAccountService appAccountService;

	@Autowired
	private DepositService depositService;

	@Autowired
	private TransfertService transfertService;

	public static void main(String[] args) {
		SpringApplication.run(PaymybuddyApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) {

		Iterable<User> users = userService.getUsers();
		users.forEach(user -> System.out.println(user.getFirstname()));

		User userToAdd = new User("Thisfirstname", "Lastname", LocalDate.of(2024, 06, 06), "555-555-555", "Toto street, 67009 Ville");
		userService.addUser(userToAdd);

		Iterable<User> usersWithUseradded = userService.getUsers();
		usersWithUseradded.forEach(userWithUseradded -> System.out.println(userWithUseradded.getFirstname()));

		Long idUserToDelete = userToAdd.getUserId();
		userService.deleteUserById(idUserToDelete);

		Iterable<User> usersTwo = userService.getUsers();
		usersTwo.forEach(user -> System.out.println(user.getFirstname()));

		Iterable<UserAccount> userAccounts = userAccountService.getUserAccounts();
		userAccounts.forEach(userAccount -> System.out.println(userAccount.getEmail()));

		Iterable<AppAccount> appAccounts = appAccountService.getAppAccounts();
		appAccounts.forEach(appAccount -> System.out.println(appAccount.getBalance()));

		Iterable<Deposit> deposits = depositService.getDeposits();
		deposits.forEach(deposit -> System.out.println(deposit.getAmount()));

		Iterable<Transfert> transferts = transfertService.getTransferts();
		transferts.forEach(transfert -> System.out.println(transfert.getAmount()));

	}
}
