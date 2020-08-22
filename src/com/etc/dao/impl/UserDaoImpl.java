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
public class UserDaoImpl implements UserDao {
	User user=new User();
	@Override
	public void userAdd(User user) {
				String sql="INSERT INTO user(account,password) VALUES(?,?)";
				String sql1="SELECT * FROM  user WHERE account=?";
				Connection conn=null;
				PreparedStatement pstmt=null;
				PreparedStatement pstmt1=null;
				ResultSet rs=null;
				try {
					conn = BaseDao.getConnection();
					pstmt1=BaseDao.setParam(conn,sql1,user.getAccount()); //判断用户名是否已经存在
					rs=pstmt1.executeQuery();
					if(rs.next()) {
						System.out.println("对不起您要注册的用户已经存在!，请重新输入");
					}
					else 
					{
						
					pstmt=BaseDao.setParam(conn,sql,user.getAccount(),user.getPassword());
					int result=BaseDao.exeUpdate(pstmt);
					if(result>0) {
						System.out.println("插入成功！");
					}else {
						System.out.println("插入失败！");
					}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					BaseDao.closeAll(conn,pstmt,null);
				}
				
		
	}

	@Override
	public void userDel(String  name) {
		String sql="delete from user where account=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = BaseDao.getConnection();
			pstmt=BaseDao.setParam(conn,sql,name);
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
	public void userUpdate(User user) {
		String sql="UPDATE user SET  account=? ,password=? WHERE id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = BaseDao.getConnection();
			pstmt=BaseDao.setParam(conn,sql,user.getAccount(),user.getPassword(),user.getId());
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
	public List<User> userQuery() {
				String sql="SELECT * from user  ";
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				List<User> list=new ArrayList<User>();
				try {
					conn = BaseDao.getConnection();
					pstmt=BaseDao.setParam(conn,sql);
					rs=pstmt.executeQuery();
					while(rs.next()) {
						int id=rs.getInt("id");
						String account=rs.getString("account");
						String password=rs.getString("password");
						list.add(new User(id,account,password));
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