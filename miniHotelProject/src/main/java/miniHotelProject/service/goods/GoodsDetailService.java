package miniHotelProject.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import miniHotelProject.domain.GoodsDTO;
import miniHotelProject.mapper.GoodsMapper;

@Service
public class GoodsDetailService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, Model model) {
		GoodsDTO dto =  goodsMapper.goodsSelectOne(goodsNum);
		model.addAttribute("goodsCommand", dto);
		// \n을 <br />로 변경하기 위해서 필요합니다.
		model.addAttribute("newLine", "\n");
	}
}
