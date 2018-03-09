package main.java.ru.sberbank.jschool.homework.gavrilov;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        EncryptedClassLoader clLoad =
                new EncryptedClassLoader(
                        "F:\\Projects\\sber\\HomeworkGavrilov\\hw05\\src\\", 10, Main.class.getClassLoader());
        try {
            Class<?> clazz = clLoad.findClass("main.java.ru.sberbank.jschool.homework.gavrilov.Test");
            Object ob = clazz.newInstance();
            clazz.getMethod("print").invoke(ob);
        } catch (ClassNotFoundException e) {
            System.out.println("not Found!");
            e.printStackTrace();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
