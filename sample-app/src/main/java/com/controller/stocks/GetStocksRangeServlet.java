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
import java.util.List;

public class GetStocksRangeServlet extends HttpServlet {
    private static final long serialVersionUID = 4L;

    public GetStocksRangeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        String fromDate = request.getAttribute("fromDate").toString();
        String toDate = request.getAttribute("toDate").toString();
        MongoDBStockDAO stockDAO = new MongoDBStockDAO(mongo);;
        List<Stock> stocks = stockDAO.getStockByRange(fromDate, toDate);

        String json = new Gson().toJson(stocks);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

}
