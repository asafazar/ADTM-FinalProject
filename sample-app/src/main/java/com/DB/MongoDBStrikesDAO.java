package com.DB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.actions.Action;
import com.model.utils.Constants;
import com.model.utils.TimeCalculator;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import java.util.Date;
import java.util.List;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBStrikesDAO {

    private DBCollection col;

    public MongoDBStrikesDAO(MongoClient mongo) {
        this.col = mongo.getDB("adtmdb").getCollection("strikes");
    }

    public void saveStrikes(List<Action> actionList) {
        Date now = new Date();
        for (Action action : actionList) {
            action.setStrikeDate(now);
            if (action.isWeekly())
            {
                action.setExpirationDate(TimeCalculator.getWeeklyExpirationDate(now));
            }
            else
            {
                if (action.getDescription().contains(Constants.MONTHES_MAP.get(now.getMonth()))) {
                    action.setExpirationDate(TimeCalculator.getMonthlyExpirationDate(now));
                }
            }

            Gson gson = new GsonBuilder().create();
            DBObject doc = (DBObject) JSON.parse(gson.toJson(action, Action.class));
            this.col.insert(doc);
        }
    }
}