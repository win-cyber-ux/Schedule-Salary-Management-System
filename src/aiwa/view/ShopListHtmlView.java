package aiwa.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import aiwa.entity.Shop;

public class ShopListHtmlView {

	public void showList(ArrayList<Shop> shops) {

		try {

			FileWriter fw = new FileWriter("shopList.html");

			fw.write("<!DOCTYPE html>\n");
			fw.write("<html lang='ja'>\n");
			fw.write("<head>\n");
			fw.write("<meta charset='UTF-8'>\n");
			fw.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n");
			fw.write("<title>店舗一覧 - SHIFT MATE</title>\n");
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
			int count = (shops != null) ? shops.size() : 0;
			fw.write("<span class='user-pill'>🏪 " + count + " 件登録済み</span>\n");
			fw.write("</div>\n");
			fw.write("</header>\n");

			fw.write("<main class='container'>\n");

			fw.write("<div class='dashboard-heading'>\n");
			fw.write("<h1>店舗一覧</h1>\n");
			fw.write("<p>登録されているアルバイト先一覧です</p>\n");
			fw.write("</div>\n");

			if (shops == null || shops.isEmpty()) {

				fw.write("<div class='empty-state'>\n");
				fw.write("<div style='font-size: 48px; margin-bottom: 12px;'>🏪</div>\n");
				fw.write("<h3 style='margin-bottom: 8px;'>店舗が登録されていません</h3>\n");
				fw.write(
						"<p style='color: var(--text-muted); font-size: 13px; margin-bottom: 20px;'>Consoleから新しい店舗を登録してください。</p>\n");
				fw.write("<a href='shopRegister.html' class='btn'>＋ 店舗登録（Console案内）</a>\n");
				fw.write("</div>\n");

			} else {

				for (Shop shop : shops) {
					fw.write("<div class='card'>\n");
					fw.write("<div class='card-header'>\n");
					fw.write("<h3 style='font-size: 18px; font-weight: 700;'>🏪 " + shop.getName() + "</h3>\n");
					fw.write("<div style='display: flex; gap: 8px;'>\n");
					fw.write("</div>\n");
					fw.write("</div>\n");

					fw.write("<div class='info-list'>\n");
					fw.write("<div class='info-row'>\n");
					fw.write("<span>基本時給</span>\n");
					fw.write("<strong style='font-size: 18px; color: var(--primary-blue);'>¥"
							+ String.format("%,.0f", shop.getHourlyRate()) + " / 時</strong>\n");
					fw.write("</div>\n");
					fw.write("</div>\n");

					fw.write("</div>\n");
				}
			}

			fw.write("<div style='display: flex; gap: 12px; margin-top: 24px;'>\n");
			fw.write("<a href='index.html' class='btn btn-secondary' style='flex: 1;'>← ホームに戻る</a>\n");
			fw.write("<a href='shopRegister.html' class='btn' style='flex: 1;'>＋ 店舗登録</a>\n");
			fw.write("</div>\n");

			fw.write("</main>\n");

			fw.write("<footer class='footer'>\n");
			fw.write("<p>&copy; SHIFT MATE - アルバイト勤務・給与管理システム</p>\n");
			fw.write("</footer>\n");

			fw.write("</body>\n");
			fw.write("</html>\n");

			fw.close();

			System.out.println("shopList.html を作成しました。");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}