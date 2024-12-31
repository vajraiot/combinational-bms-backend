package com.bms;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongodbRawDataSaving {
	static Logger logger = LoggerFactory.getLogger(BMSDataProcessing.class.getName());
	
	 static	ResourceBundle rb = ResourceBundle.getBundle("portConfig");
	static MongoDatabase database;
	
	public void mongodbRawDataInsertion(String strHexData)
     {
		 try {
			 logger.info("Going to Save HexData in MongoDB.");
		 
  	   int bytesCount=strHexData==null?0:strHexData.length()/2;
  	   
  	   getMongoDBCollection();
			Document doc  = new Document();
			doc
			//.append("rawDataHex", strbytes)
			.append("rawData", strHexData)
			.append("createdDate", LocalDateTime.now())
			.append("statusCode", 1)
			.append("bytesCount",bytesCount);
			MongoCollection<Document> grades = database.getCollection("client_data");
			grades.insertOne(doc);
			logger.info("Successfully RawData inserted in mongo DB.");
		 }
		 catch(Exception ex)
		 {
			 logger.error("Exception occured in  mongodbRawDataInsertion"+ex.toString());
		 }
		 
     }
	 
	 public static MongoDatabase getMongoDBCollection() {

			try {
			if (database == null) {

				MongoClient mongo = new MongoClient("localhost", 27017);
				//database = mongo.getDatabase("d1"); //this is for testing
				database = mongo.getDatabase("iot");
				return database;
			} else
				return database;
			}
			catch( Exception ex)
			{
				logger.error("Exception occured while Connecting with mongoDB:"+ex.toString());
				return null;
			}
		}
	
}
