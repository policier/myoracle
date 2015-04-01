package de.java2enterprise.onlineshop;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@PersistenceUnit
	private EntityManagerFactory emf;   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String id =request.getParameter("id");
			EntityManager em = emf.createEntityManager();
			String sql ="Select c.foto "
					+"from Item c "
					+ "where c.id = ?1";
			Query query = em.createQuery(sql);
			query.setParameter(1, Long.parseLong(id));
			byte[] foto = (byte[]) query.getSingleResult();
			response.reset();
			response.getOutputStream().write(foto);
		}catch(Exception ex){
			throw new ServletException(ex.getMessage());
		}
		
	}

}
