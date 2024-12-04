package miniHotelProject.mapper;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.GoodsStockDTO;

@Mapper
public interface GoodsStockMapper {
	public GoodsStockDTO goodsStockSelectOne(String goodsNum);
	public void goodsVisitCountUpdate(String goodsNum);

}
