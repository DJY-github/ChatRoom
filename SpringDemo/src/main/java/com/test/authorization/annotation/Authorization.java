package com.test.authorization.annotation;

import java.lang.annotation.*;

/**
 * ��Controller�ķ�����ʹ�ô�ע�⣬�÷�����ӳ��ʱ�����û��Ƿ��¼��δ��¼����401����
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

}
