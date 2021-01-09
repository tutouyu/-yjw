package util;

import com.company.Main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接
 * @author 余嘉威
 * @version -version
 */
public class DbConnectionUtil {
    private Connection conn=null; //保存连接对象

    public DbConnectionUtil() {//构造方法连接数据库
        InputStream inputStream = DbConnectionUtil.class.getClassLoader()
                .getResourceAsStream("dbconnection.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        String DBRIVER=properties.getProperty("DBRIVER"); //定义MySQL数据库驱动程序
        String DBURL=properties.getProperty("DBURL"); //定义MySQL数据库连接地址
        String DBUSER=properties.getProperty("DBUSER"); //MySQL数据库连接用户名
        String PASSWORD=properties.getProperty("PASSWORD"); //MySQL数据库连接密码
        try {
            Class.forName(DBRIVER);
            this.conn= DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
        } catch (Exception e) {e.printStackTrace();}
    }
    public Connection getConnection() {//返回数据库连接对象
        return this.conn;
    }
    public void close() {//关闭数据连接
        if(this.conn!=null) {
            try {this.conn.close();} catch (SQLException e) {e.printStackTrace();}
        }}
}
