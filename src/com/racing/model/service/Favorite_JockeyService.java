package com.racing.model.service;

import static com.racing.common.JDBCTemplate.commit;
import static com.racing.common.JDBCTemplate.openConnection;
import static com.racing.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.racing.model.dao.FavoriteDao_Jockey;
import com.racing.model.vo.Favorite_Jockey;

public class Favorite_JockeyService {
	private FavoriteDao_Jockey favoriteDao_Jockey = new FavoriteDao_Jockey();
	private Connection conn = null;

	public Favorite_JockeyService() {
		conn = openConnection();
	}

	public List<Favorite_Jockey> selectAll(int uNo) {
		return favoriteDao_Jockey.selectAll(conn, uNo);
	}

	public List<Favorite_Jockey> selectByName(int uNo, String jockeyName) {
		return favoriteDao_Jockey.selectByName(conn, uNo, jockeyName);
	}

	public List<Favorite_Jockey> selectByJockeyNumber(int uNo, String jockeyNum) {
		return favoriteDao_Jockey.selectByJockeyNumber(conn, uNo, jockeyNum);
	}

//	public List<Favorite_Jockey> selectByFavoriteNum(String fvHNO) {
//		return favoriteDao_Jockey.selectByFavoriteNum(conn, fvHNO);
//	}

	// 마번으로 즐겨찾기 insert
	public int insert(int UNO, String jkno) {
		int result = favoriteDao_Jockey.insert(conn, UNO, jkno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	// 마번으로 즐겨찾기 delete
	public int delete(int UNO, String jkno) {
		int result = favoriteDao_Jockey.delete(conn, UNO, jkno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;

	}

//	//마명으로 즐겨찾기 insert
//		public int insertJockeyName(int UNO, String jkname) {
//			List<Favorite_Jockey> list = selectByName(jkname);
//			for(Favorite_Jockey f : list) {
//				if(f.getJkName().equals(jkname)) {
//					return favoriteDao_Jockey.insert(conn, UNO, f.getJkNo());
//				}
//			}
//			return -1;
//		}

//	//마명으로 즐겨찾기 delete
//			public int deleteJockeyName(int UNO, String jkname) {
//				List<Favorite_Jockey> list = selectByName(jkname);
//				for(Favorite_Jockey f : list) {
//					if(f.getJkName().equals(jkname)) {
//						return favoriteDao_Jockey.delete(conn, UNO, f.getJkNo());
//					}
//				}
//				return -1;
//			}
//	
//	//insert 하고 commit
//	public int insert(FavoriteDao_Jockey favoriteDao_Jockey) {
//		int result = favoriteDao_Jockey.insert(conn, 1, "001");
//		if (result > 0) {
//			commit(conn);
//		} else {
//			rollback(conn);
//		}
//		return result;
//	}

}
