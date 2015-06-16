package com.mongodb;

import static java.util.Arrays.asList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDriverApp {

	public static void main(String[] args) {
		//MongoClient mongoClient =new MongoClient("localhost",27017);
		//MongoClient mongoClient =new MongoClient(asList(new ServerAddress("localhost",27017)));
		MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
		MongoClient mongoClient = new MongoClient(new ServerAddress(),options);
		
		MongoDatabase db = mongoClient.getDatabase("test").withReadPreference(ReadPreference.secondary());
		MongoCollection<Document> col = db.getCollection("test");
		System.out.println("mongoClient="+mongoClient);
		System.out.println("db="+db);
		System.out.println("col="+col);
		//System.out.println("="+db);
	}

}
