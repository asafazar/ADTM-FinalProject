import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class MongoDBStockDAO {

    private DBCollection col;

    public MongoDBStockDAO(MongoClient mongo) {
        this.col = mongo.getDB("ADTM").getCollection("MAOF");
    }

    public Stock createStock(Stock stock) {
        DBObject doc = StockConvertor.toDBObject(stock);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        stock.setID(id.toString());
        return stock;
    }

    public void updateStock(Stock stock) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(stock.getID())).get();
        this.col.update(query, StockConvertor.toDBObject(stock));
    }

    public List<Stock> readAllStocks() {
        List<Stock> data = new ArrayList<Stock>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            Stock stock = StockConvertor.toStock(doc);
            data.add(stock);
        }
        return data;
    }

    public void deleteStock(Stock stock) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(stock.getID())).get();
        this.col.remove(query);
    }

    public Stock readStock(Stock stock) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(stock.getID())).get();
        DBObject data = this.col.findOne(query);
        return StockConvertor.toStock(data);
    }
}