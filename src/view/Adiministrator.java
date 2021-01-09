package view;

import dao.AddDao;
import dao.DeleteDao;
import dao.EditDao;
import dao.SearchDao;
import model.Bicycle;
import util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 管理员界面
 * @author 余嘉威
 * @version -version
 */
public class Adiministrator {
    public void adminitrator() throws SQLException, IOException {
        JFrame jf=new JFrame("课设");
        jf.setSize(550, 400);
        jf.setBackground(Color.white);
        jf.setLocation(300, 200);
        jf.setLayout(null);
        JTextField text=new JTextField();
        text.setBounds(50,30,150,30);
        jf.add(text);
        JButton search=new JButton("搜索");
        search.setBounds(220,30,80,30);
        jf.add(search);
        JButton rent=new JButton("编辑");
        rent.setBounds(310,30,80,30);
        jf.add(rent);
        JButton back=new JButton("删除");
        back.setBounds(400,30,100,30);
        jf.add(back);
        String[] titles = { "编号", "地址", "维修历史"};
        DefaultTableModel model=new DefaultTableModel(titles, 0);
        SearchDao ad=new SearchDao();
        JTable table = new JTable( ad.bySearch(model)){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==0) {
                    return false;
                }
                return true;
            }
        };
        JScrollPane scr = new JScrollPane(table);
        scr.setBounds(50,80,450,100);
        jf.add(scr);
        JLabel num=new JLabel("编号");
        JLabel address=new JLabel("地址");
        JLabel history=new JLabel("维修历史");
        num.setBounds(50,190,80,30);
        address.setBounds(50,230,80,30);
        history.setBounds(50,270,80,30);
        jf.add(num);
        jf.add(address);
        jf.add(history);
        JTextField t1=new JTextField();
        JTextField t2=new JTextField();
        JTextField t3=new JTextField();
        t1.setBounds(140,190,150,30);
        t2.setBounds(140,230,150,30);
        t3.setBounds(140,270,150,30);
        jf.add(t1);
        jf.add(t2);
        jf.add(t3);
        JButton add=new JButton("添加");
        add.setBounds(300,210,90,25);
        jf.add(add);
        JButton recover=new JButton("恢复");
        recover.setBounds(400,220,100,25);
        jf.add(recover);
        JButton in=new JButton("导入EXCEL");
        in.setBounds(400,250,100,25);
        jf.add(in);
        JButton out=new JButton("导出EXCEL");
        out.setBounds(400,280,100,25);
        jf.add(out);
        JButton oppsite=new JButton("倒序");
        oppsite.setBounds(400,190,100,25);
        jf.add(oppsite);
        JButton rand=new JButton("插入随机数据");
        rand.setBounds(300,240,90,25);
        jf.add(rand);
        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    if (JOptionPane.showConfirmDialog(null, "确定要编辑数据吗？", "",    JOptionPane.YES_NO_OPTION) == 0) {
                        String num= (String) model.getValueAt(table.getSelectedRow(),0);
                        EditDao ed=new EditDao();
                        try {
                            System.out.println((String)model.getValueAt(table.getSelectedRow(),1));
                            ed.byEdit((String)model.getValueAt(table.getSelectedRow(),0),(String)model.getValueAt(table.getSelectedRow(),1),(String)model.getValueAt(table.getSelectedRow(),2));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "请选择要编辑的行");
                    }
                }
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDao ad=new AddDao();
                ad.byadd(t1.getText(),t2.getText(),t3.getText());
                for(int i=model.getRowCount();i>0;i--){
                    model.removeRow(i-1);
                }
               SearchDao sd=new SearchDao();
                try {
                    sd.bySearch(model);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    if (JOptionPane.showConfirmDialog(null, "确定要删除数据吗？", "",    JOptionPane.YES_NO_OPTION) == 0) {
                        String num= (String) model.getValueAt(table.getSelectedRow(),0);
                        DeleteDao dd=new DeleteDao();
                        dd.bydelete(num);
                        model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
                    } else {
                        JOptionPane.showMessageDialog(null, "请选择要删除的行");
                    }
                }

            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=model.getRowCount();i>0;i--){
                    model.removeRow(i-1);
                }
              SearchDao sd=new SearchDao();
                try {
                    Bicycle []bicycle=sd.shSearch(text.getText());
                    for(int i=0;i<bicycle.length;i++){
                        if(bicycle[i]!=null)
                        model.addRow(new Object[] {bicycle[i].getNum(), bicycle[i].getAddress(),bicycle[i].getHistory()});
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        BuUtil bu=new BuUtil();
        recover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Bicycle[] bicycle=bu.buUtil();
                    DeleteDao dd=new DeleteDao();
                    dd.allDelete();
                    for(int i=model.getRowCount();i>0;i--){
                        model.removeRow(i-1);
                    }
                    for(int i=0;i<bicycle.length;i++){
                        if(bicycle[i]!=null){
                            AddDao ad=new AddDao();
                            ad.byadd(bicycle[i].getNum(),bicycle[i].getAddress(),bicycle[i].getHistory(),bicycle[i].status1,bicycle[i].status2);
                            model.addRow(new Object[]{bicycle[i].getNum(),bicycle[i].getAddress(),bicycle[i].getHistory()});
                        }
                        else
                            break;
                    }
                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExcelUtil eu= null;
                try {
                    eu = new ExcelUtil();
                    Bicycle[] bicycle=eu.excelUtilIn();
                    for(int i=model.getRowCount();i>0;i--){
                        model.removeRow(i-1);
                    }
                    for(int i=0;i<bicycle.length;i++){
                        if(bicycle[i]!=null){
                            model.addRow(new Object[]{bicycle[i].getNum(),bicycle[i].getAddress(),bicycle[i].getHistory()});
                        }
                        else
                            break;
                    }
                    AddDao ad=new AddDao();
                    ad.xmlAdd(bicycle);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExcelUtil eu= null;
                try {
                    eu = new ExcelUtil();
                    eu.excelUtilOut(model.getRowCount(),model);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        oppsite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length=model.getRowCount();
                Bicycle[] bicycle=new Bicycle[length];
                for(int i=0;i<length;i++){
                    bicycle[i]=new Bicycle();
                    bicycle[i].setNum((String) model.getValueAt(i,0));
                    bicycle[i].setAddress((String) model.getValueAt(i,1));
                    bicycle[i].setHistory((String) model.getValueAt(i,2));
                }
                SortUtil su=new SortUtil();
                Bicycle[]bicycles=su.sortUtil(bicycle,0,length-1);
                for(int i=model.getRowCount();i>0;i--){
                    model.removeRow(i-1);
                }
                for(int i=0;i<length;i++){
                    model.addRow(new Object[]{bicycles[i].getNum(),bicycles[i].getAddress(),bicycles[i].getHistory()});
                }
            }
        });
        rand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomThreadUtil random=new RandomThreadUtil();
                Thread random1=new Thread(random);
                random1.start();
            }
        });
        jf.setVisible(true);
    }
}
