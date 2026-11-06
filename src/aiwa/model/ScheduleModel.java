package aiwa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import aiwa.entity.Schedule;

public class ScheduleModel {

	public boolean register(Schedule schedule) {

		boolean result = false;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "INSERT INTO schedules(userid, shop_id, work_date, start_time,end_time, break_minutes) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, schedule.getUserid());

			stmt.setInt(2, schedule.getShopid());

			stmt.setString(3, schedule.getWorkDate());

			stmt.setString(4, schedule.getStartTime());

			stmt.setString(5, schedule.getEndTime());

			stmt.setInt(6, schedule.getBreakMinutes());

			int count = stmt.executeUpdate();

			if (count > 0) {
				result = true;
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Schedule> getAllByUserId(int userid) {

		ArrayList<Schedule> schedules = new ArrayList<Schedule>();

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "SELECT * FROM schedules WHERE userid = ? "
					+ "ORDER BY work_date, start_time";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Schedule schedule = new Schedule();

				schedule.setId(rs.getInt("id"));

				schedule.setUserid(rs.getInt("userid"));

				schedule.setShopid(rs.getInt("shop_id"));

				schedule.setWorkDate(rs.getString("work_date"));

				schedule.setStartTime(rs.getString("start_time"));

				schedule.setEndTime(rs.getString("end_time"));

				schedule.setBreakMinutes(rs.getInt("break_minutes"));

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

	public boolean update(Schedule schedule) {

		boolean result = false;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "UPDATE schedules "
					+ "SET shop_id = ?, "
					+ "work_date = ?, "
					+ "start_time = ?, "
					+ "end_time = ?, "
					+ "break_minutes = ? "
					+ "WHERE id = ? "
					+ "AND userid = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, schedule.getShopid());

			stmt.setString(2, schedule.getWorkDate());

			stmt.setString(3, schedule.getStartTime());

			stmt.setString(4, schedule.getEndTime());

			stmt.setInt(5, schedule.getBreakMinutes());

			stmt.setInt(6, schedule.getId());

			stmt.setInt(7, schedule.getUserid());

			int count = stmt.executeUpdate();

			if (count > 0) {
				result = true;
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public boolean delete(int id, int userid) {

		boolean result = false;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "DELETE FROM schedules "
					+ "WHERE id = ? "
					+ "AND userid = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.setInt(2, userid);

			int count = stmt.executeUpdate();

			if (count > 0) {
				result = true;
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}
	public boolean deleteByShopId(int shopid, int userid) {

		boolean result = false;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "DELETE FROM schedules "
					+ "WHERE shop_id = ? "
					+ "AND userid = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, shopid);
			stmt.setInt(2, userid);

			stmt.executeUpdate();

			result = true;

			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

}