package miniHotelProject.service.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.OrderListDTO;
import miniHotelProject.mapper.MemberMapper;
import miniHotelProject.mapper.PurchaseMapper;

@Service
public class OrderProcessListService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PurchaseMapper purchaseMapper;
	public void execute(HttpSession session, Model model, String purchaseNum) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		
		Map<String , String> map = new HashMap<String, String>();
		map.put("memberNum", memberNum);
		map.put("purchaseNum", purchaseNum);
		
		List<OrderListDTO> list = purchaseMapper.orderList(map); 
		
		model.addAttribute("list", list);
	}

}
