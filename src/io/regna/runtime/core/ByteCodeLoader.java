package io.regna.runtime.core;

import io.regna.runtime.annotations.Beta;
import javassist.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

@Deprecated
@Beta(reason = "The Bytecode Loader is unnecessary", futurePlans = "Deletion", deprecated = true)
public class ByteCodeLoader {

    ClassPool cp;
    static final Logger LOG = Logger.getLogger(ByteCodeLoader.class.getName());

    public ByteCodeLoader(String file){
        cp = ClassPool.getDefault();
        try {
            cp.insertClassPath(new File(file).getParent());
        } catch (NotFoundException e) {
            LOG.info("Unable to find ClassPath");
        }
    }

    public void runProgram(String[] args, String classname){
        try {
            CtClass clazz = cp.get(classname);
            Method method = clazz.toClass().getMethod("main", String[].class);
            method.invoke(args);
        } catch (NotFoundException e) {
            LOG.severe("Class Not Found!");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
