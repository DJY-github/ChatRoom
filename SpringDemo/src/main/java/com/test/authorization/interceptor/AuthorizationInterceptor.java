package com.test.authorization.interceptor;

import com.test.authorization.annotation.Authorization;
import com.test.authorization.manager.TokenManager;
import com.test.authorization.model.TokenModel;
import com.test.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * ���������ж������Ƿ���Ȩ��
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // ��ȡtoken
        String authorization = request.getHeader(Constants.AUTHORIZATION);

        // ��֤token
        TokenModel model = manager.getToken(authorization);
        if (manager.checkToken(model)) {
            //���token��֤�ɹ�����token��Ӧ���û�������request�У�����֮��ע��
            request.setAttribute(Constants.CURRENT_USER_NAME, model.getUsername());
            return true;
        }

        // ��֤ʧ�ܣ� ����401����
        if (method.getAnnotation(Authorization.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
