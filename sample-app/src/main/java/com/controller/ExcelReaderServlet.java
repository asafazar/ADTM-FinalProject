package com.controller;

import com.google.gson.Gson;
import com.model.liveUpdates.LiveDataValue;
import com.model.liveUpdates.LiveSumData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asaf on 17/02/2016.
 */
public class ExcelReaderServlet  extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        final String WEEK_SHEET_NAME = "Week";
        final String MONTH_SHEET_NAME = "Month";
        String WEEK_ADD_SHEET_NAME = " ";
        String MONTH_ADD_SHEET_NAME = " ";
        final String WEEK_DATA_SHEET_NAME = "Data W";
        final String MONTH_DATA_SHEET_NAME = "Data M";

        try {
            String file = "C:/פוזיציות .xlsx";
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            List<HSSFSheet> sheets = new ArrayList<>();
            HSSFSheet weekSheet = wb.getSheet(WEEK_SHEET_NAME);
            HSSFSheet monthSheet = wb.getSheet(MONTH_SHEET_NAME);
            HSSFSheet weekDataSheet = wb.getSheet(WEEK_DATA_SHEET_NAME);
            HSSFSheet monthDataSheet = wb.getSheet(MONTH_DATA_SHEET_NAME);
            sheets.add(weekSheet);
            sheets.add(monthSheet);
            sheets.add(weekDataSheet);
            sheets.add(monthDataSheet);
            HSSFRow row;
            List<LiveSumData> weekSumDataValuesList = new ArrayList<>();
            List<LiveSumData> monthSumDataValuesList = new ArrayList<>();
            List<LiveSumData> weekSumAddDataValuesList = new ArrayList<>();
            List<LiveSumData> monthSumAddDataValuesList = new ArrayList<>();
            List<LiveDataValue> weekDataValuesList = new ArrayList<>();
            List<LiveDataValue> monthDataValuesList = new ArrayList<>();
            int addNumber = 2;

            // adding another sheets
            for (int i = 0; i < wb.getNumberOfSheets() - 4; i++)
            {
                HSSFSheet weekAddkSheet = wb.getSheet(WEEK_ADD_SHEET_NAME + String.valueOf(addNumber));
                HSSFSheet monthAddSheet = wb.getSheet(MONTH_ADD_SHEET_NAME + String.valueOf(addNumber));
                sheets.add(weekAddkSheet);
                sheets.add(monthAddSheet);
                addNumber +=2;
            }

            int rows; // No of rows
            for (HSSFSheet currSheet : sheets) {
                //rows = currSheet.getPhysicalNumberOfRows();
                if (!weekSumAddDataValuesList.isEmpty())
                {
                    String json = new Gson().toJson(weekSumAddDataValuesList);
                    response.setContentType("application/json");
                    response.getWriter().write(json);
                    weekSumAddDataValuesList = new ArrayList<>();
                }

                if (!monthSumAddDataValuesList.isEmpty())
                {
                    String json = new Gson().toJson(monthSumAddDataValuesList);
                    response.setContentType("application/json");
                    response.getWriter().write(json);
                    monthSumAddDataValuesList = new ArrayList<>();
                }
                int cols = 0; // No of columns
                int tmp = 0;

                // This trick ensures that we get the data properly even if it doesn't start from first few rows
                // additional edit problem cells
                for (int i = 0; i < 30 ; i++) {
                    row = currSheet.getRow(i);
                    if (row != null) {
                        tmp = currSheet.getRow(i).getPhysicalNumberOfCells();
                        if (tmp > cols) cols = tmp;


                        for (int c = 0; i < cols; i++)
                        {
                            if (row.getCell(c).toString().equals(null) ||
                                    row.getCell(c).toString().equals("#N/A"))
                            {
                                row.getCell(c).setCellValue(0.0);
                            }
                        }
                    }

                    if (currSheet.getSheetName().startsWith(WEEK_SHEET_NAME) ||
                            currSheet.getSheetName().startsWith(MONTH_SHEET_NAME)){
                        monthSumAddDataValuesList = new ArrayList<>();
                        LiveSumData currLiveSumData = new LiveSumData(Double.valueOf(row.getCell(0).toString()),
                                Double.valueOf(row.getCell(1).toString()),Double.valueOf(row.getCell(2).toString()),
                                Double.valueOf(row.getCell(3).toString()),Double.valueOf(row.getCell(4).toString()),
                                Double.valueOf(row.getCell(5).toString()),Double.valueOf(row.getCell(6).toString()),
                                Double.valueOf(row.getCell(7).toString()),Double.valueOf(row.getCell(8).toString()),
                                Double.valueOf(row.getCell(9).toString()),Double.valueOf(row.getCell(10).toString()),
                                Double.valueOf(row.getCell(11).toString()),Double.valueOf(row.getCell(12).toString()),
                                Double.valueOf(row.getCell(13).toString()),Double.valueOf(row.getCell(14).toString()),
                                Double.valueOf(row.getCell(15).toString()),Double.valueOf(row.getCell(16).toString()),
                                Double.valueOf(row.getCell(17).toString()),Double.valueOf(row.getCell(18).toString()),
                                Double.valueOf(row.getCell(20).toString()),Double.valueOf(row.getCell(21).toString()));

                        if (currSheet.getSheetName().equals(WEEK_SHEET_NAME))
                        {
                            monthSumDataValuesList.add(currLiveSumData);
                        }
                        else if (currSheet.getSheetName().equals(MONTH_SHEET_NAME))
                        {
                            weekSumDataValuesList.add(currLiveSumData);
                        }
                        else if (currSheet.getSheetName().startsWith(WEEK_SHEET_NAME + ""))
                        {
                            weekSumAddDataValuesList.add(currLiveSumData);
                        }
                        else if (currSheet.getSheetName().startsWith(MONTH_SHEET_NAME + ""))
                        {
                            monthSumAddDataValuesList.add(currLiveSumData);
                        }
                    }
                    else if (currSheet.getSheetName().startsWith(WEEK_DATA_SHEET_NAME) ||
                            currSheet.getSheetName().startsWith(MONTH_DATA_SHEET_NAME)){

                        LiveDataValue liveDataValueRaw;

                        if (row != null) {
                                liveDataValueRaw = new LiveDataValue(Double.valueOf(row.getCell(0).toString()),
                                        Double.valueOf(row.getCell(1).toString()),
                                        Double.valueOf(row.getCell(2).toString()));

                                if (currSheet.getSheetName().equals(WEEK_DATA_SHEET_NAME))
                                {
                                    weekDataValuesList.add(liveDataValueRaw);
                                }
                                else
                                {
                                    monthDataValuesList.add(liveDataValueRaw);
                                }
                        }
                    }
                }
            }

            String json1 = new Gson().toJson(weekSumDataValuesList);
            String json2 = new Gson().toJson(monthSumDataValuesList);
            String json3 = new Gson().toJson(weekDataValuesList);
            String json4 = new Gson().toJson(monthSumDataValuesList);
            response.setContentType("application/json");
            response.getWriter().write(json1);
            response.getWriter().write(json2);
            response.getWriter().write(json3);
            response.getWriter().write(json4);
        }
        catch(Exception ioe) {
            ioe.printStackTrace();
        }
    }

        protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
