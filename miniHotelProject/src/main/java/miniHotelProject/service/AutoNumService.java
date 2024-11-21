package miniHotelProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniHotelProject.mapper.AutoNumMapper;

@Service
public class AutoNumService {
	@Autowired
	AutoNumMapper autoNumMapper;
	public String execute(String sep, String columnName, Integer len
			, String tableName) {
		String autoNum = autoNumMapper.AutoNumSelect(sep, columnName, len, tableName);
		return autoNum;
	}
}
