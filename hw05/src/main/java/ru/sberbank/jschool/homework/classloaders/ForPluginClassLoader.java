package ru.sberbank.jschool.homework.classloaders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ForPluginClassLoader extends ClassLoader {

    private final String pathToPlugin;

    private final HashMap<String, Class<?>> cacheClasses = new HashMap<>();

    public ForPluginClassLoader(ClassLoader parent, String pathToPlugin) {
        super(parent);
        this.pathToPlugin = pathToPlugin;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> result;
        try {
            if (cacheClasses.containsKey(name)) {
                result = cacheClasses.get(name);
            } else {
                result = findClass(name);
                cacheClasses.put(name, result);
            }
        } catch (ClassNotFoundException e) {
            return super.loadClass(name);
        }
        return result;
    }

    @Override
    protected Class<?> findClass(String binaryClassName) throws ClassNotFoundException {
        Path path = Paths.get(pathToPlugin + File.separator
                + binaryClassName.replace(".", File.separator) + ".class");
        try {
            byte[] bytes = Files.readAllBytes(path);
            return defineClass(null, bytes, 0, bytes.length);
        } catch (IOException e) {
            return super.findClass(binaryClassName);
        }
    }
}
