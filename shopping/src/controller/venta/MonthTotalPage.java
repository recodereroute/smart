package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.CustomerTotalDTO;
import model.DTO.ProductTotalDTO;
import model.DTO.monthTotalDTO;

public class MonthTotalPage {
	public void monthTotal(HttpServletRequest request) {
		SalesDAO dao = new SalesDAO();
		List<monthTotalDTO> list = dao.monthTotal();
		String googleList = "[[ '상품', '총 판매개수' , '총 판매금액']";
		for(monthTotalDTO dto : list) {
			String tmp = dto.getMnth() +"월 "+ dto.getProdName();
			googleList += ",['" + tmp + "'," + dto.getTotQty() + "," 
								+ dto.getTotPrice() + "]"; 
		}
		googleList += "]";
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
}
