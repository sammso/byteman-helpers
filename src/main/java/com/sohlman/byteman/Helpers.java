package com.sohlman.byteman;

import org.jboss.byteman.rule.Rule;
import org.jboss.byteman.rule.helper.Helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Helpers extends Helper {
    protected Helpers(Rule rule) {
        super(rule);
    }

    public static String cookiesToJson(Object[] objects) {
        if (objects == null) {
            return "<null>";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        try {
            Method getName =null;
            Method getValue = null;
            Method getPath = null;
            boolean first=true;

            for (Object object : objects) {
                Class cls = object.getClass();
                Method[] methods = cls.getMethods();
                int i = 0;
                for (Method method : methods) {
                    //System.out.println(method.getName());
                    if (method.getName().equals(GET_NAME)) i++;
                    if (method.getName().equals(GET_VALUE)) i++;
                    if (method.getName().equals(GET_PATH)) i++;
                }
                if (i == 3) {
                    if( getName==null) getName = cls.getDeclaredMethod(GET_NAME);
                    if( getValue==null) getValue = cls.getDeclaredMethod(GET_VALUE);
                    if( getPath==null) getPath = cls.getDeclaredMethod(GET_PATH);
                    if (!first) sb.append(",");
                    sb.append("{'").append(getName.invoke(object)).append("'");
                    sb.append(":'").append(getValue.invoke(object)).append("',");
                    sb.append("path:'").append(getPath.invoke(object)).append("'");
                    sb.append("}");
                    first=false;
                }

            }
        } catch (NoSuchMethodException e) {
            // Ignore
        } catch (IllegalAccessException e) {
            // Ignore
        } catch (InvocationTargetException e) {
            // Ignore
        }
        sb.append("]");
        return sb.toString();
    }

    public static String GET_NAME = "getName";
    public static String GET_VALUE = "getValue";
    public static String GET_PATH = "getPath";
}
