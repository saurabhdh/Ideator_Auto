/*
 * Copyright (c) 2013-2015 MicroPact, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of MicroPact, Inc.
 * Use is subject to license terms.
 */
package com.ideator.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

/**
 * @author MicroPact, Inc.
 */
public class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static void sendText(final String txt, Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Class<?> type = field.getType();
            if (type == WebElement.class) {
                WebElement value = (WebElement) field.get(obj);
                value.clear();
                value.sendKeys(txt);
            }
        } catch (NoSuchFieldException e) {
            try {
                Method method = obj.getClass().getMethod(
                        "set" + StringUtils.capitalize(fieldName), String.class);
                method.setAccessible(true);
                method.invoke(obj, txt);
            } catch (Exception e1) {
                throw new AssertionError("unable to set " + fieldName, e1);
            }
        } catch (Exception e) {
            throw new AssertionError("unable to set " + fieldName, e);
        }
    }
}
