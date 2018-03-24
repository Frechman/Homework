package ru.sberbank.jschool.homework.gavrilov;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        EncryptedClassLoader clLoad =
                new EncryptedClassLoader(
                        "F:\\Projects\\sber\\HomeworkGavrilov\\hw05\\src\\main\\java\\",
                        10, Main.class.getClassLoader());
        try {
            Class<?> clazz = clLoad.findClass("ru.sberbank.jschool.homework.gavrilov.Test");
            Object ob = clazz.newInstance();
            clazz.getMethod("print").invoke(ob);
        } catch (ClassNotFoundException e) {
            System.out.println("not Found!");
            e.printStackTrace();
        } catch (NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
