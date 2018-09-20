package abd.usama.knit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import connect.ConnectionProvider;


@WebServlet("/AddMessage")
public class AddMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddMessage() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageData obj = new Gson().fromJson(request.getReader(),MessageData.class);
		try {
			Connection  con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			String query = "insert into comment1 (username,email,subject,comment2) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setString(3,obj.getSubject());
			ps.setString(4, obj.getComment());
			ps.executeUpdate();
			con.commit();
			con.close();
			
			Map<String,Object> data = new HashMap<>();
			data.put("name", obj.getName());
			data.put("email", obj.getEmail());
			data.put("subject",obj.getSubject());
			data.put("comment", obj.getComment());
			data.put("databaseStatus", true);
			String jason = new Gson().toJson(data);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jason);
			
		}catch(SQLException e){
			e.printStackTrace();
			Map<String,Object> data = new HashMap<>();
			
			data.put("databaseStatus", false);
			String jason = new Gson().toJson(data);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jason);
			
		}
	}

}
