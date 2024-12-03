package miniHotelProject.service.goodsIpgo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import miniHotelProject.domain.GoodsIpgoDTO;
import miniHotelProject.mapper.GoodsIpgoMapper;

@Service
public class IpgoListService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(Model model) {
		List<GoodsIpgoDTO> list = goodsIpgoMapper.goodsIpgoList();
		model.addAttribute("list", list);
	}
}
