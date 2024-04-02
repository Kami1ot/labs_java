import com.mysql.cj.jdbc.Driver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import static org.apache.poi.hsmf.datatypes.Types.BOOLEAN;

public class Helper {
    private Statement stmt;
    private Connection con;


    public void Connection(String url, String user, String password) {

        try {
            DriverManager.registerDriver(new Driver());
            this.con = DriverManager.getConnection(url, user, password);
            this.stmt = con.createStatement();

            if (!con.isClosed()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Data Base Connected");
                System.out.println("--------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }


    }

    public void show_table() throws SQLException {

        System.out.println("--------------------------------------------------");
        ResultSet rs = this.stmt.executeQuery("SHOW TABLES");
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
            this.stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + tablename + " (test_column int);");
            System.out.println("Таблица " + tablename + " создана");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Неверное название таблицы, введите другое название");
        }

    }


    public void to_excel(String tablename, String filename) throws IOException, SQLException {
        ResultSet rs = this.stmt.executeQuery("SELECT * FROM " + tablename + ";");

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
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            Cell head_cell = h_row.createCell(i - 1);
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
//        выводл названий столбцов
        System.out.println("Содержимое книги Excel:");
        System.out.println("-".repeat(rs.getMetaData().getColumnCount() * (maxWidth + rs.getMetaData().getColumnCount()) + 6));
        for (Cell cell : header_row) {
            System.out.printf("| %-" + (maxWidth + 2) + "s ", cell.getStringCellValue());
        }
//        вывод данных
        System.out.println("|");
        System.out.println("-".repeat(rs.getMetaData().getColumnCount() * (maxWidth + rs.getMetaData().getColumnCount()) + 6));
        for (int i = 1; i < readSheet.getPhysicalNumberOfRows(); i++) {
            Row row = readSheet.getRow(i);
            for (Cell cell : row) {
                System.out.printf("| %-" + (maxWidth + 2) + "s ", cell.getStringCellValue());
            }
            System.out.println("|");
        }
        readWorkbook.close();
        System.out.println("-".repeat(rs.getMetaData().getColumnCount() * (maxWidth + rs.getMetaData().getColumnCount()) + 6));

    }

    public void execute_Update(String query) throws SQLException {
        this.stmt.executeUpdate(query);
    }

    public void importExcelToDatabase(String filePath, String tableName) throws IOException, SQLException {

        stmt.executeUpdate("DROP TABLE IF EXISTS " + tableName);

        Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));
        Sheet sheet = workbook.getSheetAt(0);


        Row headerRow = sheet.getRow(0);
        int columnCount = headerRow.getPhysicalNumberOfCells();
        List<String> columnNames = new ArrayList<>();

        for (int i = 0; i < columnCount; i++) {
            columnNames.add(headerRow.getCell(i).getStringCellValue());
        }


        StringBuilder createTableQuery = new StringBuilder();
        createTableQuery.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");

        for (int i = 0; i < columnNames.size(); i++) {
            if (i > 0) {
                createTableQuery.append(", ");
            }
            createTableQuery.append("`").append(columnNames.get(i)).append("`").append(" VARCHAR(255)");
        }

        createTableQuery.append(");");


        this.stmt.executeUpdate(createTableQuery.toString());


        StringBuilder insertDataQuery = new StringBuilder();
        insertDataQuery.append("INSERT INTO ").append(tableName).append(" (");

        for (int i = 0; i < columnCount; i++) {
            if (i > 0) {
                insertDataQuery.append(", ");
            }
            insertDataQuery.append("`").append(columnNames.get(i)).append("`");
        }

        insertDataQuery.append(") VALUES (");

        for (int i = 0; i < columnCount; i++) {
            if (i > 0) {
                insertDataQuery.append(", ");
            }
            insertDataQuery.append("?");
        }

        insertDataQuery.append(")");


        PreparedStatement prstmt = this.con.prepareStatement(insertDataQuery.toString());
        for (Row row : sheet) {
            if (row.getRowNum() == 0) { // Пропускаем первую строку, если она содержит заголовки
                continue;
            }

            for (int i = 0; i < columnCount; i++) {
                Cell cell = row.getCell(i);
                if (cell == null || cell.getCellType() == CellType.BLANK) {
                    prstmt.setObject(i + 1, null);
                } else {
                    switch (cell.getCellType()) {
                        case STRING:
                            prstmt.setString(i + 1, cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String formattedDate = dateFormat.format(cell.getDateCellValue());
                                prstmt.setString(i + 1, formattedDate);
                            } else {
                                prstmt.setDouble(i + 1, cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            prstmt.setBoolean(i + 1, cell.getBooleanCellValue());
                            break;
                        default:
                            prstmt.setObject(i + 1, null);
                            break;
                    }

                }
            }

            prstmt.executeUpdate();

        }

    }

    public void rs_to_console(String query) throws SQLException {
        ResultSet rs = this.stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int maxWidth = 0;
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            maxWidth = Math.max(maxWidth, rsmd.getColumnLabel(i).length());
        }

        System.out.println("Содержимое Запроса:");
        System.out.println("-".repeat(rsmd.getColumnCount() * (maxWidth + rsmd.getColumnCount()) + 6));
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.printf("| %-" + (maxWidth + 2) + "s ", rsmd.getColumnLabel(i));
        }
        System.out.println("|");
        System.out.println("-".repeat(rsmd.getColumnCount() * (maxWidth + rsmd.getColumnCount()) + 6));
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String value = rs.getString(i);
                System.out.printf("| %-" + (maxWidth + 2) + "s ", value == null ? "" : value);
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(rsmd.getColumnCount() * (maxWidth + rsmd.getColumnCount()) + 6));
        rs.close();


    }
}
