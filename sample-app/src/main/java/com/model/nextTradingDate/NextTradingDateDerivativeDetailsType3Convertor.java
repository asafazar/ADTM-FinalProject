package com.model.nextTradingDate;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.sql.Time;
import java.util.Date;


public class NextTradingDateDerivativeDetailsType3Convertor {

    // convert Stock Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(NextTradingDateDerivativeDetailsType3 nextTradingDateDerivativeDetailsType3) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("RECORD_TYPE", nextTradingDateDerivativeDetailsType3.getRecordType())
                .append("DERIVATIVE_ID", nextTradingDateDerivativeDetailsType3.getDerivativeId())
                .append("UNDERLYING_ASSET_CODE", nextTradingDateDerivativeDetailsType3.getFluctuationCoefficient())
                .append("EXPIRATION_DATE", nextTradingDateDerivativeDetailsType3.isMultiplierInPriceCode())
                .append("STRIKE_PRICE", nextTradingDateDerivativeDetailsType3.getBasePrice())
                .append("DERIVATIVE_TYPE", nextTradingDateDerivativeDetailsType3.getDerivativeSymbol())
                .append("RISK_FREE_INTRST_RATE", nextTradingDateDerivativeDetailsType3.getTradingStartTime())
                .append("UNDERLYING_ASSET_STNDRT", nextTradingDateDerivativeDetailsType3.getTradingEndTime())
                .append("EXTREME_SCNRO_FCTOR_FOR", nextTradingDateDerivativeDetailsType3.getMinimumOrderSize())
                .append("RISK_ARRAY_NUMBER", nextTradingDateDerivativeDetailsType3.getMaximumOrderSize())
                .append("UNDERLYING_ASSET_INTRST", nextTradingDateDerivativeDetailsType3.getMinimumOrderSize())
                .append("DERIVATIVES_MARGINS", nextTradingDateDerivativeDetailsType3.getLotSize())
                .append("DERIVATIVE_MRGN_FOR_SPRD", nextTradingDateDerivativeDetailsType3.getLastTradingDate())
                .append("NUM_OF_DAYS_FOR_SPRD", nextTradingDateDerivativeDetailsType3.isClearingMethodFlag())
                .append("DERIVATIVE_INCLD_IN_SPRD", nextTradingDateDerivativeDetailsType3.getContractSize())
                .append("EXTRM_SCNRO_FR_INDX_RISE", nextTradingDateDerivativeDetailsType3.getExpirationWeekNumber())
                .append("DELTA", nextTradingDateDerivativeDetailsType3.getFiller());

        if (nextTradingDateDerivativeDetailsType3.getId() != null)
            builder = builder.append("_id", new ObjectId(nextTradingDateDerivativeDetailsType3.getId()));
        return builder.get();

    }

    // convert DBObject Object to Stock
    // take special note of converting ObjectId to String
    public static NextTradingDateDerivativeDetailsType3 toNextTradingDateDerivativeDetailsType3(DBObject doc)
    {
        NextTradingDateDerivativeDetailsType3 nextTradingDateDerivativeDetailsType3 = new NextTradingDateDerivativeDetailsType3();

        nextTradingDateDerivativeDetailsType3.setRecordType(Integer.parseInt((String)doc.get("RECORD_TYPE")));
        nextTradingDateDerivativeDetailsType3.setDerivativeId(Integer.parseInt((String)doc.get("DERIVATIVE_ID")));
        nextTradingDateDerivativeDetailsType3.setFluctuationCoefficient(Integer.parseInt((String)doc.get("UNDERLYING_ASSET_CODE")));
        nextTradingDateDerivativeDetailsType3.setMultiplierInPriceCode(((String)doc.get("UNDERLYING_ASSET_CODE")).equals("1"));
        nextTradingDateDerivativeDetailsType3.setBasePrice(Integer.parseInt((String)doc.get("UNDERLYING_ASSET_CODE")));
        nextTradingDateDerivativeDetailsType3.setDerivativeSymbol((String)doc.get("STRIKE_PRICE"));

        Time startTime = new Time(Integer.valueOf(doc.get("Time").toString().substring(0,1)),
                Integer.valueOf(doc.get("Time").toString().substring(2,3)),
                Integer.valueOf(doc.get("Time").toString().substring(4,5)));
        nextTradingDateDerivativeDetailsType3.setTradingStartTime(startTime);
        Time endTime = new Time(Integer.valueOf(doc.get("Time").toString().substring(0,1)),
                Integer.valueOf(doc.get("Time").toString().substring(2,3)),
                Integer.valueOf(doc.get("Time").toString().substring(4,5)));
        nextTradingDateDerivativeDetailsType3.setTradingEndTime(endTime);

        nextTradingDateDerivativeDetailsType3.setMinimumOrderSize(Integer.parseInt((String)doc.get("RISK_FREE_INTRST_RATE")));
        nextTradingDateDerivativeDetailsType3.setMaximumOrderSize(Integer.parseInt((String)doc.get("UNDERLYING_ASSET_STNDRT")));
        nextTradingDateDerivativeDetailsType3.setLotSize(Integer.parseInt((String)doc.get("EXTREME_SCNRO_FCTOR_FOR")));

        Date date = new Date(Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(0,3)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(4,5)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(5,6)));
        nextTradingDateDerivativeDetailsType3.setLastTradingDate(date);

        nextTradingDateDerivativeDetailsType3.setClearingMethodFlag(((String)doc.get("UNDERLYING_ASSET_INTRST")).equals("1"));
        nextTradingDateDerivativeDetailsType3.setContractSize(Integer.parseInt((String)doc.get("DERIVATIVES_MARGINS")));
        nextTradingDateDerivativeDetailsType3.setExpirationWeekNumber(Integer.parseInt((String)doc.get("DERIVATIVE_MRGN_FOR_SPRD")));
        nextTradingDateDerivativeDetailsType3.setFiller((String)doc.get("FILLER"));
        ObjectId id = (ObjectId) doc.get("_id");
        nextTradingDateDerivativeDetailsType3.setId(id.toString());
        return nextTradingDateDerivativeDetailsType3;
    }
}