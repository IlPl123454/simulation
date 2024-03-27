package plenkovii.action;

import plenkovii.Map;
import plenkovii.entity.Entity;

import java.lang.reflect.InvocationTargetException;

public abstract class Action {
    public abstract void perform(Map map, Class<? extends Entity> targetClass)
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException;
}
