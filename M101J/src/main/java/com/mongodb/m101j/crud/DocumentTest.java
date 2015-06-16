package com.mongodb.m101j.crud;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId; 
import  static com.mongodb.m101j.util.Helpers.printJson;

public class DocumentTest {

	public static void main(String[] args) {
		Document document =new Document()
				.append("str","Mongo DB, Hello")
				.append("int", 42)
				.append("double",1.1)
				.append("b",false)
				.append("date", new Date())
				.append("objectId",new ObjectId())
				.append("null",null)
				.append("embeddedDoc",new Document("x",0))
				.append("list",Arrays.asList(1,2,3));
				
		
		printJson(document);
	}

}
