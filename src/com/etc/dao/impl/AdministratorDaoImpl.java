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
import com.etc.test.*;
public class AdministratorDaoImpl implements AdministratorDao {
	Administrator administrator=new Administrator();
	public void administratorAdd(Administrator administrator) {
	
		String sql="INSERT INTO administrator(id,account,password) VALUES(?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = BaseDao.getConnection();
			pstmt=BaseDao.setParam(conn,sql,administrator.getId(),administrator.getAccount(),administrator.getPassword());
			int result=BaseDao.exeUpdate(pstmt);
			if(result>0) {
				System.out.println("插入成功！");
			}else {
				System.out.println("插入失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeAll(conn,pstmt,null);
		}
		

}

@Override
public void administratorDel(int  id) {
String sql="delete from user where id=?";
Connection conn=null;
PreparedStatement pstmt=null;
try {
	conn = BaseDao.getConnection();
	pstmt=BaseDao.setParam(conn,sql,administrator.getId());
	int result=BaseDao.exeUpdate(pstmt);
	if(result>0) {
		System.out.println("删除成功！");
	}else {
		System.out.println("删除失败！");
	}
} catch (SQLException e) {
	e.printStackTrace();
}finally {
	BaseDao.closeAll(conn,pstmt,null);
}

}

@Override
public void administratorUpdate(Administrator administrator) {
String sql="UPDATE administrator SET  account=? ,password=? WHERE id=?";
Connection conn=null;
PreparedStatement pstmt=null;
try {
	conn = BaseDao.getConnection();
	pstmt=BaseDao.setParam(conn,sql,administrator.getAccount(),administrator.getPassword(),administrator.getId());
	int result=BaseDao.exeUpdate(pstmt);
	if(result>0) {
		System.out.println("修改成功！");
	}else {
		System.out.println("修改失败！");
	}
} catch (SQLException e) {
	e.printStackTrace();
}finally {
	BaseDao.closeAll(conn,pstmt,null);
}

}

@Override
public List<Administrator> administratorQuery() {
		String sql="SELECT * from administrator";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Administrator> list=new ArrayList<Administrator>();
		try {
			conn = BaseDao.getConnection();
			pstmt=BaseDao.setParam(conn,sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String account=rs.getString("account");
				String password=rs.getString("password");
		
					list.add(new Administrator(id,account,password));

				
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
