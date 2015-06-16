package com.mongodb.m101j.homework.week2;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.m101j.util.Helpers.printJson;

import java.util.ArrayList;
import java.util.List;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Sorts.ascending;

public class HomeWorkLowestScoreRemover {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("students");
		MongoCollection<Document> gradeDocuments = db.getCollection("grades");
		/*
		If you select homework grade-documents, sort by student and then by score, 
		you can iterate through and find the lowest score for each student 
		by noticing a change in student id. As you notice that change of student_id, 
		remove the document
		*/
		
		Bson filter = eq("type", "homework");
		Bson sortStudent = new Document ("student_id",1);
		Bson sortScore = new Document ("score",1);
		//    collection.find().sort(orderBy(ascending("x", "y"), descending("z")))
		//List<Document> all = gradeDocuments.find(filter).sort(sortStudent).sort(sortScore).into(new ArrayList<Document>());
		//List<Document> all = gradeDocuments.find(filter).sort(ascending("student_id","score")).into(new ArrayList<Document>());
		 MongoCursor<Document> iterator = gradeDocuments.find(filter).sort(ascending("student_id","score")).iterator();
		
//		for(Document curr:all) {
//			
//			printJson(curr);
//		}
		 Object studentId = -1;
		try {
            while (iterator.hasNext()) {
                Document entry = iterator.next();
                if (!entry.get("student_id").equals(studentId)) {
   	            // the lowest homework should be first. remove it and so on
                    System.out.println("Removing: " + entry);
                    Object id = entry.get("_id");
                    gradeDocuments.deleteOne(eq("_id", id));
                }
                studentId = entry.get("student_id");
           }
        } finally {
        	iterator.close();
        }
        
		
	}

}
