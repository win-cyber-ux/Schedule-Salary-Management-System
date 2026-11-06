package aiwa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import aiwa.entity.Schedule;
import aiwa.entity.Shop;
import aiwa.entity.User;
import aiwa.model.ScheduleModel;
import aiwa.model.ShopModel;
import aiwa.view.ScheduleListHtmlView;

public class ScheduleController {

	Scanner scan = new Scanner(System.in);
	Scanner scanStr = new Scanner(System.in);

	ScheduleModel scheduleModel = new ScheduleModel();
	ShopModel shopModel = new ShopModel();

	public void showMenu(User user) {
		while (true) {

			System.out.println();
			System.out.println("==============================");

			System.out.println("        勤務時間 MENU");

			System.out.println("==============================");

			System.out.println("1. スケジュール登録");
			System.out.println("2. スケジュール一覧");
			System.out.println("3. スケジュール編集");
			System.out.println("4. スケジュール削除");
			System.out.println("0. 戻る");

			System.out.print("番号を入力してください：");
			int scheduleMenu = scan.nextInt();

			if (scheduleMenu == 1) {

				System.out.println();
				System.out.println("===== スケジュール登録 =====");

				ArrayList<Shop> shops = shopModel.getAllByUserId(user.getId());

				if (shops.isEmpty()) {

					System.out.println("先に店舗を登録してください。");

				} else {

					System.out.println();
					System.out.println("===== 店舗を選択 =====");

					for (Shop shop : shops) {

						System.out.println(
								"ID: "
										+ shop.getId()
										+ " / 店舗: "
										+ shop.getName());
					}

					System.out.print("店舗ID：");
					int shopid = scan.nextInt();

					if (!shopModel.existsShop(shopid, user.getId())) {

						System.out.println("この店舗IDは登録されていません。");

					} else {

						System.out.print("勤務日（例：2026-09-27）：");
						String dateInput = scanStr.nextLine();

						String workDate = normalizeDate(dateInput);

						System.out.print("開始時間 (HH:mm)：");
						String startTime = scanStr.nextLine();

						System.out.print("終了時間 (HH:mm)：");
						String endTime = scanStr.nextLine();

						System.out.print("休憩時間（分）：");
						int breakMinutes = scan.nextInt();

						Schedule schedule = new Schedule();

						schedule.setUserid(user.getId());
						schedule.setShopid(shopid);
						schedule.setWorkDate(workDate);
						schedule.setStartTime(startTime);
						schedule.setEndTime(endTime);
						schedule.setBreakMinutes(breakMinutes);

						boolean scheduleResult = scheduleModel.register(schedule);

						if (scheduleResult) {
							System.out.println("スケジュール登録 OK！");
							 HtmlGenerator generator = new HtmlGenerator();
							 generator.generateAllHtml(user);
						} else {
							System.out.println("スケジュール登録 FAIL");
						}
					}
				}
			} else if (scheduleMenu == 2) {

				System.out.println();
				System.out.println("===== スケジュール一覧 =====");

				ArrayList<Schedule> schedules = scheduleModel.getAllByUserId(user.getId());

				if (schedules.isEmpty()) {

					System.out.println("スケジュールがありません。");

				}
				ScheduleListHtmlView scheduleList = new ScheduleListHtmlView();

				scheduleList.showList(schedules);

			} else if (scheduleMenu == 3) {

				ArrayList<Schedule> editSchedules = scheduleModel.getAllByUserId(user.getId());

				if (editSchedules.size() == 0) {

					System.out.println("スケジュールがありません。");
					break;
				}

				System.out.println();
				System.out.println("===== スケジュール編集 =====");

				for (Schedule schedule : editSchedules) {

					System.out.println(
							"ID: " + schedule.getId()
									+ " / 店舗ID: " + schedule.getShopid()
									+ " / 日付: " + schedule.getWorkDate()
									+ " / "
									+ schedule.getStartTime()
									+ " ～ "
									+ schedule.getEndTime());
				}

				System.out.print("編集するIDを入力してください：");

				int editId = scan.nextInt();

				System.out.println();

				Schedule editSchedule = null;

				for (Schedule schedule : editSchedules) {

					if (schedule.getId() == editId) {

						editSchedule = schedule;

						break;
					}
				}

				if (editSchedule == null) {

					System.out.println("指定されたスケジュールがありません。");
					break;
				}

				System.out.print("店舗ID：");
				int shopid = scan.nextInt();

				System.out.print("勤務日 (YYYY-MM-DD)：");
				String workDate = scanStr.nextLine();

				System.out.print("開始時間 (HH:mm)：");
				String startTime = scanStr.nextLine();

				System.out.print("終了時間 (HH:mm)：");
				String endTime = scanStr.nextLine();

				System.out.print("休憩時間（分）：");
				int breakMinutes = scan.nextInt();
				System.out.println();

				editSchedule.setShopid(shopid);
				editSchedule.setWorkDate(workDate);
				editSchedule.setStartTime(startTime);
				editSchedule.setEndTime(endTime);
				editSchedule.setBreakMinutes(breakMinutes);

				scheduleModel.update(editSchedule);

				System.out.println();
				System.out.println("スケジュールを更新しました。");
				 HtmlGenerator generator = new HtmlGenerator();
				 generator.generateAllHtml(user);

			} else if (scheduleMenu == 4) {

				ArrayList<Schedule> deleteSchedules = scheduleModel.getAllByUserId(user.getId());

				if (deleteSchedules.size() == 0) {

					System.out.println();
					System.out.println("削除できるスケジュールがありません。");
					break;
				}

				System.out.println();
				System.out.println("===== スケジュール削除 =====");

				for (Schedule schedule : deleteSchedules) {

					System.out.println(
							"ID: " + schedule.getId()
									+ " / 店舗ID: " + schedule.getShopid()
									+ " / 日付: " + schedule.getWorkDate()
									+ " / "
									+ schedule.getStartTime()
									+ " ～ "
									+ schedule.getEndTime()
									+ " / 休憩: "
									+ schedule.getBreakMinutes()
									+ "分");
				}

				System.out.print("削除するIDを入力してください：");
				int deleteId = scan.nextInt();
				scan.nextLine();

				boolean found = false;

				for (Schedule schedule : deleteSchedules) {

					if (schedule.getId() == deleteId) {

						found = true;
						break;
					}
				}

				if (!found) {

					System.out.println("指定されたスケジュールがありません。");

					break;
				}

				scheduleModel.delete(deleteId, user.getId());

				System.out.println();
				System.out.println("スケジュールを削除しました。");
				
				 HtmlGenerator generator = new HtmlGenerator();
				    generator.generateAllHtml(user);
			} else if (scheduleMenu == 0) {

				break;

			} else {

				System.out.println(
						"正しい番号を入力してください。");
			}
		}

	}
	
	private String normalizeDate(String input) {

	    input = input.trim().replace("/", "-");

	    LocalDate date = LocalDate.parse(
	        input,
	        DateTimeFormatter.ofPattern("uuuu-M-d")
	    );

	    return date.format(
	        DateTimeFormatter.ofPattern("yyyy-MM-dd")
	    );
	}

}
