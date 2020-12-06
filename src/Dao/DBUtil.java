package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// 数据池接口
public interface DBUtil {
    public Connection getConnection();
    public void close(Connection conn, Statement stm, ResultSet rs);
}
