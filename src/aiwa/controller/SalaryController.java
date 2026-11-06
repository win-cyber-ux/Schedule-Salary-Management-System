package aiwa.controller;


import java.util.ArrayList;

import aiwa.entity.Schedule;
import aiwa.entity.Shop;
import aiwa.entity.User;
import aiwa.model.SalaryModel;
import aiwa.model.ShopModel;
import aiwa.view.SalaryHtmlView;

public class SalaryController {


	SalaryModel salaryModel = new SalaryModel();
	ShopModel shopModel = new ShopModel();

	public void showMenu(User user) {

		ArrayList<String> months =
				salaryModel.getAvailableMonths(user.getId());

		SalaryHtmlView salaryView = new SalaryHtmlView();

		if (months.isEmpty()) {

		    salaryView.showSalaryMenu(months);

		    System.out.println("勤務データがありません。");
		    return;
		}
		
		salaryView.showSalaryMenu(months);

		for (String month : months) {

			double salary = salaryModel.getMonthlySalary(
					user.getId(),
					month);

			ArrayList<Shop> shops =
					shopModel.getAllByUserId(user.getId());

			ArrayList<Double> shopSalaries =
					new ArrayList<Double>();

			for (Shop shop : shops) {

				double shopSalary =
						salaryModel.getShopMonthlySalary(
								user.getId(),
								shop.getId(),
								month);

				shopSalaries.add(shopSalary);

				ArrayList<Schedule> schedules =
						salaryModel.getShopSchedules(
								user.getId(),
								shop.getId(),
								month);

				salaryView.showShopDetail(
						month,
						shop,
						schedules);
			}

			salaryView.showSalary(
					month,
					salary,
					shops,
					shopSalaries);
		}

		System.out.println("給与HTMLを作成しました。");
	}
}
