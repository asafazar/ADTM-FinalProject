package com.model.nextTradingDate;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;


public class NextTradingDateDerivativeDetailsType5Convertor {

    // convert Stock Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(NextTradingDateDerivativeDetailsType5 nextTradingDateDerivativeDetailsType5) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("RECORD_TYPE", nextTradingDateDerivativeDetailsType5.getRecordType())
                .append("DERIVATIVE_ID", nextTradingDateDerivativeDetailsType5.getDerivativeId())
                .append("UNDERLYING_ASSET_CODE", nextTradingDateDerivativeDetailsType5.getExactExpirationDate())
                .append("EXPIRATION_DATE", nextTradingDateDerivativeDetailsType5.getSettlementPriceDate())
                .append("STRIKE_PRICE", nextTradingDateDerivativeDetailsType5.getIsin())
                .append("DERIVATIVE_TYPE", nextTradingDateDerivativeDetailsType5.getUnderlyingAssetMultiplier())
                .append("RISK_FREE_INTRST_RATE", nextTradingDateDerivativeDetailsType5.getUnderlyingAssetType())
                .append("UNDERLYING_ASSET_STNDRT", nextTradingDateDerivativeDetailsType5.getHaltReasonCode())
                .append("EXTREME_SCNRO_FCTOR_FOR", nextTradingDateDerivativeDetailsType5.getOpenPositionsLimit())
                .append("RISK_ARRAY_NUMBER", nextTradingDateDerivativeDetailsType5.getAdjustmentNumber())
                .append("UNDERLYING_ASSET_INTRST", nextTradingDateDerivativeDetailsType5.isCallFlag())
                .append("DERIVATIVES_MARGINS", nextTradingDateDerivativeDetailsType5.getDiscountedCoupon())
                .append("DERIVATIVE_MRGN_FOR_SPRD", nextTradingDateDerivativeDetailsType5.getMaximumPrearrangedOrderSize())

                .append("FILLER", nextTradingDateDerivativeDetailsType5.getFiller());
        if (nextTradingDateDerivativeDetailsType5.getId() != null)
            builder = builder.append("_id", new ObjectId(nextTradingDateDerivativeDetailsType5.getId()));
        return builder.get();
    }

    // convert DBObject Object to Stock
    // take special note of converting ObjectId to String
    public static NextTradingDateDerivativeDetailsType5 toNextTradingDateDerivativeDetailsType5(DBObject doc)
    {
        NextTradingDateDerivativeDetailsType5 nextTradingDateDerivativeDetailsType5Convertor = new NextTradingDateDerivativeDetailsType5();

        nextTradingDateDerivativeDetailsType5Convertor.setRecordType(Integer.parseInt((String)doc.get("RECORD_TYPE")));
        nextTradingDateDerivativeDetailsType5Convertor.setDerivativeId(Integer.parseInt((String)doc.get("DERIVATIVE_ID")));

        Date exactExpirationDate = new Date(Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(0,3)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(4,5)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(5,6)));
        nextTradingDateDerivativeDetailsType5Convertor.setExactExpirationDate(exactExpirationDate);

        Date settlementPriceDate = new Date(Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(0,3)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(4,5)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(5,6)));
        nextTradingDateDerivativeDetailsType5Convertor.setSettlementPriceDate(settlementPriceDate);

        nextTradingDateDerivativeDetailsType5Convertor.setIsin((String)doc.get("STRIKE_PRICE"));
        nextTradingDateDerivativeDetailsType5Convertor.setUnderlyingAssetMultiplier(Integer.parseInt((String)doc.get("DERIVATIVE_TYPE")));
        nextTradingDateDerivativeDetailsType5Convertor.setUnderlyingAssetType(Integer.parseInt((String)doc.get("RISK_FREE_INTRST_RATE")));
        nextTradingDateDerivativeDetailsType5Convertor.setHaltReasonCode(Integer.parseInt((String)doc.get("UNDERLYING_ASSET_STNDRT")));
        nextTradingDateDerivativeDetailsType5Convertor.setOpenPositionsLimit(Integer.parseInt((String)doc.get("EXTREME_SCNRO_FCTOR_FOR")));
        nextTradingDateDerivativeDetailsType5Convertor.setAdjustmentNumber(Integer.parseInt((String)doc.get("RISK_ARRAY_NUMBER")));
        nextTradingDateDerivativeDetailsType5Convertor.setCallFlag(((String)doc.get("UNDERLYING_ASSET_INTRST")).equals("1"));
        nextTradingDateDerivativeDetailsType5Convertor.setDiscountedCoupon(Integer.parseInt((String)doc.get("DERIVATIVES_MARGINS")));
        nextTradingDateDerivativeDetailsType5Convertor.setMaximumPrearrangedOrderSize(Integer.parseInt((String)doc.get("DERIVATIVE_MRGN_FOR_SPRD")));
        nextTradingDateDerivativeDetailsType5Convertor.setFiller((String)doc.get("FILLER"));
        ObjectId id = (ObjectId) doc.get("_id");
        nextTradingDateDerivativeDetailsType5Convertor.setId(id.toString());
        return nextTradingDateDerivativeDetailsType5Convertor;
    }
}