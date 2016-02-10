package com.model.riskArraysDerivative;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;


public class RiskArrayDerivativeConvertor {

    // convert Stock Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(RiskArrayDerivative riskArrayDerivative) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("RECORD_TYPE", riskArrayDerivative.getRecordType())
                .append("DERIVATIVE_ID", riskArrayDerivative.getDerivativeId())
                .append("UNDERLYING_ASSET_CODE", riskArrayDerivative.getUnderlyingAssetCode())
                .append("EXPIRATION_DATE", riskArrayDerivative.getExpirationDate())
                .append("STRIKE_PRICE", riskArrayDerivative.getStrikePrice())
                .append("DERIVATIVE_TYPE", riskArrayDerivative.getDerivativeId())
                .append("RISK_FREE_INTRST_RATE", riskArrayDerivative.getRiskFreeInterestRateRisk())
                .append("UNDERLYING_ASSET_STNDRT", riskArrayDerivative.getUnderlyingAssetStandardDeviationPercent())
                .append("EXTREME_SCNRO_FCTOR_FOR", riskArrayDerivative.getExtremeScenarioFactorForIndexDecline())
                .append("RISK_ARRAY_NUMBER", riskArrayDerivative.getRiskArrayNumber())
                .append("UNDERLYING_ASSET_INTRST", riskArrayDerivative.getUnderlyingAssetInterestRate())
                .append("DERIVATIVES_MARGINS", riskArrayDerivative.getDerivativeMargins())
                .append("DERIVATIVE_MRGN_FOR_SPRD", riskArrayDerivative.getDerivativeMarginsForSpreadStrategy())
                .append("NUM_OF_DAYS_FOR_SPRD", riskArrayDerivative.getNumberOfDaysForSpreadStrategy())
                .append("DERIVATIVE_INCLD_IN_SPRD", riskArrayDerivative.getDerivativeIncludedInSpreadStrategyFlag())
                .append("EXTRM_SCNRO_FR_INDX_RISE", riskArrayDerivative.getExtremeScenarioFactorForIndexRise())
                .append("DELTA", riskArrayDerivative.getDelta())
                .append("DELTA_SIGN", riskArrayDerivative.getDeltaSign())
                .append("PRICE_SCAN_RANGR", riskArrayDerivative.getPriceScanRangePercent())
                .append("STNRD_DEVIATION_FLUCTION", riskArrayDerivative.getStandardDeviationFluctuation())
                .append("FILLER", riskArrayDerivative.getFiller());
        if (riskArrayDerivative.getId() != null)
            builder = builder.append("_id", new ObjectId(riskArrayDerivative.getId()));
        return builder.get();
    }

    // convert DBObject Object to Stock
    // take special note of converting ObjectId to String
    public static RiskArrayDerivative toAction(DBObject doc)
    {
        RiskArrayDerivative riskArrayDerivative = new RiskArrayDerivative();

        riskArrayDerivative.setRecordType(Integer.parseInt((String)doc.get("RECORD_TYPE")));
        riskArrayDerivative.setDerivativeId(Integer.parseInt((String)doc.get("DERIVATIVE_ID")));
        riskArrayDerivative.setUnderlyingAssetCode(Integer.parseInt((String)doc.get("UNDERLYING_ASSET_CODE")));

        Date date = new Date(Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(0,3)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(4,5)),
                Integer.valueOf(doc.get("EXPIRATION_DATE").toString().substring(5,6)));
        riskArrayDerivative.setExpirationDate(date);
        riskArrayDerivative.setStrikePrice(Integer.parseInt((String)doc.get("STRIKE_PRICE")));
        riskArrayDerivative.setDerivativeType(Integer.parseInt((String)doc.get("DERIVATIVE_TYPE")));
        riskArrayDerivative.setRiskFreeInterestRateRisk(Integer.parseInt((String)doc.get("RISK_FREE_INTRST_RATE")));
        riskArrayDerivative.setUnderlyingAssetStandardDeviationPercent(Integer.parseInt((String)doc.get("UNDERLYING_ASSET_STNDRT")));
        riskArrayDerivative.setExtremeScenarioFactorForIndexDecline(Integer.parseInt((String)doc.get("EXTREME_SCNRO_FCTOR_FOR")));
        riskArrayDerivative.setRiskArrayNumber(Integer.parseInt((String)doc.get("RISK_ARRAY_NUMBER")));
        riskArrayDerivative.setUnderlyingAssetInterestRate(Integer.parseInt((String)doc.get("UNDERLYING_ASSET_INTRST")));
        riskArrayDerivative.setDerivativeMargins(Integer.parseInt((String)doc.get("DERIVATIVES_MARGINS")));
        riskArrayDerivative.setDerivativeMarginsForSpreadStrategy(Integer.parseInt((String)doc.get("DERIVATIVE_MRGN_FOR_SPRD")));
        riskArrayDerivative.setNumberOfDaysForSpreadStrategy(Integer.parseInt((String)doc.get("NUM_OF_DAYS_FOR_SPRD")));
        riskArrayDerivative.setDerivativeIncludedInSpreadStrategyFlag(Integer.parseInt((String)doc.get("DERIVATIVE_INCLD_IN_SPRD")));
        riskArrayDerivative.setExtremeScenarioFactorForIndexRise(Integer.parseInt((String)doc.get("EXTRM_SCNRO_FR_INDX_RISE")));
        riskArrayDerivative.setDelta(Double.valueOf((String)doc.get("DELTA")));
        riskArrayDerivative.setDeltaSign(Integer.parseInt((String)doc.get("DELTA_SIGN")));
        riskArrayDerivative.setPriceScanRangePercent(Integer.parseInt((String)doc.get("PRICE_SCAN_RANGR")));
        riskArrayDerivative.setStandardDeviationFluctuation(Integer.parseInt((String)doc.get("STNRD_DEVIATION_FLUCTION")));
        riskArrayDerivative.setFiller((String)doc.get("FILLER"));
        ObjectId id = (ObjectId) doc.get("_id");
        riskArrayDerivative.setId(id.toString());
        return riskArrayDerivative;
    }
}