package com.mongodb.m101j.crud;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import  static com.mongodb.m101j.util.Helpers.printJson;

public class InsertTest {

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("insertTest");
		
		coll.drop();
		
		Document smith = new Document("name", "Smith")
							.append("age", "30")
							.append("profession", "programmer");
		
		Document jones = new Document("name", "Jones")
		.append("age", "25")
		.append("profession", "hacker");
		
		printJson(smith);
		coll.insertMany(Arrays.asList(smith,jones));
		printJson(smith);

	}

}
