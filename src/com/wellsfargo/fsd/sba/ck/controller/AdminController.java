package com.wellsfargo.fsd.sba.ck.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.ProductDetails;
import com.wellsfargo.fsd.sba.ck.service.ProductDetailsService;
import com.wellsfargo.fsd.sba.ck.service.ProductDetailsServiceImpl;

/**
 * Servlet implementation class AdminController
 */
@WebServlet({"/login","/newproduct","/insertproduct","/deleteproduct","/editproduct","/saveproduct","/list","/logout"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDetailsService productService;
    
    @Override
    public void init() throws ServletException {
    	productService= new ProductDetailsServiceImpl();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action =  request.getServletPath();
		String view = "";
		
		try {
			switch (action) {
			case "/login" : 
				view = adminLogin(request, response);
				break;
			case "/newproduct":
				view = showNewProductForm(request, response);
				break;
			case "/insertproduct":
				view = saveProduct(request, response);
				break;
			case "/deleteproduct":
				view = deleteProduct(request, response);
				break;
			case "/editproduct":
				view = showEditProductForm(request, response);
				break;
			case "/saveproduct":
				view = saveProduct(request, response);
				break;
			case "/list":
				view = listAllProducts(request, response);
				break;	
			case "/logout":
				view = adminLogout(request, response);
				break;	
			default : view = "notFound.jsp"; 
				break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}		
		request.getRequestDispatcher(view).forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String view = "";

		String userName = request.getParameter("Username");
		String password=request.getParameter("Password");
		if(userName.equals("admin") && password.equals("admin")) {
			view=listAllProducts(request, response);
		}else {
			request.setAttribute("msg", "Invalid Login Credentials ! Try again.");
			view="index.jsp";
		}
		return view;
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		try {
			List<ProductDetails> products = productService.getAllProducts();
			request.setAttribute("products", products);
			view = "listProducts.jsp";
		} catch (CoronaKitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		String view = "";

		ProductDetails product = new ProductDetails();
		request.setAttribute("product", product);
		request.setAttribute("isNew", true);
		view = "newProducts.jsp";

		return view;
	}

	private String saveProduct(HttpServletRequest request, HttpServletResponse response) {

		ProductDetails product = new ProductDetails();
		product.setProductId(Integer.parseInt(request.getParameter("productId")));
		product.setProductName(request.getParameter("productName"));
		product.setProductDescription(request.getParameter("productDescription"));	
		product.setProductQuantity(Integer.parseInt(request.getParameter("productQuantity")));
		product.setProductPrice(Double.parseDouble(request.getParameter("productPrice")));

		String view = "";

		try {

			if(request.getServletPath().equals("/insertproduct")) {
				productService.validateAndAdd(product);
				view=listAllProducts(request, response);
			}else {
				productService.validateAndSave(product);			
			}

			request.setAttribute("msg", "Product is saved");
			view=listAllProducts(request, response);

		} catch (CoronaKitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}

		return view;
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) {
		String view = "";

		int productId = Integer.parseInt(request.getParameter("id"));
		try {
			ProductDetails product = productService.getProduct(productId);
			request.setAttribute("product", product);
			request.setAttribute("isNew", false);
			view="newProducts.jsp";
		} catch (CoronaKitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}

		return view;
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		String view = "";

		int productId = Integer.parseInt(request.getParameter("id"));
		try {
			productService.deleteProduct(productId);
			request.setAttribute("msg", "Product Record Deleted!");
			view=listAllProducts(request, response);
		} catch (CoronaKitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}

		return view;
	}
	
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();		
		return "index.jsp";
	}

}
