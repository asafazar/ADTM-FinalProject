package com.model.nextTradingDate;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;


public class NextTradingDateDerivativeDetailsType2Convertor {

    public static DBObject toDBObject(NextTradingDateDerivativeDetailsType2 nextTradingDateDerivativeDetailsType2)
    {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("RECORD_TYPE", nextTradingDateDerivativeDetailsType2.getRecordType())
                .append("DERIVATIVE_ID", nextTradingDateDerivativeDetailsType2.getDerivativeId())
                .append("UNDERLYING_ASSET_CODE", nextTradingDateDerivativeDetailsType2.getDerivativeType())
                .append("EXPIRATION_DATE", nextTradingDateDerivativeDetailsType2.getExpirationDate())
                .append("STRIKE_PRICE", nextTradingDateDerivativeDetailsType2.getStrikePrice())
                .append("DERIVATIVE_TYPE", nextTradingDateDerivativeDetailsType2.getUnderlyingAssetCode())
                .append("RISK_FREE_INTRST_RATE", nextTradingDateDerivativeDetailsType2.getUnderlyingAssetMultiplier())
                .append("UNDERLYING_ASSET_STNDRT", nextTradingDateDerivativeDetailsType2.getUnderlyingAssetType())
                .append("EXTREME_SCNRO_FCTOR_FOR", nextTradingDateDerivativeDetailsType2.isAdjustedDerivativeFlag())
                .append("RISK_ARRAY_NUMBER", nextTradingDateDerivativeDetailsType2.getUpperDailyFluctuation())
                .append("UNDERLYING_ASSET_INTRST", nextTradingDateDerivativeDetailsType2.getLowerDailyFluctuation())
                .append("DERIVATIVES_MARGINS", nextTradingDateDerivativeDetailsType2.getDerivativeHebrewName())
                .append("DERIVATIVE_MRGN_FOR_SPRD", nextTradingDateDerivativeDetailsType2.isShortTermDerviativeFlag())
                .append("NUM_OF_DAYS_FOR_SPRD", nextTradingDateDerivativeDetailsType2.isNewDerviativeFlag())
                .append("DERIVATIVE_INCLD_IN_SPRD", nextTradingDateDerivativeDetailsType2.getFiller())
                .append("EXTRM_SCNRO_FR_INDX_RISE", nextTradingDateDerivativeDetailsType2.getMarketId())
                .append("DELTA", nextTradingDateDerivativeDetailsType2.isDerivativeIssuedDuringTradeFlag());

        if (nextTradingDateDerivativeDetailsType2.getId() != null)
            builder = builder.append("_id", new ObjectId(nextTradingDateDerivativeDetailsType2.getId()));

        return builder.get();
    }

    public static NextTradingDateDerivativeDetailsType2 toNextTradingDateDerivativeDetailsType2(DBObject doc)
    {
        NextTradingDateDerivativeDetailsType2 nextTradingDateDerivativeDetailsType2= new NextTradingDateDerivativeDetailsType2();
        nextTradingDateDerivativeDetailsType2.setRecordType(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeDetailsType2.setDerivativeId(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeDetailsType2.setDerivativeType(Integer.valueOf((String) doc.get("")));
        Date date = new Date(Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(0,3)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(4,5)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(5,6)));
        nextTradingDateDerivativeDetailsType2.setExpirationDate(date);
        nextTradingDateDerivativeDetailsType2.setStrikePrice(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeDetailsType2.setUnderlyingAssetCode(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeDetailsType2.setUnderlyingAssetMultiplier(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeDetailsType2.setUnderlyingAssetType(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeDetailsType2.setAdjustedDerivativeFlag(((String) doc.get("")).equals("1"));
        nextTradingDateDerivativeDetailsType2.setUpperDailyFluctuation(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeDetailsType2.setLowerDailyFluctuation(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeDetailsType2.setDerivativeHebrewName((String) doc.get(""));
        nextTradingDateDerivativeDetailsType2.setShortTermDerviativeFlag(((String) doc.get("")).equals("1"));
        nextTradingDateDerivativeDetailsType2.setNewDerviativeFlag(((String) doc.get("")).equals("1"));
        nextTradingDateDerivativeDetailsType2.setFiller((String) doc.get(""));
        nextTradingDateDerivativeDetailsType2.setMarketId(Integer.valueOf((String) doc.get("")));
        nextTradingDateDerivativeDetailsType2.setDerivativeIssuedDuringTradeFlag(((String) doc.get("")).equals("1"));
        ObjectId id = (ObjectId) doc.get("_id");
        nextTradingDateDerivativeDetailsType2.setId(id.toString());

        return nextTradingDateDerivativeDetailsType2;
    }
}