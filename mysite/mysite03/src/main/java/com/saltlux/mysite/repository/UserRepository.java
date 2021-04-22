package com.saltlux.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.saltlux.mysite.exception.UserRepositoryException;
import com.saltlux.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private DataSource dataSource;

	public UserVo findByNo(Long no ) {
		UserVo userVo = null ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			conn = dataSource.getConnection();
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
		} catch(SQLException e) {
			throw new UserRepositoryException(e.toString()); // db exception을 runtimeException으로 전환

		} finally {
			try {
				if(result!=null) result.close();
				if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				throw new UserRepositoryException(e.toString()); 
			}

		}
		return userVo;
	}

	public UserVo findByEmailAndPassword(UserVo vo ) {
		UserVo userVo = null ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			conn = dataSource.getConnection();
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
		} catch(SQLException e) {
			throw new UserRepositoryException(e.toString()); 

		} finally {
			try {
				if(result!=null) result.close();
				if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				throw new UserRepositoryException(e.toString()); 
			}

		}
		return userVo;
	}

	public boolean updateNameAndGender(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "update user set name = ?, gender=? where no = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setLong(3, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;
			return result;
		} catch(SQLException e) {
			throw new UserRepositoryException(e.toString()); 

		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				throw new UserRepositoryException(e.toString()); 
			}

		}
	}

	public boolean updateAll(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "update user set name = ?, gender=?, password=? where no = ?;";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setString(3, vo.getPassword());
			pstmt.setLong(4, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;
			return result;
		} catch(SQLException e) {
			throw new UserRepositoryException(e.toString()); 
		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				throw new UserRepositoryException(e.toString()); 
			}

		}
	}

	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "insert into user ( no, name, email, password, gender, join_date) " +
					"values (null, ?, ?, ?, ?,now());";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			int count = pstmt.executeUpdate();
			result = count == 1;
			return result;
		} catch(SQLException e) {
			throw new UserRepositoryException(e.toString()); 
		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				throw new UserRepositoryException(e.toString()); 
			}
		}
	}
}
