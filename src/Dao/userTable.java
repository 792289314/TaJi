package Dao;

import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 对数据库里user表的一些列操作
public class userTable {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;


    // 通过 邮箱密码 获取 用户实例
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        DBUtil db = new DB(); // 连接数据池
        try {
            conn = db.getConnection();
            if (conn == null) return null;
            String sql = "select * from user where uemail=? and upassword=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("uid"),
                        rs.getString("uname"),
                        rs.getString("uemail")
                );
            } else {
                System.out.println("无结果集");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return user;
    }

    public boolean isExistenceByEmail(String email) {
        boolean flag = false;
        DBUtil db = new DB();
        try {
            conn = db.getConnection();
            String sql = "select * from user where uemail=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            if (rs.next()) flag = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return flag;
    }

    // 根据邮箱寻找用户
    public User searchUserByEmail(String email) {
        User user = null;
        DBUtil db = new DB();
        try {
            conn = db.getConnection();
            String sql = "select * from user where uemail=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("uid"),
                        rs.getString("uname"),
                        rs.getString("uemail")
                );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return user;
    }

    // 添加 新用户
    public boolean addUser(String name, String email, String password) {
        boolean flag = false;
        DBUtil db = new DB();
        try {
            conn = db.getConnection();
            String sql = "insert into user(uname, upassword, uemail) VALUES (?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.executeUpdate();

            flag = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return flag;
    }

    public User register(String name, String email, String password) {
        User user = null;
        if (addUser(name, email, password)) user = searchUserByEmail(email);
        return user;
    }

    // 修改密码
    public boolean modifyPassword(String email, String password) {
        boolean flag = false;
        DBUtil db = new DB();
        try {
            conn = db.getConnection();
            String sql = "update user set upassword =? where uemail = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,password);
            pst.setString(2, email);
            pst.executeUpdate();
            flag = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            db.close(conn, pst, rs);
        }
        return flag;
    }
}
