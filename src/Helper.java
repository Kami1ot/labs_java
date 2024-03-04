import com.mysql.cj.jdbc.Driver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Helper {
    private static Statement stmt;

    public  void Connection(String url, String user, String password) throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection con = DriverManager.getConnection(url, user, password);
        stmt = con.createStatement();


        if (!con.isClosed()) {
            System.out.println("--------------------------------------------------");
            System.out.println("Data Base Connected");
            System.out.println("--------------------------------------------------");
        }

    }

    public void show_table() throws SQLException {
        System.out.println("--------------------------------------------------");
        ResultSet rs = stmt.executeQuery("SHOW TABLES");
        int counter = 0;
        System.out.printf("| %-4s | %-20s |\n", "ID", "Названия таблиц");
        System.out.println("--------------------------------------------------");
        while (rs.next()) {
            counter++;
            System.out.printf("| %-4d | %-20s |\n", counter, rs.getString(1));
        }
        System.out.println("--------------------------------------------------");
        System.out.println("Всего таблиц: " + counter);

    }


    public void create_table() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название таблицы: ");
        String tablename = in.nextLine();
        try {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + tablename + " (test_column int);");
            System.out.println("Таблица " + tablename + " создана");
        }catch(SQLSyntaxErrorException e){
            System.out.println("Неверный ввод названия таблицы, введите другое");
        }

    }


    public void to_excel(String tablename, String filename) throws IOException, SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tablename + ";");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);


        // Записываем данные из ResultSet в Excel
        Row h_row = sheet.createRow(0);
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
            Cell head_cell = h_row.createCell(i-1);
            head_cell.setCellValue(rs.getMetaData().getColumnName(i));
            head_cell.setCellStyle(cellStyle);
        }

        int rownum = 1;
        while (rs.next()) {
            Row row = sheet.createRow(rownum++);
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                Cell cell = row.createCell(i - 1);
                cell.setCellValue(rs.getString(i));
                cell.setCellStyle(cellStyle);
            }
        }
        // Сохраняем книгу Excel в файл
        FileOutputStream out = new FileOutputStream(filename);
        workbook.write(out);
        out.close();
        workbook.close();

        // Выводим содержимое книги Excel на экран


        Workbook readWorkbook = WorkbookFactory.create(new FileInputStream(filename));
        Sheet readSheet = readWorkbook.getSheetAt(0);

        Row header_row = readSheet.getRow(0);

        int maxWidth = 0;
        for (Row row : readSheet) {
            for (Cell cell : row) {
                maxWidth = Math.max(maxWidth, cell.getStringCellValue().length());
            }
        }
        System.out.println("Содержимое книги Excel:");
        System.out.println("-".repeat(rs.getMetaData().getColumnCount() * (maxWidth + rs.getMetaData().getColumnCount())+6));
        for (Cell cell : header_row){
            System.out.printf("| %-"+(maxWidth+2)+"s ",  cell.getStringCellValue());
        }
        System.out.println("|");
        System.out.println("-".repeat(rs.getMetaData().getColumnCount() * (maxWidth + rs.getMetaData().getColumnCount())+6));
        for (int i = 1; i < readSheet.getPhysicalNumberOfRows(); i++) {
            Row row = readSheet.getRow(i);
            for (Cell cell : row) {
                System.out.printf("| %-"+(maxWidth+2)+"s ", cell.getStringCellValue());
            }
            System.out.println("|");
        }
        readWorkbook.close();
        System.out.println("-".repeat(rs.getMetaData().getColumnCount() * (maxWidth + rs.getMetaData().getColumnCount())+6));
    }

    public void execute_Update(String query) throws SQLException {
        stmt.executeUpdate(query);
    }

}
