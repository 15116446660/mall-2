package com.perenc.mall.platform.interception;

import com.perenc.mall.common.context.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserRestInterceptor
 * @Description: 用户请求接口前，进行权限校验
 *
 * @Author: GR
 * @Date: 2019/9/19 11:04 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/19     GR     		
 */
@Slf4j
public class UserRestInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("========== 请求接口前，执行用户登录校验工作 =========");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清楚本地上下文相关信息
        BaseContextHandler.clean();
        super.afterCompletion(request, response, handler, ex);
    }
}
