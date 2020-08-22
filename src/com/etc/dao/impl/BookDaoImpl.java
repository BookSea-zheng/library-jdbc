package com.etc.dao.impl;

import com.etc.dao.*;
import com.etc.entity.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.etc.dao.BaseDao;
import com.etc.dao.UserDao;
import com.etc.entity.Book;

public class BookDaoImpl implements BookDao {
	Book book = new Book();

	@Override
	public void bookAdd(Book book) {
		String sql = "INSERT INTO book(name,author,price) VALUES(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BaseDao.getConnection();
			pstmt = BaseDao.setParam(conn, sql, book.getName(), book.getAuthor(), book.getPrice());
			int result = BaseDao.exeUpdate(pstmt);
			if (result > 0) {
				System.out.println("插入成功！");
			} else {
				System.out.println("插入失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, pstmt, null);
		}

	}

	@Override
	public void bookDel(String name) {
		String sql = "delete from book where name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BaseDao.getConnection();
			pstmt = BaseDao.setParam(conn, sql, name);
			int result = BaseDao.exeUpdate(pstmt);
			if (result > 0) {
				System.out.println("删除成功！");
			} else {
				System.out.println("删除失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, pstmt, null);
		}

	}

	@Override
	public void bookUpdate(Book book) {
		String sql = "UPDATE book SET  name=? ,author=?,price=?	WHERE id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BaseDao.getConnection();
			pstmt = BaseDao.setParam(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getId());
			int result = BaseDao.exeUpdate(pstmt);
			if (result > 0) {
				System.out.println("更新成功！");
			} else {
				System.out.println("更新失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, pstmt, null);
		}

	}

	@Override
	public List<Book> bookQuery() {
		String sql = "SELECT * from book";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
		try {
			conn = BaseDao.getConnection();
			pstmt = BaseDao.setParam(conn, sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				int price = rs.getInt("price");
				list.add(new Book(id, name, author, price));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}
 
	public void bookSelect(String name) {     //图书模糊查询
		String sql = "SELECT * FROM book WHERE name LIKE ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag=false;
		try {
			conn = BaseDao.getConnection();
			pstmt = BaseDao.setParam(conn, sql, "%" + name + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				flag=true;
				
				String bookname = rs.getString("name");
				System.out.println("《" + bookname + "》");
			} 
		if(!flag)	{
				System.out.println("书库中暂无该书信息！");  //如果书库中没有要查找的是
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, pstmt, null);
		}

	}
}
