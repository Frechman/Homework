package ru.sberbank.jschool.homework.classloaders;

public class MyPlugin implements Plugin {

    @Override
    public void run(String[] urls) {
        for (String url : urls) {
            System.out.println(url);
        }
    }
}
