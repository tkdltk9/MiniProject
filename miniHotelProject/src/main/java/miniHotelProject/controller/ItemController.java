package miniHotelProject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
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
}
