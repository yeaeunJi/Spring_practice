package com.saltlux.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.saltlux.mysite.db.Mysql;
import com.saltlux.mysite.vo.UserVo;

public class UserDao {

	//logger 
	private static final Logger logger   = Logger.getLogger(UserDao.class);
 
	
	public UserVo findByNo(Long no ) {
		System.out.println("*************** user 번호로 사용자 정보 조회 시작 *************** ");
		UserVo userVo = null ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);
			String sql ="select no, name, email, gender from user where no = ?;";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			result = pstmt.executeQuery();

			if(result.next()) {
				String name = result.getString(2);
				String email = result.getString(3);
				String gender = result.getString(4);
				userVo = new UserVo();
				userVo.setName(name);
				userVo.setNo(no);
				userVo.setEmail(email);
				userVo.setGender(gender);
			}
			System.out.println("*************** user 번호로 사용자 정보 조회 완료 *************** ");
		} catch(SQLException e) {
			System.out.println("error-"+e);

		} finally {
			try {
				if(result!=null) result.close();
				//if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				System.out.println("error-"+e);
			}

		}
		return userVo;
	}

	public UserVo findByEmailAndPassword(UserVo vo ) {
		System.out.println("*************** 로그인 시작 *************** ");
		UserVo userVo = null ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);

			String sql ="select no, name from user where email=? and password=?;";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());

			result = pstmt.executeQuery();

			if(result.next()) {
				Long no = result.getLong(1);
				String name = result.getString(2);

				userVo = new UserVo();
				userVo.setName(name);
				userVo.setNo(no);
			}
			System.out.println("*************** 로그인 완료 *************** ");
		} catch(SQLException e) {
			System.out.println("error-"+e);

		} finally {
			try {
				if(result!=null) result.close();
				//if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				System.out.println("error-"+e);
			}

		}
		return userVo;
	}

	public boolean updateNameAndGender(UserVo vo) {
		System.out.println("*************** updateNameAndGender 시작 *************** ");
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(false);

			String sql = "update user set name = ?, gender=? where no = ?;";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			//			pstmt.setString(3, vo.getPassword());
			pstmt.setString(2, vo.getGender());
			pstmt.setLong(3, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;
			System.out.println("*************** updateNameAndGender end *************** ");
			return result;
		} catch(SQLException e) {
			System.out.println("error-"+e);
			return result;
		} finally {
			try {
				//if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				System.out.println("error-"+e);
			}

		}
	}

	public boolean updateAll(UserVo vo) {
		System.out.println("*************** updateAll start *************** ");
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(false);

			String sql = "update user set name = ?, gender=?, password=? where no = ?;";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setString(3, vo.getPassword());
			pstmt.setLong(4, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;
			System.out.println("*************** updateAll start *************** ");
			return result;
		} catch(SQLException e) {
			System.out.println("error-"+e);
			return result;
		} finally {
			try {
				//if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				System.out.println("error-"+e);
			}

		}
	}
	public boolean insert(UserVo vo) {
		System.out.println("*************** insert start *************** ");
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(false);

			String sql = "insert " +
					"into user ( no, name, email, password, gender, join_date) " +
					"values (null, ?, ?, ?, ?,now());";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			int count = pstmt.executeUpdate();
			result = count == 1;
			System.out.println("*************** insert end *************** ");
			return result;
		} catch(SQLException e) {
			System.out.println("error-"+e);
			return result;
		} finally {
			try {
				//if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				System.out.println("error-"+e);
			}
		}
	}
}
