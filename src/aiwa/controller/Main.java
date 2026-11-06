package aiwa.controller;

import java.util.Scanner;
import aiwa.entity.User;
import aiwa.model.UserModel;


public class Main {

	public static void main(String[] args) {

		UserModel usermodel = new UserModel();

		Scanner scan = new Scanner(System.in);
		Scanner scanStr = new Scanner(System.in);

		System.out.println("================================");
		System.out.println("SHIFT MATE - アルバイト勤務・給与管理システム");
		System.out.println("================================");

		while (true) {

			System.out.println();
			System.out.println("===== MENU =====");
			System.out.println("1: Login");
			System.out.println("2: Register");
			System.out.println("0: Exit");

			System.out.print("番号を入力してください：");

			int menu = scan.nextInt();
			scan.nextLine();

			if (menu == 1) {

				User user = new User();

				System.out.println();
				System.out.println("===== LOGIN =====");

				while (true) {

					System.out.print("email：");

					String email = scan.nextLine();

					if (email.contains("@")) {

						user.setEmail(email);

						break;

					} else {

						System.out.println("正しいメールアドレスを入力してください。");
					}
				}

				System.out.print("password：");

				String password = scan.nextLine();

				user.setPassword(password);

				boolean result = usermodel.login(user);

				if (result) {

					System.out.println();
					System.out.println("Login Successful!");

					int userid = user.getId();

					System.out.println("Login User ID: " + userid);

					HtmlGenerator generator = new HtmlGenerator();
					generator.generateAllHtml(user);

					while (true) {

						System.out.println();
						System.out.println("==============================");
						System.out.println("          HOME");
						System.out.println("==============================");

						System.out.println("1. 店舗");
						System.out.println("2. 勤務時間");
						System.out.println("3. 給与");
						System.out.println("0. Logout");

						System.out.println("番号を入力してください：");

						int homeMenu = scan.nextInt();

						if (homeMenu == 1) {

							ShopController shopController = new ShopController();
							shopController.showMenu(user);

						} else if (homeMenu == 2) {

							ScheduleController scheduleController = new ScheduleController();
							scheduleController.showMenu(user);

						} else if (homeMenu == 3) {

							SalaryController salaryController = new SalaryController();
							salaryController.showMenu(user);

						} else if (homeMenu == 0) {

							System.out.println("Logout");

							break;

						} else {

							System.out.println("正しい番号を入力してください。");
						}
					}

				} else {

					System.out.println(
							"Login failed.");
				}

			} else if (menu == 2) {

				User user = new User();

				System.out.println();
				System.out.println("===== REGISTER =====");
				System.out.print("username：");

				String username = scanStr.nextLine();

				user.setUsername(username);

				while (true) {

					System.out.print("email：");

					String email = scanStr.nextLine();

					if (email.contains("@")) {

						user.setEmail(
								email);

						break;

					} else {

						System.out.println("正しいメールアドレスを入力してください。");
					}
				}

				System.out.print("password：");

				String password = scanStr.nextLine();

				user.setPassword(password);

				boolean result = usermodel.register(user);

				if (result) {

					System.out.println("Register Successful");

				} else {

					System.out.println("Register Failed");
				}

			} else if (menu == 0) {

				System.out.println("終了します。");

				break;

			} else {

				System.out.println("正しい番号を入力してください。");
			}
		}

		scan.close();
		scanStr.close();
	}
	
}
