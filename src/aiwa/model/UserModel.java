package aiwa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import aiwa.entity.User;

public class UserModel {

	public boolean findAccount(User user) {

		boolean result = false;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "SELECT * FROM users WHERE email = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, user.getEmail());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				result = true;
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public boolean login(User user) {

		boolean result = false;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, user.getEmail());

			stmt.setString(2, user.getPassword());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				int userid = rs.getInt("id");

				user.setId(userid);

				result = true;

			} else {

				System.out.println("Login fail");

				result = false;
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public boolean register(User user) {

		boolean result = false;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "SELECT * FROM users WHERE email = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, user.getEmail());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				System.out.println("Account Exit");

				result = false;

			} else {

				userAdd(user);

				result = true;
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public void userAdd(User user) {

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "INSERT INTO users "
					+ "(username, email, password) "
					+ "VALUES (?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, user.getUsername());

			stmt.setString(2, user.getEmail());

			stmt.setString(3, user.getPassword());

			stmt.executeUpdate();

			System.out.println("Register OK");

			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}