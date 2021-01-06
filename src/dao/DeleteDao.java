package dao;

import util.DbConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库删除操作
 * @author 余嘉威
 * @version -version
 */
public class DeleteDao {
    /**
     *管理员删除单车信息
     * @param num
     * @exception SQLException
     */
    public void bydelete(String num){
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con=dbcu.getConnection();
        try {
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from bicycle");
            while(rs.next()){
                if(rs.getString(1).equals(num)){
                    stmt.execute("delete from bicycle " + " where num='" + num + "'");
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(num);
    }
    /**
     *备份还原删除全部单车信息
     * @exception SQLException
     */
    public void allDelete() throws SQLException {
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con=dbcu.getConnection();
        Statement stmt=con.createStatement();
        stmt.execute("truncate table bicycle ");
    }
}
