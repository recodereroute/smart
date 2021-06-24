package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.CustomerTotalDTO;

public class CustomerTotalPage {
	public void customerTotal(HttpServletRequest request) {
		SalesDAO dao = new SalesDAO();
		List<CustomerTotalDTO> list = dao.customerTotal();
		String googleList = "[['아이디/이름', '총 구매금액', '총 개수', '평균 금액']";
		for(CustomerTotalDTO dto : list) {
			googleList += ",['"+dto.getMemId() + "/" + dto.getMemName() 
						+"'," + dto.getSumPrice() + "," + dto.getCountTotal()
						+"," + dto.getAvgPrice() + "]"; 
		}
		googleList += "]";
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
}
