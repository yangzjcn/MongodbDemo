package com.yzj;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

/**
 * MongoDB 学习测试Demo
 * 
 * @author yzj
 * 
 */
public class MongoDBJDBC
{
	public static void main(String args[])
	{
		// saveDocument();
		// queryDocument();
		// updateDocument();
		// deleteDocument();
	}

	public static void saveDocument()
	{
		try
		{
			MongoCollection<Document> collection = ConnectionUtils.getDatabase().getCollection("test");
			System.out.println("集合 test 选择成功");
			// 插入文档
			/**
			 * 1. 创建文档 org.bson.Document 参数为key-value的格式 2. 创建文档集合List<Document>
			 * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>)
			 * 插入单个文档可以用 mongoCollection.insertOne(Document)
			 * */
			Document document = new Document("title", "我在学习MongoDB!").append("description", "这是个描述!")
					.append("likes", 100).append("by", "小杨");
			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			collection.insertMany(documents);
			System.out.println("文档插入成功");
		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void queryDocument()
	{
		try
		{
			MongoCollection<Document> collection = ConnectionUtils.getDatabase().getCollection("test");
			System.out.println("集合 test 选择成功");

			// 检索所有文档
			/**
			 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3.
			 * 通过游标遍历检索出的文档集合
			 * */
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext())
			{
				System.out.println(mongoCursor.next());
			}

		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void updateDocument()
	{
		try
		{
			MongoCollection<Document> collection = ConnectionUtils.getDatabase().getCollection("test");
			System.out.println("集合 test 选择成功");

			// 更新文档 将文档中likes=100的文档修改为likes=200
			collection.updateMany(Filters.eq("likes", 100), new Document("$set", new Document("likes", 200)));
			// 检索查看结果
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext())
			{
				System.out.println(mongoCursor.next());
			}

		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static void deleteDocument()
	{
		try
		{
			MongoCollection<Document> collection = ConnectionUtils.getDatabase().getCollection("test");
			System.out.println("集合 test 选择成功");

			// 删除符合条件的第一个文档
			collection.deleteOne(Filters.eq("likes", 100));
			// 删除所有符合条件的文档
			collection.deleteMany(Filters.eq("likes", 200));
			// 检索查看结果
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext())
			{
				System.out.println(mongoCursor.next());
			}

		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

}
