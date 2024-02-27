package com.fc.controller;

import com.fc.filter.ErrorFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Florence
 * @since 2023/06/12
 */

@RestController
public class ErrorController {
    @RequestMapping(ErrorFilter.ERROR_URL)
    public void handleError(HttpServletRequest request) throws Throwable{
        Object o = request.getAttribute(ErrorFilter.ERROR_URL);
        if (o == null) {

        } else {
            throw (Throwable) o;
        }
    }
}
