package io.regna.runtime.core;

import io.regna.rt.Core;
import io.regna.runtime.annotations.InDev;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import io.regna.log.Logger;

@InDev(plans = "further development to load classes", safe = true)
public class ReflectiveLoader {

    static final String TAG = ReflectiveLoader.class.getCanonicalName();

    File class_dir;
    ClassLoader loader;
    //static final Logger LOG = Logger.getLogger(ReflectiveLoader.class.getName());

    public ReflectiveLoader(String file){
        class_dir = new File(file);
        URL[] urls = new URL[0];
        try {
            urls = new URL[]{class_dir.getCanonicalFile().toURI().toURL()};
            System.out.println(Arrays.toString(urls));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loader = new URLClassLoader(urls);
    }

    public ReflectiveLoader(URL... url){
        loader = new URLClassLoader(url);
    }

    public void runProgram(String[] args, String classname){
        try {
            Class clazz = loader.loadClass(classname);
            Method main = clazz.getMethod("main", String[].class);
            main.invoke(null, (Object) args);
        } catch (ClassNotFoundException e) {
            //LOG.severe("Unable to find or load Program " + classname);
            Logger.runtime.fatal(TAG, "Unable to find or load program " + classname);
        } catch (NoSuchMethodException e) {
            //LOG.severe("No main method found, therefore the program is not executable");
            Logger.runtime.fatal(TAG, "No main method found, therefore the program is not executable");
        } catch (IllegalAccessException e) {
            //LOG.severe("Severe Internal Error " + e.getLocalizedMessage());
            Logger.runtime.fatal(TAG, "Fatal Internal Excxeption ", e);
        } catch (InvocationTargetException e) {
            Core.sysLog().fatal(TAG, "Internal Exception", e);
            //LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

}
