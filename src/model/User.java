package model;
/**
 * 用户类 没返回直接跳转了 可以但没必要
 * @author 余嘉威
 * @version -version
 */
public class User {
    String name;
    String num;
    int credit;
    int administrator;
    String account;
    String password;
    public void setName(String name) {
        this.name = name;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setAdministrator(int administrator) {
        this.administrator = administrator;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public int getCredit() {
        return credit;
    }

    public int getAdministrator() {
        return administrator;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }


}
