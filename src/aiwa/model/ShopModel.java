package aiwa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import aiwa.entity.Shop;

public class ShopModel {

	public boolean register(Shop shop) {

		boolean result = false;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "INSERT INTO shops(userid, name, hourly_rate) VALUES(?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, shop.getUserid());

			stmt.setString(2, shop.getName());

			stmt.setDouble(3, shop.getHourlyRate());

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

	public ArrayList<Shop> getAllByUserId(int userid) {

		ArrayList<Shop> shops = new ArrayList<Shop>();

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "SELECT * FROM shops WHERE userid = ? ORDER BY id";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Shop shop = new Shop();

				shop.setId(rs.getInt("id"));

				shop.setUserid(rs.getInt("userid"));

				shop.setName(rs.getString("name"));

				shop.setHourlyRate(rs.getDouble("hourly_rate"));

				shops.add(shop);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return shops;
	}

	public Shop getByShopid(int shopid) {
		Shop shop = new Shop();
		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "SELECT * FROM shops WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, shopid);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				shop.setId(rs.getInt("id"));

				shop.setUserid(rs.getInt("userid"));

				shop.setName(rs.getString("name"));

				shop.setHourlyRate(rs.getDouble("hourly_rate"));

			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return shop;
	}

	public boolean update(Shop shop) {

		boolean result = false;

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");

			String sql = "UPDATE shops SET name = ?, hourly_rate = ? "
					+ "WHERE id = ? "
					+ "AND userid = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, shop.getName());

			stmt.setDouble(2, shop.getHourlyRate());

			stmt.setInt(3, shop.getId());

			stmt.setInt(4, shop.getUserid());

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

			String sql = "DELETE FROM shops WHERE id = ? AND userid = ?";

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

	public boolean existsShop(int shopid, int userid) {
		boolean result = false;

		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:teamwork03.db");
			String sql = "SELECT id FROM shops WHERE id = ? AND userid = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, shopid);
			stmt.setInt(2, userid);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = true;
			}

			rs.close();
			conn.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}