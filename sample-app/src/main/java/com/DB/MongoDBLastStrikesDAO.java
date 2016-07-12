package com.DB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.actions.Strike;
import com.model.utils.Constants;
import com.model.utils.TimeCalculator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import java.util.Date;
import java.util.List;

/**
 * Created by asaf on 14/06/2016.
 */
public class MongoDBLastStrikesDAO {

    private DBCollection col;

    public MongoDBLastStrikesDAO(MongoClient mongo) {
        this.col = mongo.getDB("adtmdb").getCollection("lastStrikes");
    }

    public void saveStrikes(List<Strike> strikeList)
    {
        col.remove(new BasicDBObject());
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
