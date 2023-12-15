package ua.ithillel.reflect.proxy;

import lombok.RequiredArgsConstructor;
import ua.ithillel.reflect.anno.Cacheable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

@RequiredArgsConstructor
public class CacheProxy implements InvocationHandler {
    private final Object target;
    private Map<Object, Object> cache = new WeakHashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class) && args.length > 0) {
            final Object first = args[0];

            if (cache.containsKey(first)) {
                return cache.get(first);
            }

            final Object result = method.invoke(target, args);
            cache.put(first, result);

            return result;
        }

        return method.invoke(target, args);
    }
}
