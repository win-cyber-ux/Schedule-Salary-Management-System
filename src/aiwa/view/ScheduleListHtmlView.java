package aiwa.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import aiwa.entity.Schedule;
import aiwa.entity.Shop;
import aiwa.model.ShopModel;

public class ScheduleListHtmlView {

	public void showList(ArrayList<Schedule> schedules) {

		ShopModel shopModel = new ShopModel();

		try {

			FileWriter fw = new FileWriter("scheduleList.html");

			fw.write("<!DOCTYPE html>\n");
			fw.write("<html lang='ja'>\n");
			fw.write("<head>\n");
			fw.write("<meta charset='UTF-8'>\n");
			fw.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n");
			fw.write("<title>勤務スケジュール一覧 - SHIFT MATE</title>\n");
			fw.write("<link rel='stylesheet' href='style.css'>\n");
			fw.write("</head>\n");

			fw.write("<body>\n");

			fw.write("<header class='top-header'>\n");
			fw.write("<div class='header-content'>\n");
			fw.write("<div class='brand'>\n");
			fw.write(
					"<div class='brand-logo'><div class='brand-pill'></div><div class='brand-pill light'></div></div>\n");
			fw.write(
					"<div><div class='brand-title'>SHIFT MATE</div><div class='sub-en'>アルバイト勤務・給与管理システム</div></div>\n");
			fw.write("</div>\n");
			int count = (schedules != null) ? schedules.size() : 0;
			fw.write("<span class='user-pill'>📅 " + count + " 件のシフト</span>\n");
			fw.write("</div>\n");
			fw.write("</header>\n");

			fw.write("<main class='container'>\n");

			fw.write("<div class='dashboard-heading'>\n");
			fw.write("<h1>勤務スケジュール一覧</h1>\n");
			fw.write("<p>登録されている勤務日時および休憩時間です</p>\n");
			fw.write("</div>\n");

			if (schedules == null || schedules.isEmpty()) {

				fw.write("<div class='empty-state'>\n");
				fw.write("<div style='font-size: 48px; margin-bottom: 12px;'>📅</div>\n");
				fw.write("<h3 style='margin-bottom: 8px;'>登録されたスケジュールがありません</h3>\n");
				fw.write(
						"<p style='color: var(--text-muted); font-size: 13px; margin-bottom: 20px;'>Consoleから新しい勤務を登録してください。</p>\n");
				fw.write("<a href='scheduleRegister.html' class='btn'>＋ 勤務登録（Console案内）</a>\n");
				fw.write("</div>\n");

			} else {

				for (Schedule schedule : schedules) {
					Shop shop = shopModel.getByShopid(schedule.getShopid());
					fw.write(
							"<div class='card' style='display: flex; justify-content: space-between; align-items: center;'>\n");

					fw.write("<div>\n");
					fw.write("<h3 style='font-size: 18px; font-weight: 700; color: #0f172a;'>🗓️ "
							+ schedule.getWorkDate() + "</h3>\n");
					fw.write(
							"<div style='display: flex; gap: 16px; margin-top: 6px; font-size: 14px; color: var(--text-muted);'>\n");

					fw.write("<span>🏪 店舗ID: " + schedule.getShopid() + "</span>\n");

					fw.write("<span>🏪 店舗名: " + shop.getName() + "</span>\n");
					fw.write("<span>⏰ " + schedule.getStartTime() + " ～ " + schedule.getEndTime() + "</span>\n");
					fw.write("<span>☕ 休憩: " + schedule.getBreakMinutes() + "分</span>\n");
					fw.write("</div>\n");
					fw.write("</div>\n");

					fw.write("<div>\n");
					fw.write("<a href='scheduleEdit.html' class='btn btn-secondary' "
					        + "style='padding: 6px 14px; font-size: 12px;'>編集</a>\n");
					fw.write("</div>\n");

					fw.write("</div>\n");
				}
			}

			fw.write("<div style='display: flex; gap: 12px; margin-top: 24px;'>\n");
			fw.write("<a href='index.html' class='btn btn-secondary' style='flex: 1;'>← ホームに戻る</a>\n");
			fw.write("<a href='scheduleRegister.html' class='btn' style='flex: 1;'>＋ 勤務登録</a>\n");
			fw.write("</div>\n");

			fw.write("</main>\n");

			fw.write("<footer class='footer'>\n");
			fw.write("<p>&copy; SHIFT MATE - アルバイト勤務・給与管理システム</p>\n");
			fw.write("</footer>\n");

			fw.write("</body>\n");
			fw.write("</html>\n");

			fw.close();

			System.out.println("scheduleList.html を作成しました。");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}