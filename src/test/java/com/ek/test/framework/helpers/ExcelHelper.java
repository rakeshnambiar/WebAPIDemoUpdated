package com.ek.test.framework.helpers;

import com.ek.test.framework.hooks.ScenarioHook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by S427701 on 18/01/2016.
 */
public class ExcelHelper {
    public static String ReadExcelData(String SheetName, String ParmName) {
        java.sql.Connection Conn = null;
        String Value = null;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=src\\test\\resources\\TestData.xlsx");
            java.sql.Statement stmt = Conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select * from [" + SheetName + "$] where Parameter='" + ParmName + "';";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Value = rs.getString("Value");
            }
        } catch (Exception e) {
            System.err.println(e);
            return Value;
        } finally {
            try {
                Conn.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return Value;
    }

    /**
     * Author - Rakesh
     * To fetch the multiple values from excel
     * @SheetName  Name of the excel sheet
     * @ParmName   parameter name in the excel sheet in single quote seperated by comma
     * @return String array
     * @throws ParseException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static String[] ReadExcelValues(String SheetName, String ParmName) {
        java.sql.Connection Conn = null;
        int arrLength = 1;
        String[] parmCount = ParmName.split(",");
        if(parmCount.length > 1){
            arrLength = parmCount.length;
        }
        String[] Value = new String[arrLength];
          try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=src\\test\\resources\\TestData.xlsx");
            java.sql.Statement stmt = Conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * FROM [" + SheetName + "$] WHERE Parameter IN (" + ParmName + ");";
            ResultSet rs = stmt.executeQuery(query);
            int iterator = 0;
            while (rs.next()) {
                Value[iterator] = rs.getString(2);
                iterator = iterator+1;
            }
        } catch (Exception e) {
            System.err.println(e);
            return Value;
        } finally {
            try {
                Conn.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return Value;
    }

    /**
     * Author - Rakesh
     * To Verify the Excel Value
     *
     * @param strfilePath, value(s) to be checked in the excel file seperated by '|' sysmbol
     * @return boolean
     */
    public static boolean VerifyExcelValue(String strfilePath, String strExpectedVal) {
        boolean Found = false;
        try {
            InputStream input = new FileInputStream(strfilePath);
            HSSFWorkbook XLWB = new HSSFWorkbook(input);
            HSSFSheet XLSheet = XLWB.getSheetAt(0);
            int iterator;
            String[] ExpVal = strExpectedVal.split("\\|");
            for (iterator = 0; iterator < ExpVal.length; iterator++) {
                ScenarioHook.getScenario().write("Verifying the Value : " + ExpVal[iterator] + "<br/>");
                Found = false;
                for (Row row1 : XLSheet) {
                    for (Cell cell1 : row1) {
                        if (cell1.getCellType() == Cell.CELL_TYPE_STRING) {
                            if (cell1.getRichStringCellValue().getString().trim().equals(ExpVal[iterator])) {
                                Found = true;
                                break;
                            }
                        }
                    }
                }
                if (!Found) {
                    ScenarioHook.getScenario().write("Error : Value NOT found in the Excel file : " + ExpVal[iterator] + "<br/>");
                    break;
                }
            }
            XLWB.close();
        } catch (Exception e) {
            Found = false;
        }
        return Found;
    }
}
