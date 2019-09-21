package com.perenc.mall.merchant.interception;

import com.perenc.mall.common.constant.ContextConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.entity.CacheUserInfo;
import com.perenc.mall.common.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
@Component
public class UserRestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    @Qualifier("redisUtils")
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("========== 请求接口前，执行用户登录校验工作 =========");

        HttpSession session = request.getSession();

        CacheUserInfo cacheUserInfo = (CacheUserInfo) redisUtils.get(session.getId());
        if (null == cacheUserInfo) {
            log.info("用户执行登录");
            cacheUserInfo = CacheUserInfo.build()
                    .setUserId(session.getId())
                    .setStoreId(10101010)
                    .setRoleId("测试");
            redisUtils.set(session.getId(), cacheUserInfo);
        }

        //存储当前上下文相关信息
        BaseContextHandler.setUserID(cacheUserInfo.getUserId());
        BaseContextHandler.setStoreId(cacheUserInfo.getStoreId());
        BaseContextHandler.setUserName(cacheUserInfo.getUserName());

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清楚本地上下文相关信息
        BaseContextHandler.clean();
        super.afterCompletion(request, response, handler, ex);
    }
}
