package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.ProductTotalDTO;

public class ProductTotalPage {
	public void productTotal(HttpServletRequest	request) {
		SalesDAO dao = new SalesDAO();
		List<ProductTotalDTO> list = dao.productTotal();
		String googleList = "[['상품', '총 판매개수', '총 판매금액']";
		for(ProductTotalDTO dto : list) {
//			int tmp = Integer.parseInt(dto.getTotQty());
//			tmp *= 10000;
			googleList += ",['" + dto.getProdName() + "'," + dto.getTotQty() + "," 
								+ dto.getTotPrice() + "]"; 
		}
		googleList += "]";
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
	
}
