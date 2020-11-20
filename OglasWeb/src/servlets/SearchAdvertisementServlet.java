package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.AdvertisementBeanRemote;

@WebServlet("/SearchAdvertisementServlet")
public class SearchAdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchAdvertisementServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var keyword = request.getParameter("keyword");
		var list = ((AdvertisementBeanRemote) request.getSession().getAttribute("advBean")).findAdvertisements(keyword);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("homePage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var description = request.getParameter("text");
		
		var res = ((AdvertisementBeanRemote) request.getSession().getAttribute("advBean")).addAdvertisement(description);
		
		request.getSession().setAttribute("successAdd", res);
		request.getRequestDispatcher("homePage.jsp").forward(request, response);
	}

}
