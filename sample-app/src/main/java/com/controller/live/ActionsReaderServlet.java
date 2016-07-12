package com.controller.live;

import com.DB.MongoDBStrikesDAO;
import com.DB.MongoDBLastStrikesDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.actions.Strike;
import com.model.utils.Constants;
import com.mongodb.MongoClient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

/**
 * Created by asaf on 10/06/2016.
 */
public class ActionsReaderServlet extends HttpServlet {
    private static final long serialVersionUID = 54L;
    private static final String KEY_VALUE_SEPARATOR = '"' + ":" + '"';
    private static final String FIELD_SEPARATOR = '"' + "," + '"';
    public static String finalString = "";
    List<Strike> liveStrikes;


    public void init(final ServletConfig sc) throws ServletException {
        Timer t = new Timer();
        t.scheduleAtFixedRate(
                new TimerTask()
                {
                    public void run()
                    {
                        liveStrikes = new ArrayList<>();
                        String allDataAsJson = "[";

                        try
                        {
                            FileInputStream file = new FileInputStream(new File("C:/asaf/data.xlsx"));
                            XSSFWorkbook workbook = new XSSFWorkbook(file);
                            XSSFSheet sheet = workbook.getSheetAt(0);

                            Iterator<Row> rowIterator = sheet.iterator();
                            rowIterator.next();

                            while (rowIterator.hasNext())
                            {
                                String currActionString = "{";
                                Row currRaw = rowIterator.next();
                                //For each row, iterate through all the columns
                                Iterator<Cell> callCellIterator = currRaw.cellIterator();

                                while (callCellIterator.hasNext())
                                {
                                    Cell currCell = callCellIterator.next();
                                    int cellColumn = currCell.getColumnIndex();
                                    //Check the cell type and format accordingly
                                    currActionString += '"' + Constants.ACTION_FIELDS[cellColumn]
                                            + KEY_VALUE_SEPARATOR +
                                            getCellValue(currCell) + '"' +  ",";
                                }

                                currActionString = addCurrStrikeJsonSuffix(currActionString);

                                if (!currActionString.contains("NA") &&
                                        !currActionString.contains("strikePrice" + KEY_VALUE_SEPARATOR + '"'))
                                {
                                    Gson gson = new GsonBuilder().create();
                                    Strike curr = gson.fromJson(currActionString, Strike.class);
                                    liveStrikes.add(curr);
                                    allDataAsJson += currActionString + ",";
                                }
                            }

                            file.close();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        setAllStrikeJson(allDataAsJson);

                       if (isMarketIsOpen())
                       {
                           saveLastStrikesToDB(sc);
                       }

                        Constants.lastStrikes = liveStrikes;
                    }
                },
                0,
                10000);
    }

    private void setAllStrikeJson(String allDataAsJson) {
        allDataAsJson = allDataAsJson.substring(0, allDataAsJson.length() -1);
        allDataAsJson += "]";
        finalString = allDataAsJson;
    }

    private String addCurrStrikeJsonSuffix(String currActionString) {
        currActionString = currActionString.substring(0, currActionString.length() - 1);
        currActionString += "}";
        return currActionString;
    }

    private void saveLastStrikesToDB(ServletConfig sc) {
        MongoClient mongo = (MongoClient) sc.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStrikesDAO actionDAO = new MongoDBStrikesDAO(mongo);
        MongoDBLastStrikesDAO mongoDBLastStrikesDAO = new MongoDBLastStrikesDAO(mongo);
        actionDAO.saveStrikes(liveStrikes);
        mongoDBLastStrikesDAO.saveStrikes(liveStrikes);
    }

    private boolean isMarketIsOpen()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Date marketOpenHour = new Date();
        marketOpenHour.setHours(9);
        marketOpenHour.setMinutes(30);
        Date sundayMarketCloseHour = new Date();
        marketOpenHour.setHours(16);
        marketOpenHour.setMinutes(35);
        Date restOfWeekMarketCloseHour = new Date();
        marketOpenHour.setHours(17);
        marketOpenHour.setMinutes(35);

        if (cal.DAY_OF_WEEK == Calendar.SUNDAY)
        {
            return (cal.getTime().after(marketOpenHour) && cal.getTime().before(sundayMarketCloseHour));
        }
        else if(cal.getTime().getDay() <= Calendar.THURSDAY)
        {
            return (cal.getTime().after(marketOpenHour) && cal.getTime().before(restOfWeekMarketCloseHour));
        }

        return false;
    }

    private Object getCellValue(Cell cell) {
        if (cell.getColumnIndex() == 0 && (!cell.getStringCellValue().equals("")) && (!cell.getStringCellValue().equals("NA")))
        {
            String description = cell.getStringCellValue();
            String strike = cell.getStringCellValue();
            String returnFields = cell.getStringCellValue().substring(4,8) + FIELD_SEPARATOR;
            returnFields += "isWeekly" + KEY_VALUE_SEPARATOR;

            if (strike.contains("W"))
            {
                returnFields += "1";
            }
            else {
                returnFields += "0";
            }

            returnFields += FIELD_SEPARATOR + "description" + KEY_VALUE_SEPARATOR + description + FIELD_SEPARATOR;
            returnFields += "isCall" + KEY_VALUE_SEPARATOR;
            if (strike.startsWith("C"))
            {
                returnFields += "true";
            }
            else {
                returnFields += "0";
            }

            return returnFields;
        }
        else if (cell.getColumnIndex() == 13 && (!cell.getStringCellValue().equals("")) && (!cell.getStringCellValue().equals("NA")))
        {
            String timeStr = cell.getStringCellValue();
            Time time = new Time(Integer.parseInt(timeStr.substring(0,2)),
                    Integer.parseInt(timeStr.substring(3,5)), 0);
            return time;

        }
        switch (cell.getCellType())
        {
            case Cell.CELL_TYPE_NUMERIC:
                if (cell.getColumnIndex() == 23)
                {
                    return (long)cell.getNumericCellValue();
                }
                else
                {
                    return cell.getNumericCellValue();
                }
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_FORMULA:
                switch(cell.getCachedFormulaResultType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        return cell.getNumericCellValue();
                    case Cell.CELL_TYPE_STRING:
                        return cell.getRichStringCellValue();
                    case Cell.CELL_TYPE_ERROR:
                        return 0;
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                return 0;
            case Cell.CELL_TYPE_ERROR:
                return 0;
        }

        return 0;
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

