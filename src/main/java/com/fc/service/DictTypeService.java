package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.DictType;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.DictTypeVo;
import com.fc.pojo.vo.req.page.DictTypePageReqVo;

/**
 * (DictType)表服务接口
 *
 * @author florence
 * @since 2023-04-27
 */
public interface DictTypeService extends IService<DictType> {

    PageVo<DictTypeVo> list(DictTypePageReqVo query);
}

