package com.model.actions;

import com.model.utils.Constants;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.sql.Time;
import java.util.Date;


public class ActionConvertor {

    // convert Stock Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(AbstractAction action) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("ContractName", action.getContractName());
        Constants.ACTION_NUMBER actionEnum = action.getActionNumber();
        switch (actionEnum)
        {
            case ACTION_CALL_OPTION:
                builder.append("ActionNumber", (Constants.ACTION_NUMBER.ACTION_CALL_OPTION));
                break;
            case ACTION_CALL_WRITE:
                builder.append("ActionNumber", (Constants.ACTION_NUMBER.ACTION_CALL_WRITE));
                break;
            case ACTION_PUT_OPTION:
                builder.append("ActionNumber", (Constants.ACTION_NUMBER.ACTION_PUT_OPTION));
                break;
            case ACTION_PUT_WRITE:
                builder.append("ActionNumber", (Constants.ACTION_NUMBER.ACTION_PUT_WRITE));
                break;
            default:
        }

        builder.append("MaofValue", action.getMaofValue())
                .append("ActionValue", action.getActionValue())
                .append("Name", action.getName())
                .append("Amount", action.getAmount())
                .append("ActionPrice", action.getActionPrice())
                .append("TotalPrice", action.getTotalPrice())
                .append("OptionType", action.isWeekly())
                .append("Time", action.getTime())
                .append("Date", action.getDate())
                .append("IsExecuted", action.getIsExecuted())
                .append("StrategyId", action.getStrategyId())
                .append("ActionExecutingPrice", action.getActionExecutingPrice())
                .append("TotalExecutingPrice", action.getTotalExecutingPrice())
                .append("ExpirationDate", action.getExpirationDate());
        if (action.getId() != null)
            builder = builder.append("_id", new ObjectId(action.getId()));
        return builder.get();
    }

    // convert DBObject Object to Stock
    // take special note of converting ObjectId to String
    public static AbstractAction toAction(DBObject doc)
    {
        AbstractAction action ;
        Constants.ACTION_NUMBER actionEnum = Constants.ACTION_NUMBER.getValue((Integer.parseInt((String)doc.get("ActionNumber"))));
        switch (actionEnum)
        {
            case ACTION_CALL_OPTION:
                action = new ActionCallOption();
                break;
            case ACTION_CALL_WRITE:
                action = new ActionCallWrite();
                break;
            case ACTION_PUT_OPTION:
                action = new ActionPutOption();
                break;
            case ACTION_PUT_WRITE:
                action = new ActionPutWrite();
                break;
            default:
                action = new ActionCallOption();
        }

        action.setContractName((String)doc.get("ContractName"));
        action.setActionNumber();
        action.setMaofValue(Integer.parseInt((String) doc.get("MaofValue")));
        action.setActionValue(Integer.parseInt((String) doc.get("ActionValue")));
        action.setName((String)doc.get("Name"));
        action.setAmount(Integer.parseInt((String) doc.get("Amount")));
        action.setActionPrice(Integer.parseInt((String) doc.get("ActionPrice")));
        action.setTotalPrice(Integer.parseInt((String) doc.get("TotalPrice")));
        action.setWeekly(((String) doc.get("OptionType")).equals("1"));
        Time time = new Time(Integer.valueOf(doc.get("Time").toString().substring(0,1)),
                Integer.valueOf(doc.get("Time").toString().substring(2,3)),
                Integer.valueOf(doc.get("Time").toString().substring(4,5)));
        action.setTime(time);
        Date date = new Date(Integer.valueOf(doc.get("Date").toString().substring(0,3)),
                Integer.valueOf(doc.get("Date").toString().substring(4,5)),
                Integer.valueOf(doc.get("Date").toString().substring(5,6)));
        action.setDate(date);
        action.setIsExecuted(((String)doc.get("IsExecuted")).equals("1"));
        action.setActionExecutingPrice(Integer.parseInt((String) doc.get("ActionExecutingPrice")));
        action.setTotalExecutingPrice(Integer.parseInt((String) doc.get("TotalExecutingPrice")));
        action.setStrategyId((String)doc.get("StrategyId"));
        ObjectId id = (ObjectId) doc.get("_id");
        action.setId(id.toString());
        return action;
    }
}