package com.example.couponsystem.utiles;

import java.lang.reflect.Constructor;

public final class Singleton<T extends Object>
{
    private static Object instance;

    private Singleton()
    {

    }

    public static <T> T getInstance(Class<T> c)
    {
        if(instance == null)
        {
            synchronized(c)
            {
                if(instance == null)
                {
                    try
                    {
                        Constructor[] constructors =  c.getDeclaredConstructors();
                        for(Constructor constructor : constructors)
                        {
                            constructor.setAccessible(true);
                            if(constructor.getParameterCount() == 0)
                            {
                                instance = constructor.newInstance(null);
                            }
                        }
                    }
                    catch(Exception exception)
                    {
                        System.out.println("Can't instantiate singleton, maybe because the class don't has private parameterless constructor");
                    }
                }
            }
        }

        return (T) instance;
    }
}
