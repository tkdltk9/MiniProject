package miniHotelProject.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsIpgoNameDTO")
public class GoodsIpgoNameDTO {
	String goodsName;
	GoodsIpgoDTO goodsIpgoDTO;
}
