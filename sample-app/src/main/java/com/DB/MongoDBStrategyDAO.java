package com.DB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Strategy.Strategy;
import com.model.Strategy.StrategyConvertor;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoDBStrategyDAO {

    private DBCollection col;

    public MongoDBStrategyDAO(MongoClient mongo) {
        this.col = mongo.getDB("adtmdb").getCollection("strategies");
    }

    public Strategy createStrategy(Strategy strategy)
    {
        Gson gson = new Gson();
        BasicDBObject doc = (BasicDBObject) JSON.parse(gson.toJson(strategy, Strategy.class));
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        strategy.setId(id.toString());

        return strategy;
    }

    public void updateStrategy(Strategy strategy)
    {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(strategy.getId())).get();
        this.col.update(query, StrategyConvertor.toDBObject(strategy));
    }

    public List<Strategy> readAllStrategies()
    {
        List<Strategy> data = new ArrayList<Strategy>();
        DBCursor cursor = col.find();

        while (cursor.hasNext())
        {
            DBObject doc = cursor.next();
            data.add(StrategyConvertor.toStrategy(doc));
        }

        return data;
    }

    public void deleteStrategy(String strategyId)
    {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(strategyId)).get();
        this.col.remove(query);
    }

    public Strategy readStrategy(Strategy strategy)
    {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(strategy.getId())).get();
        DBObject data = this.col.findOne(query);

        return StrategyConvertor.toStrategy(data);
    }

    public List<Strategy> getUserStrategies(String userEmail)
    {
        List<Strategy> data = new ArrayList<>();
        DBObject query = BasicDBObjectBuilder.start()
                .append("userId", userEmail).get();
        DBCursor cursor = col.find(query);

        while (cursor.hasNext())
        {
            DBObject doc = cursor.next();
            Gson gson = new GsonBuilder().create();
            Strategy currStrategy = gson.fromJson(JSON.serialize(doc), Strategy.class);
            currStrategy.setId(doc.get("_id").toString());
            data.add(currStrategy);
        }

        return data;
    }
}