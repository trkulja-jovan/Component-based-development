package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.AdvertisementBeanRemote;

@WebServlet("/RespondToAddServlet")
public class RespondToAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RespondToAddServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var idAd = Integer.parseInt(request.getParameter("idAdvertisement"));
		var res = ((AdvertisementBeanRemote) request.getSession().getAttribute("advBean")).respond(idAd);
		
		request.getSession().setAttribute("successRespond", res);
		request.getRequestDispatcher("homePage.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
