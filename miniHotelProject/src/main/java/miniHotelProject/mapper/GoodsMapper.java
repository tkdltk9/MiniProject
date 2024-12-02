package miniHotelProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import miniHotelProject.domain.GoodsDTO;

@Mapper
public interface GoodsMapper {
	public Integer goodsInsert(GoodsDTO dto);
	public List<GoodsDTO> goodsSelectList();
	public GoodsDTO goodsSelectOne(String goodsNum);
	public int goodsUpdate(GoodsDTO dto);
	public Integer goodsDelete(String goodsNum);
	public List<GoodsDTO> goodsMainSelect(
			@Param("startRow") int startRow
			, @Param("endRow") int endRow);
	public int goodsCount(String searchWord);


}
