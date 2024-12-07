package miniHotelProject.service.purchase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.PurchaseCommand;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.PurchaseDTO;
import miniHotelProject.mapper.MemberMapper;
import miniHotelProject.mapper.PurchaseMapper;

@Service
public class GoodsOrderService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PurchaseMapper purchaseMapper;
	public String execute(PurchaseCommand purchaseCommand, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = memberMapper.memberNumSelect(auth.getUserId());
		String purchaseNum = purchaseMapper.selectPurchaseNum();
		System.out.println("purchaseNum : " + purchaseNum);
		/// 구매
		PurchaseDTO dto = new PurchaseDTO();
		BeanUtils.copyProperties(purchaseCommand, dto);
		dto.setPurchaseNum(purchaseNum);
		dto.setPurchasePrice(purchaseCommand.getTotalPaymentPrice());
		dto.setMemberNum(memberNum);
		purchaseMapper.purchaseInsert(dto);
		// 구매리스트
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purchaseNum", purchaseNum);
		map.put("memberNum", memberNum);
		map.put("goodsNum", purchaseCommand.getGoodsNum());
		map.put("qty", purchaseCommand.getQty());
		map.put("totalPrice", purchaseCommand.getTotalPaymentPrice());
		System.out.println(purchaseNum);
		System.out.println(memberNum);
		System.out.println(purchaseCommand.getGoodsNum());
		System.out.println(purchaseCommand.getQty());
		System.out.println(purchaseCommand.getTotalPaymentPrice());
		purchaseMapper.purchaseListInsert(map);
		return purchaseNum;
	}
}
