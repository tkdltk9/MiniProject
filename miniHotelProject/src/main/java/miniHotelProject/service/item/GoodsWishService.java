package miniHotelProject.service.item;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.mapper.ItemMapper;
import miniHotelProject.mapper.MemberMapper;

@Service
public class GoodsWishService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	ItemMapper itemMapper;
	public void execute(String goodsNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		
		Map<String , String> map = new HashMap<String , String>();
		map.put("goodsNum", goodsNum);
		map.put("memberNum", memberNum);
		
		itemMapper.wishItem(map);
	}
}
