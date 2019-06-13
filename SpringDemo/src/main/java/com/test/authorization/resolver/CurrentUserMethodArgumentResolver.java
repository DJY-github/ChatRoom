package com.test.authorization.resolver;

import com.test.authorization.annotation.CurrentUser;
import com.test.config.Constants;
import com.test.model.UserModel;
import com.test.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * ����ע�룬������CurrentUserע��ķ�������ע�뵱ǰ��¼�û�
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // ���������UserModel ������CurrentUserע��
        if (parameter.getParameterType().isAssignableFrom(UserModel.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // ȡ����Ȩʱ����ĵ�¼�û���
        String currentUserName = (String)webRequest.getAttribute(Constants.CURRENT_USER_NAME, RequestAttributes.SCOPE_REQUEST);
        if (currentUserName != null) {
            return userService.findUserByName(currentUserName);
        }
        throw new MissingServletRequestPartException(Constants.CURRENT_USER_NAME);
    }
}
