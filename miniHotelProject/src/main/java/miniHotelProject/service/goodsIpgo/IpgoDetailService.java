package miniHotelProject.service.goodsIpgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import miniHotelProject.domain.GoodsIpgoNameDTO;
import miniHotelProject.mapper.GoodsIpgoMapper;

@Service
public class IpgoDetailService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public GoodsIpgoNameDTO execute(String ipgoNum, String goodsNum, Model model) {
		GoodsIpgoNameDTO dto = goodsIpgoMapper.ipgoGoodsSelectOne(ipgoNum, goodsNum);
		model.addAttribute("dto", dto);
		return dto;
	}
}
