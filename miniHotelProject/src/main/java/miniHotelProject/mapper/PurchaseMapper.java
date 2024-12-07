package miniHotelProject.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.PaymentDTO;
import miniHotelProject.domain.PurchaseDTO;

@Mapper
public interface PurchaseMapper {
	public String selectPurchaseNum();
	public Integer purchaseInsert(PurchaseDTO dto);
	public Integer purchaseListInsert(Map<String, Object> map);
	public Integer paymentInsert(PaymentDTO dto);
	public PurchaseDTO purchaseSelectOne(String purchaseNum);
}