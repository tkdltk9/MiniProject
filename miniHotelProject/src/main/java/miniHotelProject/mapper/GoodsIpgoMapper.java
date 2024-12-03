package miniHotelProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import miniHotelProject.domain.GoodsIpgoDTO;
import miniHotelProject.domain.GoodsIpgoNameDTO;

@Mapper
public interface GoodsIpgoMapper {
	public void goodsIpgoInsert(GoodsIpgoDTO dto);
	public List<GoodsIpgoDTO> goodsIpgoList();
	public GoodsIpgoNameDTO ipgoGoodsSelectOne(
			@Param("ipgoNum") String ipgoNum
			, @Param("goodsNum") String goodsNum);

}
