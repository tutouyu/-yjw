package com.company;

import dao.AddDao;
import model.Bicycle;
import org.apache.poi.ss.formula.functions.T;
import util.DbConnectionUtil;
import util.ExcelUtil;
import util.RandomThreadUtil;
import util.SortUtil;
import view.Adiministrator;
import view.Login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Random;
/**
 * 测试类
 * @author 余嘉威
 * @version -version
 */
public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Login login=new Login();
        login.login();
    }
}

