package com.saltlux.mysite.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Mongo {
	private static Mongo mongo =  new Mongo() ;
	private static MongoClient conn;
	
	static {
		mongo =  new Mongo();
	} // static initializer(자바 프로그램 실행 시 단한번 호출됨)

	private Mongo(){	
		conn = new MongoClient("localhost", 27017);
	} ; // Constructor

	public static Mongo getInstance() {
		return mongo;
	}

	// db connection method
	public static MongoDatabase getDatabase(){
		try {
			return  conn.getDatabase("webdb");
		} catch (Exception e) {
			System.out.println("getDatabase"+e.getLocalizedMessage());
			return null;
		}
	}

}
