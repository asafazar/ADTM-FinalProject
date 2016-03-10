package com.DB;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by asaf on 09/03/2016.
 */
public class MongoDBUserDAO {

    private DBCollection col;
    String key = "Bar12345Bar12345"; // 128 bit key
    Key aesKey;

    public MongoDBUserDAO(MongoClient mongo) {
        this.col = mongo.getDB("adtmdb").getCollection("users");
    }

    public String createUser(String userName, String password)
    {
        try {
            // Create key and cipher
            aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            DBObject query = BasicDBObjectBuilder.start()
                    .append("userName", userName).get();
            DBObject data = this.col.findOne(query);
            if (data == null) {
                BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
                builder.append("userName", userName)
                        .append("password", encrypted);
                DBObject doc = builder.get();
                this.col.insert(doc);
                ObjectId id = (ObjectId) doc.get("_id");
                return id.toString();
            }
            else
            {
                return "0";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "0";
        }
    }

    public String getUserId(String userName, String password)
    {
        try
        {
            // Create key and cipher
            aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            DBObject query = BasicDBObjectBuilder.start()
                    .append("userName", userName)
                    .append("password", encrypted).get();
            DBObject data = this.col.findOne(query);

            if (data != null)
            {
                return data.get("_id").toString();
            }
            else
            {
                return "0";
            }
        }
        catch(Exception e)
        {
            return "0";
        }
    }
}
