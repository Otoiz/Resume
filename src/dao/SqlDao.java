package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBconfig;
import dto.Career;
import dto.Certi;
import dto.LoginUser;
import dto.Personal;
import dto.Station;

//アクセスロジック(DAO)
public class SqlDao {
	//DBconfig.properties
	public final String file_path = "/eclipse/pleiades/2022-03/workspace/ResumeManagement/DBconfig.properties";
	//DBconfigのオブジェクト生成
	DBconfig config = new DBconfig();
	
	//DB情報取得
	public String[] getDbInfo() throws IOException {

		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = config.getDBinfo(file_path);
		
		return(DbInfo);
	}
	
	
	//ログイン認証
	public List<LoginUser> check(String user, String password) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();
		
		String sql = "select * from t_login_users "
				+ "where user = ? and password = ?";
		
		//ログインユーザのオブジェクト生成
		LoginUser login_user = new LoginUser();
		
		//ログインユーザの情報をリスト形式で取得
		List<LoginUser> user_info = new ArrayList<LoginUser>();
		
		//データベースへの接続
		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			//変数sqlの一番目の?に引数のuserをセットする
			stmt.setString(1, user);
			//変数sqlの二番目の?に引数のpasswordをセットする
			stmt.setString(2, password);
			//sqlを実行し該当するデータ格納
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				login_user.setId(rs.getInt("id"));
				login_user.setName(rs.getString("user"));
				login_user.setPassword(rs.getString("password"));
				user_info.add(login_user);
			} else {
				login_user.setName("No user");
				login_user.setPassword("Not match password");
				user_info.add(login_user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user_info;
	}

	//
	public void update_Lastlogin(String user) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();
		
		String sql = "update t_login_users "
				+ "set lastlogin = CURRENT_TIMESTAMP "
				+ "where user = ?";
		
		//データベースへの接続
		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){	
			conn.setAutoCommit(false);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				//変数sqlの一番目の?に引数をセットする
				stmt.setString(1, user);
				
				stmt.executeUpdate();
				conn.commit();
			} catch(Exception e) {
				conn.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
		} catch (SQLException e1) {
			System.out.println("SQLの例外が発生しました");
			e1.printStackTrace();
		}
	}

	
	//社員情報取得
	public Personal get_personal_info(int employeeId) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();
		
		String sql = "select * from t_personal_informations "
				+ "where id = ?";

		Personal personal_info = new Personal();

		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//変数sqlの一番目の?に引数のidをセットする
			stmt.setInt(1, employeeId);
			//sqlを実行し該当するデータ格納
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				personal_info.setId(rs.getInt("id"));
				personal_info.setName(rs.getString("name"));
				personal_info.setSex(rs.getString("sex"));
				personal_info.setBirthday((rs.getString("birthday")).substring(0,10));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return personal_info;
	}

	//最寄り駅取得
	public List<Station> get_closetstation_info(int employeeId) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();
		
		String sql = "select * from t_closet_stations "
				+ "where id = ? order by sort";
		
		List<Station> station_info = new ArrayList<Station>();
		Station station;
		
		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//変数sqlの一番目の?に引数のidをセットする
			stmt.setInt(1, employeeId);
			//sqlを実行し該当するデータ格納
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				station = new Station();
				station.setId(rs.getInt("id"));
				station.setSort(rs.getInt("sort"));
				station.setStation(rs.getString("station"));
				station_info.add(station);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return station_info;
	}

	
	//資格情報取得
	public List<Certi> get_certification_info(int employeeId) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();
		
		String sql = "select * from t_certifications "
				+ "where id = ?  ORDER BY sort";

		List<Certi> certi_info = new ArrayList<Certi>();
		Certi certi;

		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//変数sqlの一番目の?に引数のidをセットする
			stmt.setInt(1, employeeId);
			//sqlを実行し該当するデータ格納
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				certi = new Certi();
				certi.setId(rs.getInt("id"));
				certi.setSort(rs.getInt("sort"));
				certi.setCertification(rs.getString("certification"));
				certi_info.add(certi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return certi_info;
	}

	//業務経歴取得
	public List<Career> get_career_info(int employeeId) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();
		
		String sql = "select * from t_careers "
				+ "where id = ?  ORDER BY start DESC,end DESC";

		List<Career> career_info = new ArrayList<Career>();
		Career career;

		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){	
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//変数sqlの一番目の?に引数のidをセットする
			stmt.setInt(1, employeeId);
			//sqlを実行し該当するデータ格納
			ResultSet rs = stmt.executeQuery();

			int cnt = 0;
			while(rs.next()) {
				cnt++;
				career = new Career();
				career.setId(cnt);
				career.setStartdate((rs.getString("start")).substring(0,10));
				career.setEnddate((rs.getString("end")).substring(0,10));
				career.setSysname(rs.getString("systemname"));
				career.setSysdtl(rs.getString("system_dtl"));
				career.setRoles(rs.getString("roles"));
				career.setTools(rs.getString("tools"));
				career.setCredate((rs.getString("created")));
				career.setUpdate((rs.getString("updated")));
				career_info.add(career);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return career_info;
	}

	//業務経歴1つ取得
	public Career get_career(int employeeId, String credate) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();
		
		String sql = "select * from t_careers "
				+ "where id = ? and created = ?";
		
		Career career = new Career();

		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){	
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//変数sqlの?に引数をセットする
			stmt.setInt(1, employeeId);
			stmt.setString(2, credate);
			
			//sqlを実行し該当するデータ格納
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				career.setId(rs.getInt("id"));
				career.setStartdate((rs.getString("start")).substring(0,10));
				career.setEnddate((rs.getString("end")).substring(0,10));
				career.setSysname(rs.getString("systemname"));
				career.setSysdtl(rs.getString("system_dtl"));
				career.setRoles(rs.getString("roles"));
				career.setTools(rs.getString("tools"));
				career.setCredate((rs.getString("created")));
				career.setUpdate((rs.getString("updated")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return career;
	}

	//業務経歴情報を登録するメソッド
	public void insert_career_info(int employeeId, String start, String end, String systemname, String system_dtl, String roles, String tools) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();

		String sql = "insert into "
				+ "t_careers(id,start,end,systemname,system_dtl,roles,tools) "
				+ "values (?,?,?,?,?,?,?)";

		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){	
			conn.setAutoCommit(false);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				//変数sqlの一番目の?に引数をセットする
				stmt.setInt(1, employeeId);
				//変数sqlの二番目の?に引数をセットする
				stmt.setString(2, start);
				//変数sqlの三番目の?に引数をセットする
				stmt.setString(3, end);
				//変数sqlの四番目の?に引数をセットする
				stmt.setString(4, systemname);
				//変数sqlの五番目の?に引数をセットする
				stmt.setString(5, system_dtl);
				//変数sqlの六番目の?に引数をセットする
				stmt.setString(6, roles);
				//変数sqlの七番目の?に引数をセットする
				stmt.setString(7, tools);
				stmt.executeUpdate();
				conn.commit();
			} catch(Exception e) {
				conn.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
		} catch (SQLException e1) {
			System.out.println("SQLの例外が発生しました");
			e1.printStackTrace();
		}
	}

	//社員情報を変更するメソッド
	public void update_personal_info(int employeeId, String birthday) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();

		String sql = "update t_personal_informations "
				+ "set birthday = ? "
				+ "where id = ? ";

		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){	
			conn.setAutoCommit(false);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				//変数sqlの一番目の?に引数をセットする
				stmt.setString(1, birthday);
				//変数sqlの二番目の?に引数をセットする
				stmt.setInt(2, employeeId);
				
				stmt.executeUpdate();
				conn.commit();
			} catch(Exception e) {
				conn.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
		} catch (SQLException e1) {
			System.out.println("SQLの例外が発生しました");
			e1.printStackTrace();
		}
	}

	//最寄り駅を変更するメソッド
	public void update_station_info(int employeeId, List<Station> station_data) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();

		String sql = "delete from t_closet_stations "
				+ "where id = ? ";

		String sql2 = "insert into "
				+ "t_closet_stations(id,sort,station) "
				+ "values (?,?,?)";
		
		
		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){	
			conn.setAutoCommit(false);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				//変数sqlの一番目の?に引数をセットする
				stmt.setInt(1, employeeId);
				
				stmt.executeUpdate();
				
			} catch(Exception e) {
				conn.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
			
			for(Station star: station_data) {

				try(PreparedStatement stmt = conn.prepareStatement(sql2)){
					//変数sqlの?番目の?に引数をセットする
					stmt.setInt(1, star.getId());
					stmt.setInt(2, star.getSort());
					stmt.setString(3, star.getStation());
				
					stmt.executeUpdate();
				
					
				} catch(Exception e) {
					conn.rollback();
					System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
					throw e;
				}
			}
		
			conn.commit();
		
		} catch (SQLException e1) {
			System.out.println("SQLの例外が発生しました");
			e1.printStackTrace();
		}
	}
	
	//経歴情報を変更するメソッド
	public void update_career(Career career) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();

		String sql = "update t_careers "
				+ "set start = ?, "
				+ "end = ?, "
				+ "systemname = ?, "
				+ "system_dtl = ?, "
				+ "roles = ?, "
				+ "tools = ? "
				+ "where id = ?  and created = ? ";

		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){	
			conn.setAutoCommit(false);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				//変数sqlの一番目の?に引数をセットする
				stmt.setString(1, career.getStartdate());
				stmt.setString(2, career.getEnddate());
				stmt.setString(3, career.getSysname());
				stmt.setString(4, career.getSysdtl());
				stmt.setString(5, career.getRoles());
				stmt.setString(6, career.getTools());
				stmt.setInt(7, career.getId() );
				stmt.setString(8, career.getCredate());
				stmt.executeUpdate();
				conn.commit();
			} catch(Exception e) {
				conn.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
		} catch (SQLException e1) {
			System.out.println("SQLの例外が発生しました");
			e1.printStackTrace();
		}
	}

	//経歴情報を削除するメソッド
	public void delete_career(Career career) throws IOException {
		//DBconfig.propertiesの各値をlist形式で取得
		String[] DbInfo = getDbInfo();

		String sql = "delete from t_careers "
				+ "where id = ?  and created = ? ";

		try(Connection conn = DriverManager.getConnection(DbInfo[0],DbInfo[1], DbInfo[2])){	
			conn.setAutoCommit(false);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				//変数sqlの一番目の?に引数をセットする
				stmt.setInt(1, career.getId() );
				stmt.setString(2, career.getCredate());
				stmt.executeUpdate();
				conn.commit();
			} catch(Exception e) {
				conn.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
		} catch (SQLException e1) {
			System.out.println("SQLの例外が発生しました");
			e1.printStackTrace();
		}
	}

}
