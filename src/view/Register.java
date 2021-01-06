package view;

import dao.AddDao;
import util.DbConnectionUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 注册界面
 * @author 余嘉威
 * @version -version
 */
public class Register {
    public void register(){
        JFrame jf=new JFrame("课设");
        jf.setSize(550, 400);
        jf.setBackground(Color.white);
        jf.setLocation(300, 200);
        jf.setLayout(null);
        JLabel account=new JLabel("账号");
        JLabel password=new JLabel("密码");
        JLabel name=new JLabel("名字");
        JLabel num=new JLabel("身份证");
        account.setBounds(100,50,80,30);
        password.setBounds(100,90,80,30);
        name.setBounds(100,130,80,30);
        num.setBounds(100,170,80,30);
        jf.add(account);
        jf.add(password);
        jf.add(name);
        jf.add(num);
        JTextField t1=new JTextField();
        JTextField t2=new JTextField();
        JTextField t3=new JTextField();
        JTextField t4=new JTextField();
        t1.setBounds(190,50,220,30);
        t2.setBounds(190,90,220,30);
        t3.setBounds(190,130,220,30);
        t4.setBounds(190,170,220,30);
        jf.add(t1);
        jf.add(t2);
        jf.add(t3);
        jf.add(t4);
        JButton jb=new JButton("注册");
        jb.setBounds(250,250,80,30);
        jf.add(jb);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account=t1.getText();
                String password=t2.getText();
                String name=t3.getText();
                String num=t4.getText();
                AddDao ad=new AddDao();
                ad.rgadd(account,password,name,num);
            }
        });
        jf.setVisible(true);
    }
}
