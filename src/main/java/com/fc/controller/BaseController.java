package com.fc.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.common.util.JsonVos;
import com.fc.pojo.result.CodeMsg;
import com.fc.pojo.vo.JsonVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.function.Function;

/**
 * @author Florence
 * @since 2023/04/30
 */

@Validated
public abstract class BaseController<Po, ReqVo> {
    protected abstract IService<Po> getService() ;
    protected  abstract Function<ReqVo, Po> getFunction();

    @PostMapping("/save")
    @ApiOperation("添加或更新")
    public JsonVo save(@Valid ReqVo reqVo) {
        Po po = getFunction().apply(reqVo);
        if (getService()
                .saveOrUpdate(po)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }

    @PostMapping("/remove")
    @ApiOperation("通过id删除")
    public JsonVo remove(@NotBlank(message = "id不能为空") @RequestParam String id) {
        if (getService().removeByIds(Arrays.asList(id.split(",")))) {
            return JsonVos.ok(CodeMsg.REMOVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.REMOVE_ERROR);
        }
    }
}
