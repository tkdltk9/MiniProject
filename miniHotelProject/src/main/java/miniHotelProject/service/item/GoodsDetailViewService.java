package miniHotelProject.service.item;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.GoodsStockDTO;
import miniHotelProject.mapper.GoodsStockMapper;
import miniHotelProject.mapper.ItemMapper;
import miniHotelProject.mapper.MemberMapper;

@Service
public class GoodsDetailViewService {
	@Autowired
	GoodsStockMapper goodsStockMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	ItemMapper itemMapper;
	@Autowired
	ObjectMapper mapper;
	public void execute(String goodsNum, Model model, HttpServletResponse response, HttpSession session) {
		GoodsStockDTO dto = goodsStockMapper.goodsStockSelectOne(goodsNum);
		model.addAttribute("stock", dto.getStock());
		goodsStockMapper.goodsVisitCountUpdate(goodsNum);
		model.addAttribute("dto", dto);
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(auth != null) {
			String memberNum =  memberMapper.memberNumSelect(auth.getUserId());
			Map<String, String> map = new HashMap<String, String>();
			map.put("goodsNum", goodsNum);
			map.put("memberNum", memberNum);
			Integer i = itemMapper.wishCountSelectOne(map);
			model.addAttribute("wish", i);
		}
		
	}
}
