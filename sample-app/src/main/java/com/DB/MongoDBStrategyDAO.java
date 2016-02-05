package com.DB;

import com.model.strategy.Strategy;
import com.model.strategy.StrategyConvertor;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBStrategyDAO {

    private DBCollection col;

    public MongoDBStrategyDAO(MongoClient mongo) {
        this.col = mongo.getDB("adtmdb").getCollection("strategy");
    }

    public Strategy createStrategy(Strategy strategy) {
        DBObject doc = StrategyConvertor.toDBObject(strategy);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        strategy.setId(id.toString());
        return strategy;
    }

    public void updateStrategy(Strategy strategy) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(strategy.getId())).get();
        this.col.update(query, StrategyConvertor.toDBObject(strategy));
    }

    public List<Strategy> readAllStrategies() {
        List<Strategy> data = new ArrayList<Strategy>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            data.add(StrategyConvertor.toStrategy(doc));
        }
        return data;
    }

    public void deleteStrategy(Strategy strategy) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(strategy.getId())).get();
        this.col.remove(query);
    }

    public Strategy readStrategy(Strategy strategy) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(strategy.getId())).get();
        DBObject data = this.col.findOne(query);
        return StrategyConvertor.toStrategy(data);
    }
}