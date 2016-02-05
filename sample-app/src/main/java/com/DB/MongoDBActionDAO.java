package com.DB;

import com.model.actions.AbstractAction;
import com.model.actions.ActionConvertor;
import com.mongodb.*;
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

    public AbstractAction createAction(AbstractAction action) {
        DBObject doc = ActionConvertor.toDBObject(action);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        action.setId(id.toString());
        return action;
    }

    public void updateAction(AbstractAction action) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(action.getId())).get();
        this.col.update(query, ActionConvertor.toDBObject(action));
    }

    public List<AbstractAction> readAllActions() {
        List<AbstractAction> data = new ArrayList<AbstractAction>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            data.add(ActionConvertor.toAction(doc));
        }
        return data;
    }

    public void deleteAction(AbstractAction action) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(action.getId())).get();
        this.col.remove(query);
    }

    public AbstractAction readAction(String actionId) {

        AbstractAction action;
        //= new ArrayList<AbstractAction>();

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new BasicDBObject("$eq", actionId));
        DBCursor cursor = (DBCursor) col.findOne(query);
        DBObject doc = cursor.next();
        action = ActionConvertor.toAction(doc);
        return action;
    }

    public List<AbstractAction> getActionsByStrategyId(String strategyId)
    {
        List<AbstractAction> actions = new ArrayList<AbstractAction>();

        BasicDBObject query = new BasicDBObject();
        query.put("StraegyId", new BasicDBObject("$eq", strategyId));
        DBCursor cursor = col.find(query);

        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            actions.add(ActionConvertor.toAction(doc));
        }
        return actions;
    }
}