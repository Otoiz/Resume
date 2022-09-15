package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SqlDao;
import dto.Career;
import dto.Certi;
import dto.LoginUser;
import dto.Personal;
import dto.Station;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int employee_id;
	
	//ログイン画面の表示処理
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	//ログイン認証
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);

		
		//ログイン画面で入力された値を取得
		String user = request.getParameter("user_name");
		String password = request.getParameter("password");
		
		SqlDao sql = new SqlDao();
		List<LoginUser> login = sql.check(user, password);
		
		String login_user = login.get(0).getName();
		String login_pass = login.get(0).getPassword();
		
		if(user.equals(login_user) && password.equals(login_pass)) {
			//ログイン成功
			this.employee_id = login.get(0).getId();
			session.setAttribute("employee_id", String.valueOf(this.employee_id));
			
			//社員情報を格納するリストを作成
			Personal personal_data = new Personal();
			//社員情報を取得するメソッド
			personal_data = sql.get_personal_info(employee_id);
			//requestオブジェクトに社員情報を持たせる
			request.setAttribute("personal", personal_data);

			//最寄り駅情報を格納するリストを作成
			List<Station> station_data = new ArrayList<Station>();
			//最寄り駅情報を取得するメソッド
			station_data = sql.get_closetstation_info(employee_id);
			//requestオブジェクトに最寄り駅情報を持たせる
			request.setAttribute("station", station_data);

			//資格情報を格納するリストを作成
			List<Certi> certification_data = new ArrayList<Certi>();
			//資格情報を取得するメソッド
			certification_data = sql.get_certification_info(employee_id);
			//requestオブジェクトに資格情報を持たせる
			request.setAttribute("certi", certification_data);
			
			//業務経歴を格納するリストを作成
			List<Career> career_data = new ArrayList<Career>();
			//業務経歴情報を取得するメソッド
			career_data = sql.get_career_info(employee_id);
			//requestオブジェクトに業務経歴情報を持たせる
			request.setAttribute("career", career_data);

			// 最終ログイン時刻更新
			SqlDao sql2 = new SqlDao();
			sql2.update_Lastlogin(user);
			
			Date dateObj = new Date();
			SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
			String display = format.format( dateObj );

			request.setAttribute("lastlogin", display);
			session.setAttribute("lastlogin", display);
			
			//経歴画面へ遷移
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/resume_list.jsp");
			dispatcher.forward(request, response);
		} else {
			//ログイン失敗→ログイン画面へ戻る
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}