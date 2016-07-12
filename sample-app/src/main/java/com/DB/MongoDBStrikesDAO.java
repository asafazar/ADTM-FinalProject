package com.DB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.actions.Strike;
import com.model.utils.Constants;
import com.model.utils.TimeCalculator;
import com.mongodb.*;
import com.mongodb.util.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoDBStrikesDAO {

    private DBCollection col;

    public MongoDBStrikesDAO(MongoClient mongo) {
        this.col = mongo.getDB("adtmdb").getCollection("strikes");
    }

    public Strike createAction(Strike strike)
    {
        Gson gson = new GsonBuilder().create();
        DBObject doc = (DBObject) JSON.parse(gson.toJson(strike, Strike.class));
        this.col.insert(doc);

        return strike;
    }

    public void updateAction(Strike strike)
    {
    }

    public List<Strike> readAllActions()
    {
        Gson gson = new GsonBuilder().create();
        List<Strike> data = new ArrayList<Strike>();
        DBCursor cursor = col.find();

        while (cursor.hasNext())
        {
            DBObject doc = cursor.next();
            data.add(gson.fromJson(JSON.serialize(doc), Strike.class));
        }

        return data;
    }

    public void deleteAction(Strike strike)
    {
    }

    public Strike readAction(String actionId)
    {
        Gson gson = new GsonBuilder().create();
        Strike strike = new Strike();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new BasicDBObject("$eq", actionId));
        DBCursor cursor = (DBCursor) col.findOne(query);
        DBObject doc = cursor.next();
        strike = gson.fromJson(JSON.serialize(doc), Strike.class);

        return strike;
    }

    public List<Strike> getActionsByStrategyId(String strategyId)
    {
        Gson gson = new GsonBuilder().create();
        List<Strike> strikes = new ArrayList<Strike>();
        BasicDBObject query = new BasicDBObject();
        query.put("StraegyId", new BasicDBObject("$eq", strategyId));
        DBCursor cursor = col.find(query);

        while (cursor.hasNext())
        {
            DBObject doc = cursor.next();
            strikes.add(gson.fromJson(JSON.serialize(doc), Strike.class));
        }

        return strikes;
    }

    public void saveStrikes(List<Strike> strikeList)
    {
        Date now = new Date();

        for (Strike strike : strikeList)
        {
            strike.setStrikeDate(now);

            if (strike.isWeekly())
            {
                strike.setExpirationDate(TimeCalculator.getWeeklyExpirationDate(now));
            }
            else
            {
                if (strike.getDescription().contains(Constants.MONTHES_MAP.get(now.getMonth())))
                {
                    strike.setExpirationDate(TimeCalculator.getMonthlyExpirationDate(now));
                }
            }

            Gson gson = new GsonBuilder().create();
            DBObject doc = (DBObject) JSON.parse(gson.toJson(strike, Strike.class));
            this.col.insert(doc);
        }
    }
}