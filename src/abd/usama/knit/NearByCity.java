package abd.usama.knit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NearByCity
 */
@WebServlet("/NearByCity")
public class NearByCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
		String   city  = null;
		int distance   = 0;
		
    public NearByCity() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		doPost(request,response);
		}catch(Exception e ){
			response.sendRedirect("Exception.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			PrintWriter pw = response.getWriter();
		
			response.setContentType("text/html");
			city  = request.getParameter("city");
				distance = Integer.parseInt(request.getParameter("distance"));
			CityAndDistance cad  = new CityAndDistance();
			String nearByCities[] = cad.getNearBycities(distance, city);
			System.out.println("Servlet called");
			System.out.println("near by cities length:"+nearByCities.length);
			System.out.println("near by cities first:"+nearByCities[0]);
			if(nearByCities[0] !=null){
				pw.println("<!DOCTYPE html>\r\n"+
							"<html>\r\n"+
							 "<head>\r\n"+
							 "<title>Nearby Places</title>\r\n"+
							 "<meta charset=\"UTF-8\">\r\n"+
							 "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"+
	                         "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n"+
	                         "<link rel=\"stylesheet\" type=\"text/css\" href=\"nearCity.css\">\r\n"+
	                         "</head>\r\n"+
	                         "<body >\r\n"+
	                         "<!-- Navbar (sit on top) -->\r\n"+
	                         "<div class=\"w3-top\">\r\n"+
	                         "<div class=\"w3-bar w3-white w3-wide w3-padding w3-card\">\r\n"+
	                         "<a href=\"touristGuide/webContent/index.html\" class=\"w3-bar-item w3-button\"><b>Tourist Guide</b></a>\r\n"+
	                         "<!-- Float links to the right. Hide them on small screens -->\r\n"+
	                         "<div class=\"w3-right w3-hide-small\">\r\n"+
	                         "<a href=\"\\TouristGuide 1.0\\index.html#Popular Searches\" class=\"w3-bar-item w3-button\">Popular Searches</a>\r\n"+
	                         "<a href=\"\\TouristGuide 1.0\\approx.html\" class=\"w3-bar-item w3-button\">Expenses Appoximator</a>\r\n"+
	                         "<a href=\"\\TouristGuide 1.0\\input.html\" class=\"w3-bar-item w3-button\">Nearby Places</a>\r\n"+
	                         "</div>\r\n"+
	                         "</div>\r\n"+
	                         "</div>\r\n"+
	                         "<div  class=\"div_locator\">");
							 //pw.println("<h1>Tourist spots from "+city+" in the range of "+distance+"Km <h1>");
							pw.println("<h2>Nearest Tourist Destinations</h2>");
							
							 for(int i = 0 ; i<nearByCities.length&&i<10; i++)
								{
									if(nearByCities[i]!=null) {
									
									//pw.println("<li><a href=\"https://www.google.co.in/search?q="+nearByCities[i]+"\">"+nearByCities[i]+"</a></li>");
										pw.println("<li><a href=\"https://wikitravel.org/en/"+nearByCities[i]+"\">"+nearByCities[i]+"</a></li>");
										
									}
								}
							
							pw.println("</div>\r\n"+
							
	                         "</body>\r\n"+
	                         "</html>");	
				
			}
			else{
				pw.println("<!DOCTYPE html>\r\n"+
				"<html>\r\n"+
				 "<head>\r\n"+
				 "<title>Nearby Places</title>\r\n"+
				 "<meta charset=\"UTF-8\">\r\n"+
				 "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"+
	             "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n"+
	             "<link rel=\"stylesheet\" type=\"text/css\" href=\"nearCity.css\">\r\n"+
	             "</head>\r\n"+
	             "<body >\r\n"+
	             "<!-- Navbar (sit on top) -->\r\n"+
	             "<div class=\"w3-top\">\r\n"+
	             "<div class=\"w3-bar w3-white w3-wide w3-padding w3-card\">\r\n"+
	             "<a href=\"touristGuide/webContent/index.html\" class=\"w3-bar-item w3-button\"><b>Tourist Guide</b></a>\r\n"+
	             "<!-- Float links to the right. Hide them on small screens -->\r\n"+
	             "<div class=\"w3-right w3-hide-small\">\r\n"+
	             "<a href=\"#\" class=\"w3-bar-item w3-button\">Popular Searches</a>\r\n"+
	             "<a href=\"#\" class=\"w3-bar-item w3-button\">Expenses Appoximator</a>\r\n"+
	             "<a href=\"#\" class=\"w3-bar-item w3-button\">Nearby Places</a>\r\n"+
	             "</div>\r\n"+
	             "</div>\r\n"+
	             "</div>\r\n"+
	             "<div  class=\"div_locator\">");
				 //pw.println("<h1>Tourist spots from "+city+" in the range of "+distance+"Km <h1>");
				pw.println("<h2>Nearest Tourist Destinations</h2>");
				pw.println("<h3>Result Not Found</h3>");
				pw.println("<h4>No Nearest Tourist Destination found, Please insert some acceptable Range so that we can give you Nearest place."
						+ " Insert some big range For destination. Actually this range refers to distance in kilometer .</h4>"
						);
			}
			pw.println("</div>\r\n"+
	                "</body>\r\n"+
	                "</html>");	
		}catch(Exception e ){
			response.sendRedirect("Exception.html");
		}
		
		
	}


}
