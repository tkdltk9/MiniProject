package miniHotelProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.GoodsDTO;

@Mapper
public interface GoodsMapper {
	public Integer goodsInsert(GoodsDTO dto);
	public List<GoodsDTO> goodsSelectList();
	public GoodsDTO goodsSelectOne(String goodsNum);


}
