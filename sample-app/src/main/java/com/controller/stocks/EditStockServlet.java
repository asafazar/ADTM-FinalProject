package com.controller.stocks;

import com.DB.MongoDBStockDAO;
import com.google.gson.Gson;
import com.model.stocks.Stock;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class EditStockServlet extends HttpServlet {

    private static final long serialVersionUID = -6554920927964049383L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null || "".equals(id))
        {
            throw new ServletException("id missing for edit operation");
        }

        System.out.println("Stock edit requested with id=" + id);
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStockDAO stockDAO = new MongoDBStockDAO(mongo);
        Stock stock = new Stock();
        stock.setID(id);
        stock = stockDAO.readStock(stock);
        request.setAttribute("stock", stock);
        List<Stock> stocks = stockDAO.readAllStocks();
        request.setAttribute("stocks", stocks);
        String json = new Gson().toJson(stocks);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");

        if (id == null || "".equals(id))
        {
            throw new ServletException("id missing for edit operation");
        }

        int contractNumber = Integer.parseInt(request.getParameter("ContractNumber"));
        int amount = Integer.parseInt(request.getParameter("Amount"));
        String assetCode = request.getParameter("AssetCode");
        String buyCodeNumber = request.getParameter("BuyCodeNumber");
        String sellCodeNumber = request.getParameter("SellCodeNumber");
        String assetName = request.getParameter("AssetName");
        Time time = Time.valueOf(request.getParameter("Time"));
        String tradeNumber = request.getParameter("TradeNumber");
        boolean tradeStatus = Boolean.parseBoolean(request.getParameter("TradeStatus"));
        boolean participateCode = Boolean.parseBoolean(request.getParameter("ParticipateCode"));
        boolean matchingTradeDeal = Boolean.parseBoolean(request.getParameter("MatchingTradeDeal"));
        double price = Double.parseDouble(request.getParameter("Price"));
        String dateStr = request.getParameter("Date");
        Date date = new Date(Integer.valueOf(dateStr.substring(0,3)),
                Integer.valueOf(dateStr.substring(4,5)), Integer.valueOf(dateStr.substring(6,7)));

        if ((assetCode == null || assetCode.equals(""))
                || (assetName == null || assetName.equals("")))
        {
            throw new ServletException("id missing for edit action operation");
        }
        else
        {
            MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
            MongoDBStockDAO stockDAO = new MongoDBStockDAO(mongo);
            Stock stock = new Stock();
            stock.setID(id);
            stock.setContractNumber(contractNumber);
            stock.setAmount(amount);
            stock.setAssetCode(assetCode);
            stock.setBuyCodeNumber(buyCodeNumber);
            stock.setSellCodeNumber(sellCodeNumber);
            stock.setAssetName(assetName);
            stock.setTime(time);
            stock.setTradeNumber(tradeNumber);
            stock.setTradeStatus(tradeStatus);
            stock.setParticipateCode(participateCode);
            stock.setMatchingTradeDeal(matchingTradeDeal);
            stock.setPrice(price);
            stock.setDate(date);
            stockDAO.updateStock(stock);
            System.out.println("Stock edited Successfully with id= "+stock.getID());
            request.setAttribute("success", "Stock edited Successfully");
            List<Stock> stocks = stockDAO.readAllStocks();
            request.setAttribute("stocks", stocks);
            String json = new Gson().toJson(stocks);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }
}