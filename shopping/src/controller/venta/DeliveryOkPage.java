package controller.venta;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.DeliveryDTO;

public class DeliveryOkPage {
	public void excute(HttpServletRequest request) {
		DeliveryDTO dto = new DeliveryDTO();
		dto.setArrivalDate(request.getParameter("arrivalDate"));
		dto.setDeliveryCom(request.getParameter("deliveryCom"));
		dto.setDeliveryExpDate(request.getParameter("deliveryExpDate"));
		dto.setDeliveryFee(request.getParameter("deliveryFee"));
		dto.setDeliveryNum(request.getParameter("deliveryNum"));
		dto.setPurchaseNum(request.getParameter("purchaseNum"));
		SalesDAO dao = new SalesDAO();
		dao.deliveryCreate(dto);
	}
}
