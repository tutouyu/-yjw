package util;

import model.Bicycle;

import java.sql.SQLException;

public class SortUtil {
    /**
     * 快排倒序
     * @param bicycle,low,high
     * @return Bicycle
     */
    public Bicycle[] sortUtil(Bicycle[] bicycle,int low,int high){
        if (low < high) {
       int i   = low;
       int j   = high;
        int key = Integer.parseInt(bicycle[i].num);
       while (i < j) {
             while (i < j && Integer.parseInt(bicycle[j].num) <= key) {
              j--;
           }
            if (i < j) {
                bicycle[i].num =bicycle[j].num ;
           }
            while (i < j && Integer.parseInt(bicycle[i].num) >= key) {
                i++;
             }
          if (i < j) {
              bicycle[j].num =bicycle[i].num;
             }
         }
            bicycle[i].num= Integer.toString(key);
         sortUtil(bicycle, low, i - 1);
         sortUtil(bicycle, i + 1, high);
     }
        return bicycle;
    }

}
