import org.bson.types.ObjectId;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.sql.Time;
import java.util.Date;


public class StockConvertor {

    // convert Stock Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Stock stock) {

        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("ContractNumber", stock.getContractNumber()).append("Amount", stock.getAmount())
                .append("AssetCode", stock.getAssetCode()).append("BuyCodeNumber", stock.getBuyCodeNumber())
                .append("SellCodeNumber", stock.getSellCodeNumber()).append("AssetName", stock.getAssetName())
                .append("Time", stock.getTime()).append("TradeNumber", stock.getTradeNumber())
                .append("TradeStatus", stock.isTradeStatus()).append("ParticipateCode", stock.isParticipateCode())
                .append("MatchingTradeDeal", stock.isMatchingTradeDeal()).append("Price", stock.getPrice())
                .append("Date", stock.getDate());
        if (stock.getID() != null)
            builder = builder.append("_id", new ObjectId(stock.getID()));
        return builder.get();
    }

    // convert DBObject Object to Stock
    // take special note of converting ObjectId to String
    public static Stock toStock(DBObject doc)
    {
        Stock stock = new Stock();
        stock.setContractNumber(Integer.parseInt((String) doc.get("ContractNumber")));
        stock.setAmount(Integer.parseInt((String)doc.get("Amount")));
        stock.setAssetCode((String) doc.get("AssetCode"));
        stock.setBuyCodeNumber((String) doc.get("BuyCodeNumber"));
        stock.setSellCodeNumber((String) doc.get("SellCodeNumber"));
        stock.setAssetName((String) doc.get("AssetName"));
        stock.setTime((Time) doc.get("Time"));
        stock.setTradeNumber((String) doc.get("TradeNumber"));
        stock.setTradeStatus(Boolean.parseBoolean((String) doc.get("TradeStatus")));
        stock.setParticipateCode(Boolean.parseBoolean((String) doc.get("ParticipateCode")));
        stock.setMatchingTradeDeal(Boolean.parseBoolean((String) doc.get("MatchingTradeDeal")));
        stock.setPrice(Double.parseDouble((String) doc.get("Price")));
        stock.setDate((Date) doc.get("Date"));
        ObjectId id = (ObjectId) doc.get("_id");
        stock.setID(id.toString());
        return stock;
    }
}