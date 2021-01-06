package view;

import dao.AddDao;
import dao.DeleteDao;
import dao.EditDao;
import dao.SearchDao;
import model.Bicycle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
/**
 * 用户界面
 * @author 余嘉威
 * @version -version
 */
public class User {
    String num;
    String rtaddress;
    public void user(String account) throws SQLException {
        JFrame jf=new JFrame("课设");
        jf.setSize(550, 400);
        jf.setBackground(Color.white);
        jf.setLocation(300, 200);
        jf.setLayout(null);
        JTextField text=new JTextField();
        text.setBounds(50,30,200,30);
        jf.add(text);
        JButton search=new JButton("搜索");
        search.setBounds(270,30,80,30);
        jf.add(search);
        JButton rent=new JButton("租借");
        rent.setBounds(360,30,80,30);
        jf.add(rent);
        JLabel ba=new JLabel("归还地址");
        ba.setBounds(50,70,60,30);
        jf.add(ba);
        JTextField bt=new JTextField();
        bt.setBounds(120,70,130,30);
        jf.add(bt);
        JButton bb=new JButton("归还当前车辆");
        bb.setBounds(270,70,170,30);
        jf.add(bb);
        String[] titles = { "编号", "地址", "是否可用"};
        DefaultTableModel model=new DefaultTableModel(titles, 0);
        SearchDao ad=new SearchDao();
        JTable table = new JTable( ad.byUserSearch(model)){
            @Override
            public boolean isCellEditable(int row, int column) {
                    return false;
            }
        };
        JScrollPane scr = new JScrollPane(table);
        scr.setBounds(50,110,450,200);
        jf.add(scr);
        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    if (JOptionPane.showConfirmDialog(null, "确定要租该车吗", "",    JOptionPane.YES_NO_OPTION) == 0) {
                        if(model.getValueAt(table.getSelectedRow(),2).equals("可用")){
                        num= (String) model.getValueAt(table.getSelectedRow(),0);
                        rtaddress=(String) model.getValueAt(table.getSelectedRow(),1);
                        String address=(String) model.getValueAt(table.getSelectedRow(),1);
                        model.removeRow(table.convertRowIndexToModel(table.getSelectedRow()));
                        model.addRow(new Object[]{num,address,"使用中"});
                            EditDao ed=new EditDao();
                            try {
                                ed.reEdit(num);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "不可用");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "请选择要租的车");
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
                    Bicycle[]bicycle=sd.shUserSearch(text.getText());
                    for(int i=0;i<bicycle.length;i++) {
                        if (bicycle[i] != null) {
                            if(bicycle[i].getStatus1()==1){
                            model.addRow(new Object[]{bicycle[i].getNum(), bicycle[i].getAddress(),"维护中"});}
                            else if(bicycle[i].getStatus2()==1){
                                model.addRow(new Object[]{bicycle[i].getNum(), bicycle[i].getAddress(),"使用中"});}
                            else
                                model.addRow(new Object[]{bicycle[i].getNum(), bicycle[i].getAddress(),"可用"});
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditDao ed=new EditDao();
                AddDao ad=new AddDao();
                try {
                    ad.opAdd(account,bt.getText(),rtaddress,num);
                    ed.bkEdit(num,bt.getText());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
               for(int i=0;i<model.getRowCount();i++){
                   if(model.getValueAt(i,0).equals(num)){
                       model.removeRow(i);
                       model.addRow(new Object[]{num,bt.getText(),"可用"});
                   }
               }
            }
        });
        jf.setVisible(true);
    }
}
