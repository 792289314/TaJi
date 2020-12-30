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

    // 新建用户分类
    public boolean addClassify(long id, Classify classify) {
        boolean flag = false;
        DBUtil db = new DB();
        try {
            conn = db.getConnection();
            if (conn == null) return false;
            String sql = "insert into classify(uid,  cname, cflag, ccolor) VALUES (?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            pst.setString(2, classify.getName());
            pst.setBoolean(3, classify.getFlag());
            pst.setString(4, classify.getColor());
            pst.executeUpdate();
            flag = true;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }

        return flag;
    }

    // 通过用户名 查找 该用户 除了未分类以外的 所有的分类
    public ArrayList<Classify> getAllClassifiesExceptUnClassified(long id) {
        ArrayList<Classify> classifyList = new ArrayList<>();
        DBUtil db = new DB();
        try {
            conn = db.getConnection();
            if (conn == null) return null;
            String sql = "select classify.cid 'cid', cname, cflag, ccolor, count('*') 'cnt'\n" +
                    "from classify,diary\n" +
                    "where classify.uid = ?  and classify.cid = diary.cid and classify.uid = diary.uid\n" +
                    "  and not (cname = '未分类') group by classify.cid";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                classifyList.add(new Classify(
                        rs.getInt("cid"),
                        rs.getString("cname"),
                        rs.getBoolean("cflag"),
                        rs.getString("ccolor"),
                        rs.getLong("cnt")
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
