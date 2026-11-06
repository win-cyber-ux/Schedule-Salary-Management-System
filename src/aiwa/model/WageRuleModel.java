package aiwa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import aiwa.entity.WageRule;

public class WageRuleModel {

    private static final String URL = "jdbc:sqlite:teamwork03.db";

    // 登録
    public boolean register(WageRule rule) {

        String sql = "INSERT INTO wage_rules "
                + "(shop_id, start_time, end_time, rate_percent) "
                + "VALUES (?, ?, ?, ?)";

        try (
                Connection con = DriverManager.getConnection(URL);
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, rule.getShopId());
            ps.setString(2, rule.getStartTime());
            ps.setString(3, rule.getEndTime());
            ps.setDouble(4, rule.getRatePercent());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 店舗のルール一覧
    public ArrayList<WageRule> getAllByShopId(int shopId) {

        ArrayList<WageRule> rules =
                new ArrayList<WageRule>();

        String sql = "SELECT * FROM wage_rules "
                + "WHERE shop_id = ? "
                + "ORDER BY start_time";

        try (
                Connection con = DriverManager.getConnection(URL);
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, shopId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                WageRule rule = new WageRule();

                rule.setId(rs.getInt("id"));
                rule.setShopId(rs.getInt("shop_id"));
                rule.setStartTime(rs.getString("start_time"));
                rule.setEndTime(rs.getString("end_time"));
                rule.setRatePercent(
                        rs.getDouble("rate_percent")
                );

                rules.add(rule);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rules;
    }

    // 削除
    public boolean delete(int id, int shopId) {

        String sql = "DELETE FROM wage_rules "
                + "WHERE id = ? AND shop_id = ?";

        try (
                Connection con = DriverManager.getConnection(URL);
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            ps.setInt(2, shopId);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean deleteByShopId(int shopId) {

        String sql =
                "DELETE FROM wage_rules WHERE shop_id = ?";

        try (
            Connection con =
                    DriverManager.getConnection(URL);
            PreparedStatement ps =
                    con.prepareStatement(sql)
        ) {

            ps.setInt(1, shopId);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}