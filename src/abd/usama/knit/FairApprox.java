package abd.usama.knit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FairApprox
 */
@WebServlet("/FairApprox")
public class FairApprox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FairApprox() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
		String sourceCity = null;
		String destCity   = null;
		int    noOfDays   = 0;
		String Class      = null;
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		sourceCity = request.getParameter("source_city");
		destCity   = request.getParameter("dest_city");
		noOfDays   = Integer.parseInt(request.getParameter("staydays"));
		Class     = request.getParameter("class");
		
		CityAndDistance cad = new CityAndDistance();
		int Distance = cad.getDistance(sourceCity, destCity);
		System.out.println(Distance);
		System.out.println(Class);
		System.out.println(noOfDays);
		
		double total_Expense = 0.0;
		if(Class.equalsIgnoreCase("economical"))
			total_Expense  = Distance*14 + (noOfDays)*400 + (noOfDays)*1000 + (noOfDays)*200;
		else if(Class.equalsIgnoreCase("delux"))
			total_Expense =  Distance*24 + (noOfDays)*1200 + (noOfDays)*4000 + (noOfDays)*800;
		else
			total_Expense  = Distance*20 + (noOfDays)*800 + (noOfDays)*2000 + (noOfDays)*400; 
		
		
		System.out.println("Approx expenses with "+Class+"class is "+total_Expense);
		
		pw.println("<!DOCTYPE html>\r\n"+
				"<html>\r\n"+
				 "<head>\r\n"+
				 "<title>Fare Appraoximation</title>\r\n"+
				 "<meta charset=\"UTF-8\">\r\n"+
				 "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"+
                 "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n"+
                 "<link rel=\"stylesheet\" type=\"text/css\" href=\"fareApprox.css\">\r\n"+
                 "</head>\r\n"+
                 "<body >\r\n"+
                 "<!-- Navbar (sit on top) -->\r\n"+
                 "<div class=\"w3-top\">\r\n"+
                 "<div class=\"w3-bar w3-white w3-wide w3-padding w3-card\">\r\n"+
                 "<a href=\"index.html\" class=\"w3-bar-item w3-button\"><b>Tourist Guide</b></a>\r\n"+
                 "<!-- Float links to the right. Hide them on small screens -->\r\n"+
                 "<div class=\"w3-right w3-hide-small\">\r\n"+
                 "<a href=\"\\TouristGuide 1.0\\index.html#Popular Searches\" class=\"w3-bar-item w3-button\">Popular Searches</a>\r\n"+
                 "<a href=\"\\TouristGuide 1.0\\approx.html\" class=\"w3-bar-item w3-button\">Expenses Appoximator</a>\r\n"+
                 "<a href=\"\\TouristGuide 1.0\\input.html\" class=\"w3-bar-item w3-button\">Nearby Places</a>\r\n"+
                 "</div>\r\n"+
                 "</div>\r\n"+
                 "</div>\r\n"+
                 "<div  class=\"div_locator\">");
				pw.println("<h2>For this kind of Requirements Total Approximate Expenses</h2>");
				pw.println("<h1>"+total_Expense+" Rs.</h1>");
				pw.println("<h4>NOTE:-</h4><p> Our Websites Approximate total expenses "
						+ "including Fooding,Residing,miscleneous,Fare Approximation(including return fare).</p>");
				pw.println("</div>\r\n"+
                        "</body>\r\n"+
                        "</html>");	
		}catch(Exception e){
			response.sendRedirect("Exception.html");
		}
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			doGet(request, response);
		}catch(Exception e)
		{
			response.sendRedirect("Exception.html");
		}
	}

}
