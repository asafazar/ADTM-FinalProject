package com.model.nextTradingDate;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

/**
 * Created by asaf on 10/02/2016.
 */
public class NextTradingDateDerivativeCTDConvertor {

    // convert Stock Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(NextTradingDateDerivativeCTD nextTradingDateDerivativeCTD) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("RECORD_TYPE", nextTradingDateDerivativeCTD.getRecordType())
                .append("DERIVATIVE_ID", nextTradingDateDerivativeCTD.getDerivativeId())
                .append("UNDERLYING_ASSET_CODE", nextTradingDateDerivativeCTD.getRecordNumber())
                .append("EXPIRATION_DATE", nextTradingDateDerivativeCTD.getSecurityId1())
                .append("STRIKE_PRICE", nextTradingDateDerivativeCTD.getConversionFactor1())
                .append("DERIVATIVE_TYPE", nextTradingDateDerivativeCTD.getAccruedInterest1())
                .append("EXPIRATION_DATE", nextTradingDateDerivativeCTD.getSecurityId2())
                .append("STRIKE_PRICE", nextTradingDateDerivativeCTD.getConversionFactor2())
                .append("DERIVATIVE_TYPE", nextTradingDateDerivativeCTD.getAccruedInterest2())
                .append("EXPIRATION_DATE", nextTradingDateDerivativeCTD.getSecurityId3())
                .append("STRIKE_PRICE", nextTradingDateDerivativeCTD.getConversionFactor3())
                .append("DERIVATIVE_TYPE", nextTradingDateDerivativeCTD.getAccruedInterest3());
        if (nextTradingDateDerivativeCTD.getId() != null)
            builder = builder.append("_id", new ObjectId(nextTradingDateDerivativeCTD.getId()));
        return builder.get();
    }

    // convert DBObject Object to Stock
    // take special note of converting ObjectId to String
    public static NextTradingDateDerivativeCTD toNextTradingDateDerivativeCTD(DBObject doc)
    {
        NextTradingDateDerivativeCTD nextTradingDateDerivativeCTD = new NextTradingDateDerivativeCTD();

        nextTradingDateDerivativeCTD.setRecordType(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setDerivativeId(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setRecordNumber(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setSecurityId1(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setConversionFactor1(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setAccruedInterest1(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setSecurityId2(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setConversionFactor2(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setAccruedInterest2(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setSecurityId3(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setConversionFactor3(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeCTD.setAccruedInterest3(Integer.valueOf((String) doc.get("")));
        ObjectId id = (ObjectId) doc.get("_id");
        nextTradingDateDerivativeCTD.setId(id.toString());
        return nextTradingDateDerivativeCTD;
    }
}
