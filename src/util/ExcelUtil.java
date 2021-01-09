package util;

import model.Bicycle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.*;
/**
 * EXCEL文件导入导出
 * @author 余嘉威
 * @version -version
 */
public class ExcelUtil {
    public ExcelUtil() throws IOException {
}
    /**
     * 导入excel
     * @return Bicycle[]
     */
     public Bicycle[] excelUtilIn() throws IOException {
        File file=null;
         Bicycle[] bicycle=new Bicycle[100];
         JFileChooser jfc=new JFileChooser(new File("."));
         int result =jfc.showOpenDialog(null);
         if(result==JFileChooser.APPROVE_OPTION) {
             file=jfc.getSelectedFile();
         }
         FileInputStream fis = new FileInputStream(file);
         Workbook wb = new XSSFWorkbook(fis);
         Sheet sheet = wb.getSheetAt(0);

         for(int i=0;i<sheet.getPhysicalNumberOfRows();i++){
             bicycle[i]=new Bicycle();
             Row row=sheet.getRow(i);
             Cell c1=row.getCell(0);
             c1.setCellType(CellType.STRING);
             bicycle[i].num=c1.getStringCellValue();
             Cell c2=row.getCell(1);
             c2.setCellType(CellType.STRING);
             bicycle[i].address=c2.getStringCellValue();
             Cell c3=row.getCell(3);
             c1.setCellType(CellType.STRING);
             bicycle[i].history=c1.getStringCellValue();
         }
         return bicycle;
     }
    /**
     * excel导出
     * @param rownum,model
     */
     public void excelUtilOut(int rownum, DefaultTableModel model) throws IOException {
         File file=new File("source/test1.xlsx");
         Workbook workbook=new XSSFWorkbook();
         Sheet sheet = workbook.createSheet("test1");
         for(int i=0;i<rownum;i++){
             Row row = sheet.createRow(i);
             for(int j=0;j<3;j++){
                 Cell cell = row.createCell(j);
                 cell.setCellValue((String)model.getValueAt(i,j));
             }
         }
         FileOutputStream fos=new FileOutputStream(file);
         workbook.write(fos);
         fos.close();
     }
     public void xmlAdd(){

     }
}
