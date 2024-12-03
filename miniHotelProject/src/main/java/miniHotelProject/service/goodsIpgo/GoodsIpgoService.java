package miniHotelProject.service.goodsIpgo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.GoodsIpgoCommand;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.GoodsIpgoDTO;
import miniHotelProject.mapper.EmployeeMapper;
import miniHotelProject.mapper.GoodsIpgoMapper;

@Service
public class GoodsIpgoService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		
		BeanUtils.copyProperties(goodsIpgoCommand, dto);
		dto.setEmpNum(empNum);
		
		goodsIpgoMapper.goodsIpgoInsert(dto);
	}

}
