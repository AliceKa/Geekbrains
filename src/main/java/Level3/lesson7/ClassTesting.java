package Level3.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClassTesting {
    public static void start(Object classExample) throws RuntimeException, InvocationTargetException, IllegalAccessException {
        try {
            List<Method> listBS = Arrays.stream(classExample.getClass().getDeclaredMethods())
                    .filter(m -> m.getAnnotation(BeforeSuite.class) != null)
                    .collect(Collectors.toList());

            List<Method> listAS = Arrays.stream(classExample.getClass().getDeclaredMethods())
                    .filter(m -> m.getAnnotation(AfterSuite.class) != null)
                    .collect(Collectors.toList());

            List<Method> listTestSorted = Arrays.stream(classExample.getClass().getDeclaredMethods())
                    .filter(m -> m.getAnnotation(Test.class) != null)
                    .sorted(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()))
                    .collect(Collectors.toList());

            if (listBS.size() != 1 || listAS.size() != 1) {
                throw new RuntimeException();
            }
            for (Method m : listBS) {
                m.invoke(classExample, null);
            }
            for (Method m : listTestSorted) {
                m.invoke(classExample, null);
            }
            for (Method m : listAS) {
                m.invoke(classExample, null);
            }
        } catch (NullPointerException ignored) {
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        // Either ClassExample type
        ClassExample classExample = new ClassExample();
        start(classExample);
        // Or an Object type
        Object classExample1 = new ClassExample();
        start(classExample1);
    }
}
