package com.saltlux.mysite.dao;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.saltlux.mysite.db.Mongo;
import com.saltlux.mysite.vo.BoardVo2;
import com.saltlux.mysite.vo.PageVo;

public class BoardDao2 {

	public  int  getNewGNo() {
		MongoDatabase db = null;
		db = Mongo.getDatabase();
		int max = 0;
		MongoCollection<Document> collection = db.getCollection("board");
		Document query = new Document("gNo", -1);
		ArrayList<Object> results = new ArrayList<>();
		collection.find().sort(query).limit(1).into(results);

		for(Object doc: results) {
			Document doc1 = (Document)doc;
			max = doc1.getInteger("gNo");
		}
		return max+1;
	}

	public boolean insert(BoardVo2 vo) {
		boolean result = false;
		MongoDatabase db = null;
		try {
			db = Mongo.getDatabase();

			if (vo.getgNo() == 0) {
				int gNo = getNewGNo();
				vo.setgNo(gNo);
			}
			String dateFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);
			vo.setRegDate(df.format(new Date()));
			MongoCollection<Document> boards = db.getCollection("board");
			Document doc = new Document(vo.voToMap());

			boards.insertOne(doc);
		} catch (Exception e) {
			System.out.println("insert error-"+e);
		} 				
		return result;
	}


	public boolean updateOrderNo(int gNo, int oNo, int number) {
		MongoDatabase db = Mongo.getDatabase();
		MongoCollection<Document> boards = db.getCollection("board");
		UpdateResult updateResult= boards.updateMany(gt("oNo", oNo), Updates.inc("oNo", number));
		return updateResult.getModifiedCount() > 0 ?true:false;
	}

	public List<BoardVo2> findAll(PageVo pageVo){
		MongoDatabase db = Mongo.getDatabase();			
		ArrayList<Object> results = new ArrayList<>();
		ArrayList<BoardVo2> list = new ArrayList<>();
		try {
			MongoCollection<Document> boards = db.getCollection("board");
			Document sort = new Document().append("gNo", -1).append("oNo", 1);
			int offset = Integer.parseInt(pageVo.getStart().toString());
			int limit = Integer.parseInt(pageVo.getShowNum().toString());

			boards.find().sort(sort).skip(offset).limit(limit).into(results);
			for (Object obj : results) {
				BoardVo2 vo = new BoardVo2();
				Document doc = (Document) obj;
				vo.setNo(doc.getObjectId("_id").toString());
				vo.setTitle(doc.getString("title"));
				vo.setWriter(doc.getString("writer"));
				vo.setContents(doc.getString("contents"));
				vo.setRegDate(doc.getString("regDate"));
				vo.setUserNo(doc.getInteger("userNo"));
				vo.setgNo(doc.getInteger("gNo"));
				vo.setoNo(doc.getInteger("oNo"));
				vo.setDepth(doc.getInteger("depth"));
				list.add(vo);
			}
		}catch(IllegalArgumentException e) {
			System.out.println("IllegalArgumentException  : "+e.getMessage());
		}
		catch(Exception e) {
			System.out.println("findAll Exception : "+e.getMessage());
		}	
		return list;
	}

	public PageVo paging(Long curPage, Long start, Long showNum, Long startPage, Long endPage,	Long pageShowNum){
		MongoDatabase db = Mongo.getDatabase();
		MongoCollection<Document> collection = db.getCollection("board");		
		long totalCount = collection.count();
		long totalPage = (long) (Math.ceil(totalCount/(float)showNum));
		PageVo page = new PageVo(curPage, start, showNum, startPage, endPage, pageShowNum);
		page.setTotalCount(totalCount);
		page.setTotal(totalPage==0?1:totalPage);
		return page;
	}


	public BoardVo2 findOne(String no){
		MongoDatabase db = null;
		db = Mongo.getDatabase();
		MongoCollection<Document> collection = db.getCollection("board");
		BoardVo2 vo = new BoardVo2();

		ObjectId id = new ObjectId(no);
		Document query = new Document().append("_id", id );
		ArrayList<Object> results = new ArrayList<>();
		collection.find(query).into(results);

		for (Object obj : results) {
			Document doc = (Document) obj;
			vo.setNo(doc.getObjectId("_id").toString());
			vo.setTitle(doc.getString("title"));
			vo.setWriter(doc.getString("writer"));
			vo.setContents(doc.getString("contents"));
			vo.setRegDate(doc.getString("regDate"));
			vo.setUserNo(doc.getInteger("userNo"));
			vo.setgNo(doc.getInteger("gNo"));
			vo.setoNo(doc.getInteger("oNo"));
			vo.setDepth(doc.getInteger("depth"));
		}
		return vo;
	}


	public boolean update(BoardVo2 vo) {
		MongoDatabase db = Mongo.getDatabase();
		MongoCollection<Document> boards = db.getCollection("board");
		Document updateValue = new Document("title", vo.getTitle() ).append("contents", vo.getContents());
		Document updateDoc = new Document("$set",updateValue);
		ObjectId id = new ObjectId(vo.getNo());
		Document query = new Document().append("_id", id );
		UpdateResult updateResult= boards.updateOne(query, updateDoc);
		return updateResult.getModifiedCount() > 0 ?true:false;
	}

	public boolean delete(String no) {
		MongoDatabase db = Mongo.getDatabase();
		MongoCollection<Document> collection = db.getCollection("board");

		ObjectId id = new ObjectId(no);
		Document query = new Document().append("_id", id );
		ArrayList<Object> results = new ArrayList<>();
		DeleteResult queryResult =  collection.deleteOne(query);
		return queryResult.getDeletedCount() == 1?true:false;
	}

	public boolean isGetChild(BoardVo2 vo) {
		MongoDatabase db =Mongo.getDatabase();
		int count = 0;
		MongoCollection<Document> collection = db.getCollection("board");
		ArrayList<Object> results = new ArrayList<>();
		collection.find(and(gt("depth", vo.getDepth()), eq("oNo", vo.getoNo()+1), eq("gNo", vo.getgNo()))).into(results);
		count = results.size();
		return count>0?true:false;
	}

	public BoardVo2 getParentInfo(String no) {
		MongoDatabase db = null;
		db = Mongo.getDatabase();
		MongoCollection<Document> collection = db.getCollection("board");
		BoardVo2 vo = new BoardVo2();
		ObjectId id = new ObjectId(no);
		Document filter = new Document().append("_id", id );
		ArrayList<Object> results = new ArrayList<>();
		collection.find(filter).projection(fields(include("gNo", "oNo", "depth"), excludeId())).into(results);

		for (Object obj : results) {
			Document doc = (Document) obj;
			vo.setNo(no);
			vo.setgNo(doc.getInteger("gNo"));
			vo.setoNo(doc.getInteger("oNo"));
			vo.setDepth(doc.getInteger("depth"));
		}
		return vo;
	}

	public int getMaxONo(int gNo) {
		MongoDatabase db = null;
		db = Mongo.getDatabase();
		int max = 0;
		MongoCollection<Document> collection = db.getCollection("board");
		Document filter = new Document("gNo", gNo);
		Document sort = new Document("oNo", -1);
		ArrayList<Object> results = new ArrayList<>();
		collection.find(filter).sort(sort).limit(1).into(results);

		for(Object doc: results) {
			Document doc1 = (Document)doc;
			max = doc1.getInteger("oNo");
			System.out.println(doc1.get("oNo"));
		}
		return max;
	}
}