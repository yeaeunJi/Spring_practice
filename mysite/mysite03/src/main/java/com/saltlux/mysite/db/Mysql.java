package com.saltlux.mysite.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Mysql {
	private static Mysql mysql ;
	private static Connection normalConn = null;
	private static Connection replicatedConn = null;
	public static boolean useReplicated = false;

	static {
		try {
			mysql =  new Mysql();
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}

	} 

	private Mysql() throws SQLException {	
		if (! useReplicated) normalConnect();
		else replicatedConnect();
	} ; // Constructor

	public void normalConnect() throws SQLException {
		normalConn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			normalConn = DriverManager.getConnection(url, "webdb", "webdb");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("error-"+e);
		}
	}

	public void replicatedConnect() throws SQLException {
		replicatedConn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties props = new Properties();
			props.put("autoReconnect", "true");
			props.put("user", "repluser");
			props.put("password", "replpw");
		    props.put("roundRobinLoadBalance", "true");
			replicatedConn  = DriverManager.getConnection("jdbc:mysql:replication://localhost:3306, localhost:3307/webdb?characterEncoding=utf8&serverTimezone=UTC", props);
		} 
		catch (ClassNotFoundException e) {
			System.out.println("replicatedConnect ClassNotFoundException-"+e);
		}
		catch (Exception e) {
			System.out.println("error ::::::::::::::::::: "+e.getLocalizedMessage());
			System.out.println("replicatedConnect error-"+e);
		}
	}

	public static Mysql getInstance() {
		return mysql;
	}

	private static Connection getNormalConnection() {		
		return normalConn;
	}

	private  static Connection getReplicatedConnection() {
		return replicatedConn;
	}
	
	// db connection method
	public static Connection getConnection() throws SQLException {
		if (useReplicated) {
			return getReplicatedConnection();
		}else {
			return getNormalConnection();
		}
	}
}
