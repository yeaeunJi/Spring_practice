package com.saltlux.guestbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.saltlux.guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepository {

	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into guestbook values(null, ? , ?, ?, date_format(now(), '%Y-%m-%d %H:%i:%s'));";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			int count = pstmt.executeUpdate() ; 
			result = count == 1 ? true:false;

		} catch (SQLException e) { 
			System.out.println("error:"+e);
		} finally { // 자원 정리
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<GuestbookVo> findAll(){
		List<GuestbookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null; 

		try {
			conn = getConnection();
			String sql = "select no, name, password, contents, date_format(reg_date, '%Y-%m-%d %H:%i:%s') as reg_date from guestbook order by no desc ;";
			pstmt = conn.prepareStatement(sql);

			result = pstmt.executeQuery();

			while(result.next()) {
				Long no = result.getLong(1); // 1부터 시작
				String name = result.getString(2);
				String password = result.getString(3);
				String contents = result.getString(4);
				String regDate = result.getString(5);
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				list.add(vo);
			}

		} catch (SQLException e) { 
			System.out.println("error:"+e);
		} finally { // 자원 정리
			try {
				if(result != null) result.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public boolean delete(GuestbookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "delete from guestbook where no=? and password=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			int count = pstmt.executeUpdate() ;
			result = count == 1 ? true:false;

		} catch (SQLException e) { 
			System.out.println("error:"+e);
		} finally { // 자원 정리
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("error - "+e);
		}
		return conn;
	}

}
