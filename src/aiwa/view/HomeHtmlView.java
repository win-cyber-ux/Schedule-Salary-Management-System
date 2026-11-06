package aiwa.view;

import java.io.FileWriter;
import java.io.IOException;

public class HomeHtmlView {

	public void showHome(int userid) {

		try {

			FileWriter fw = new FileWriter("index.html");

			fw.write("<!DOCTYPE html>\n");
			fw.write("<html lang='ja'>\n");
			fw.write("<head>\n");
			fw.write("<meta charset='UTF-8'>\n");
			fw.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n");
			fw.write("<title>SHIFT MATE - アルバイト勤務・給与管理システム</title>\n");
			fw.write("<link rel='stylesheet' href='style.css'>\n");
			fw.write("</head>\n");

			fw.write("<body>\n");

			// Header
			fw.write("<header class='top-header'>\n");
			fw.write("<div class='header-content'>\n");
			fw.write("<div class='brand'>\n");
			fw.write("<div class='brand-logo'>\n");
			fw.write("<div class='brand-pill'></div>\n");
			fw.write("<div class='brand-pill light'></div>\n");
			fw.write("</div>\n");
			fw.write("<div>\n");
			fw.write("<div class='brand-title'>SHIFT MATE</div>\n");
			fw.write("<div class='sub-en'>アルバイト勤務・給与管理システム</div>\n");
			fw.write("</div>\n");
			fw.write("</div>\n");
			fw.write("<span class='user-pill'>👤 ユーザーID: " + userid + "</span>\n");
			fw.write("</div>\n");
			fw.write("</header>\n");

			// Main Container
			fw.write("<main class='container'>\n");

			// Dashboard Heading
			fw.write("<div class='dashboard-heading'>\n");
			fw.write("<h1>ダッシュボード</h1>\n");
			fw.write("<p>アルバイトの店舗・勤務・給与情報を一括管理します</p>\n");
			fw.write("</div>\n");

			// Primary Cards Grid
			fw.write("<div class='dash-grid-top'>\n");

			// 1. Shop List
			fw.write("<a href='shopList.html' class='dash-card'>\n");
			fw.write("<div class='card-body-left'>\n");
			fw.write("<div class='icon-box blue'>🏪</div>\n");
			fw.write("<div class='card-info'>\n");
			fw.write("<h2>店舗一覧</h2>\n");
			fw.write("<p>登録済みのアルバイト先を\n確認・管理</p>\n");
			fw.write("</div>\n");
			fw.write("</div>\n");
			fw.write("<div class='circle-btn'>→</div>\n");
			fw.write("</a>\n");

			// 2. Schedule
			fw.write("<a href='scheduleList.html' class='dash-card'>\n");
			fw.write("<div class='card-body-left'>\n");
			fw.write("<div class='icon-box teal'>📅</div>\n");
			fw.write("<div class='card-info'>\n");
			fw.write("<h2>勤務スケジュール</h2>\n");
			fw.write("<p>登録済みのシフトや勤務実績を\n確認</p>\n");
			fw.write("</div>\n");
			fw.write("</div>\n");
			fw.write("<div class='circle-btn'>→</div>\n");
			fw.write("</a>\n");

			// 3. Salary
			fw.write("<a href='salary.html' class='dash-card'>\n");
			fw.write("<div class='card-body-left'>\n");
			fw.write("<div class='icon-box amber'>¥</div>\n");
			fw.write("<div class='card-info'>\n");
			fw.write("<h2>給与確認</h2>\n");
			fw.write("<p>今月の予想給与・実績給与を\n算出</p>\n");
			fw.write("</div>\n");
			fw.write("</div>\n");
			fw.write("<div class='circle-btn'>→</div>\n");
			fw.write("</a>\n");

			fw.write("</div>\n");

			// Secondary Register Cards Grid
			fw.write("<div class='dash-grid-bottom'>\n");

			// 4. Shop Register
			fw.write("<a href='shopRegister.html' class='register-dash-card'>\n");
			fw.write("<div class='card-body-left'>\n");
			fw.write("<div class='plus-icon-box'>＋</div>\n");
			fw.write("<div class='card-info'>\n");
			fw.write("<h2>店舗登録</h2>\n");
			fw.write("<p>新しいアルバイト先を追加\n(Console案内)</p>\n");
			fw.write("</div>\n");
			fw.write("</div>\n");
			fw.write("<div class='circle-btn'>→</div>\n");
			fw.write("</a>\n");

			// 5. Work Register
			fw.write("<a href='scheduleRegister.html' class='register-dash-card'>\n");
			fw.write("<div class='card-body-left'>\n");
			fw.write("<div class='plus-icon-box'>＋</div>\n");
			fw.write("<div class='card-info'>\n");
			fw.write("<h2>勤務登録</h2>\n");
			fw.write("<p>新しいシフトを追加\n(Console案内)</p>\n");
			fw.write("</div>\n");
			fw.write("</div>\n");
			fw.write("<div class='circle-btn'>→</div>\n");
			fw.write("</a>\n");

			fw.write("</div>\n");

			fw.write("</main>\n");

			// Footer
			fw.write("<footer class='footer'>\n");
			fw.write("<p>&copy; SHIFT MATE - アルバイト勤務・給与管理システム</p>\n");
			fw.write("</footer>\n");

			
			fw.write("</body>\n");
			fw.write("</html>\n");

			fw.close();

			System.out.println("index.html を作成しました。");
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}