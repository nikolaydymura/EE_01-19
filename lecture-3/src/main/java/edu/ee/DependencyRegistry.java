package edu.ee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DependencyRegistry {

  private final Map<String, Object> dependencies =
          new HashMap<>();

  public DependencyRegistry(Properties properties) {
    Set<Map.Entry<Object, Object>> entries = properties.entrySet();
    //create objects
    for (Map.Entry<Object, Object> prop : entries) {
      String key = (String) prop.getKey();
      if(key.contains(".")){
        continue;
      }
      String className = (String) prop.getValue();

      Object object = createObject(className);
      dependencies.put(key, object);
    }
    //set fields
    for (Map.Entry<Object, Object> prop : entries) {
      String key = (String) prop.getKey();
      Object value = prop.getValue();
      if(!key.contains(".")){
        continue;
      }
      String [] parts = key.split("[.]");
      String objKey = parts[0];
      String propertyKey = parts[1];

      if(propertyKey.endsWith("-ref")){
        propertyKey = propertyKey.substring(0, propertyKey.length() - 4);
        value = dependencies.get(propertyKey);
      }
      Object obj = dependencies.get(objKey);
      setProperty(obj, propertyKey, value);
    }
  }

  public <T> T getDependency(String key) {
    return (T) dependencies.get(key);
  }

  private Object createObject(String className) {
    try {
      Class<?> aClass = Class.forName(className);
      Object obj = aClass.newInstance();
      return obj;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private void setProperty(Object obj, String key, Object value){
    String methodName = "set" + key;
    Method[] methods = obj.getClass().getMethods();
    Optional<Method> method = Arrays.stream(methods)
            .filter((m) -> m.getName().toLowerCase().contains(methodName))
            .findFirst();

    Method m = method.get();
    try {
      m.invoke(obj, value);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
