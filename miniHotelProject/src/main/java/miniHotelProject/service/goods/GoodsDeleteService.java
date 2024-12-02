package miniHotelProject.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniHotelProject.mapper.GoodsMapper;

@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum) {
		goodsMapper.goodsDelete(goodsNum);
	}
}
