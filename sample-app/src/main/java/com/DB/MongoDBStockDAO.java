package com.DB;

import com.model.stocks.Stock;
import com.model.stocks.StockConvertor;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoDBStockDAO {

    private DBCollection col;

    public MongoDBStockDAO(MongoClient mongo) {
        this.col = mongo.getDB("maof").getCollection("history");
    }

    public Stock createStock(Stock stock) {
        DBObject doc = StockConvertor.toDBObject(stock);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        stock.setID(id.toString());
        return stock;
    }

    public void updateStock(Stock stock) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(stock.getID())).get();
        this.col.update(query, StockConvertor.toDBObject(stock));
    }

    public List<Stock> readAllStocks() {
        List<Stock> data = new ArrayList<Stock>();
        DBCursor cursor = col.find();
        int i = 0;

        while (cursor.hasNext() && (i < 10)) {
            DBObject doc = cursor.next();
            Stock stock = StockConvertor.toStock(doc);
            data.add(stock);
            i++;
        }
        return data;
    }

    public void deleteStock(Stock stock) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(stock.getID())).get();
        this.col.remove(query);
    }

    public Stock readStock(Stock stock) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(stock.getID())).get();
        DBObject data = this.col.findOne(query);
        return StockConvertor.toStock(data);
    }

    public List<Stock> getStockByRange(String fromDate, String toDate)
    {
        List<Stock> stocksRange = new ArrayList<Stock>();

        BasicDBObject query = new BasicDBObject();
        query.put("Date", new BasicDBObject("$gt", fromDate).append("$lt", toDate));
        DBCursor cursor = col.find(query);

        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Stock stock = StockConvertor.toStock(doc);
            stocksRange.add(stock);
        }
        return stocksRange;
    }
}