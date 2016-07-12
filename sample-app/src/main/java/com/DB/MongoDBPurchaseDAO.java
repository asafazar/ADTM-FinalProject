package com.DB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.actions.Purchase;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asaf on 13/06/2016.
 */
public class MongoDBPurchaseDAO {

    private DBCollection col;

    public MongoDBPurchaseDAO(MongoClient mongo) {
        this.col = mongo.getDB("adtmdb").getCollection("purchase");
    }

    public void createPurchase(List<Purchase> purchases, String strategyId)
    {
        for (Purchase currPurchase : purchases)
        {
            Gson gson = new GsonBuilder().create();
            currPurchase.setStrategyId(strategyId);
            DBObject doc = (DBObject) JSON.parse(gson.toJson(currPurchase, Purchase.class));
            this.col.insert(doc);
        }
    }

    public Purchase updatePurchase(Purchase currPurchase)
    {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(currPurchase.getId())).get();
        Gson gson = new GsonBuilder().create();
        DBObject doc = (DBObject) JSON.parse(gson.toJson(currPurchase, Purchase.class));
        this.col.update(query, doc);
        DBCursor cursor = this.col.find(query);

        return gson.fromJson(JSON.serialize(cursor.next()), Purchase.class);
    }

    public List<Purchase> getPurchaseForStrategy(String strategyId)
    {
        List<Purchase> strategyPurchases = new ArrayList<>();
        DBObject query = BasicDBObjectBuilder.start()
                .append("strategyId", strategyId).get();
        DBCursor cursor = col.find(query);
        while (cursor.hasNext())
        {
            DBObject doc = cursor.next();
            Gson gson = new GsonBuilder().create();
            Purchase currPurchase = gson.fromJson(JSON.serialize(doc), Purchase.class);
            currPurchase.setId(doc.get("_id").toString());
            strategyPurchases.add(currPurchase);
        }

        return strategyPurchases;
    }
}
