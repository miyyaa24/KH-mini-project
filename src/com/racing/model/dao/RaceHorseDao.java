package com.racing.model.dao;

import static com.racing.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.racing.common.JDBCTemplate;
import com.racing.model.vo.RaceHorse;

public class RaceHorseDao {
	
	// 전체조회
	public List<RaceHorse> selectAll(Connection conn) {
		List<RaceHorse> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM HORSE_INFO ORDER BY 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				int count = 1;
				String hrNo         = rs.getString(count++);
				String meet	     = rs.getString(count++);
				String hrName    = rs.getString(count++);
				String name	     = rs.getString(count++);
				String sex	     = rs.getString(count++);
				String birthday  = rs.getString(count++);
				String rank	     = rs.getString(count++);
				String faHrName  = rs.getString(count++);
				String moHrName  = rs.getString(count++);
				Long rcCntT	     = rs.getLong(count++);
				int ord1CntT     = rs.getInt(count++);
				int ord2CntT     = rs.getInt(count++);
				int ord3CntT     = rs.getInt(count++);
				Long chaksunT     = rs.getLong(count++);
				String hrLastAmt = rs.getString(count++);
				
				RaceHorse info = new RaceHorse(hrNo, meet, hrName, name, sex, birthday, rank, faHrName, moHrName, rcCntT, ord1CntT, ord2CntT, ord3CntT, chaksunT, hrLastAmt);
				list.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 마명으로 경주마 검색
	public List<RaceHorse> selectByHorseName(Connection conn, String horseName) {
		List<RaceHorse> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM HORSE_INFO WHERE hrName LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + horseName + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int count = 1;
				String hrNo         = rs.getString(count++);
				String meet	     = rs.getString(count++);
				String hrName    = rs.getString(count++);
				String name	     = rs.getString(count++);
				String sex	     = rs.getString(count++);
				String birthday  = rs.getString(count++);
				String rank	     = rs.getString(count++);
				String faHrName  = rs.getString(count++);
				String moHrName  = rs.getString(count++);
				Long rcCntT	     = rs.getLong(count++);
				int ord1CntT     = rs.getInt(count++);
				int ord2CntT     = rs.getInt(count++);
				int ord3CntT     = rs.getInt(count++);
				Long chaksunT     = rs.getLong(count++);
				String hrLastAmt = rs.getString(count++);

				RaceHorse info = new RaceHorse(hrNo, meet, hrName, name, sex, birthday, rank, faHrName, moHrName, rcCntT, ord1CntT, ord2CntT, ord3CntT, chaksunT, hrLastAmt);
				list.add(info);
			}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			return list;
		}

	// 마번으로 경주마 검색
	public List<RaceHorse> selectByHorseNo(Connection conn, String horseNo) {
		List<RaceHorse> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM HORSE_INFO WHERE hrNo LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + horseNo + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int count = 1;
				String hrNo         = rs.getString(count++);
				String meet	     = rs.getString(count++);
				String hrName   = rs.getString(count++);
				String name	     = rs.getString(count++);
				String sex	     = rs.getString(count++);
				String birthday  = rs.getString(count++);
				String rank	     = rs.getString(count++);
				String faHrName  = rs.getString(count++);
				String moHrName  = rs.getString(count++);
				long rcCntT	     = rs.getLong(count++);
				int ord1CntT     = rs.getInt(count++);
				int ord2CntT     = rs.getInt(count++);
				int ord3CntT     = rs.getInt(count++);
				long chaksunT     = rs.getLong(count++);
				String hrLastAmt = rs.getString(count++);

				RaceHorse info = new RaceHorse(hrNo, meet, hrName, name, sex, birthday, rank, faHrName, moHrName, rcCntT, ord1CntT, ord2CntT, ord3CntT, chaksunT, hrLastAmt);
				list.add(info);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	// 통산총출주회수(rcCntT) 높은순서대로  1-10등까지 출력
	public List<RaceHorse> selectByrcCntT(Connection conn) {
		List<RaceHorse> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT ROWNUM, rct.* "
					+ "FROM (  SELECT hrno, hrname, sex, birthday, rcCntT  "
					+ "        FROM HORSE_INFO "
					+ "        ORDER BY rcCntT DESC) rct "
					+ "WHERE ROWNUM <= 10";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int count = 1;
				int rowNum = rs.getInt(count++);
				String hrNo         = rs.getString(count++);
				String hrName    = rs.getString(count++);
				String sex	     = rs.getString(count++);
				String birthday  = rs.getString(count++);
				long rcCntT	     = rs.getLong(count++);

				RaceHorse info = new RaceHorse(rowNum, hrNo, hrName, sex, birthday, rcCntT);
				list.add(info);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 말도입가(hrLastAmt) 높은 순서대로 1-10등까지 출력
	public List<RaceHorse> selectByhrLastAmt(Connection conn) {
		List<RaceHorse> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT ROWNUM, price.* FROM( "
					+ "   SELECT hrno, hrname, sex, birthday, REGEXP_REPLACE(hrLastAmt, '[^0-9]+') AS \"말 도입가\" FROM horse_info "
					+ "    Where REGEXP_REPLACE(hrLastAmt, '[^0-9]+') is not null "
					+ "    ORDER BY to_number(REGEXP_REPLACE(hrLastAmt, '[^0-9]+')) desc) price "
					+ " where ROWNUM <= 10";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int count = 1;
				int rowNum = rs.getInt(count++);
				String hrNo         = rs.getString(count++);
				String hrName    = rs.getString(count++);
				String sex	     = rs.getString(count++);
				String birthday  = rs.getString(count++);
				String hrLastAmt = rs.getString(count++);

				RaceHorse info = new RaceHorse(rowNum, hrNo, hrName, sex, birthday, hrLastAmt);
				list.add(info);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 통산 착순 상금 순
	public List<RaceHorse> selectBychaksunT(Connection conn) {
		List<RaceHorse> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT ROWNUM, reward.* "
					+ "   FROM (  SELECT hrno, hrname, sex, birthday, chaksunT "
					+ "        FROM HORSE_INFO "
					+ "        ORDER BY chaksunT DESC) reward "
					+ "WHERE ROWNUM <= 10";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int count = 1;
				int rowNum = rs.getInt(count++);
				String hrNo         = rs.getString(count++);
				String hrName    = rs.getString(count++);
				String sex	     = rs.getString(count++);
				String birthday  = rs.getString(count++);
				long chaksunT    = rs.getLong(count++);

				RaceHorse info = new RaceHorse(rowNum, hrNo, hrName, sex, birthday, chaksunT); list.add(info);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	public int insert(Connection conn, RaceHorse raceHorse) {
		try {
//			System.out.println(raceHorse);
			String sql = "INSERT INTO "
					+ " HORSE_INFO( hrNo, meet, hrName, name, sex, "
					+ "				birthday, rank, faHrName, moHrName, rcCntT, "
					+ "				ord1CntT, ord2CntT, ord3CntT, chaksunT, hrLastAmt) " 
					+ " VALUES(?, ?, ?, "
					+ "		   ?, ?, ?, "
					+ "        ?, ?, ?, "
					+ "		   ?, ?, ?, "
					+ "        ?, ?, ?) ";
			
//1			HRNO      NOT NULL VARCHAR2(100) 
//2			MEET               VARCHAR2(50)  
//3			HRNAME             VARCHAR2(30)  
//4			NAME               VARCHAR2(200) 
//5			SEX                VARCHAR2(50)  
////6			BIRTHDAY           DATE          
//6			BIRTHDAY           VARCHAR2(30)            
//7			RANK               VARCHAR2(30)  
//8			FAHRNAME           VARCHAR2(30)  
//9			MOHRNAME           VARCHAR2(30)  
//0			RCCNTT             NUMBER        
//1			ORD1CNTT           NUMBER        
//2			ORD2CNTT           NUMBER        
//3			ORD3CNTT           NUMBER        
//4			CHAKSUNT           NUMBER        
//5			HRLASTAMT          VARCHAR2(100) 

			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int cnt = 1;
			pstmt.setString(cnt++, raceHorse.getHrNo());
			pstmt.setString(cnt++, raceHorse.getMeet());
			pstmt.setString(cnt++, raceHorse.getHrName());
			pstmt.setString(cnt++, raceHorse.getName());
			pstmt.setString(cnt++, raceHorse.getSex());
//			pstmt.setDate(cnt++, new java.sql.Date(raceHorse.getBirthday().getTime()));
			pstmt.setString(cnt++, raceHorse.getBirthday());
			pstmt.setString(cnt++, raceHorse.getRank());
			pstmt.setString(cnt++, raceHorse.getFaHrName());
			pstmt.setString(cnt++, raceHorse.getMoHrName());
			pstmt.setLong(cnt++, raceHorse.getRcCntT());
			pstmt.setInt(cnt++, raceHorse.getOrd1CntT());
			pstmt.setInt(cnt++, raceHorse.getOrd2CntT());
			pstmt.setInt(cnt++, raceHorse.getOrd3CntT());
			pstmt.setLong(cnt++, raceHorse.getChaksunT());
			pstmt.setString(cnt++, raceHorse.getHrLastAmt());

			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// test
//	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	public static void main(String[] args) throws Exception {
		Connection conn = JDBCTemplate.openConnection();
		
//		// 전체출력
//		List<RaceHorse> list = new RaceHorseDao().selectAll(conn);
//		System.out.println("갯수 : " + list.size());
//		for (RaceHorse info : list) {
//			System.out.println(info.toStringForConsole());
//			System.out.println();
//		}
//
//		// 마번으로
//		List<RaceHorse> list1 = new RaceHorseDao().selectByHorseNo(conn, "0");
//		System.out.println("갯수 : " + list1.size());
//		for (RaceHorse info : list1) {
//			System.out.println(info.toStringForConsole());
//			System.out.println();
//		}
//
//		// 마명으로
//		List<RaceHorse> list2 = new RaceHorseDao().selectByHorseName(conn, "경");
//		System.out.println("갯수 : " + list2.size());
//		for (RaceHorse info : list2) {
//			System.out.println(info.toStringForConsole());
//			System.out.println();
//		}
//
//		// 출주회(rcCntT)
//		List<RaceHorse> list3 = new RaceHorseDao().selectByrcCntT(conn);
//		System.out.println("갯수 : " + list3.size());
//		for (RaceHorse info : list3) {
//			System.out.println(info.toStringForConsoleRankRcCntT());
//			System.out.println();
//		}
//
//		// 도입가(hrLastAmt)
//		List<RaceHorse> list4 = new RaceHorseDao().selectByhrLastAmt(conn);
//		System.out.println("갯수 : " + list4.size());
//		for (RaceHorse info : list4) {
//			System.out.println(info.toStringForConsoleRankHrLastAmt());
//			System.out.println();
//		}
//		
//		// 상금(chaksunT)
//		List<RaceHorse> list5 = new RaceHorseDao().selectBychaksunT(conn);
//		System.out.println("갯수 : " + list5.size());
//		for (RaceHorse info : list5) {
//			System.out.println(info.toStringForConsoleRankChaksunT());
//			System.out.println();
//		}
		

		
		
	}
	

}