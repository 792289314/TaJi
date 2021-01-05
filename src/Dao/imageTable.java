package Dao;

import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class imageTable {
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public boolean AddImg(long diaryId, String imgDir) {
        DBUtil db = new DB();
        boolean flag = false;
        try {
            conn = db.getConnection();
            String sql = "insert into image(idir, did) VALUES(?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, imgDir);
            pst.setLong(2, diaryId);
            pst.executeUpdate();
            flag = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return flag;
    }

    public ArrayList<String> findImgById(long diaryId) {
        DBUtil db = new DB();
        ArrayList<String> files = new ArrayList<>();
        try {
            conn = db.getConnection();
            String sql = "select idir  from image where did = ?";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, diaryId);
            rs = pst.executeQuery();
            while (rs.next()) {
                files.add(rs.getString("idir"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return files;
    }
}
