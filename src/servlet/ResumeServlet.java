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
import dto.Career;
import dto.Certi;
import dto.Personal;
import dto.Station;


/**
 * Servlet implementation class ResumeServlet
 */
@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResumeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //業務経歴一覧画面のリンク押下時
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//業務経歴登録画面で入力された値をパラメーターで取得
		HttpSession session = request.getSession(false);
		
		String employee = (String)session.getAttribute("employee_id");
	    int employeeId = Integer.parseInt(employee);
	    
		SqlDao sql = new SqlDao();

		//社員情報を格納するリストを作成
		Personal personal_data = new Personal();
		//社員情報を取得するメソッド
		personal_data = sql.get_personal_info(employeeId);
		//requestオブジェクトに社員情報を持たせる
		request.setAttribute("personal", personal_data);

		//最寄り駅情報を格納するリストを作成
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
		//経歴画面へ遷移
		String lastlogin = (String)session.getAttribute("lastlogin");
		request.setAttribute("lastlogin", lastlogin);
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/resume_list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
