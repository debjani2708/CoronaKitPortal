package com.wellsfargo.fsd.sba.ck.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellsfargo.fsd.sba.ck.exception.CoronaKitException;
import com.wellsfargo.fsd.sba.ck.model.KitDetails;
import com.wellsfargo.fsd.sba.ck.model.ProductDetails;
import com.wellsfargo.fsd.sba.ck.model.UserDetails;
import com.wellsfargo.fsd.sba.ck.service.KitService;
import com.wellsfargo.fsd.sba.ck.service.KitServiceImpl;


/**
 * Servlet implementation class UserController
 */
@WebServlet({"/newuser","/showproducts","/addnewitem","/deleteitem","/placeorder","/orderfinalized"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private KitService kitService;
	String [] str;
	List<KitDetails> products;
    
    @Override
    public void init() throws ServletException {
    	kitService= new KitServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =  request.getServletPath();
		
		String view = "";
		try {
			switch (action) {
			case "/newuser":
				view = showNewUserForm(request, response);
				break;
			case "/showproducts":
				view = showAllProducts(request, response);
				break;	
			case "/addnewitem":
				view = addNewItemToKit(request, response);
				break;
			case "/deleteitem":
				view = deleteItemFromKit(request, response);
				break;
			case "/placeorder":
				view = showPlacedOrderDetails(request, response);
				break;
			case "/orderfinalized":
				view = showOrderFinalized(request, response);
				break;	
			default : view = "notfound.jsp"; break;	
			}
		}catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
		
			request.getRequestDispatcher(view).forward(request, response);	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String showPlacedOrderDetails(HttpServletRequest request, HttpServletResponse response) throws CoronaKitException {
		
		HttpSession session=request.getSession();
		
		String view = "";

		products=new ArrayList();
		KitDetails product=null;

		String [] str=request.getParameterValues("quantity");
		String [] productID=request.getParameterValues("productID");
		
		Double total=0.0;
		List<Double> amount=new ArrayList();
		
		for(int i =0;i<productID.length;i++)
		{	
			int productId =Integer.parseInt(productID[i]); 
			try {

				product = kitService.getProduct(productId);
				product.setOrderQuantity(Integer.parseInt(str[i]));
				product.setProductOrderAmount(product.getProductPrice()* product.getOrderQuantity());
				amount.add(product.getProductOrderAmount());
				products.add(product);
				
			}catch(CoronaKitException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = "errPage.jsp";
			}
		}
		
		/* Calculating the Total Amount */
		for(int i=0;i<amount.size();i++)
		{
			total=total+amount.get(i);
		}
		
		session.setAttribute("Amount", total);
		
		request.setAttribute("order", products);
		view="orderSummary.jsp";
		
		return view;
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		KitDetails p=new KitDetails();
		List<KitDetails> products=new ArrayList();
		KitDetails product=null;
		
		int deleteProductId = Integer.parseInt(request.getParameter("id"));
		
		int i=0;
		for(i =0;i<str.length;i++)
		{	
			int  productId =Integer.parseInt(str[i]);  
			if(!(productId ==deleteProductId)) {
			try {
				product = kitService.getProduct(productId);
				products.add(product);
				
				
			}catch(CoronaKitException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = "errPage.jsp";
			}
			}
		}

		request.setAttribute("product", products);
		view="placeOrder.jsp";
		return view;
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		String view = "";

		List<KitDetails> products=new ArrayList();
		KitDetails product=null;

		 str=request.getParameterValues("productid");

		for(int i =0;i<str.length;i++)
		{	
			int productId =Integer.parseInt(str[i]); 
			try {
				product = kitService.getProduct(productId);
				products.add(product);
			}catch(CoronaKitException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = "errPage.jsp";
			}
		}			
		request.setAttribute("product", products);
		view="placeOrder.jsp";
		return view;
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session= request.getSession();
		
		UserDetails user = new UserDetails();
		user.setOrderFinalized(false);
		session.setAttribute("OrderFinalizedStatus", user.isOrderFinalized());
		
		String view = "";
		try {
			List<KitDetails> products = kitService.getAllProducts();
			request.setAttribute("products", products);
			view = "showProductsToAdd.jsp";

		} catch (CoronaKitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();

		String view = "";

		String personName = request.getParameter("PersonName");
		String email=request.getParameter("emailId");
		
		Long contact=Long.parseLong(request.getParameter("contactNo"));
		String contactNo=String.valueOf(contact);

		if(!(personName.isBlank()) && !(email.isBlank()) && (contactNo.length()==10)){			
			session.setAttribute("Name", personName);
			view=showAllProducts(request, response);
		}else {
			request.setAttribute("msg", "Invalid User entries ! Try again.");
			view="index.jsp";
		}
		return view;
	}
	

	private String showOrderFinalized(HttpServletRequest request, HttpServletResponse response) throws CoronaKitException {
		
		/*Finalizing the order*/
		UserDetails user = new UserDetails();
		String view = "";
		user.setOrderFinalized(true);
		if(user.isOrderFinalized()) {
		view="orderFinalized.jsp";
		
		/* updating the product quanity in coronaKit DB table as per the user purchase*/
		KitDetails product=new KitDetails(); 
		
		for(int j=0;j<products.size();j++)
		{			
			Integer prodid=products.get(j).getProductId();
			Integer productQuantity=products.get(j).getProductQuantity();
		    Integer orderQuantity=products.get(j).getOrderQuantity();
		    
			product.setProductId(prodid);
			product.setProductQuantity(productQuantity);
			product.setOrderQuantity(orderQuantity);
			
			kitService.validateAndSave(product);						
		}

		}else {
			request.setAttribute("msg", "There is some issue with your order ! Try again.");
			view="index.jsp";
		}
		return view;
	}

}
