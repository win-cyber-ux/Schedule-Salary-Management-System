package aiwa.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import aiwa.entity.Schedule;
import aiwa.entity.Shop;
import aiwa.model.SalaryModel;

public class SalaryHtmlView {

	
	// =========================================================
	// 月別給与 + 店舗別給与
	// =========================================================
	public void showSalary(
			String month,
			double salary,
			ArrayList<Shop> shops,
			ArrayList<Double> shopSalaries) {

		try {

			String htmlfile =
					"salary_" + month.replace("-", "_") + ".html";

			FileWriter fw = new FileWriter(htmlfile);

			// -------------------------------------------------
			// HTML開始
			// -------------------------------------------------
			fw.write("<!DOCTYPE html>\n");
			fw.write("<html lang='ja'>\n");

			fw.write("<head>\n");
			fw.write("<meta charset='UTF-8'>\n");
			fw.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n");
			fw.write("<title>給与確認 - SHIFT MATE</title>\n");
			fw.write("<link rel='stylesheet' href='style.css'>\n");
			fw.write("</head>\n");

			fw.write("<body>\n");

			// -------------------------------------------------
			// Header
			// -------------------------------------------------
			fw.write("<header class='top-header'>\n");
			fw.write("<div class='header-content'>\n");

			fw.write("<div class='brand'>\n");

			fw.write(
					"<div class='brand-logo'><div class='brand-pill'></div><div class='brand-pill light'></div></div>\n");

			fw.write(
					"<div><div class='brand-title'>SHIFT MATE</div><div class='sub-en'>アルバイト勤務・給与管理システム</div></div>\n");

			fw.write("</div>\n");

			fw.write("<span class='user-pill'>💰 給与計算</span>\n");

			fw.write("</div>\n");
			fw.write("</header>\n");

			// -------------------------------------------------
			// Main
			// -------------------------------------------------
			fw.write("<main class='container'>\n");

			fw.write("<div class='dashboard-heading'>\n");
			fw.write("<h1>給与確認</h1>\n");
			fw.write("<p>登録された勤務時間・時給データを基に自動計算しています</p>\n");
			fw.write("</div>\n");


			// -------------------------------------------------
			// 全店舗の合計
			// -------------------------------------------------
			fw.write(
					"<div class='card' style='text-align: center; padding: 48px 24px; background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); color: #ffffff;'>\n");

			fw.write(
					"<span class='user-pill' style='background: rgba(255, 255, 255, 0.15); color: #ffffff; margin-bottom: 12px;'>対象月: "
							+ month + "</span>\n");

			fw.write(
					"<div style='font-size: 14px; color: #94a3b8;'>今月の給与合計</div>\n");

			fw.write(
					"<div style='font-size: 44px; font-weight: 800; color: #38bdf8; margin-top: 8px;'>¥"
							+ String.format("%,.0f", salary)
							+ "</div>\n");

			fw.write("</div>\n");


			// -------------------------------------------------
			// 店舗別給与
			// -------------------------------------------------
			fw.write("<div style='margin-top: 32px;'>\n");

			fw.write("<h2>🏪 店舗別給与</h2>\n");

			if (shops.isEmpty()) {

				fw.write(
						"<div class='card' style='text-align:center;'>店舗がありません。</div>\n");

			} else {

				for (int i = 0; i < shops.size(); i++) {

					Shop shop = shops.get(i);

					double shopSalary =
							shopSalaries.get(i);


					// Detail HTMLのファイル名
					String fileName =
							"salary_"
							+ month.replace("-", "_")
							+ "_shop_"
							+ shop.getId()
							+ ".html";


					// 店舗カード全体をクリック可能にする
					fw.write(
							"<a href='" + fileName
							+ "' style='text-decoration:none; color:inherit;'>\n");


					fw.write(
							"<div class='card' style='margin-top:16px; padding:24px; cursor:pointer;'>\n");


					// 店舗名
					fw.write(
							"<div style='font-size:20px; font-weight:700;'>🏪 "
							+ shop.getName()
							+ "</div>\n");


					// 店舗給与
					fw.write(
							"<div style='font-size:28px; font-weight:800; margin-top:10px;'>¥"
							+ String.format("%,.0f", shopSalary)
							+ "</div>\n");


					// 詳細リンク表示
					fw.write(
							"<div style='margin-top:8px; color:#64748b;'>詳細を見る →</div>\n");


					fw.write("</div>\n");

					fw.write("</a>\n");
				}
			}

			fw.write("</div>\n");


			// -------------------------------------------------
			// Home button
			// -------------------------------------------------
			fw.write("<div style='margin-top: 24px;'>\n");

			fw.write(
					"<a href='index.html' class='btn btn-secondary' style='width: 100%;'>← ホームに戻る</a>\n");

			fw.write("</div>\n");


			// -------------------------------------------------
			// Footer
			// -------------------------------------------------
			fw.write("</main>\n");

			fw.write("<footer class='footer'>\n");
			fw.write(
					"<p>&copy; SHIFT MATE - アルバイト勤務・給与管理システム</p>\n");
			fw.write("</footer>\n");

			fw.write("</body>\n");
			fw.write("</html>\n");


			fw.close();

			System.out.println(
					htmlfile + " を作成しました。");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}


	// =========================================================
	// 店舗別給与詳細
	// =========================================================

	public void showShopDetail(
			String month,
			Shop shop,
			ArrayList<Schedule> schedules) {

		try {

			String fileName =
					"salary_"
					+ month.replace("-", "_")
					+ "_shop_"
					+ shop.getId()
					+ ".html";

			FileWriter fw = new FileWriter(fileName);

			// =========================================
			// HTML開始
			// =========================================

			fw.write("<!DOCTYPE html>\n");
			fw.write("<html lang='ja'>\n");

			fw.write("<head>\n");
			fw.write("<meta charset='UTF-8'>\n");
			fw.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n");
			fw.write("<title>店舗給与 - SHIFT MATE</title>\n");
			fw.write("<link rel='stylesheet' href='style.css'>\n");
			fw.write("</head>\n");

			fw.write("<body>\n");

			// =========================================
			// Header
			// =========================================

			fw.write("<header class='top-header'>\n");
			fw.write("<div class='header-content'>\n");

			fw.write("<div class='brand'>\n");
			fw.write("<div class='brand-title'>SHIFT MATE</div>\n");
			fw.write("<div class='sub-en'>アルバイト勤務・給与管理システム</div>\n");
			fw.write("</div>\n");

			fw.write("<span class='user-pill'>💰 店舗給与</span>\n");

			fw.write("</div>\n");
			fw.write("</header>\n");

			// =========================================
			// Main
			// =========================================

			fw.write("<main class='container'>\n");

			fw.write("<div class='dashboard-heading'>\n");

			fw.write("<h1>" + month + "</h1>\n");

			fw.write("<p>🏪 " + shop.getName() + "</p>\n");

			fw.write("</div>\n");


			// =========================================
			// 店舗情報
			// =========================================

			fw.write("<div class='card' style='text-align:center;'>\n");

			fw.write("<div style='font-size:14px; color:#64748b;'>時給</div>\n");

			fw.write("<div style='font-size:32px; font-weight:800; margin-top:8px;'>¥"
					+ String.format("%,.0f", shop.getHourlyRate())
					+ "</div>\n");

			fw.write("</div>\n");


			// =========================================
			// 勤務一覧
			// =========================================

			fw.write("<div style='margin-top:32px;'>\n");

			fw.write("<h2>📅 勤務明細</h2>\n");


			double totalSalary = 0;

			int totalWorkMinutes = 0;


			if (schedules.isEmpty()) {

				fw.write(
						"<div class='card' style='text-align:center;'>"
						+ "この月の勤務データはありません。"
						+ "</div>\n");

			} else {

				for (Schedule schedule : schedules) {

					String startTime =
							schedule.getStartTime();

					String endTime =
							schedule.getEndTime();

					int breakMinutes =
							schedule.getBreakMinutes();


					// =====================================
					// 時間を分に変換
					// =====================================
SalaryModel salaryModel = new SalaryModel();
					int startMinutes = salaryModel.timeToMinutes(startTime);

					int endMinutes = salaryModel.timeToMinutes(endTime);


					// 日をまたぐ勤務
					if (endMinutes < startMinutes) {

						endMinutes += 24 * 60;
					}


					// 実働時間
					int workMinutes =
							endMinutes
							- startMinutes
							- breakMinutes;


					if (workMinutes < 0) {

						workMinutes = 0;
					}


					totalWorkMinutes += workMinutes;


					// =====================================
					// 給与計算
					// =====================================

					double workHours =
							workMinutes / 60.0;

				

					double dailySalary =
					        salaryModel.calculateSalary(
					                startTime,
					                endTime,
					                breakMinutes,
					                shop.getHourlyRate(),
					                shop.getId());

					totalSalary += dailySalary;


					// =====================================
					// 勤務カード
					// =====================================

					fw.write(
							"<div class='card' style='margin-top:16px;'>\n");


					fw.write(
							"<div style='font-size:18px; font-weight:700;'>"
							+ "📅 "
							+ schedule.getWorkDate()
							+ "</div>\n");


					fw.write(
							"<p style='margin-top:12px;'>"
							+ "⏰ "
							+ startTime
							+ " ～ "
							+ endTime
							+ "</p>\n");


					fw.write(
							"<p>☕ 休憩："
							+ breakMinutes
							+ "分</p>\n");


					fw.write(
							"<p>⌛ 実働："
							+ String.format("%.2f", workHours)
							+ "時間</p>\n");


					fw.write(
							"<p style='font-size:20px; font-weight:700;'>"
							+ "💰 給与：¥"
							+ String.format("%,.0f", dailySalary)
							+ "</p>\n");


					fw.write("</div>\n");
				}
			}


			fw.write("</div>\n");


			// =========================================
			// 店舗合計
			// =========================================

			double totalWorkHours =
					totalWorkMinutes / 60.0;


			fw.write(
					"<div class='card' style='margin-top:32px; text-align:center;'>\n");


			fw.write(
					"<div style='font-size:14px; color:#64748b;'>"
					+ "勤務時間合計"
					+ "</div>\n");


			fw.write(
					"<div style='font-size:28px; font-weight:800; margin-top:8px;'>"
					+ String.format("%.2f", totalWorkHours)
					+ "時間"
					+ "</div>\n");


			fw.write(
					"<div style='font-size:14px; color:#64748b; margin-top:20px;'>"
					+ "店舗給与合計"
					+ "</div>\n");


			fw.write(
					"<div style='font-size:40px; font-weight:800; margin-top:8px;'>¥"
					+ String.format("%,.0f", totalSalary)
					+ "</div>\n");


			fw.write("</div>\n");


			// =========================================
			// 戻るボタン
			// =========================================

			fw.write("<div style='margin-top:24px;'>\n");

			fw.write(
					"<a href='salary_"
					+ month.replace("-", "_")
					+ ".html' "
					+ "class='btn btn-secondary' "
					+ "style='width:100%;'>"
					+ "← 給与一覧へ</a>\n");

			fw.write("</div>\n");


			fw.write("</main>\n");


			// =========================================
			// Footer
			// =========================================

			fw.write("<footer class='footer'>\n");

			fw.write(
					"<p>&copy; SHIFT MATE - アルバイト勤務・給与管理システム</p>\n");

			fw.write("</footer>\n");


			fw.write("</body>\n");
			fw.write("</html>\n");


			fw.close();


			System.out.println(
					fileName + " を作成しました。");


		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	public void showSalaryMenu(ArrayList<String> months) {

	    try {

	        FileWriter fw = new FileWriter("salary.html");

	        fw.write("<!DOCTYPE html>");
	        fw.write("<html lang='ja'>");
	        fw.write("<head>");
	        fw.write("<meta charset='UTF-8'>");
	        fw.write("<title>給与確認 - SHIFT MATE</title>");
	        fw.write("<link rel='stylesheet' href='style.css'>");
	        fw.write("</head>");

	        fw.write("<body>");

	        fw.write("<main class='container'>");

	        fw.write("<div class='dashboard-heading'>");
	        fw.write("<h1>💰 給与確認</h1>");
	        fw.write("<p>確認したい月を選択してください。</p>");
	        fw.write("</div>");

	        if (months == null || months.isEmpty()) {

	            fw.write("<div class='empty-state' style='margin-top:32px;'>");

	            fw.write("<div style='font-size:48px; margin-bottom:12px;'>💰</div>");

	            fw.write("<h3 style='margin-bottom:8px;'>給与データがありません</h3>");

	            fw.write("<p style='color:var(--text-muted); font-size:13px; margin-bottom:20px;'>");
	            fw.write("まだ勤務記録が登録されていません。<br>");
	            fw.write("勤務時間を登録すると、給与を確認できます。");
	            fw.write("</p>");

	            fw.write("<a href='index.html' class='btn btn-secondary'>");
	            fw.write("← ホームに戻る");
	            fw.write("</a>");

	            fw.write("</div>");

	        } else {

	            for (String month : months) {

	                String fileName =
	                        "salary_" + month.replace("-", "_") + ".html";

	                fw.write("<a href='" + fileName
	                        + "' style='text-decoration:none; color:inherit;'>");

	                fw.write("<div class='card' "
	                        + "style='margin-top:16px; padding:24px;'>");

	                fw.write("<div style='font-size:22px; font-weight:700;'>");

	                fw.write("📅 " + month);

	                fw.write("</div>");

	                fw.write("<div style='margin-top:8px; color:#64748b;'>");

	                fw.write("給与を見る →");

	                fw.write("</div>");

	                fw.write("</div>");

	                fw.write("</a>");
	            }
	        }
	        fw.write("</main>");

	        fw.write("</body>");
	        fw.write("</html>");

	        fw.close();

	    } catch (IOException e) {

	        e.printStackTrace();
	    }
	}

}

