package com.DB;

import com.model.user.User;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.List;

/**
 * Created by asaf on 09/03/2016.
 */
public class MongoDBUserDAO {

    private DBCollection col;
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue =
            new byte[] { 'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

    public MongoDBUserDAO(MongoClient mongo) {
        this.col = mongo.getDB("adtmdb").getCollection("users");
    }

    private static Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

    public boolean createUser(String displayName, String password, String email)
    {
        try
        {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            String encryptedValue = new BASE64Encoder().encode(encrypted);
            DBObject query = BasicDBObjectBuilder.start()
                    .append("email", email).get();
            DBObject data = this.col.findOne(query);

            if (data == null)
            {
                BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
                builder.append("displayName", displayName)
                        .append("password", encryptedValue)
                        .append("email", email);
                DBObject doc = builder.get();
                this.col.save(doc);

                return true;
            }

            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();

            return false;
        }
    }

    public User getUserByMail(String email)
    {
        Gson gson = new Gson();
        BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        DBObject query = builder.start()
                .append("email", email).get();
        DBCursor cursor = this.col.find(query);

        if (cursor.hasNext())
        {
            DBObject dbObject = cursor.next();
            User user = gson.fromJson(JSON.serialize(dbObject), User.class);
            user.setId(dbObject.get("_id").toString());

            return user;
        }

        return null;
    }
    public List<User> getAllUsers()
    {
        // TODO : implement method, need to think if this important

        return null;
    }
    public boolean getUserId(String email, String password)
    {
        try
        {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);
            DBObject query = BasicDBObjectBuilder.start()
                    .append("email", email).get();
            DBObject data = this.col.findOne(query);

            if (data != null)
            {
                byte[] decordedValue = new BASE64Decoder().decodeBuffer(
                        data.get("password").toString());
                byte[] decValue = c.doFinal(decordedValue);
                String decryptedValue = new String(decValue);

                if (decryptedValue.equals(password))
                {
                    return true;
                }

                return false;
            }

            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public void deleteUser(String userId)
    {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(userId)).get();
        this.col.remove(query);
    }

    public void saveUser(String id, String userName, String mail, String pictureLink)
    {
        BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
        builder.append("displayName", userName)
                .append("mail", mail)
                .append("pictureLink", pictureLink);
        DBObject doc = builder.get();
        this.col.insert(doc);
    }

    public User save(User user)
    {
        Gson gson = new Gson();
        DBObject doc = (DBObject) JSON.parse(gson.toJson(user, User.class));
        this.col.save(doc);
        User newUser = getUserByMail(user.getEmail());

        return newUser;
    }
}
