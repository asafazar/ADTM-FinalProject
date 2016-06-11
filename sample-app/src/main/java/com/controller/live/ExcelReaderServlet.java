package com.controller.live;

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
    String[] fullColumnHeaders = {
            "Strike Price", "Trade","Base Price","Current Price","Daily Change", "Ask 1", "Ask Amount 1", "Bid 1","Bid Amount 1",
            "unknown","Theoretical value","Daily Value","Daily cycle","Deal time","Open positions","s.t. stock market",
            "s.t. skew","s.t. glume","Deals number","Predicted","Contracts for sale","Contracts for buy",
            "Contracts number", "ContractId"
    };
    public static String finalString = "";
    public void init() throws ServletException {

        Timer t = new Timer();

        t.scheduleAtFixedRate(
                new TimerTask()
                {
                    public void run()
                    {
                        List<JSONObject> callJsonList = new ArrayList<JSONObject>();
                        List<JSONObject> putJsonList = new ArrayList<JSONObject>();

                        try {
                            FileInputStream file = new FileInputStream(new File("C:/asaf/data.xlsx"));

                            //Create Workbook instance holding reference to .xlsx file
                            XSSFWorkbook workbook = new XSSFWorkbook(file);

                            //Get first/desired sheet from the workbook
                            XSSFSheet sheet = workbook.getSheetAt(0);

                            JSONObject callJsonObj;
                            JSONObject putJsonObj;

                            //Iterate through each rows one by one
                            Iterator<Row> rowIterator = sheet.iterator();

                            rowIterator.next();

                            while (rowIterator.hasNext())
                            {
                                callJsonObj = new JSONObject();
                                putJsonObj = new JSONObject();
                                Row callRow = rowIterator.next();
                                Row putRow = rowIterator.next();
                                //For each row, iterate through all the columns
                                Iterator<Cell> callCellIterator = callRow.cellIterator();
                                Iterator<Cell> putCellIterator = putRow.cellIterator();

                                while (callCellIterator.hasNext())
                                {
                                    Cell callCell = callCellIterator.next();
                                    Cell putCell = putCellIterator.next();
                                    int cellColumn = callCell.getColumnIndex();
                                    //Check the cell type and format accordingly
                                    insertCellToJson(callJsonObj, callCell, cellColumn, " C");
                                    insertCellToJson(putJsonObj, putCell, cellColumn, " P");
                                }

                                callJsonList.add(callJsonObj);
                                putJsonList.add(putJsonObj);
                            }
                            file.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String allDataAsJson = "[";

                        for (int i = 0; i < callJsonList.size(); i++)
                        {
                            String tempCall = callJsonList.get(i).toString();
                            String tempPut = putJsonList.get(i).toString();
                            allDataAsJson += tempCall.substring(0, tempCall.length() - 1) + ","
                                    + tempPut.substring(1) + ",";
                        }

                        String finalJson = allDataAsJson.substring(0, allDataAsJson.length() - 1);
                        finalJson += "]";
                        finalString = finalJson;
                    }
                },
                0,      // run first occurrence immediately
                30000);
    }

    private void insertCellToJson(JSONObject jsonObj, Cell cell, int cellColumn, String addon) {
        switch (cell.getCellType())
        {
            case Cell.CELL_TYPE_NUMERIC:
                jsonObj.put(fullColumnHeaders[cellColumn] + addon, cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                jsonObj.put(fullColumnHeaders[cellColumn] + addon, cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                switch(cell.getCachedFormulaResultType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        if (cellColumn == 23)
                        {
                            jsonObj.put(fullColumnHeaders[cellColumn] + addon,(long) cell.getNumericCellValue());
                        }
                        else
                        {
                            jsonObj.put(fullColumnHeaders[cellColumn] + addon,cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        jsonObj.put(fullColumnHeaders[cellColumn] + addon,cell.getRichStringCellValue());
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        jsonObj.put(fullColumnHeaders[cellColumn] + addon,0);
                        break;
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                jsonObj.put(fullColumnHeaders[cellColumn] + addon,0);
                break;
            case Cell.CELL_TYPE_ERROR:
                jsonObj.put(fullColumnHeaders[cellColumn] + addon,0);
                break;
        }
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
