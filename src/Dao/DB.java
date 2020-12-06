package Dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 数据池类
public class DB implements DBUtil {
    private static DataSource ds = null;

    public DataSource getDataSource() throws Exception {
        long start = System.currentTimeMillis();
        if (ds == null) {
            Context initContext = new InitialContext();
            if (initContext == null) throw new Exception("No Context");
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TaJiDB");
            long end = System.currentTimeMillis();
            System.out.println("数据池连接用时：" + (end - start) + " ms");
        }
        return ds;
    }

    @Override
    public Connection getConnection() {
        try {
            Connection conn = getDataSource().getConnection();
            if (conn != null) return conn;
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void close(Connection conn, Statement stm, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
