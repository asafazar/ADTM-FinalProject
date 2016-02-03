package com.controller;

import com.DB.MongoDBStockDAO;
import com.model.stocks.Stock;
import com.mongodb.MongoClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteStock")
public class DeleteStockServlet extends HttpServlet {

    private static final long serialVersionUID = 6798036766148281767L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBStockDAO stockDAO = new MongoDBStockDAO(mongo);
        Stock stock = new Stock();
        stock.setID(id);
        stockDAO.deleteStock(stock);
        System.out.println("Stock deleted successfully with id=" + id);
        request.setAttribute("success", "Stock deleted successfully");
        List<Stock> stocks = stockDAO.readAllStocks();
        request.setAttribute("stocks", stocks);

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/stocks.jsp");
        rd.forward(request, response);
    }

}