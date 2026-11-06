package aiwa.view;

import java.io.FileWriter;
import java.io.IOException;

public class ShopRegisterHtmlView {

	public void showRegisterForm() {

		try {

			FileWriter fw = new FileWriter("shopRegister.html");

			fw.write("<!DOCTYPE html>\n");
			fw.write("<html lang='ja'>\n");
			fw.write("<head>\n");
			fw.write("<meta charset='UTF-8'>\n");
			fw.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n");
			fw.write("<title>店舗登録 - SHIFT MATE</title>\n");
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
			fw.write("<span class='user-pill'>🏪 店舗登録</span>\n");
			fw.write("</div>\n");
			fw.write("</header>\n");

			fw.write("<main class='container'>\n");

			fw.write("<div class='card'>\n");
			fw.write("<div class='card-header'>\n");
			fw.write("<h2 style='font-size: 20px; font-weight: 800;'>🏪 店舗の新規登録</h2>\n");
			fw.write("<span class='pill pill-warning'>Console入力モード</span>\n");
			fw.write("</div>\n");

			fw.write("<div class='console-notice'>\n");
			fw.write("<p><strong>💡 登録手順：</strong> 店舗名および基本時給の入力は <strong>Java Console</strong> から行ってください。</p>\n");
			fw.write("</div>\n");

			fw.write("<div class='info-list'>\n");
			fw.write("<div class='info-row'><span>入力項目 1</span><strong>店舗名（例: Cafe SHIFT MATE 渋谷店）</strong></div>\n");
			fw.write("<div class='info-row'><span>入力項目 2</span><strong>基本時給（例: 1200）</strong></div>\n");
			fw.write(
					"<div class='info-row'><span>入力ステータス</span><strong style='color: var(--primary-blue);'>Console待機中...</strong></div>\n");
			fw.write("</div>\n");

			fw.write("<div style='display: flex; gap: 12px; margin-top: 24px;'>\n");
			fw.write("<a href='index.html' class='btn btn-secondary' style='flex: 1;'>← ホームに戻る</a>\n");
			fw.write("<a href='shopList.html' class='btn' style='flex: 1;'>店舗一覧を確認する</a>\n");
			fw.write("</div>\n");

			fw.write("</div>\n");
			fw.write("</main>\n");

			fw.write("<footer class='footer'>\n");
			fw.write("<p>&copy; SHIFT MATE - アルバイト勤務・給与管理システム</p>\n");
			fw.write("</footer>\n");

			fw.write("</body>\n");
			fw.write("</html>\n");

			fw.close();

			System.out.println("shopRegister.html を作成しました。");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}