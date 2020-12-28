package Dao;

import Entity.Classify;
import Entity.Diary;
import Entity.DiaryAndClassify;
import Entity.DiaryAndUserAndClassify;

import java.sql.*;
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
                        " from classify,diary where diary.uid =  ? and classify.cid=diary.cid order by diary.dtime DESC ";
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
                pst.setBoolean(3, diary.getFlag());
                pst.setString(4, diary.getText());
                pst.setTimestamp(5, diary.getTime());
                pst.setInt(6, diary.getWeather());

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


    // 修改日记
    public boolean modifyDiary(Diary diary) {
        DBUtil db = new DB();
        boolean flag = false;
        try {
            conn = db.getConnection();
            if (conn != null) {
                // 可以修改 分类归属、可见状态 文本 天气
                String sql = "update diary set cid=?,dflag=?,dtext=?,dweather=? where did=?";
                pst = conn.prepareStatement(sql);
                pst.setLong(1, diary.getClassify().getId());
                pst.setBoolean(2, diary.getFlag());
                pst.setString(3, diary.getText());
                pst.setInt(4, diary.getWeather());
                pst.setLong(5, diary.getId());

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

    // 删除日记
    public boolean deleteDiary(long diaryId) {
        DBUtil db = new DB();
        boolean flag = false;
        try {
            conn = db.getConnection();
            if (conn != null) {
                // 可以修改 分类归属、可见状态 文本 天气
                String sql = "delete from diary where did=?";
                pst = conn.prepareStatement(sql);
                pst.setLong(1, diaryId);
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


    // 过客列表里 获取 指定日期当天 所有 公开的日记
    public ArrayList<DiaryAndUserAndClassify> getAllDiaryByDate(String time) {
        DBUtil db = new DB();
        ArrayList<DiaryAndUserAndClassify> list = new ArrayList<>();
        try {
            conn = db.getConnection();
            if (conn != null) {
                // 可以修改 分类归属、可见状态 文本 天气
                String sql = "select uname, dtime, dtext, dweather, cname, ccolor from diary,user, classify\n" +
                        "where to_days(dtime) = to_days(?) and ( dflag or cflag) = false  " +
                        "and diary.uid = user.uid and diary.cid = classify.cid";
                pst = conn.prepareStatement(sql);
                pst.setString(1, time);
                rs = pst.executeQuery();

                while (rs.next()) {
                    list.add(new DiaryAndUserAndClassify(
                            rs.getString("uname"),
                            rs.getString("dtext"),
                            rs.getTimestamp("dtime"),
                            rs.getInt("dweather"),
                            rs.getString("cname"),
                            rs.getString("ccolor")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return list;
    }
}
