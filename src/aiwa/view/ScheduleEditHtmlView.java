package aiwa.view;

import java.io.FileWriter;
import java.io.IOException;

import aiwa.entity.Schedule;

public class ScheduleEditHtmlView {

	public void showEditForm(Schedule schedule) {

		try {

			FileWriter fw = new FileWriter("scheduleEdit.html");

			fw.write("<!DOCTYPE html>\n");
			fw.write("<html lang='ja'>\n");
			fw.write("<head>\n");
			fw.write("<meta charset='UTF-8'>\n");
			fw.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n");
			fw.write("<title>スケジュール編集 - SHIFT MATE</title>\n");
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
			fw.write("<span class='user-pill'>✏️ シフト編集</span>\n");
			fw.write("</div>\n");
			fw.write("</header>\n");

			fw.write("<main class='container'>\n");

			fw.write("<div class='card'>\n");
			fw.write("<div class='card-header'>\n");
			fw.write("<h2 style='font-size: 20px; font-weight: 800;'>✏️ 勤務スケジュールの確認・変更</h2>\n");
			fw.write("</div>\n");

			fw.write("<div class='info-list' style='margin-bottom: 24px;'>\n");
			fw.write("<div class='info-row'><span>店舗ID</span><strong>" + schedule.getShopid() + "</strong></div>\n");
			fw.write("<div class='info-row'><span>勤務日</span><strong>" + schedule.getWorkDate() + "</strong></div>\n");
			fw.write("<div class='info-row'><span>時間</span><strong>" + schedule.getStartTime() + " ～ "
					+ schedule.getEndTime() + "</strong></div>\n");
			fw.write("<div class='info-row'><span>休憩時間</span><strong>" + schedule.getBreakMinutes()
					+ " 分</strong></div>\n");
			fw.write("</div>\n");

			fw.write("<div style='display: flex; gap: 12px;'>\n");
			fw.write("<a href='scheduleList.html' class='btn btn-secondary' style='flex: 1;'>← 戻る</a>\n");
			fw.write("</div>\n");

			fw.write("</div>\n");
			fw.write("</main>\n");

			fw.write("<footer class='footer'>\n");
			fw.write("<p>&copy; SHIFT MATE - アルバイト勤務・給与管理システム</p>\n");
			fw.write("</footer>\n");

			fw.write("</body>\n");
			fw.write("</html>\n");

			fw.close();

			System.out.println("scheduleEdit.html を作成しました。");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}