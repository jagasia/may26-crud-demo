package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cts.fw.model.Item;
import com.cts.fw.model.ItemDao;
import com.cts.fw.model.ItemDaoImpl;

/**
 * Servlet implementation class ItemServlet
 */
@MultipartConfig
@WebServlet({ "/ItemServlet", "/item" })
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") == null) {
			return;
		}
		Item item=null;
		Integer id = Integer.parseInt(request.getParameter("id"));
		String btn = request.getParameter("btn");
		
		if (btn != "Delete") {
			String category = "";
			String name = "";
			Double price = 0.0;
			if (request.getParameter("category") != null && request.getParameter("category") != "")
				category = request.getParameter("category");
			if (request.getParameter("name") != null && request.getParameter("name") != "")
				name = request.getParameter("name");
//			System.out.println("we received******"+request.getParameter("price")+"*************");
			if (request.getParameter("price") != null && request.getParameter("price") != "" && request.getParameter("price").length()>0)
				price = Double.parseDouble(request.getParameter("price"));
			byte[] picture = null;
			Part part = request.getPart("picture");
			InputStream is = part.getInputStream();
			int size = is.available();
			if (size != 0) {
				picture = new byte[size];
				is.read(picture);
			}
			item = new Item(id, category, name, price, picture);
		}
		
		ItemDao idao = new ItemDaoImpl();

		try {
			switch (btn) {
			case "Add":
				idao.create(item);
				break;
			case "Update":
				idao.update(item);
				break;
			case "Delete":
				idao.delete(id);
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//go back to home
		response.sendRedirect("home");
		
	}

}
