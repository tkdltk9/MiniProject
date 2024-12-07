package miniHotelProject.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("purchaseListDTO")
public class PurchaseListDTO {
	String purchaseNum;
	String goodsNum;
	Integer purchaseQty;
	Integer totalPrice;	
}