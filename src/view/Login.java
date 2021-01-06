package view;

import dao.SearchDao;
import util.DbConnectionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/**
 * 登陆界面
 * @author 余嘉威
 * @version -version
 */
public class Login {
     public void login(){
         JFrame jf=new JFrame("课设");
         jf.setSize(550, 400);
         jf.setBackground(Color.white);
         jf.setLocation(300, 200);
         jf.setLayout(null);
         JLabel title=new JLabel("单车管理系统");
         JLabel account=new JLabel("账号");
         JLabel password=new JLabel("密码");
         title.setBounds(180,30,400,50);
         title.setFont(new Font("Dialog", 1, 30));
         account.setBounds(130,100,40,20);
         password.setBounds(130,140,40,20);
         jf.add(title);
         jf.add(account);
         jf.add(password);
         JTextField text1=new JTextField();
         JTextField text2=new JTextField();
         text1.setBounds(180,100,200,20);
         text2.setBounds(180,140,200,20);
         jf.add(text1);
         jf.add(text2);
         JButton login=new JButton("登录");
         JButton register=new JButton("注册");
         login.setBounds(200,190,70,30);
         register.setBounds(280,190,70,30);
         jf.add(login);
         jf.add(register);
         login.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 SearchDao sd=new SearchDao();
                 sd.lgSearch(text1.getText(),text2.getText(),jf);
             }
         });
         register.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 jf.dispose();
                 Register register=new Register();
                 register.register();
             }
         });
         jf.setVisible(true);
     }
}
