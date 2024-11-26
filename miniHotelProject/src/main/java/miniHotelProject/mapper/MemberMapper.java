package miniHotelProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	Integer memberInsert(MemberDTO dto);
	List<MemberDTO> memberSelectAll();
	MemberDTO memberSelectOne(String memberNum);
	Integer memberUpdate(MemberDTO dto);
	void memberDelete(String memberNum);

}
