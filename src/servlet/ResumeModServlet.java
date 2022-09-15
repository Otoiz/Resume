package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SqlDao;
import dao.SqlGet;
import dto.Career;
import dto.Certi;
import dto.Personal;
import dto.Role;
import dto.Station;

/**
 * Servlet implementation class ResumeModServlet
 */
@WebServlet("/ResumeModServlet")
public class ResumeModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResumeModServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //業務経歴一覧画面で経歴の変更ボタンを押下
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//業務経歴一覧画面で入力された値をパラメーターで取得
		HttpSession session = request.getSession(false);
		
		String employee = (String)session.getAttribute("employee_id");
		int employeeId = Integer.parseInt(employee);
		
		Career career = new Career();
		List <Role> role_info = new ArrayList<Role>();
		
		//表示データを取得
		String credate = request.getParameter("credate");
		
		SqlDao sql = new SqlDao();
		SqlGet sqlG = new SqlGet();

		//経歴情報を取得するメソッド
		career = sql.get_career(employeeId, credate);
		//requestオブジェクトに資格情報を持たせる
		request.setAttribute("career", career);
		
		//役割を取得するメソッド
		role_info = sqlG.get_role_info();
		//requestオブジェクトに資格情報を持たせる
		request.setAttribute("role", role_info);		
		
		RequestDispatcher dispatcher_list =
				request.getRequestDispatcher("WEB-INF/jsp/career.jsp");
			dispatcher_list.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//経歴情報を変更するメインロジック
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		String employee = (String)session.getAttribute("employee_id");
	    int employeeId = Integer.parseInt(employee);
	    
	    Career career = new Career();
	    
		//業務経歴変更画面で入力された値をパラメーターで取得
		career.setId(employeeId);
	    career.setStartdate(request.getParameter("startdate"));
	    career.setEnddate(request.getParameter("enddate"));
	    career.setSysname(request.getParameter("sysname"));
	    career.setSysdtl(request.getParameter("sysdtl"));
		career.setRoles(request.getParameter("roles"));
		career.setTools(request.getParameter("tools"));
		career.setCredate(request.getParameter("credate"));
		
		SqlDao sql = new SqlDao();
		
		//変更、削除判別
		String kind = request.getParameter("kind");
		
		if( kind.equals("変更") ) {
			//経歴情報を変更するメソッドの呼び出し
			sql.update_career(career);
		} else {
			//経歴情報を削除するメソッドの呼び出し
			sql.delete_career(career);
		}
		
		//社員情報を格納するリストを作成
		Personal personal_data = new Personal();
		//社員情報を取得するメソッド
		personal_data = sql.get_personal_info(employeeId);
		//requestオブジェクトに社員情報を持たせる
		request.setAttribute("personal", personal_data);

		//最寄り駅を格納するリストを作成
		List<Station> station_data = new ArrayList<Station>();
		
		//最寄り駅情報を取得するメソッド
		station_data = sql.get_closetstation_info(employeeId);
		//requestオブジェクトに最寄り駅情報を持たせる
		request.setAttribute("station", station_data);

		//資格情報を格納するリストを作成
		List<Certi> certification_data = new ArrayList<Certi>();
		//資格情報を取得するメソッド
		certification_data = sql.get_certification_info(employeeId);
		//requestオブジェクトに資格情報を持たせる
		request.setAttribute("certi", certification_data);
		
		//業務経歴を格納するリストを作成
		List<Career> career_data = new ArrayList<Career>();
		//業務経歴情報を取得するメソッド
		career_data = sql.get_career_info(employeeId);
		//requestオブジェクトに業務経歴情報を持たせる
		request.setAttribute("career", career_data);

		String lastlogin = (String)session.getAttribute("lastlogin");
		request.setAttribute("lastlogin", lastlogin);
		
		//業務経歴情報に遷移
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/resume_list.jsp");
		dispatcher.forward(request, response);
	}


}
