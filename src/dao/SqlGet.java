package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Role;

public class SqlGet {

	//役割情報取得
	public List<Role> get_role_info() throws IOException {
		
		SqlDao sqlD = new SqlDao();
		
		String[] DbInfo = sqlD.getDbInfo();
		
		String sql = "select * from m_roles order by sort";

		List<Role> role_info = new ArrayList<Role>();
		Role role;

		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//sqlを実行し該当するデータ格納
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				role = new Role();
				role.setNo(rs.getInt("no"));
				role.setSort(rs.getInt("sort"));
				role.setName(rs.getString("role"));
				role_info.add(role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return role_info;
	}

}
