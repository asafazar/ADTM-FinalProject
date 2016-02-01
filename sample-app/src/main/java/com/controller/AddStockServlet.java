package com.controller;

import com.DB.MongoDBStockDAO;
import com.model.Stock;
import com.mongodb.MongoClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@WebServlet("/addStock")
public class AddStockServlet extends HttpServlet {

    private static final long serialVersionUID = -7060758261496829905L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {

            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>MyServlet.java:doGet(): Servlet code!</title>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1>This is a simple java servlet.</h1>");

            writer.println("</body>");
            writer.println("</html>");
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
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
        Date date = new Date(Integer.valueOf(dateStr.substring(0, 3)),
                Integer.valueOf(dateStr.substring(4, 5)), Integer.valueOf(dateStr.substring(6, 7)));

        if ((assetCode == null || assetCode.equals(""))
                || (assetName == null || assetName.equals(""))) {
            request.setAttribute("error", "Mandatory Parameters Missing");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/stocks.jsp");
            rd.forward(request, response);
        } else {
            Stock stock = new Stock();
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
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBStockDAO stockDAO = new MongoDBStockDAO(mongo);
            stockDAO.createStock(stock);
            System.out.println("Stock Added Successfully with id=" + stock.getID());
            request.setAttribute("success", "Stock Added Successfully");
            List<Stock> stocks = stockDAO.readAllStocks();
            request.setAttribute("stocks", stocks);

            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/persons.jsp");
            rd.forward(request, response);
        }
    }
}
