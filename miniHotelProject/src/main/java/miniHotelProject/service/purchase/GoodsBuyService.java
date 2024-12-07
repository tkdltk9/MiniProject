package miniHotelProject.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.GoodsDTO;
import miniHotelProject.mapper.GoodsMapper;
import miniHotelProject.mapper.MemberMapper;

@Service
public class GoodsBuyService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(String goodsNum, Integer qty, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		
		GoodsDTO dto = goodsMapper.goodsSelectOne(goodsNum);
		model.addAttribute("dto", dto);
		Integer goodsPrice = dto.getGoodsPrice();
		Integer sumPrice = 0; // 결제 금액
		sumPrice = goodsPrice * qty;
		System.out.println(sumPrice);
		model.addAttribute("sumPrice", sumPrice);
		model.addAttribute("qty", qty);
	}
}
