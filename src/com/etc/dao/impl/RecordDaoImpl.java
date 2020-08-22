package com.etc.dao.impl;
import  com.etc.dao.*;
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


public class RecordDaoImpl implements RecordDao {
        Record  record=new Record();
	@Override
	public void recordAdd(Record record) {
		String sql="INSERT INTO record(bookname,user) VALUES(?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = BaseDao.getConnection();
			pstmt=BaseDao.setParam(conn,sql,record.getBookname(),record.getUser());
			int result=BaseDao.exeUpdate(pstmt);
			if(result>0) {
				System.out.println("借书成功！");
			}else {
				System.out.println("借书失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeAll(conn,pstmt,null);
		}
		

}

@Override
public void recordDel(String  name) {
String sql="delete from record where bookname=?";
Connection conn=null;
PreparedStatement pstmt=null;
try {
	conn = BaseDao.getConnection();
	pstmt=BaseDao.setParam(conn,sql,name);
	int result=BaseDao.exeUpdate(pstmt);
	if(result>0) {
		System.out.println("还书成功！");
	}else {
		System.out.println("还书失败！");
	}
} catch (SQLException e) {
	e.printStackTrace();
}finally {
	BaseDao.closeAll(conn,pstmt,null);
}

}

@Override
public void recordUpdate(Record record) {
String sql="UPDATE record SET bookname=?  WHERE user=?";
Connection conn=null;
PreparedStatement pstmt=null;
try {
	conn = BaseDao.getConnection();
	pstmt=BaseDao.setParam(conn,sql,record.getBookname(),record.getUser());
	int result=BaseDao.exeUpdate(pstmt);
	if(result>0) {
		System.out.println("更新成功！");
	}else {
		System.out.println("更新失败！");
	}
} catch (SQLException e) {
	e.printStackTrace();
}finally {
	BaseDao.closeAll(conn,pstmt,null);
}

}

@Override
public List<Record> recordQuery() {
		String sql="SELECT * from record";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Record> list=new ArrayList<Record>();
		try {
			conn = BaseDao.getConnection();
			pstmt=BaseDao.setParam(conn,sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String bookname=rs.getString("bookname");
				String user=rs.getString("user");
				System.out.println("id:"+id+" bookname: "+bookname+" user: "+user);
				list.add(new Record(id,bookname,user));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

return list;
}

}
