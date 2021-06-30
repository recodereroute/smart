package controller.venta;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VentaController extends HttpServlet
	implements Servlet{
	public void doProcess(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		String requsetURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requsetURI.substring(contextPath.length());
		if(command.equals("/venta.vnt")) {
			ClientSalesPage action = new ClientSalesPage();
			action.clientSale(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/venta.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/userSales.vnt")) {
			UserSalesPage action = new UserSalesPage();
			action.userSales(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/userSales.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/customerTotal.vnt")) {
			CustomerTotalPage action = new CustomerTotalPage();
			action.customerTotal(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/customerTotal.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/productTotal.vnt")) {
			ProductTotalPage action = new ProductTotalPage();
			action.productTotal(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/productTotal.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/monthTotal.vnt")) {
			MonthTotalPage action = new MonthTotalPage();
			action.monthTotal(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/monthTotal.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/annualTotal.vnt")) {
			AnnualTotalPage action = new AnnualTotalPage();
			action.annualTotal(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/annualTotal.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/createDelivery.vnt")) {
			CreateDeliveryPage action = new CreateDeliveryPage();
			action.excute(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("sales/deliveryForm.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("deliveryOk.vnt")) {
			DeliveryOkPage action = new DeliveryOkPage();
			action.excute(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("venta.vnt");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

}
