package com.test.authorization.annotation;

import java.lang.annotation.*;

/**
 * ��Controller�ķ���������ʹ�ô�ע�⣬�÷�����ӳ��ʱ��ע�뵱ǰ��¼��User����
 */
@Documented
@Inherited
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
