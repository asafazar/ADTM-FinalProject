package com.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by asaf on 17/02/2016.
 */
public class ExcelReaderServlet  extends HttpServlet {
    private static final long serialVersionUID = 52L;

    public static String finalString = "";
    public void init() throws ServletException {

        Timer t = new Timer();

        t.scheduleAtFixedRate(
                new TimerTask()
                {
                    public void run()
                    {
                        List<JSONObject> jsonList = new ArrayList<JSONObject>();
                        List<Integer> columnNumbers = new ArrayList<Integer>();
                        columnNumbers.add(0);
                        columnNumbers.add(5);
                        columnNumbers.add(6);
                        columnNumbers.add(7);
                        columnNumbers.add(8);
                        columnNumbers.add(23);

                        String[] columnHeadlines = {
                                "שם נייר"
                                ,"","","","",
                                "כמות היצע ל1", "היצע ל1", "כמות ביקוש ל1", "ביקוש ל1",
                                "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                                "מספר נייר"};
                        try {
                            FileInputStream file = new FileInputStream(new File("C:/asaf/data.xlsx"));

                            //Create Workbook instance holding reference to .xlsx file
                            XSSFWorkbook workbook = new XSSFWorkbook(file);

                            //Get first/desired sheet from the workbook
                            XSSFSheet sheet = workbook.getSheetAt(0);

                            JSONObject jsonObj;

                            //Iterate through each rows one by one
                            Iterator<Row> rowIterator = sheet.iterator();

                            //cut headlines
                            //for (int i = 0; i < 3; i++)
                            //{
                            rowIterator.next();
                            //}

                            while (rowIterator.hasNext())
                            {
                                jsonObj = new JSONObject();
                                Row row = rowIterator.next();
                                //For each row, iterate through all the columns
                                Iterator<Cell> cellIterator = row.cellIterator();

                                while (cellIterator.hasNext())
                                {
                                    Cell cell = cellIterator.next();
                                    int cellColumn = cell.getColumnIndex();
                                    //Check the cell type and format accordingly
                                    if (columnNumbers.contains(cellColumn))
                                    {
                                        switch (cell.getCellType())
                                        {
                                            case Cell.CELL_TYPE_NUMERIC:
                                                if (cell.getColumnIndex() == 0)
                                                {
                                                    jsonObj.put(columnHeadlines[cellColumn],(long) cell.getNumericCellValue());
                                                }
                                                else
                                                {
                                                    jsonObj.put(columnHeadlines[cellColumn], cell.getNumericCellValue());
                                                }
                                                break;
                                            case Cell.CELL_TYPE_STRING:
                                                jsonObj.put(columnHeadlines[cellColumn],cell.getStringCellValue());
                                                break;
                                            case Cell.CELL_TYPE_FORMULA:
                                                switch(cell.getCachedFormulaResultType()) {
                                                    case Cell.CELL_TYPE_NUMERIC:
                                                        if (cellColumn == 23)
                                                        {
                                                            jsonObj.put(columnHeadlines[cellColumn],(long) cell.getNumericCellValue());
                                                        }
                                                        else
                                                        {
                                                            jsonObj.put(columnHeadlines[cellColumn],cell.getNumericCellValue());
                                                        }
                                                        break;
                                                    case Cell.CELL_TYPE_STRING:
                                                        jsonObj.put(columnHeadlines[cellColumn],cell.getRichStringCellValue());
                                                        break;
                                                    case Cell.CELL_TYPE_ERROR:
                                                        jsonObj.put(columnHeadlines[cellColumn],0);
                                                        break;
                                                }
                                                break;
                                            case Cell.CELL_TYPE_BLANK:
                                                jsonObj.put(columnHeadlines[cellColumn],0);
                                                break;
                                            case Cell.CELL_TYPE_ERROR:
                                                jsonObj.put(columnHeadlines[cellColumn],0);
                                                break;
                                        }
                                    }
                                }
                                jsonList.add(jsonObj);
                            }
                            file.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String allDataAsJson = "[";

                        for (JSONObject currJson : jsonList) {
                            allDataAsJson += currJson;
                            allDataAsJson += ",";
                        }

                        String finalJson = allDataAsJson.substring(0, allDataAsJson.length() - 1);
                        finalJson += "]";
                        finalString = finalJson;
                    }
                },
                0,      // run first occurrence immediately
                30000);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.getWriter().write(finalString);
    }

        protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
