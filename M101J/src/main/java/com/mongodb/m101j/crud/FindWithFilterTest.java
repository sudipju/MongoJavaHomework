package com.mongodb.m101j.crud;

import static com.mongodb.m101j.util.Helpers.printJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

public class FindWithFilterTest {

	public static void main(String[] args) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("findWithFilterTest");
		
		collection.drop();
		
		for(int i = 0; i < 10 ; i++) {
			collection.insertOne(new Document()
					.append("x", new Random().nextInt(2))
					.append("y",new Random().nextInt(100)));
		}
//		Bson filter = new Document("x",0)
//		.append("y", new Document("$gt",10)).append("$lt", 90);
//		
		Bson filter = and(eq("x", 0),gt("y",10),lt("y",90));
		List<Document> all = collection.find(filter).into(new ArrayList<Document>());
		
		for(Document curr:all) {
			printJson(curr);
		}
		
		System.out.println("Find all with iteration: ");
		
		
		
		System.out.println("Count:");
		long count = collection.count(filter);
		System.out.println("count= " +count);

		
	}

}
