package com.ecme.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecme.sessionbean.impl.StatefulSessionBean;
import com.ecme.sessionbean.impl.StatelessSessionBean;

/**
 * Servlet implementation class SessionBeanServlet
 */
@WebServlet("/sbServlet")
public class SessionBeanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	StatefulSessionBean ssb;
	@EJB
	StatelessSessionBean statelessSessionBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionBeanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<html>");

		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("<h2>Statefull Session Bean</h2>");
		response.getWriter().append("<hr/>Response from Stateful Session Bean:"+ssb.sayHello(Thread.currentThread().getName()));
		ssb.updateData(Thread.currentThread().getName());
		response.getWriter().append("<p> --- Data updated:").append(ssb.getSomeData()).append(" - Counter ").append(ssb.getCounter()+"</p>");
		response.getWriter().append("<hr/>");
		response.getWriter().append("<h2>Stateless Session Bean</h2>");
		response.getWriter().append("<hr/>Response from Stateful Session Bean:"+statelessSessionBean.sayHello(Thread.currentThread().getName()));
		statelessSessionBean.updateData(Thread.currentThread().getName());
		response.getWriter().append("<p> --- Data updated:").append(statelessSessionBean.getSomeData()).append(" - Counter ").append(statelessSessionBean.getCounter()+"</p>");
		response.getWriter().append("<hr/>");
		response.getWriter().append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
