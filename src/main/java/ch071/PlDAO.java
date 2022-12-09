package ch071;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ch07.Player;
import ch07.Position;

public class PlDAO {
	public static Connection getConnection() {
		Connection conn;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/world");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	public void updatePlayer(Pl p) {
		Connection conn = getConnection();
		String sql = "UPDATE player SET name=?, position=?, birthDay=?, height=?, isDeleted=?"
				+ "	WHERE backNo=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, p.getPname());
			pStmt.setString(2, p.getPosition().toString());
			pStmt.setString(3, p.getBirthDay().toString());
			pStmt.setInt(4, p.getHeight());
			pStmt.setInt(5, p.getIsDeleted());
			pStmt.setInt(6, p.getBackNo());
			
			// Update 실행
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pl getPlayer(int backNo) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM player WHERE backNo=?;";
		Pl p = new Pl();
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, backNo);
			
			// Select 실행
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				p.setBackNo(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setPosition(Position.valueOf(rs.getString(3)));
				p.setBirthDay(LocalDate.parse(rs.getString(4)));
				p.setHeight(rs.getInt(5));
				p.setIsDeleted(rs.getInt(6));
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public List<Pl> getPlayers() {
		Connection conn = getConnection();
		List<Pl> list = new ArrayList<>();
		String sql = "SELECT * FROM player WHERE isDeleted = 0"
				+ "	ORDER BY FIELD(`position`,'투수','포수','내야수','외야수');";
		try {
			Statement stmt = conn.createStatement();
			
			// Select 실행
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Pl p = new Pl();
				p.setBackNo(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setPosition(Position.valueOf(rs.getString(3)));
				p.setBirthDay(LocalDate.parse(rs.getString(4)));
				p.setHeight(rs.getInt(5));
				p.setIsDeleted(rs.getInt(6));
				list.add(p);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	public void insertPlayer(Pl p) {
		Connection conn = getConnection();
		String sql = "INSERT INTO player VALUES (?, ?, ?, ?, ?, DEFAULT);";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, p.getBackNo());
			pStmt.setString(2, p.getPname());
			pStmt.setString(3, p.getPosition().toString());
			pStmt.setString(4, p.getBirthDay().toString());
			pStmt.setInt(5, p.getHeight());
			
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void deletePlayer(int backNo) {
		Connection conn = getConnection();
		String sql = "UPDATE player SET isDeleted=1	WHERE backNo=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, backNo);
			
			// Delete - Update 실행
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
