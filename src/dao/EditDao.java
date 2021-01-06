package dao;

import util.DbConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库修改操作
 * @author 余嘉威
 * @version -version
 */
public class EditDao {
    /**
     *管理员更改单车信息
     * @param address,history,num
     * @exception SQLException
     */
    public void byEdit(String num,String address,String history) throws SQLException {
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con= dbcu.getConnection();
        Statement stmt= con.createStatement();
        stmt.execute("update bicycle set address='" + address + "',history='" + history + "' where num='" + num + "'");
        con.close();
        stmt.close();
    }
    /**
     *租车改地址
     * @param num
     * @exception SQLException
     */
    public void reEdit(String num) throws SQLException {
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con= dbcu.getConnection();
        Statement stmt= con.createStatement();
        stmt.execute("update bicycle set status2=1 where num='" + num + "'");
        con.close();
        stmt.close();
    }
    /**
     *还车改地址
     * @param num,address
     * @exception SQLException
     */
    public void bkEdit(String num,String address) throws SQLException {
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con= dbcu.getConnection();
        Statement stmt= con.createStatement();
        stmt.execute("update bicycle set status2=0,address='" + address + "' where num='" + num + "'");
        con.close();
        stmt.close();
    }
}
