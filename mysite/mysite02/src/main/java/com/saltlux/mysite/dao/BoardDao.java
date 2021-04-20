package com.saltlux.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.saltlux.mysite.db.Mysql;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.PageVo;

public class BoardDao {
	//private static final Logger logger   = Logger.getLogger(BoardDao.class);
	
	private  Long  getNewGNo() {
		System.out.println("*************** getNewGNo start *************** ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Long max = 0L;
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);
			String sql = "select ifnull(max(group_no),0)+1 as max from board;  ";
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeQuery();
			System.out.println(pstmt.toString());
			result.next();
			max = result.getLong(1); 
			System.out.println("*************** getNewGNo end *************** ");
		} catch (SQLException e) {
			System.out.println("getNewGNo error-"+e);
		} finally {
			try {
				if(pstmt!=null)	pstmt.close();
				//if(conn!=null) conn.close();
				if(result!=null) result.close();
			} catch (SQLException e) {
				System.out.println("여기!!!");
				e.printStackTrace();
			}
		}
		return max;
	}

	// insert 
	public boolean insert(BoardVo vo) {
		System.out.println("*************** insert start *************** ");
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Mysql.getConnection();
			Long gNo = getNewGNo();
			
			if(Mysql.useReplicated) {
				conn.setReadOnly(false);
			}
			String sql = "insert"	+ 
					"	into board (no, title, contents, user_no, count, reg_date, group_no, order_no, depth, del_flag) "	+
					"   values (null, ?, ?,?, 0, now(), ?, 1 , 0, 'F');";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3,  vo.getUserNo());
			pstmt.setLong(4, gNo );
			System.out.println(pstmt.toString());
			int count = pstmt.executeUpdate();
			result = count == 1;
			System.out.println("*************** insert end *************** ");
		} catch (SQLException e) {
			System.out.println("insert error-"+e);
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				//if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean replyInsert(BoardVo vo) {
		System.out.println("*************** replyInsert start *************** ");
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(false);
			String sql = "insert"	+ 
					"	into board (no, title, contents, user_no, count, reg_date, group_no, order_no, depth, del_flag) "	+
					"   values (null, ?, ?,?, 0, now(), ?, ? , ?, 'F');";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3,  vo.getUserNo());
			pstmt.setLong(4, vo.getgNo());
			pstmt.setLong(5,  vo.getoNo());
			pstmt.setLong(6, vo.getDepth());
			int count = pstmt.executeUpdate();
			result = count == 1;
			System.out.println("*************** replyInsert end *************** ");
		} catch (SQLException e) {
			System.out.println("replyInsert error-"+e);
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				//if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}


	public boolean updateOrderNo(Long gNo, Long oNo, Long no) {
		System.out.println("*************** updateOrderNo start *************** ");
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(false);
			sql = "update board set order_no= order_no+1 where group_no = ? and order_no > ? and no > 0;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, gNo);
			pstmt.setLong(2, oNo);
			int count = pstmt.executeUpdate();
			result = count >= 1;
			System.out.println("*************** updateOrderNo end *************** ");
		} catch (SQLException e) {
			System.out.println("updateOrderNo error-"+e);
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				//if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> findAll(PageVo pageVo, String keyword){
		System.out.println("*************** findAll start *************** ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<BoardVo> list = new ArrayList<BoardVo>();

		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);
			String sql = "select no, title, (select name from user where no=board.user_no) as writer, user_no, count, reg_date, group_no, order_no, depth, del_flag  "
					+ "from board where contents like ? or title like ? order by group_no desc, order_no limit ?,?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%" );
			pstmt.setString(2,"%"+keyword+"%" );
			pstmt.setLong(3, pageVo.getStart());
			pstmt.setLong(4, pageVo.getShowNum());
			result = pstmt.executeQuery();
			while(result.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(result.getLong(1));
				vo.setTitle(result.getString(2));
				vo.setWriter(result.getString(3));
				vo.setUserNo(result.getLong(4));
				vo.setCount(result.getLong(5));
				vo.setRegDate(result.getString(6));
				vo.setgNo(result.getLong(7));
				vo.setoNo(result.getLong(8));
				vo.setDepth(result.getLong(9));
				vo.setDelFlag(result.getString(10).charAt(0));
				list.add(vo);
			}
			System.out.println("*************** findAll end *************** ");
		} catch (SQLException e) {
			System.out.println("findAll error-"+e);
		} finally {
			try {
				if(pstmt!=null)	pstmt.close();
				//if(conn!=null) conn.close();
				if(result!=null) result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public PageVo paging(Long shownum, String keyword){
		System.out.println("*************** paging start *************** ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		PageVo page = null;
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);
			String sql =  "SELECT count(no) as total, CASE WHEN ceiling(count(no)/?)  = 0 THEN 1 ELSE ceiling(count(no)/?)  END AS totalpage FROM board where contents like ? or title like ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,shownum );
			pstmt.setLong(2,shownum );
			pstmt.setString(3,"%"+keyword+"%" );
			pstmt.setString(4,"%"+keyword+"%" );
			//System.out.println(pstmt.toString());
			result = pstmt.executeQuery();

			Long totalPage = 1L;
			Long totalCount = 0L;
			page = new PageVo();

			while(result.next()) {
				totalCount = result.getLong(1);
				totalPage = result.getLong(2);
			}

			page.setTotalCount(totalCount);
			page.setTotal(totalPage);
			System.out.println("*************** paging end *************** ");
		} catch (SQLException e) {
			System.out.println("paging error-"+e);
		} finally {
			try {
				if(pstmt!=null)	pstmt.close();
				//if(conn!=null) conn.close();
				if(result!=null) result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	public List<BoardVo> search(String keyword){
		System.out.println("*************** search start *************** ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<BoardVo> list = new ArrayList<BoardVo>();

		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);
			String sql =  "select no, title, (select name from user where no=board.user_no) as writer, user_no, count, reg_date, group_no, order_no, depth,  del_flag  from board "+
					" where contents like ? or title like ?; ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			result = pstmt.executeQuery();

			while(result.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(result.getLong(1));
				vo.setTitle(result.getString(2));
				vo.setWriter(result.getString(3));
				vo.setUserNo(result.getLong(4));
				vo.setCount(result.getLong(5));
				vo.setRegDate(result.getString(6));
				vo.setgNo(result.getLong(7));
				vo.setoNo(result.getLong(8));
				vo.setDepth(result.getLong(9));
				vo.setDelFlag(result.getString(10).charAt(0));

				list.add(vo);
			}
			System.out.println("*************** search end *************** ");
		} catch (SQLException e) {
			System.out.println("search error-"+e);
		} finally {
			try {
				if(pstmt!=null)	pstmt.close();
				//if(conn!=null) conn.close();
				if(result!=null) result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// findOne
	public BoardVo findOne(Long no){
		System.out.println("*************** findOne start *************** ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		BoardVo vo = new BoardVo();

		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);
			String sql = "select no, title, contents, user_no, (select name from user where no=board.user_no) as writer, count, reg_date, depth, group_no, order_no from board " 
					+" where no = ? and del_flag = 'F' ;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			result = pstmt.executeQuery();


			result.next();
			vo.setNo(result.getLong(1));
			vo.setTitle(result.getString(2));
			vo.setContents(result.getString(3));
			vo.setUserNo(result.getLong(4));
			vo.setWriter(result.getString(5));
			vo.setCount(result.getLong(6));
			vo.setRegDate(result.getString(7));
			vo.setDepth(result.getLong(8));
			vo.setgNo(result.getLong(9));
			vo.setoNo(result.getLong(10));
			System.out.println("*************** findOne end *************** ");
		} catch (SQLException e) {
			System.out.println("findOne error-"+e);
		} finally {
			try {
				if(pstmt!=null)	pstmt.close();
				//if(conn!=null) conn.close();
				if(result!=null) result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	// update
	public boolean update(BoardVo vo) {
		System.out.println("*************** update start *************** ");
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(false);
			sql = "update board set title=?, contents=? where no = ?;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3,  vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;
			System.out.println("*************** update end *************** ");
		} catch (SQLException e) {
			System.out.println("update error-"+e);
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				//if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// update count
	public boolean updateCount(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(false);
			sql = "update board set count=? where no = ?;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,  vo.getCount());
			pstmt.setLong(2,  vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("updateCount error-"+e);
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				//if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean delete(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(false);
			String sql = "delete from board where no = ? ;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,  vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("delete error-"+e);
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				//if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean getChildCount(BoardVo vo) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);
			String sql = "select count(*)  from board where depth > ? and order_no = ?+1 and group_no=?;";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setLong(1,  vo.getNo());
			pstmt.setLong(1, vo.getDepth());
			pstmt.setLong(2, vo.getoNo());
			pstmt.setLong(3, vo.getgNo());
			rs = pstmt.executeQuery();

			rs.next();
			int count = 0;
			count = rs.getInt(1);
			result = count>0?true:false;

		} catch (SQLException e) {
			System.out.println("getChildCount error-"+e);
		} finally {
			try {
				if(pstmt!=null)	pstmt.close();
				//if(conn!=null) conn.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}



	public BoardVo getParentInfo(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		BoardVo vo = new BoardVo();

		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);
			String sql = "select group_no, order_no, depth from board where no=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			result = pstmt.executeQuery();

			result.next();
			vo.setgNo(result.getLong(1));
			vo.setoNo(result.getLong(2));
			vo.setDepth(result.getLong(3));

		} catch (SQLException e) {
			System.out.println("getParentInfo error-"+e);
		} finally {
			try {
				if(pstmt!=null)	pstmt.close();
				//if(conn!=null) conn.close();
				if(result!=null) result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	public Long getMaxONo(Long gNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Long max = 0L;
		try {
			conn = Mysql.getConnection();
			if(Mysql.useReplicated) conn.setReadOnly(true);
			String sql = "select ifnull(max(order_no),0)+1 as max from board where group_no = ?;  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, gNo);
			result = pstmt.executeQuery();

			result.next();
			max = result.getLong(1); 

		} catch (SQLException e) {
			System.out.println("getMaxONo error-"+e);
		} finally {
			try {
				if(pstmt!=null)	pstmt.close();
			//	if(conn!=null) conn.close();
				if(result!=null) result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return max;
	}
}