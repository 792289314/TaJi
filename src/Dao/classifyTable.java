package Dao;

import Entity.Classify;

import java.sql.*;
import java.util.ArrayList;

public class classifyTable {
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    // 通过用户名 查找 该用户所有的分类
    public ArrayList<Classify> getClassifyById(long id) {
        ArrayList<Classify> classifyList = new ArrayList<>();
        DBUtil db = new DB();
        try {
            conn = db.getConnection();
            if (conn == null) return null;
            String sql = "select cid, cname, cflag, ccolor from classify where uid = ?";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                classifyList.add(new Classify(
                        rs.getInt("cid"),
                        rs.getString("cname"),
                        rs.getBoolean("cflag"),
                        rs.getString("ccolor")
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }

        return classifyList;
    }
}
