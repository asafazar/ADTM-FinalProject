package com.DB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.actions.Action;
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

    public Action createAction(Action action) {
        Gson gson = new GsonBuilder().create();
        DBObject doc = (DBObject) JSON.parse(gson.toJson(action, Action.class));
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        return action;
    }

    public void updateAction(Action action) {
    }

    public List<Action> readAllActions() {
        Gson gson = new GsonBuilder().create();
        List<Action> data = new ArrayList<Action>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
        }
        return data;
    }

    public void deleteAction(Action action) {
    }

    public Action readAction(String actionId) {
        Gson gson = new GsonBuilder().create();
        Action action = new Action();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new BasicDBObject("$eq", actionId));
        DBCursor cursor = (DBCursor) col.findOne(query);
        DBObject doc = cursor.next();/*
        action = gson.fromJson(JSON.);*/
        return action;
    }

    public List<Action> getActionsByStrategyId(String strategyId)
    {
        List<Action> actions = new ArrayList<Action>();

        BasicDBObject query = new BasicDBObject();
        query.put("StraegyId", new BasicDBObject("$eq", strategyId));
        DBCursor cursor = col.find(query);
        return actions;
    }

    public List<Action> getActionsByActionNumAndValue(int actionNum, int value)
    {
        List<Action> actions = new ArrayList<Action>();
        BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> queriesList = new ArrayList<BasicDBObject>();
        queriesList.add(new BasicDBObject("actionNumber", actionNum));
        queriesList.add(new BasicDBObject("actionValue", value));
        query.put("$and", queriesList);
        DBCursor cursor = col.find(query);

        return actions;
    }
}