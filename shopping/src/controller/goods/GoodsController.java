package controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoodsController extends HttpServlet
	implements Servlet{
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
//		request.setCharacterEncoding("utf-8"); //이런식으로 맨 위에 선언하고 시작하면 안되는지?
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		if(command.equals("/goodsList.gd")) {
			GoodsListPage action = new GoodsListPage();
			action.goodsList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsList.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/goodsRegist.gd")) {
			GoodsNumberPage action = new GoodsNumberPage();
			action.goodsNum(request);
			response.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsJoin.jsp");
			dispatcher.include(request, response);
		}else if(command.equals("/goodsJoin.gd")) {
			GoodsJoinPage action = new GoodsJoinPage();
			action.goodsJoin(request);
			response.sendRedirect("goodsList.gd");
		}else if(command.equals("/prodDetail.gd")) {
			GoodsModifyPage action = new GoodsModifyPage();
			action.goodsModify(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/goodsModify.gd")) {
			GoodsUpdatePage action = new GoodsUpdatePage();
			action.goodsUpdate(request);
			response.sendRedirect("goodsList.gd");
		}else if(command.equals("/prodDel.gd")) {
			GoodsDeletePage action = new GoodsDeletePage();
			action.prodDelete(request);
			response.sendRedirect("goodsList.gd");
		}else if(command.equals("/prodInfo.gd")) {
			response.setCharacterEncoding("utf-8");//내가 가져오는게 아니라 보내주는 입장이면 response로 처리
			GoodsModifyPage action = new GoodsModifyPage();
			action.goodsModify(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsDetail.jsp");
			dispatcher.include(request, response);
		}else if(command.equals("/goodsCartAdd.gd")) {
			GoodsCartAddPage action = new GoodsCartAddPage();
			action.cartAdd(request);
			response.sendRedirect("goodsCartList.gd");			
		}else if(command.equals("/goodsCartList.gd")) {
			GoodsCartList cartList = new GoodsCartList();
			cartList.cartList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsCart.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/goodsCartQtyDown.gd")) {
			GoodsCartQtyDownPage action = new GoodsCartQtyDownPage();
			action.cartQtyDown(request);
			response.sendRedirect("goodsCartList.gd");
		}else if(command.equals("/cartProdDel.gd")) {
			GoodsCartProdDel action = new GoodsCartProdDel();
			action.cartProdDel(request);
			response.sendRedirect("goodsCartList.gd");
		}else if(command.equals("/goodsBuy.gd")) {
			GoodsBuyPage action = new GoodsBuyPage();
			action.goodsBuy(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/order.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/goodsOrder.gd")) {
			request.setCharacterEncoding("utf-8");//받는값은 request, 보내는 값은 response
			GoodsOrderPage action = new GoodsOrderPage();
			String [] purchaseNum = action.goodsOrder(request).split(",");
			response.sendRedirect("paymentOk.gd?purchaseNum=" + purchaseNum[0] 
					+ "&purchaseTotPrice=" + purchaseNum[1]);
		}else if(command.equals("/purchaseCon.gd")) {
			PurchaseListConPage action = new PurchaseListConPage();
			action.purchaseList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/purchaseCon.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/paymentOk.gd")) {
			request.setAttribute("purchaseNum", request.getParameter("purchaseNum"));
			request.setAttribute("purchaseTotPrice", request.getParameter("purchaseTotPrice"));
			//expression language - ${}는 속성만 받고 parameter는 받지못함
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/payment.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/doPayment.gd")) {
			PaymentPage action = new PaymentPage();
			action.payment(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/buyFinished.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/goodsReview.gd")) {
			request.setAttribute("prodNum1", request.getParameter("prodNum"));
			request.setAttribute("purchaseNum1", request.getParameter("purchaseNum"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsReview.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/reviewWrite.gd")) {
			GoodsReviewPage action = new GoodsReviewPage();
			action.review(request);
			response.sendRedirect("purchaseCon.gd");
		}else if(command.equals("/goodsReviewUpdate.gd")) {
			GoodsReviewInfoPage action = new GoodsReviewInfoPage();
			action.reviewInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("goods/goodsReviewModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/reviewUpdate.gd")) {
			GoodsReviewWritePage action = new GoodsReviewWritePage();
			action.reviewUpdate(request);
			response.sendRedirect("purchaseCon.gd");
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