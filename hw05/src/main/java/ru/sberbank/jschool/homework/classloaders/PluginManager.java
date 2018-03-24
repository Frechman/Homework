package ru.sberbank.jschool.homework.classloaders;

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.Arrays;
import java.util.Objects;

public class PluginManager {

    // directory that contains plugin folders
    private final String rootDirectory;

    public PluginManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    /**
     * method takes as a parameter a folder name in the root plugin directory,
     * loads the plugin .class file from the folder if present,
     * and returns a Plugin object
     *
     * @param pluginName - name of the plugin folder
     * @return Plugin
     * @throws PluginNotFoundException - when folder named 'pluginName' is missing,
     *                                   or it contains no .class files
     */
    public Plugin loadPlugin(String pluginName) throws PluginNotFoundException {
        String pathToPlugin = rootDirectory + File.separator + pluginName;
        try {
            ForPluginClassLoader pcl =
                    new ForPluginClassLoader(Plugin.class.getClassLoader(), pathToPlugin);
            File[] files = getClassFiles(pathToPlugin);
            return (Plugin) pcl.loadClass(files[0].getName().replace(".class", "")).newInstance();
        } catch (Exception e) {
            throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
        }
    }

    private File[] getClassFiles(String directory) throws NotDirectoryException {
        File dir = new File(directory);
        if (!dir.isDirectory()) {
            throw new NotDirectoryException("The directory not exist");
        }
        return Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                .filter(file -> file.getName().endsWith(".class"))
                .toArray(File[]::new);
    }
}