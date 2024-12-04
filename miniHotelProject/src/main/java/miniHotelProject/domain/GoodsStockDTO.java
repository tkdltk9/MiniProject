package miniHotelProject.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsStockDTO")
public class GoodsStockDTO {
	String empPhone;
	String empEmail;
	Integer stock;
	GoodsDTO goodsDTO;
}
