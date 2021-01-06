package dao;

import util.DbConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库增加操作
 * @author 余嘉威
 * @version -version
 */
public class AddDao {
    /**
     *注册插入数据
     * @param account,password,name,num
     * @exception SQLException
     */
    public void rgadd(String account, String password, String name, String num){
        DbConnectionUtil dbconn = new DbConnectionUtil();
        Connection con=dbconn.getConnection();
        try {
            Statement stmt=con.createStatement();
            stmt.execute("INSERT INTO user(name,num,credit,administrator,account,password) VALUES ('" + name + "','" + num + "',100,0,'" + account + "','" + password + "')");
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     *管理员插入单车信息
     * @param address,history,num
     * @exception SQLException
     */
    public void byadd(String num,String address,String history){
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con=dbcu.getConnection();
        try {
            Statement stmt=con.createStatement();
            stmt.execute("INSERT INTO bicycle(num,address,history,status1,status2) VALUES ('" + num + "','" + address + "','" + history + "',0,0)");
            con.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     *初始完整插入数据
     * @param address,history,num,status1,status2
     * @exception SQLException
     */
    public void byadd(String num,String address,String history,int status1,int status2){
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con=dbcu.getConnection();
        try {
            Statement stmt=con.createStatement();
            stmt.execute("INSERT INTO bicycle(num,address,history,status1,status2) VALUES ('" + num + "','" + address + "','" + history + "'," + status1 + "," + status2 + ")");
            con.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     *借车还车操作
     * @param num,bkaddress,rtaddress,bicycle
     * @exception SQLException
     */
    public void opAdd(String num,String bkaddress,String rtaddress,String bicycle) throws SQLException {
            DbConnectionUtil dbcu=new DbConnectionUtil();
            Connection con=dbcu.getConnection();
            Statement stmt=con.createStatement();
            stmt.execute("INSERT INTO operation(rent,back,bicycle,user) VALUES ('" + rtaddress + "','" + bkaddress + "','" + bicycle + "','" + num + "')");
            stmt.close();
            con.close();
    }
}
