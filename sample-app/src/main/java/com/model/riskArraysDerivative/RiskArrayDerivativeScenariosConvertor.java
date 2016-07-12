package com.model.riskArraysDerivative;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;


public class RiskArrayDerivativeScenariosConvertor {

    public static DBObject toDBObject(RiskArrayDerivativeScenarios riskArrayDerivativeScenarios)
    {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("RECORD_TYPE", riskArrayDerivativeScenarios.getRecordType())
                .append("DERIVATIVE_ID", riskArrayDerivativeScenarios.getDerivativeId())
                .append("RECORD_NUMBER", riskArrayDerivativeScenarios.getRecordNumber())
                .append("SCNRO_NUMBER_1", riskArrayDerivativeScenarios.getScenarioNumber1())
                .append("UNDRLN_ASSET_PRCE_1", riskArrayDerivativeScenarios.getUnderlyingAssetNumber1())
                .append("UNDERLINE_ASSET_STANDART_DVTN_1", riskArrayDerivativeScenarios.getUnderlyingAssetStandardDeviationPercent1())
                .append("THEORITICAL_VALUE_1", riskArrayDerivativeScenarios.getTheoreticalValue1())
                .append("THEORITICAL_VALUE_SGN_1", riskArrayDerivativeScenarios.getTheoreticalValueSign1())
                .append("SCNRO_NUMBER_2", riskArrayDerivativeScenarios.getScenarioNumber2())
                .append("UNDRLN_ASSET_PRCE_2", riskArrayDerivativeScenarios.getUnderlyingAssetNumber2())
                .append("UNDERLINE_ASSET_STANDART_DVTN_2", riskArrayDerivativeScenarios.getUnderlyingAssetStandardDeviationPercent2())
                .append("THEORITICAL_VALUE_2", riskArrayDerivativeScenarios.getTheoreticalValue2())
                .append("THEORITICAL_VALUE_SGN_2", riskArrayDerivativeScenarios.getTheoreticalValueSign2())
                .append("SCNRO_NUMBER_3", riskArrayDerivativeScenarios.getScenarioNumber3())
                .append("UNDRLN_ASSET_PRCE_3", riskArrayDerivativeScenarios.getUnderlyingAssetNumber3())
                .append("UNDERLINE_ASSET_STANDART_DVTN_3", riskArrayDerivativeScenarios.getUnderlyingAssetStandardDeviationPercent3())
                .append("THEORITICAL_VALUE_3", riskArrayDerivativeScenarios.getTheoreticalValue3())
                .append("THEORITICAL_VALUE_SGN_3", riskArrayDerivativeScenarios.getTheoreticalValueSign3())
                .append("FILLER", riskArrayDerivativeScenarios.getFiller());
        if (riskArrayDerivativeScenarios.getId() != null)
            builder = builder.append("_id", new ObjectId(riskArrayDerivativeScenarios.getId()));

        return builder.get();
    }

    public static RiskArrayDerivativeScenarios toAction(DBObject doc)
    {
        RiskArrayDerivativeScenarios riskArrayDerivativeScenarios = new RiskArrayDerivativeScenarios();
        riskArrayDerivativeScenarios.setRecordType(Integer.parseInt((String)doc.get("RECORD_TYPE")));
        riskArrayDerivativeScenarios.setDerivativeId(Integer.parseInt((String)doc.get("DERIVATIVE_ID")));
        riskArrayDerivativeScenarios.setRecordNumber(Integer.parseInt((String)doc.get("RECORD_NUMBER")));
        riskArrayDerivativeScenarios.setScenarioNumber1(Integer.parseInt((String)doc.get("SCNRO_NUMBER_1")));
        riskArrayDerivativeScenarios.setUnderlyingAssetNumber1(Integer.parseInt((String)doc.get("UNDRLN_ASSET_PRCE_1")));
        riskArrayDerivativeScenarios.setUnderlyingAssetStandardDeviationPercent1(Integer.parseInt((String)doc.get("UNDERLINE_ASSET_STANDART_DVTN_1")));
        riskArrayDerivativeScenarios.setTheoreticalValue1(Integer.parseInt((String)doc.get("THEORITICAL_VALUE_1")));
        riskArrayDerivativeScenarios.setTheoreticalValueSign1(Integer.parseInt((String)doc.get("THEORITICAL_VALUE_SGN_1")));
        riskArrayDerivativeScenarios.setScenarioNumber2(Integer.parseInt((String)doc.get("SCNRO_NUMBER_2")));
        riskArrayDerivativeScenarios.setUnderlyingAssetNumber2(Integer.parseInt((String)doc.get("UNDRLN_ASSET_PRCE_2")));
        riskArrayDerivativeScenarios.setUnderlyingAssetStandardDeviationPercent2(Integer.parseInt((String)doc.get("UNDERLINE_ASSET_STANDART_DVTN_2")));
        riskArrayDerivativeScenarios.setTheoreticalValue2(Integer.parseInt((String)doc.get("THEORITICAL_VALUE_2")));
        riskArrayDerivativeScenarios.setTheoreticalValueSign2(Integer.parseInt((String)doc.get("THEORITICAL_VALUE_SGN_2")));
        riskArrayDerivativeScenarios.setScenarioNumber3(Integer.parseInt((String)doc.get("SCNRO_NUMBER_3")));
        riskArrayDerivativeScenarios.setUnderlyingAssetNumber3(Integer.parseInt((String)doc.get("UNDRLN_ASSET_PRCE_3")));
        riskArrayDerivativeScenarios.setUnderlyingAssetStandardDeviationPercent3(Integer.parseInt((String)doc.get("UNDERLINE_ASSET_STANDART_DVTN_3")));
        riskArrayDerivativeScenarios.setTheoreticalValue3(Integer.parseInt((String)doc.get("THEORITICAL_VALUE_3")));
        riskArrayDerivativeScenarios.setTheoreticalValueSign3(Integer.parseInt((String)doc.get("THEORITICAL_VALUE_SGN_3")));
        riskArrayDerivativeScenarios.setFiller((String)doc.get("RISK_ARRAY_NUMBER"));
        ObjectId id = (ObjectId) doc.get("_id");
        riskArrayDerivativeScenarios.setId(id.toString());

        return riskArrayDerivativeScenarios;
    }
}