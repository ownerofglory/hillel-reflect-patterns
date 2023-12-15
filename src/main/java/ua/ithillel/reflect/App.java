package ua.ithillel.reflect;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.reflect.anno.MyAnnotation;
import ua.ithillel.reflect.drink.client.CocktailDbClient;
import ua.ithillel.reflect.drink.client.DrinkClient;
import ua.ithillel.reflect.drink.manager.DefaultDrinkManager;
import ua.ithillel.reflect.drink.manager.DrinkManager;
import ua.ithillel.reflect.drink.model.Drink;
import ua.ithillel.reflect.pattern.Singleton;
import ua.ithillel.reflect.proxy.CacheProxy;
import ua.ithillel.reflect.vehicle.Car;
import ua.ithillel.reflect.vehicle.Truck;
import ua.ithillel.reflect.vehicle.Vehicle;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        try {
            final ExecutorService executorService = Executors.newFixedThreadPool(2);


            final Singleton instance = Singleton.INSTANCE;

            final HttpClient httpClient = HttpClient.newHttpClient();
            final ObjectMapper objectMapper = new ObjectMapper();

            DrinkClient client = new CocktailDbClient(httpClient, objectMapper);

            final CacheProxy cacheProxy = new CacheProxy(client);

            final DrinkClient clientProxy = (DrinkClient) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[]{
                    DrinkClient.class
            }, cacheProxy);

            DrinkManager manager = new DefaultDrinkManager(clientProxy);
            Drink margarita = manager.getDrinkByName("margarita");
            margarita = manager.getDrinkByName("margarita");


            Vehicle v = new Car();
            v.drive(12);


            final Class<?> carClass = Class.forName("ua.ithillel.reflect.vehicle.Car");

            Class<Truck> truckClass = Truck.class;

            Object vehObj = new Truck();
            Class<?> aClass = vehObj.getClass();

//            final Field[] fields = aClass.getFields();
            final Field[] fields = aClass.getDeclaredFields();
            for (Field field :
                    fields) {
                System.out.println(field.getName());
                // _ _ _ _ _ _ _ _
                //           0 0 0 1 0 0 1 0

                int modifiers = field.getModifiers(); // binary mask
                System.out.println("is final: " + Modifier.isFinal(modifiers));
                System.out.println("is private: " + Modifier.isPrivate(modifiers));
                System.out.println("is protected: " + Modifier.isProtected(modifiers));
                System.out.println("is static: " + Modifier.isStatic(modifiers));
            }

            final Method[] methods = aClass.getDeclaredMethods();
            for (Method method :
                    methods) {


                final int parameterCount = method.getParameterCount();
                final Class<?>[] parameterTypes = method.getParameterTypes();

                System.out.println(method.getName() + "( " + Arrays.toString(parameterTypes) + ")");

                method.getModifiers();
            }

            final Method methodCheck = aClass.getDeclaredMethod("check");
            final Method drive = aClass.getDeclaredMethod("drive", int.class);

            final Annotation[] annotations = drive.getAnnotations();

            final MyAnnotation annotation = drive.getAnnotation(MyAnnotation.class);
            if (annotation != null) {
                final int driveDistance = annotation.driveDistance();
                drive.invoke(vehObj, driveDistance);
            }


            final Class<?> returnType = drive.getReturnType();

            methodCheck.setAccessible(true);
            methodCheck.invoke(vehObj);
            drive.invoke(vehObj, 200);

            final Constructor<?> defaultConstructor = aClass.getConstructor();
            final Object newObj = defaultConstructor.newInstance();
            if (newObj instanceof  Truck) {
                Truck newTruck = (Truck) newObj;
            }
            

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
