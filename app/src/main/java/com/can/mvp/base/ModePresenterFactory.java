package com.can.mvp.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by can on 2018/3/2.
 */

public class ModePresenterFactory {

    private static ModePresenterFactory mModePresenterManager = new ModePresenterFactory();


    public static ModePresenterFactory getInstance() {
        return mModePresenterManager;
    }


    public Object getPresenter(Class mClass, int i) {

        return getInstance(getMPclazz(mClass, i));
    }

    private Class getMPclazz(Class mClass, int i) {
        Type genericSuperclass = mClass.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType mParameterizedType = (ParameterizedType) genericSuperclass;
            Type[] typeArguments = mParameterizedType.getActualTypeArguments();
            return (Class) typeArguments[i];
        }
        return null;
    }

    private Object getInstance(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object getMode(Class mClass, int i) {
        return getInstance(getMPclazz(mClass, i));
    }
}
