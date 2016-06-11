package com.DB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.actions.Strike;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBActionDAO {

    private DBCollection col;

    public MongoDBActionDAO(MongoClient mongo) {
        this.col = mongo.getDB("adtmdb").getCollection("actions");
    }

    public Strike createAction(Strike strike) {
        Gson gson = new GsonBuilder().create();
        DBObject doc = (DBObject) JSON.parse(gson.toJson(strike, Strike.class));
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        return strike;
    }

    public void updateAction(Strike strike) {
    }

    public List<Strike> readAllActions() {
        Gson gson = new GsonBuilder().create();
        List<Strike> data = new ArrayList<Strike>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
        }
        return data;
    }

    public void deleteAction(Strike strike) {
    }

    public Strike readAction(String actionId) {
        Gson gson = new GsonBuilder().create();
        Strike strike = new Strike();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new BasicDBObject("$eq", actionId));
        DBCursor cursor = (DBCursor) col.findOne(query);
        DBObject doc = cursor.next();
        return strike;
    }

    public List<Strike> getActionsByStrategyId(String strategyId)
    {
        List<Strike> strikes = new ArrayList<Strike>();

        BasicDBObject query = new BasicDBObject();
        query.put("StraegyId", new BasicDBObject("$eq", strategyId));
        DBCursor cursor = col.find(query);
        return strikes;
    }

    public List<Strike> getActionsByActionNumAndValue(int actionNum, int value)
    {
        List<Strike> strikes = new ArrayList<Strike>();
        BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> queriesList = new ArrayList<BasicDBObject>();
        queriesList.add(new BasicDBObject("actionNumber", actionNum));
        queriesList.add(new BasicDBObject("actionValue", value));
        query.put("$and", queriesList);
        DBCursor cursor = col.find(query);

        return strikes;
    }
}