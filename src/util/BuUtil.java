package util;

import dao.SearchDao;
import model.Bicycle;

import java.io.*;
import java.sql.SQLException;
/**
 * 文件数据流操作 备份
 * @author 余嘉威
 * @version -version
 */
public class BuUtil {
    /**
     *数据流存文件备份
     * @exception SQLException, IOException
     */
    public BuUtil() throws SQLException, IOException {
        SearchDao sd=new SearchDao();
        Bicycle[]bicycle= sd.bySearch();

        String filename="D:\\课设.text";
        BufferedWriter bw=new BufferedWriter(new FileWriter(filename));
        for(int i=0;i<bicycle.length;i++){
            if(bicycle[i]!=null){
                bw.write(bicycle[i].getNum()+" "+bicycle[i].getAddress()+" "+bicycle[i].getHistory()+" "+bicycle[i].getStatus1()+" "+bicycle[i].getStatus2());
                bw.newLine();
                bw.flush();
            }
            else
                break;
        }

    }
    /**
     *数据流读文件备份
     * @return Bicycle[]
     * @exception IOException
     */
    public Bicycle[] buUtil() throws IOException {
        Bicycle[]bicycle=new Bicycle[100000];
        String filename="D:\\课设.text";
        String str;
        int i=0;
        BufferedReader br=new BufferedReader(new FileReader(filename));
        while((str=br.readLine())!=null){
            String arr[]=str.split("\\s+");
            bicycle[i]=new Bicycle(arr[0],arr[1],arr[2],Integer.parseInt(arr[3]),Integer.parseInt(arr[4]));
            i++;
        }
        return bicycle;
    }

}
