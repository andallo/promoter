package ru.carbay.promoter.ds;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.logging.slf4j.SLF4JLoggerImplFactory;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by andrey on 10.09.17.
 */
public class MongoDS {

    private static MongoDS instance = new MongoDS();

    private MongoClient mongo;
    private Datastore datastore;

    public static MongoDS getInstance() {
        return instance;
    }

    public void start(Properties mongoProperties) {
        MorphiaLoggerFactory.reset();
        MorphiaLoggerFactory.registerLogger(SLF4JLoggerImplFactory.class);

        String userName = mongoProperties.getProperty("mongo.userName");
        String database = mongoProperties.getProperty("mongo.database");
        String password = mongoProperties.getProperty("mongo.password");
        String address = mongoProperties.getProperty("mongo.address");
        Integer port = Integer.valueOf(mongoProperties.getProperty("mongo.port"));

        if (userName != null && password != null) {
            MongoCredential credential = MongoCredential.createMongoCRCredential(userName, database, password.toCharArray());
            mongo = new MongoClient(new ServerAddress(address, port), Collections.singletonList(credential));
        } else {
            mongo = new MongoClient(new ServerAddress(address, port));
        }

        Morphia morphia = new Morphia();
        morphia.map();
        morphia.getMapper().getOptions().setStoreEmpties(true);
        morphia.getMapper().getOptions().setStoreNulls(false);

        datastore = morphia.createDatastore(mongo, database);

    }

    public void stop() {
        MorphiaLoggerFactory.reset();
        datastore = null;
        if (mongo != null) {
            mongo.close();
        }
    }

    public Datastore ds() {
        return datastore;
    }

}
