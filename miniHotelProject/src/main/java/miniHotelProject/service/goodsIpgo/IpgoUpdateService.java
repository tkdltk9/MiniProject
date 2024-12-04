package miniHotelProject.service.goodsIpgo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniHotelProject.command.GoodsIpgoCommand;
import miniHotelProject.domain.GoodsIpgoDTO;
import miniHotelProject.mapper.GoodsIpgoMapper;

@Service
public class IpgoUpdateService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	public void execute(GoodsIpgoCommand goodsIpgoCommand) {
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		BeanUtils.copyProperties(goodsIpgoCommand, dto);
		goodsIpgoMapper.goodsIpgoUpdate(dto);
	}
}
