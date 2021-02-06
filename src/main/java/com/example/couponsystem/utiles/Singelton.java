package com.example.couponsystem.utiles;

import java.lang.reflect.Constructor;

public final class Singelton<T>
{
    private static Object instance = null;

    private Singelton()
    {

    }

    public static <T> T getInstance()
    {
        if(instance == null)
        {
            synchronized(Singelton.class)
            {
                if(instance == null)
                {
                    Constructor<?>[] constructors = Singelton.class.getConstructors();
                    for(Constructor<?> constructor :constructors)
                    {
                        if(constructor.getParameterCount() == 0)
                        {
                            try
                            {
                                instance = (T)constructor.newInstance(null);

                            }
                            catch(Exception exception)
                            {
                                System.out.println("Cant instantiate singleton");
                            }
                        }
                    }
                }
            }
        }

        return (T) instance;
    }
}
