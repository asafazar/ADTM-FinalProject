package com.controller;

import com.pusher.rest.Pusher;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by asaf on 03/03/2016.
 */
@WebListener
public class BackgroundJobManager implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        Timer t = new Timer();

        t.scheduleAtFixedRate(
                new TimerTask()
                {
                    public void run()
                    {
                        List<JSONObject> jsonList = new ArrayList<JSONObject>();
                        List<Integer> columnNumbers = new ArrayList<Integer>();
                        columnNumbers.add(0);
                        columnNumbers.add(13);
                        columnNumbers.add(15);
                        columnNumbers.add(16);
                        columnNumbers.add(17);
                        columnNumbers.add(18);
                        columnNumbers.add(23);
                        columnNumbers.add(28);
                        columnNumbers.add(29);
                        columnNumbers.add(30);
                        columnNumbers.add(31);
                        columnNumbers.add(33);

                        String[] columnNames = new String[]{"ContractNumber", "", "", "", "", "", "", "", ""
                                , "", "", "", "", "PutTheoreticalValue", "", "PutAsk", "PutTotalAsk","PutBid",
                                "PutTotalBid", "", "", "", "", "StrikePrice", "", "", "", "", "CallAsk",
                                "CallTotalAsk", "CallBid","CallTotalBid", "", "CallTheoreticalValue"};

                        try {
                            FileInputStream file = new FileInputStream(new File("C:/Book2.xlsx"));

                            //Create Workbook instance holding reference to .xlsx file
                            XSSFWorkbook workbook = new XSSFWorkbook(file);

                            //Get first/desired sheet from the workbook
                            XSSFSheet sheet = workbook.getSheetAt(0);

                            JSONObject jsonObj;

                            //Iterate through each rows one by one
                            Iterator<Row> rowIterator = sheet.iterator();

                            //cut headlines
                            for (int i = 0; i < 3; i++)
                            {
                                rowIterator.next();
                            }

                            while (rowIterator.hasNext())
                            {
                                jsonObj = new JSONObject();
                                Row row = rowIterator.next();
                                //For each row, iterate through all the columns
                                for (Integer currColumn : columnNumbers)
                                {
                                    Cell cell = row.getCell(currColumn);
                                    switch (cell.getCellType())
                                    {
                                        case Cell.CELL_TYPE_NUMERIC:
                                            if (currColumn == 0)
                                            {
                                                jsonObj.put(columnNames[currColumn],(long) cell.getNumericCellValue());
                                            }
                                            else
                                            {
                                                jsonObj.put(columnNames[currColumn], cell.getNumericCellValue());
                                            }
                                            break;
                                        case Cell.CELL_TYPE_STRING:
                                            jsonObj.put(columnNames[currColumn],cell.getStringCellValue());
                                            break;
                                        case Cell.CELL_TYPE_FORMULA:
                                            switch(cell.getCachedFormulaResultType()) {
                                                case Cell.CELL_TYPE_NUMERIC:
                                                    if (currColumn == 0)
                                                    {
                                                        jsonObj.put(columnNames[currColumn],(long) cell.getNumericCellValue());
                                                    }
                                                    else
                                                    {
                                                        jsonObj.put(columnNames[currColumn],cell.getNumericCellValue());
                                                    }
                                                    break;
                                                case Cell.CELL_TYPE_STRING:
                                                    jsonObj.put(columnNames[currColumn],cell.getRichStringCellValue());
                                                    break;
                                                case Cell.CELL_TYPE_ERROR:
                                                    jsonObj.put(columnNames[currColumn],0);
                                                    break;
                                            }
                                            break;
                                        case Cell.CELL_TYPE_BLANK:
                                            jsonObj.put(columnNames[currColumn],0);
                                            break;
                                    }
                                }
                                jsonList.add(jsonObj);
                            }
                            file.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                        Pusher pusher = new Pusher("185377", "6ddefb4dd81db7c72a0d", "3f69261d7626161b410e");

                        for (JSONObject currJson : jsonList)
                        {
                            pusher.trigger("test_channel", "my_event", currJson);
                        }
                    }
                },
                0,      // run first occurrence immediately
                30000);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
}
