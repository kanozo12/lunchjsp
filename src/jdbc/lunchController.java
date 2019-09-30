package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vo.ResponseVO;

@WebServlet("/lunch")
public class lunchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String date = req.getParameter("date");
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		Gson gson = new GsonBuilder().create();
		
		if(date == null || date.equals("")) {
			out.print(gson.toJson(new ResponseVO(false, "입력된 날짜가 올바르지 않습니다.")));
			return;
		}
		
		Connection conn = JdbcUtil.getConnection();
		if(conn == null) {
			out.print(gson.toJson(new ResponseVO(false, "DB 연결에 오류가 발생했습니다. 잠시후 시도 하세요.")));
			return;
		}
		
		String menu = getMenuData(conn, data);
		if(menu != null && !me)
		
		
	}
}
