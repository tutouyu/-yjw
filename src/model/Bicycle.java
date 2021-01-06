package model;
/**
 * 单车信息类
 * @author 余嘉威
 * @version -version
 */
public class Bicycle {
    public String num;
    public String address;
    public String history;
    public int status1=0;
    public int status2=0;
    public Bicycle(){}
    public Bicycle(String num,String address,String history,int status1,int status2){
        this.num=num;
        this.address=address;
        this.history=history;
        this.status1=status1;
        this.status2=status2;
    }
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getStatus1() {
        return status1;
    }

    public void setStatus1(int status1) {
        this.status1 = status1;
    }

    public int getStatus2() {
        return status2;
    }

    public void setStatus2(int status2) {
        this.status2 = status2;
    }
}
