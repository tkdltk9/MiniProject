package miniHotelProject.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("deliveryDTO")
public class DeliveryDTO {
	String purchaseNum;
	String deliveryNum;
	Date deliveryDate;
	String deliveryStatus;
}