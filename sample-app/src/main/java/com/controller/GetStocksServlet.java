package com.controller;

import com.DB.MongoDBStockDAO;
import com.google.gson.Gson;
import com.model.stocks.Stock;
import com.mongodb.MongoClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetStocksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetStocksServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStockDAO stockDAO = new MongoDBStockDAO(mongo);;
        List<Stock> stocks = stockDAO.readAllStocks();

        String json = new Gson().toJson(stocks);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

}
