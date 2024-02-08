package org.ag_syssoft;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private static void findAnnotation () throws NoSuchFieldException, IllegalAccessException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Field f = ClassLoader.class.getDeclaredField("classes");
        f.setAccessible(true);
        List<Class> classes = (List<Class>) f.get(cl);
    }
    public static void sewSimulation() {
        try {
            findAnnotation();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
