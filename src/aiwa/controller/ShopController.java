package aiwa.controller;

import java.util.ArrayList;
import java.util.Scanner;

import aiwa.entity.Shop;
import aiwa.entity.User;
import aiwa.model.ScheduleModel;
import aiwa.model.ShopModel;
import aiwa.model.WageRuleModel;
import aiwa.view.ShopListHtmlView;
import aiwa.view.ShopRegisterHtmlView;

public class ShopController {

	Scanner scan = new Scanner(System.in);
	Scanner scanStr = new Scanner(System.in);

	ShopModel shopModel = new ShopModel();

	public void showMenu(User user) {

		while (true) {

			System.out.println("===== SHOP MENU =====");

			System.out.println("1. 店舗登録");

			System.out.println("2. 店舗一覧");

			System.out.println("3. 店舗編集");

			System.out.println("4. 店舗削除");
			System.out.println("5. 給与ルール");

			System.out.println("0. 戻る");

			System.out.print("番号を入力してください：");

			int shopMenu = scan.nextInt();

			if (shopMenu == 1) {

				System.out.println();
				System.out.println("===== 店舗登録 =====");

				ShopRegisterHtmlView shopView = new ShopRegisterHtmlView();

				shopView.showRegisterForm();

				Shop shop = new Shop();

				shop.setUserid(user.getId());

				System.out.println("User ID: " + user.getId());

				System.out.print("店舗名：");

				String shopName = scanStr.nextLine();

				shop.setName(shopName);

				System.out.print("\n時給：");

				double hourlyRate = scan.nextDouble();

				shop.setHourlyRate(hourlyRate);

				boolean shopResult = shopModel.register(
						shop);

				if (shopResult) {

					System.out.println("店舗登録 OK！");
					
					 HtmlGenerator generator = new HtmlGenerator();
					    generator.generateAllHtml(user);

				} else {

					System.out.println("店舗登録 FAIL");
				}
			} else if (shopMenu == 2) {

				System.out.println();
				System.out.println("===== 店舗一覧 =====");

				ArrayList<Shop> shops = shopModel.getAllByUserId(user.getId());

				if (shops.isEmpty()) {

					System.out.println("店舗がありません。");

				}

				ShopListHtmlView shopList = new ShopListHtmlView();

				shopList.showList(shops);

			} else if (shopMenu == 3) {

				System.out.println();
				System.out.println("===== 店舗編集 =====");

				ArrayList<Shop> shops = shopModel.getAllByUserId(user.getId());

				if (shops.isEmpty()) {
					System.out.println("店舗がありません。");

				} else {

					for (Shop shop : shops) {

						System.out.println("ID: " + shop.getId() + " / 店舗名: " + shop.getName()
								+ " / 時給: ¥" + shop.getHourlyRate());
					}

					System.out.print("編集する店舗ID：");

					int id = scan.nextInt();

					System.out.println("新しい店舗名：");

					String newName = scanStr.nextLine();

					System.out.print("新しい時給：");

					double newRate = scan.nextDouble();

					Shop shop = new Shop();

					shop.setId(id);

					shop.setUserid(user.getId());

					shop.setName(newName);

					shop.setHourlyRate(newRate);

					boolean resultUpdate = shopModel.update(shop);

					if (resultUpdate) {

						System.out.println("店舗を更新しました。");
						
						 HtmlGenerator generator = new HtmlGenerator();
						    generator.generateAllHtml(user);

					} else {

						System.err.println("店舗の更新に失敗しました。");
					}
				}
			}else if (shopMenu == 4) {

				System.out.println();

				System.out.println("===== 店舗削除 =====");

				ArrayList<Shop> shops = shopModel.getAllByUserId(user.getId());

				if (shops.isEmpty()) {

					System.out.println("店舗がありません。");

				} else {

					for (Shop shop : shops) {

						System.out.println("ID: " + shop.getId()
								+ " / 店舗名: "
								+ shop.getName()
								+ " / 時給: ¥"
								+ shop.getHourlyRate());
					}

					System.out.print("削除する店舗ID：");

					int id = scan.nextInt();

					// Scheduleを先に削除
					ScheduleModel scheduleModel = new ScheduleModel();

					scheduleModel.deleteByShopId(
							id,
							user.getId());
					
					WageRuleModel wageRuleModel = new WageRuleModel();
					wageRuleModel.deleteByShopId(id);

					// Shopを削除
					boolean resultDelete = shopModel.delete(
							id,
							user.getId());

					if (resultDelete) {

						System.out.println("店舗を削除しました。");
						
						 HtmlGenerator generator = new HtmlGenerator();
						    generator.generateAllHtml(user);

					} else {

						System.err.println("店舗の削除に失敗しました。");
					}
				}
			}else if (shopMenu == 5) {

			    WageRuleController wageRuleController =
			            new WageRuleController();

			    wageRuleController.showMenu(user);

			}else if (shopMenu == 0) {

				break;

			} else {

				System.out.println("正しい番号を入力してください。");
			}
		}
	}

}
