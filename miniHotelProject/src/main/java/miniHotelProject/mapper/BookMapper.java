package miniHotelProject.mapper;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.DeliveryDTO;
@Mapper
public interface BookMapper {
	public Integer bookInsert(DeliveryDTO dto);
	public Integer bookStatusUpdate(String purchaseNum);
	public Integer bookDelete(String purchaseNum);
}
