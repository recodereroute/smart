package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.ClientSaleDTO;
import model.DTO.CustomerTotalDTO;
import model.DTO.DeliveryDTO;
import model.DTO.ProductTotalDTO;
import model.DTO.monthTotalDTO;

public class SalesDAO extends DataBaseInfo{
	public void deliveryCreate(DeliveryDTO dto) {
		String delFee = " select sum(prod_del_fee) " + 
						" from purchase_list pl, products pr " + 
						" where pl.prod_num = pr.prod_num " + 
						" and pl.purchase_num = ? ";
		
		sql = 	" merge into delivery d " + 
				" using (select purchase_num " + 
				"       from purchase " + 
				"       where purchase_num = ?) p " + 
				" on (d.purchase_num = p.purchase_num) " + 
				" when MATCHED THEN " + 
				" update set DELIVERY_COM = ?, DELIVERY_NUM = ?, DELIVERY_EXP_DATE = ?, " + 
				"           ARRIVAL_DATE = ?, DELIVERY_FEE = (" + delFee + ") " + 
				" when not MATCHED THEN " + 
				" insert (DELIVERY_COM, DELIVERY_NUM, DELIVERY_EXP_DATE, ARRIVAL_DATE, DELIVERY_FEE) " + 
				" values (?,?,?,?," + delFee + "),?";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getDeliveryCom());
			pstmt.setString(3, dto.getDeliveryNum());
			pstmt.setString(4, dto.getDeliveryExpDate());
			pstmt.setString(5, dto.getArrivalDate());
			pstmt.setString(6, dto.getPurchaseNum());
			pstmt.setString(7, dto.getDeliveryCom());
			pstmt.setString(8, dto.getDeliveryNum());
			pstmt.setString(9, dto.getDeliveryExpDate());
			pstmt.setString(10, dto.getArrivalDate());
			pstmt.setString(11, dto.getPurchaseNum());
			pstmt.setString(12, dto.getPurchaseNum());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public DeliveryDTO deliverySelect(String purchaseNum) {
		DeliveryDTO dto = null;
		sql = 	" select purchase_num, delivery_com, DELIVERY_NUM, DELIVERY_EXP_DATE, ARRIVAL_DATE, DELIVERY_FEE"
			  + " from delivery "
			  + " where purchase_num = ? ";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new DeliveryDTO();
				dto.setArrivalDate(rs.getString(5));
				dto.setDeliveryCom(rs.getString(2));
				dto.setDeliveryExpDate(rs.getString(4));
				dto.setDeliveryFee(rs.getString(6));
				dto.setDeliveryNum(rs.getString(3));
				dto.setPurchaseNum(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
	public List<monthTotalDTO> monthTotal(){
		List<monthTotalDTO> list = new ArrayList<monthTotalDTO>();
		List<String> months = new ArrayList<String>();
		// 몇월 달들이 들어가 있는지 먼저 검색
		// 검색된 달들을 months 리스트에 넣어줌
		sql = 	" select to_char(purchase_date,'mm') month" + 
				" from purchase pu, purchase_list pl, products pr" + 
				" where pu.purchase_num = pl.purchase_num and pl.prod_num = pr.prod_num" + 
				" group by to_char(purchase_date,'mm')";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				months.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// 검색된 월들을 이용해 구하고 싶은 값들을 뽑아냄
		sql = 	" select prod_name, sum(purchase_qty), sum(purchase_tot_price)" + 
				" from purchase pu, purchase_list pl, products pr" + 
				" where pu.purchase_num = pl.purchase_num and pl.prod_num = pr.prod_num" + 
				" and to_char(purchase_date,'mm') = ?" + 
				" group by prod_name";
		for(String month : months) {
			getConnect();
			 try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, month);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					monthTotalDTO dto = new monthTotalDTO();
					dto.setProdName(rs.getString(1));
					dto.setTotQty(rs.getString(2));
					dto.setTotPrice(rs.getString(3));
					dto.setMnth(month);
					list.add(dto);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close();
			}
		}
		return list;
	}
	
	public List<ProductTotalDTO> productTotal(){
		List<ProductTotalDTO> list = new ArrayList<ProductTotalDTO>();
		sql = 	" select prod_name, sum(purchase_qty), sum(purchase_tot_price) " + 
				" from purchase pu, purchase_list pl, products pr " + 
				" where pu.purchase_num = pl.purchase_num and pl.prod_num = pr.prod_num " + 
				" group by prod_name";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductTotalDTO dto = new ProductTotalDTO();
				dto.setProdName(rs.getString(1));
				dto.setTotQty(rs.getString(2));
				dto.setTotPrice(rs.getString(3));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	public List<CustomerTotalDTO> customerTotal(){
		List<CustomerTotalDTO> list = new ArrayList<CustomerTotalDTO>();
		sql = 	" select m.mem_id, mem_name, sum(purchase_tot_price), count(*), avg(purchase_tot_price) " + 
				" from member m, purchase pu " + 
				" where m.mem_id = pu.mem_id " + 
				" group by m.mem_id, m.mem_name";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomerTotalDTO dto = new CustomerTotalDTO();
				dto.setAvgPrice(rs.getString(5));
				dto.setCountTotal(rs.getString(4));
				dto.setMemId(rs.getString(1));
				dto.setMemName(rs.getString(2));
				dto.setSumPrice(rs.getString(3));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	public List<ClientSaleDTO> salesList(String memId){
		List<ClientSaleDTO> list = new ArrayList<ClientSaleDTO>();
		sql = 	"select m.mem_id, mem_name, mem_phone, " + 
				"       pr.prod_num, prod_name," + 
				"       pu.purchase_num, PURCHASE_DATE, RECEIVER_NAME, PURCHASE_ADDR,RECEIVER_PHONE," + 
				"       PURCHASE_QTY, PURCHASE_PRICE, " +
				"		DELIVERY_NUM " +
				" from member m, products pr, purchase pu, purchase_list pl, delivery d " + 
				" where m.mem_id(+) = pu.mem_id and pu.purchase_num = pl.purchase_num " +
				"		and pl.prod_num = pr.prod_num and pu.purchase_num = d.purchase_num(+)";
		if(memId != null) {
			sql += " and m.mem_id = ? ";
		}
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			if(memId != null) {
				pstmt.setString(1, memId);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ClientSaleDTO dto = new ClientSaleDTO();
				dto.setMemId(rs.getString("mem_id"));
				dto.setMemName(rs.getString("mem_Name"));
				dto.setMemPhone(rs.getString("mem_Phone"));
				dto.setProdName(rs.getString("prod_Name"));
				dto.setProdNum(rs.getString("prod_Num"));
				dto.setPurchaseAddr(rs.getString("purchase_Addr"));
				dto.setPurchaseDate(rs.getDate("purchase_Date"));
				dto.setPurchaseNum(rs.getString("purchase_Num"));
				dto.setPurchasePrice(rs.getString("PURCHASE_PRICE"));
				dto.setPurchaseQty(rs.getString("purchase_Qty"));
				dto.setReceiverName(rs.getString("receiver_Name"));
				dto.setReceiverPhone(rs.getString("receiver_Phone"));
				dto.setDeliveryNum(rs.getString("DELIVERY_NUM"));
				list.add(dto);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
}
