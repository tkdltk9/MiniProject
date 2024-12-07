package miniHotelProject.command;

import lombok.Data;

@Data
public class PurchaseCommand {
	String goodsNum;
	Integer totalPaymentPrice;
	String purchaseName;
	String deliveryName;
	String deliveryPhone;
	String message;
	Integer qty;
}