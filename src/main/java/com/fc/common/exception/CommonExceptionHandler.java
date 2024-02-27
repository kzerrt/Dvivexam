package com.fc.common.exception;

import com.fc.common.util.JsonVos;
import com.fc.common.util.Rs;
import com.fc.common.util.Streams;
import com.fc.pojo.result.CodeMsg;
import com.fc.pojo.result.R;
import com.fc.pojo.vo.JsonVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Florence
 * @since 2023/04/29
 */

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public JsonVo handleThrowable(Throwable t) throws Exception {
        log.error("【出现异常】", t);

        if (t instanceof CommonException) {
            CommonException ce = (CommonException) t;
            return JsonVos.error(ce.getCode(), ce.getMessage());
        } else if (t instanceof BindException) {
            return handle((BindException) t);
        } else if (t instanceof ConstraintViolationException) {
            return handle((ConstraintViolationException) t);
        }

        Throwable cause = t;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause != t) {
            return handleThrowable(cause);
        }
        return JsonVos.error();
    }
    private JsonVo handle(CommonException ce) {
        return JsonVos.error(ce.getCode(), ce.getMessage());
    }

    private JsonVo handle(BindException be) {
        List<ObjectError> errors = be.getBindingResult().getAllErrors();
        // 函数式编程的方式：stream
        List<String> defaultMsgs = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        String msg = StringUtils.collectionToDelimitedString(defaultMsgs, ", ");
        return JsonVos.error(msg);
    }

    private JsonVo handle(ConstraintViolationException cve) {
        List<String> msgs = Streams.stream(cve.getConstraintViolations(), ConstraintViolation::getMessage);
        String msg = StringUtils.collectionToDelimitedString(msgs, ", ");
        return JsonVos.error(CodeMsg.BAD_REQUEST.code(), msg);
    }
    /*@ExceptionHandler(Throwable.class)
    public void handle(Throwable t,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        // 设置返回数据的格式
        //response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(400);
        response.getWriter().write(Rs.error(t).jsonString());
        //Debugs.run(t::printStackTrace);
        //log.error(null, t);
    }*/
}
