package com.mongodb.m101j.crud;

import static com.mongodb.m101j.util.Helpers.printJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class FindTest {

	public static void main(String[] args) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("findTest");
		
		collection.drop();
		
		for(int i = 0; i < 10 ; i++) {
			collection.insertOne(new Document("x",i));
		}
		
		System.out.println("Find one:");
		Document first = collection.find().first();
		printJson(first);
		
		System.out.println("Find all with into: ");
		
		List<Document> all = collection.find().into(new ArrayList<Document>());
		
		for(Document curr:all) {
			printJson(curr);
		}
		
		System.out.println("Find all with iteration: ");
		
		MongoCursor<Document> cursor = collection.find().iterator();
		
		try {
			while(cursor.hasNext()) {
				Document curr = cursor.next();
				printJson(curr);
				
			}
		}
		finally {
			cursor.close();
		}
		
		System.out.println("Count:");
		long count = collection.count();
		System.out.println("count= " +count);

		
	}

}
