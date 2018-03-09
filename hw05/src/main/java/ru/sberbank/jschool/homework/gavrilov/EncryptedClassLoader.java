package main.java.ru.sberbank.jschool.homework.gavrilov;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EncryptedClassLoader extends ClassLoader {


    private String folder;
    private int offset;

    public EncryptedClassLoader(String folder, int offset, ClassLoader parent) {
        super(parent);
        this.folder = folder;
        this.offset = offset;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte[] b = getDecryptBytes(className);
            return defineClass(className, b, 0, b.length);
        } catch (IOException e) {
            return super.findClass(className);
        }
    }


    private byte[] getDecryptBytes(String binaryClassName) throws IOException {
        Path path = Paths.get(folder + binaryClassName.replace(".", File.separator) + ".class.caesar");
        byte[] result = new byte[0];
        try {
            byte[] bytes = Files.readAllBytes(path);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] -= offset;
            }
            result = bytes;
        } catch (IOException e) {
            System.out.println("Error path!");
            e.printStackTrace();
        }

        return result;
    }
}
