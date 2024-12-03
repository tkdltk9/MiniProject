package miniHotelProject.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class GoodsIpgoCommand {
	@NotEmpty(message = "상품을 선택해주세요")
	String goodsNum;
	String ipgoNum;
	Integer ipgoQty;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date ipgoDate;
	Integer ipgoPrice;
}