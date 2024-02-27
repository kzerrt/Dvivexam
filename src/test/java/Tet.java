import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fc.NewJiaKao;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.enchance.MyPage;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Streams;
import com.fc.mapper.DictItemMapper;
import com.fc.mapper.DictTypeMapper;
import com.fc.mapper.PlateRegionMapper;
import com.fc.pojo.po.DictItem;
import com.fc.pojo.po.DictType;
import com.fc.pojo.po.PlateRegion;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.DictItemVo;
import com.fc.pojo.vo.list.DictTypeVo;
import com.fc.pojo.vo.list.PlateRegionVo;
import com.fc.pojo.vo.list.ProvinceVo;
import com.fc.pojo.vo.req.page.CityPageReqVo;
import com.fc.pojo.vo.req.page.DictItemPageReqVo;
import com.fc.pojo.vo.req.page.DictTypePageReqVo;
import com.fc.service.DictTypeService;
import com.fc.service.impl.DictTypeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Florence
 * @since 2023/04/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewJiaKao.class)
public class Tet {
    @Autowired
    DictTypeService service;
    @Test
    public void SqlTest() {
        List<DictTypeVo> stream = Streams.stream(service.list(), MapStructs.INSTANCE::po2vo);
        System.out.println(stream);

    }
    @Test
    public void MapTest() {

    }
}
