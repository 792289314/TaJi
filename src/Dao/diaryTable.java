package Dao;

import Entity.Classify;
import Entity.Diary;
import Entity.DiaryAndClassify;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class diaryTable {
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    // 得到用户所有的日记记录
    public ArrayList<Diary> getUserDiary(long id) {
        DBUtil db = new DB();
        ArrayList<Diary> diaries = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn != null) {
                String sql = "select  did,classify.cid 'cid', cname, cflag, ccolor, dflag,dweather,dtext,dtime" +
                        " from classify,diary where diary.uid =  ? and classify.cid=diary.cid";
                pst = conn.prepareStatement(sql);
                pst.setLong(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    diaries.add(new Diary(
                            new Classify(id, rs.getString("cname"),
                                    rs.getBoolean("cflag"),
                                    rs.getString("ccolor")),
                            rs.getInt("did"),
                            rs.getBoolean("dflag"),
                            rs.getInt("dweather"),rs.getString("dtext"),
                            rs.getTime("dtime")
                    ));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return diaries;
    }


    // 得到用户所有的日记记录 每个记录都带有分类信息
    public ArrayList<DiaryAndClassify> getUserDiaryAndClassify(long id) {
        DBUtil db = new DB();
        ArrayList<DiaryAndClassify> diaries = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn != null) {
                String sql = "select  did,classify.cid 'cid', cname, cflag, ccolor, dflag,dweather,dtext,dtime" +
                        " from classify,diary where diary.uid =  ? and classify.cid=diary.cid";
                pst = conn.prepareStatement(sql);
                pst.setLong(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    diaries.add(new DiaryAndClassify(
                            rs.getLong("cid"),
                            rs.getString("cname"),
                            rs.getBoolean("cflag"),
                            rs.getString("ccolor"),
                            rs.getInt("did"),
                            rs.getBoolean("dflag"),
                            rs.getInt("dweather"),
                            rs.getString("dtext"),
                            //rs.getString("dtime")
                            rs.getTimestamp("dtime")
                    ));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return diaries;
    }
}
