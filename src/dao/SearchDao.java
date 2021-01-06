package dao;

import model.Bicycle;
import util.DbConnectionUtil;
import view.Adiministrator;
import view.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 数据库搜索操作
 * @author 余嘉威
 * @version -version
 */
public class SearchDao {
    /**
     *登录遍历账号密码是否正确
     * @param account,password,jf
     * @exception SQLException
     */
     public void lgSearch(String account, String password, JFrame jf){
         DbConnectionUtil dbcu=new DbConnectionUtil();
         Connection con= dbcu.getConnection();
         Statement stmt= null;
         try {
             stmt = con.createStatement();
             ResultSet rs =stmt.executeQuery("select * from user");
             while(rs.next()){
                 if(rs.getString(5).equals(account)&&rs.getString(6).equals(password)&&rs.getInt(4)==0){
                     User user=new User();
                     user.user(account);
                     jf.dispose();
                     System.out.println(rs.getString(4));
                     break;
                 }
                 else if(rs.getString(5).equals(account)&&rs.getString(6).equals(password)&&rs.getInt(4)==1){
                     Adiministrator ad=new Adiministrator();
                     ad.adminitrator();
                     jf.dispose();
                     break;
                 }
             }
             con.close();
             stmt.close();
         } catch (SQLException | IOException throwables) {
             throwables.printStackTrace();
         }
     }
    /**
     *获取单车信息做列表
     * @param model
     * @return DefaultTableModel
     * @exception SQLException
     */
     public DefaultTableModel bySearch(DefaultTableModel model) throws SQLException {
         DbConnectionUtil dbcu=new DbConnectionUtil();
         Connection con= dbcu.getConnection();
         Statement stmt= con.createStatement();
         ResultSet rs =stmt.executeQuery("select * from bicycle");
         while(rs.next()){
             model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3)});
         }
         return model;
     }
    /**
     *管理员单车列表
     * @return Bicycle[]
     * @exception SQLException
     */
    public Bicycle[] bySearch() throws SQLException {
        int i=0;
        Bicycle[] bicycle= new Bicycle[10000];
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con= dbcu.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs =stmt.executeQuery("select * from bicycle");
        while(rs.next()){
            bicycle[i]=new Bicycle();
            bicycle[i].setNum(rs.getString(1));
            bicycle[i].setAddress(rs.getString(2));
            bicycle[i].setHistory(rs.getString(3));
            bicycle[i].setStatus1(rs.getInt(4));
            bicycle[i].setStatus2(rs.getInt(5));
            i++;
        }
        return bicycle;
    }
    /**
     *用户单车列表
     * @param model
     * @return DefaultTableModel
     * @exception SQLException
     */
    public DefaultTableModel byUserSearch(DefaultTableModel model) throws SQLException {
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con= dbcu.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs =stmt.executeQuery("select * from bicycle");
        while(rs.next()){
            if(rs.getInt(4)==1){
                model.addRow(new Object[] {rs.getString(1), rs.getString(2),"维护中"});
            }
            else if(rs.getInt(5)==1){
                model.addRow(new Object[] {rs.getString(1), rs.getString(2),"使用中"});
            }
            else
            model.addRow(new Object[] {rs.getString(1), rs.getString(2), "可用"});
        }
        return model;
    }
    /**
     *管理员模糊搜索
     * @param keywords
     * @return Bicycle[]
     * @exception SQLException
     */
     public Bicycle[] shSearch(String keywords) throws SQLException {
         int i=0;
         Bicycle[] bicycle= new Bicycle[10000];
         DbConnectionUtil dbcu=new DbConnectionUtil();
         Connection con= dbcu.getConnection();
         Statement stmt= con.createStatement();
         ResultSet rs =stmt.executeQuery("select * from bicycle where concat(num,address,history) like '%"+ keywords +"%'");
         while(rs.next()){
             bicycle[i]=new Bicycle();
             bicycle[i].setNum(rs.getString(1));
             bicycle[i].setAddress(rs.getString(2));
             bicycle[i].setHistory(rs.getString(3));
             i++;
         }
         return bicycle;
     }
    /**
     *用户模糊搜索
     * @param keywords
     * @return Bicycle[]
     * @exception SQLException
     */
    public Bicycle[] shUserSearch(String keywords) throws SQLException {
        int i=0;
        Bicycle[] bicycle= new Bicycle[10000];
        DbConnectionUtil dbcu=new DbConnectionUtil();
        Connection con= dbcu.getConnection();
        Statement stmt= con.createStatement();
        ResultSet rs =stmt.executeQuery("select * from bicycle where concat(num,address) like '%"+ keywords +"%'");
        while(rs.next()){
            bicycle[i]=new Bicycle();
            bicycle[i].setNum(rs.getString(1));
            bicycle[i].setAddress(rs.getString(2));
            bicycle[i].setStatus1(rs.getInt(4));
            bicycle[i].setStatus2(rs.getInt(5));
            i++;
        }
        return bicycle;
    }
}
