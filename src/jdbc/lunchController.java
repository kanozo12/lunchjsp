package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
		
		String menu = getMenuData(conn, date);
		if(menu != null && !menu.equals("")) {
			out.print(gson.toJson(new ResponseVO(true, "성공적으로 불러왔습니다.")));
		} else {
			out.print(gson.toJson(new ResponseVO(false, "급식을 불러오는 중 오류 발생")));
		}
		JdbcUtil.close(conn);
	}
	
	private String getMenuData(Connection conn, String date) {
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		
		try {
			ps1 = conn.prepareStatement("SELECT * FROM menus WHERE date = ?");
			ps1.setString(1, date);
			rs = ps1.executeQuery();
			
			if(rs.next()) {
				return rs.getString("menu"); //DB에 데이터가 존재하면 해당 데이터를 리턴
			}
			
			Document doc = Jsoup.connect("http://www.y-y.hs.kr/lunch.view?date=" + date).get();
			Element menuDom = doc.selectFirst("#moring .menuName > span");
			
			if(menuDom != null) {
				ps2 = conn.prepareStatement("INSERT INTO menus(date, menu) VALUES (?,?)");
				ps2.setString(1, date);
				ps2.setString(2, menuDom.text());
				ps2.executeQuery();
				
				return menuDom.text();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps1);
			JdbcUtil.close(ps2);
		}
		return null;
	}
}
