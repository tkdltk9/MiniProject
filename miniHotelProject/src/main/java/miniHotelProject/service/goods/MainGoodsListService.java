package miniHotelProject.service.goods;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniHotelProject.domain.GoodsDTO;
import miniHotelProject.mapper.GoodsMapper;
@Service
public class MainGoodsListService {
    @Autowired
    GoodsMapper goodsMapper;

    public void execute(Integer page, Map<String, Object> response) {
        int limit = 4; // 한 페이지에 표시할 항목 수
        int startRow = ((page - 1) * limit) + 1; // 시작 행
        int endRow = startRow + limit - 1; // 끝 행

        // 데이터베이스에서 데이터 가져오기
        List<GoodsDTO> list = goodsMapper.goodsMainSelect(startRow, endRow);
        int count = goodsMapper.goodsCount(null); // 총 항목 수
        int maxPage = (int) ((double) count / limit + 0.95); // 총 페이지 수 계산

        // Map에 데이터 저장
        response.put("list", list);
        response.put("maxPage", maxPage);
    }
}

