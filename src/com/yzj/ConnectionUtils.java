package com.yzj;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConnectionUtils
{

	/**
	 * 获取连接MogonDB对象
	 * 
	 * @return MongoDatabase
	 */
	@SuppressWarnings("resource")
	public static MongoDatabase getDatabase()
	{
		// 连接到 mongodb 服务
		MongoClient mongoClient = new MongoClient("localhost", 27017);

		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		System.out.println("Connect to database successfully!");
		return mongoDatabase;
	}
}
