package miniHotelProject.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.GoodsStockDTO;
import miniHotelProject.mapper.GoodsStockMapper;

@Service
public class GoodsDetailViewService {
	@Autowired
	GoodsStockMapper goodsStockMapper;
	public void execute(String goodsNum, Model model, HttpServletResponse response, HttpSession session) {
		GoodsStockDTO dto = goodsStockMapper.goodsStockSelectOne(goodsNum);
		model.addAttribute("stock", dto.getStock());
		goodsStockMapper.goodsVisitCountUpdate(goodsNum);
		model.addAttribute("dto", dto);
	}
}
