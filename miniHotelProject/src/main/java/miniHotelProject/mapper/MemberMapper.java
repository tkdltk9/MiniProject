package miniHotelProject.mapper;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	void memberInsert(MemberDTO dto);

}
