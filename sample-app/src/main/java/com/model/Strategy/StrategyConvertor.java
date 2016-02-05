package com.model.strategy;

import com.DB.MongoDBActionDAO;
import com.model.actions.AbstractAction;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;


public class StrategyConvertor {

    // convert Stock Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Strategy strategy) {
        BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("isWeekly", strategy.isWeekly())
                .append("Description", strategy.getDescription())
                .append("Comment", strategy.getComment())
                .append("Date", strategy.getDate());
        if (strategy.getId() != null)
            builder = builder.append("_id", new ObjectId(strategy.getId()));
        return builder.get();
    }

    // convert DBObject Object to Stock
    // take special note of converting ObjectId to String
    public static Strategy toStrategy(DBObject doc)
    {
        Strategy strategy = new Strategy();
        MongoClient mongo = new MongoClient("192.168.1.100", 27017);
        MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);
        List<AbstractAction> actions = actionDAO.getActionsByStrategyId
                (((ObjectId) doc.get("_id")).toString());
        strategy.setWeekly(((String)doc.get("isWeekly")).equals("1"));
        strategy.setDescription((String) doc.get("Description"));
        strategy.setComment((String)doc.get("Comment"));
        Date date = new Date(Integer.valueOf(doc.get("Date").toString().substring(0,3)),
                Integer.valueOf(doc.get("Date").toString().substring(4,5)),
                Integer.valueOf(doc.get("Date").toString().substring(5,6)));
        strategy.setDate(date);
        ObjectId id = (ObjectId) doc.get("_id");
        strategy.setId(id.toString());
        return strategy;
    }
}