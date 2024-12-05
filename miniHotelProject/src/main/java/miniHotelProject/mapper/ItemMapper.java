package miniHotelProject.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {
	public int wishItem(Map<String , String> map);
	public Integer wishCountSelectOne(Map<String, String> map);

}
