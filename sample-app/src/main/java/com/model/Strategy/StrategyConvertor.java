package com.model.Strategy;

import com.DB.MongoDBActionDAO;
import com.model.actions.AbstractAction;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;
import java.util.List;


public class StrategyConvertor {

    // convert Stock Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Strategy strategy) {
        BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("IsWeekly", strategy.isWeekly())
                .append("Description", strategy.getDescription())
                .append("Comment", strategy.getComment())
                .append("Date", strategy.getDate())
                .append("ExpirationDate", strategy.getDate())
                .append("userId", strategy.getUserId());
        if (strategy.getId() != null)
            builder = builder.append("_id", new ObjectId(strategy.getId()));
        return builder.get();
    }

    // convert DBObject Object to Stock
    // take special note of converting ObjectId to String
    public static Strategy toStrategy(DBObject doc)
    {
        Context env = null;
        String dbHost = "";
        int dbPort = 0;
        try {
            env = (Context)new InitialContext().lookup("java:comp/env");
            dbHost = (String)env.lookup("MONGODB_IP");
            dbPort = Integer.parseInt((String)env.lookup("MONGODB_PORT"));
        } catch (NamingException e) {
            e.printStackTrace();
        }
        Strategy strategy = new Strategy();
        MongoClient mongo = new MongoClient(dbHost, dbPort);
        MongoDBActionDAO actionDAO = new MongoDBActionDAO(mongo);
        List<AbstractAction> actions = actionDAO.getActionsByStrategyId
                (((ObjectId) doc.get("_id")).toString());
        strategy.setActions(actions);
        strategy.setWeekly(((String)doc.get("IsWeekly")).equals("1"));
        strategy.setDescription((String) doc.get("Description"));
        strategy.setComment((String)doc.get("Comment"));
        Date date = new Date(Integer.valueOf(doc.get("Date").toString().substring(0,3)),
                Integer.valueOf(doc.get("Date").toString().substring(4,5)),
                Integer.valueOf(doc.get("Date").toString().substring(5,6)));
        strategy.setDate(date);
        strategy.setUserId((String) doc.get("userId"));
        ObjectId id = (ObjectId) doc.get("_id");
        strategy.setId(id.toString());
        return strategy;
    }
}