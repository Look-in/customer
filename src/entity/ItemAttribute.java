package entity;

/**
 * Invoking methods of classes that extends Item with attributes
 * from request.getParameters
 * Can use Commons BeanUtils
 *
 * @author Serg Shankunas
 */

import entity.event.Item;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ItemAttribute {

    private static ItemAttribute instance;

    public static ItemAttribute getInstance() {
        if (instance == null) {
            instance = new ItemAttribute();
        }
        return instance;
    }


    private Class[] params = {int.class,float.class,String.class};

    public ItemAttribute() {
    }

    private String upperCaseFirst(String value) {
        char[] array = value.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }

    public void setItemAttributes(Item item, Map<String, String[]> attributes) {
        String str;
        Method m;
        Object attribute;
        for (Map.Entry<String, String[]> entry : attributes.entrySet()) {
            for (Class param : params) {
                try {
                    m = item.getClass().getMethod(String.format("set%s", upperCaseFirst(entry.getKey())), param);
                    str = entry.getValue()[0];
                    if (str != null && !str.isEmpty()) {
                        if (param == int.class) attribute = Integer.valueOf(str);
                        else if (param == float.class) attribute = Float.valueOf(str);
                        else attribute = str;
                        m.invoke(item, attribute);
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                }
            }
        }
    }
}
