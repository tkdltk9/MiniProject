package miniHotelProject.mapper;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.MemberDTO;

@Mapper
public interface MemberInfoMapper {
	MemberDTO memberSelectOne(String memberId);
	Integer memberUpdate(MemberDTO dto);
	Integer memberDelete(String memberId);

}
