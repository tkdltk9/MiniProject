package miniHotelProject.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("ipgoDTO")
@AllArgsConstructor // 생성자
@NoArgsConstructor // 기본 생성자
public class GoodsIpgoDTO {
	String goodsNum;
	String ipgoNum;
	Integer ipgoQty;
	Date ipgoDate;
	Date madeDate;
	Integer ipgoPrice;
	String empNum;
}