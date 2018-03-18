package ru.sberbank.jschool.homework.classloaders;

public class Main {

    public static void main(String[] args) {
        PluginManager manager =
                new PluginManager("F:/Projects/sber/HomeworkGavrilov/hw05/src/main/java/ru/sberbank/jschool/homework");

        try {
            Plugin my = manager.loadPlugin("classloaders");
            my.run(new String[]{"1", "2", "3"});
        } catch (PluginNotFoundException e) {
            e.printStackTrace();
        }
    }
}