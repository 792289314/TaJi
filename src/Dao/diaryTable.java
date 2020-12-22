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
                            rs.getInt("dweather"), rs.getString("dtext"),
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

    // 插入日记
    public boolean AddDiary(Diary diary, long id) {
        DBUtil db = new DB();
        boolean flag = false;
        try {
            conn = db.getConnection();
            if (conn != null) {
                String sql = "insert into diary (uid, cid, dflag, dtext, dtime, dweather) " +
                        "VALUES (?,?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setLong(1, id);
                pst.setLong(2, diary.getClassify().getId());
                pst.setBoolean(3,diary.getFlag());
                pst.setString(4,diary.getText());
                pst.setTimestamp(5,diary.getTime());
                pst.setInt(6,diary.getWeather());

                pst.executeUpdate();
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return flag;
    }

    // 根据分类cid 统计日记的个数
    public long calcCntOfDiaryByClassifyId(long uid, long cid) {
        long cnt = 0;
        DBUtil db = new DB();
        try {
            conn = db.getConnection();
            if (conn == null) return cnt;
            String sql = "select count(*) 'cnt' from diary where uid = ? and cid = ?";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, uid);
            pst.setLong(2, cid);
            rs = pst.executeQuery();
            if (rs.next()) cnt = rs.getLong("cnt");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }

        return cnt;
    }
}
