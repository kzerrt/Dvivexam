package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.DictItem;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.DictItemVo;
import com.fc.pojo.vo.req.page.DictItemPageReqVo;

/**
 * (DictItem)表服务接口
 *
 * @author florence
 * @since 2023-04-27 15:05:12
 */
public interface DictItemService extends IService<DictItem> {
    PageVo<DictItemVo>  list(DictItemPageReqVo query);
}

