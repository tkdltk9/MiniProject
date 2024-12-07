package miniHotelProject.controller;

import java.io.PrintWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.BookCommand;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.mapper.MemberMapper;
import miniHotelProject.service.item.GoodsWishService;

@Controller
@RequestMapping("item")
public class ItemController {
	@Autowired
	GoodsWishService goodsWishService;
	@RequestMapping("wishItem")
	@ResponseBody
	public void wishAdd(@RequestBody Map<String, Object> map,HttpSession session) {
		goodsWishService.execute(map.get("goodsNum").toString(), session);
	}
	@Autowired
	MemberMapper memberMapper;
	@GetMapping("buyItem")
	public String buyItem(BookCommand bookCommand, HttpSession session
			, HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberNum = null;
		try {
			memberNum = memberMapper.memberNumSelect(auth.getUserId());
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:/";  // session이 없다
		}
		if(memberNum == null) {
			String str = "<script>"
					   + "alert('관리자는 구매할 수 없습니다.');"
					   + "location.href='/corner/detailView/"+bookCommand.getGoodsNum()+"';"
					   +"</script>";
			try {
			out = response.getWriter();
			out.print(str);
			out.close();
			}catch(Exception e) {};
		}
		return "redirect:/purchase/goodsBuy?goodsNum="+bookCommand.getGoodsNum()+"&qty="+bookCommand.getQty();
	}
}
