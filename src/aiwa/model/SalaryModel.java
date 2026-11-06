package aiwa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import aiwa.entity.WageRule;


import aiwa.entity.Schedule;

public class SalaryModel {

	public double getMonthlySalary(int userid, String month) {

		double totalSalary = 0;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "SELECT s.shop_id, s.start_time, s.end_time, "
			        + "s.break_minutes, sh.hourly_rate "
					+ "FROM schedules s "
					+ "JOIN shops sh ON s.shop_id = sh.id "
					+ "WHERE s.userid = ? "
					+ "AND strftime('%Y-%m', s.work_date) = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, userid);
			stmt.setString(2, month);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

			    String startTime =
			            rs.getString("start_time");

			    String endTime =
			            rs.getString("end_time");

			    int breakMinutes =
			            rs.getInt("break_minutes");

			    double hourlyRate =
			            rs.getDouble("hourly_rate");

			    int shopId =
			            rs.getInt("shop_id");

			    double salary = calculateSalary(
			            startTime,
			            endTime,
			            breakMinutes,
			            hourlyRate,
			            shopId);

			    totalSalary += salary;
			}

			rs.close();
			conn.close();
			stmt.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return totalSalary;
	}
	
	public ArrayList<String> getAvailableMonths(int userid) {

		ArrayList<String> months = new ArrayList<String>();

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "SELECT DISTINCT strftime('%Y-%m', work_date) AS month "
					+ "FROM schedules "
					+ "WHERE userid = ? "
					+ "ORDER BY month DESC";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				months.add(rs.getString("month"));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return months;
	}
	
	
	public double getShopMonthlySalary(int userid, int shopid, String month) {

		double totalSalary = 0;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "SELECT s.shop_id, s.start_time, s.end_time, "
			        + "s.break_minutes, sh.hourly_rate "
					+ "FROM schedules s "
					+ "JOIN shops sh ON s.shop_id = sh.id "
					+ "WHERE s.userid = ? "
					+ "AND s.shop_id = ? "
					+ "AND strftime('%Y-%m', s.work_date) = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, userid);
			stmt.setInt(2, shopid);
			stmt.setString(3, month);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

			    String startTime =
			            rs.getString("start_time");

			    String endTime =
			            rs.getString("end_time");

			    int breakMinutes =
			            rs.getInt("break_minutes");

			    double hourlyRate =
			            rs.getDouble("hourly_rate");

			    int shopId =
			            rs.getInt("shop_id");

			    double salary = calculateSalary(
			            startTime,
			            endTime,
			            breakMinutes,
			            hourlyRate,
			            shopId);

			    totalSalary += salary;
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return totalSalary;
	}
	
	public ArrayList<Schedule> getShopSchedules(
			int userid,
			int shopid,
			String month) {

		ArrayList<Schedule> schedules =
				new ArrayList<Schedule>();

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn =
					DriverManager.getConnection(
							"jdbc:sqlite:teamwork03.db");

			String sql =
					"SELECT id, userid, shop_id, work_date, "
					+ "start_time, end_time, break_minutes "
					+ "FROM schedules "
					+ "WHERE userid = ? "
					+ "AND shop_id = ? "
					+ "AND strftime('%Y-%m', work_date) = ? "
					+ "ORDER BY work_date ASC";

			PreparedStatement stmt =
					conn.prepareStatement(sql);

			stmt.setInt(1, userid);
			stmt.setInt(2, shopid);
			stmt.setString(3, month);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Schedule schedule = new Schedule();

				schedule.setId(rs.getInt("id"));
				schedule.setUserid(rs.getInt("userid"));
				schedule.setShopid(rs.getInt("shop_id"));
				schedule.setWorkDate(
						rs.getString("work_date"));
				schedule.setStartTime(
						rs.getString("start_time"));
				schedule.setEndTime(
						rs.getString("end_time"));
				schedule.setBreakMinutes(
						rs.getInt("break_minutes"));

				schedules.add(schedule);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return schedules;
	}

	public double calculateSalary(
	        String startTime,
	        String endTime,
	        int breakMinutes,
	        double hourlyRate,
	        int shopId) {

	    int startMinutes = timeToMinutes(startTime);
	    int endMinutes = timeToMinutes(endTime);

	    if (endMinutes < startMinutes) {
	        endMinutes += 24 * 60;
	    }

	    int workMinutes = endMinutes - startMinutes;

	    workMinutes -= breakMinutes;

	    if (workMinutes < 0) {
	        workMinutes = 0;
	    }

	    WageRuleModel wageRuleModel = new WageRuleModel();

	    ArrayList<WageRule> rules =
	            wageRuleModel.getAllByShopId(shopId);

	    double salary = 0;

	    for (int i = 0; i < workMinutes; i++) {

	        int currentMinutes =
	                (startMinutes + i) % (24 * 60);

	        double rate = hourlyRate;

	        for (WageRule rule : rules) {

	            int ruleStart =
	                    timeToMinutes(rule.getStartTime());

	            int ruleEnd =
	                    timeToMinutes(rule.getEndTime());

	            boolean matched;

	            if (ruleEnd < ruleStart) {

	                // 例：22:00 ～ 05:00
	                matched =
	                        currentMinutes >= ruleStart
	                        || currentMinutes < ruleEnd;

	            } else {

	                // 例：05:00 ～ 09:00
	                matched =
	                        currentMinutes >= ruleStart
	                        && currentMinutes < ruleEnd;
	            }

	            if (matched) {

	                rate = hourlyRate
	                        * (1 + rule.getRatePercent() / 100.0);

	                break;
	            }
	        }

	        salary += rate / 60.0;
	    }

	    return salary;
	}
	public int timeToMinutes(String time) {

		if (time == null || time.trim().isEmpty()) {
			return 0;
		}

		String[] parts = time.trim().split(":");

		int hour = Integer.parseInt(parts[0]);

		int minute = 0;

		if (parts.length > 1) {
			minute = Integer.parseInt(parts[1]);
		}

		return hour * 60 + minute;
	}
}