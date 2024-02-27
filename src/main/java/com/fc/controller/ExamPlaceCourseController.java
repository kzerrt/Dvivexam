package com.fc.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Constants;
import com.fc.common.util.JsonVos;
import com.fc.pojo.po.ExamPlaceCourse;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.list.ExamPlaceCourseVo;
import com.fc.pojo.vo.req.page.ExamPlaceCoursePageReqVo;
import com.fc.pojo.vo.req.save.ExamPlaceCourseReqVo;
import com.fc.service.ExamPlaceCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;


/**
 * 考场课程(ExamPlaceCourse)表控制层
 *
 * @author Florence
 * @since 2023/05/05
 */
@RestController
@RequestMapping("/examPlaceCourses")
@Api(tags = "考试课程，科二科三")
public class ExamPlaceCourseController extends BaseController<ExamPlaceCourse, ExamPlaceCourseReqVo>{
    /**
     * 服务对象
     */
    @Autowired
    private ExamPlaceCourseService service;
    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(value = {Constants.Permisson.EXAM_PLACE_COURSE_LIST})
    public PageJsonVo<ExamPlaceCourseVo> list(ExamPlaceCoursePageReqVo query) {
        return JsonVos.ok(service.list(query));
    }

    @Override
    protected IService<ExamPlaceCourse> getService() {
        return service;
    }

    @Override
    protected Function<ExamPlaceCourseReqVo, ExamPlaceCourse> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}


