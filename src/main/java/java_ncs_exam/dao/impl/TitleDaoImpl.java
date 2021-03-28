package java_ncs_exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java_ncs_exam.dao.TitleDao;
import java_ncs_exam.dao.dbconn.JdbcConn;
import java_ncs_exam.dto.Title;
import java_ncs_exam.exam.exception.SqlConstraintException;

public class TitleDaoImpl implements TitleDao {
	private static TitleDaoImpl instance = new TitleDaoImpl(); 
	public static TitleDaoImpl getInstance() {	
		if (instance == null) {
			instance = new TitleDaoImpl();
		}
		return instance;    

	}
	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select tNo, tName from title";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				List<Title> list = new ArrayList<>();
				do {
					list.add(getTitle(rs));

				} while (rs.next());
				return list;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int tNo = rs.getInt("tNo");
		String tName = rs.getString("tName");
		return new Title(tNo, tName);
	}
	@Override
	public Title selectTitleByNo(Title title) {
		String sql = "select tNo, tName from title where tNo = ?";
		try (Connection con = JdbcConn.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1,title.gettNo());  //첫번쨰 매개변수에 employee에 있는 getempno를 넣어줘야한다.
			try (ResultSet rs = pstmt.executeQuery()) {  //
				
				if (rs.next()) {  
					return getTitle(rs);
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertTitle(Title title) {
		String sql ="insert into Title values(? , ?)";
		try (Connection con = JdbcConn.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, title.gettNo());
			pstmt.setString(2, title.gettName());		
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(),e);
			
		}

	}

	@Override
	public int updateTitle(Title title) {
		String sql= "update Title set tNo = ?, tName = ? where tNo = ?";
		try (Connection con = JdbcConn.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
		
			pstmt.setInt(1, title.gettNo());
			pstmt.setString(2, title.gettName()); 
			pstmt.setInt(3, title.gettNo());		
			return pstmt.executeUpdate();

		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteTitle(int titleNo) {
		String sql= "delete from Title where tNo = ?";
		try (Connection con = JdbcConn.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setInt(1, titleNo);
				return pstmt.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			return 0;
		}


}
