package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdvertisementBean;
import interfaces.AdvertisementBeanRemote;
import interfaces.RegistrationRemote;
import lookup.Lookup;


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private @EJB RegistrationRemote regInterface;

    public RegistrationServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var username = request.getParameter("username");
		var password = request.getParameter("password");
		
		var bean = Lookup.doLookup(AdvertisementBean.class, AdvertisementBeanRemote.class);
		var user = bean.login(username, password);
		
		request.getSession().setAttribute("advBean", bean);
		if(user != null) {
			request.setAttribute("successLogin", true);
			request.getRequestDispatcher("homePage.jsp").forward(request, response);
		} else {
			request.setAttribute("successLogin", false);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var username = request.getParameter("username");
		var password = request.getParameter("password");
		var nickname = request.getParameter("nickname");
		
		if(regInterface.register(nickname, username, password)) {
			request.setAttribute("success", true);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.setAttribute("success", false);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
