import com.mysql.cj.jdbc.Driver;
import org.apache.poi.hwpf.model.types.LSTFAbstractType;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class tetxt{

    public static void main(String[] args) throws IOException, SQLException {

        DriverManager.registerDriver(new Driver());
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba8_2";


        Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("Urovni2_1_1_new_1_2.xlsx"));
        Sheet readSheet = readWorkbook.getSheetAt(0);
        Row header_row = readSheet.getRow(1);
        int count_of_columns = header_row.getPhysicalNumberOfCells();
//        Cell cell1 = header_row.getCell(6);

        Row test_row = readSheet.getRow(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(dateFormat.format(test_row.getCell(2).getDateCellValue()));


//        for (Row row : readSheet){
//            for (int i = 0; i < count_of_columns; i++){
//                System.out.println(row.getCell(i));
//            }
//        }
//        Statement stmt = con.createStatement();
//        String query = "SELECT * FROM " + tablename;
//        ResultSet rs = stmt.executeQuery(query);
//        ResultSetMetaData columns_name = rs.getMetaData();
//        int database_count_of_columns = columns_name.getColumnCount();
//        System.out.println(database_count_of_columns);
//
//
//        PreparedStatement prstmt = con.prepareStatement("INSERT INTO " + tablename + " (?) VALUE (?)");
//        PreparedStatement prstmt1 = con.prepareStatement("UPDATE " + tablename + " set ? = ? where");
//
//        String[] tt;
//        for (Row row : readSheet){
//            prstmt.setString(1, String.valueOf(columns_name.getColumnName(0)));
//            prstmt.setInt(2, Integer.parseInt(String.valueOf(row.getCell(1))));
//            prstmt.executeUpdate();
//            for (int i = 1; i < count_of_columns; i++){
//                if (row.getCell(i).getCellType() == CellType.BLANK){
//
//                }else{
//                    System.out.println(test_row.getCell(i));
//                }
//            }
//
//
//
//       }
    }
}

