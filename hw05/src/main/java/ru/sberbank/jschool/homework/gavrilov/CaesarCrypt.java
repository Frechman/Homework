package main.java.ru.sberbank.jschool.homework.gavrilov;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CaesarCrypt {

    public static void cryptClass(String pathToClass, int offset) {

        try (FileOutputStream fos = new FileOutputStream(pathToClass + ".caesar")) {

            byte[] bytes = Files.readAllBytes(Paths.get(pathToClass));
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] += offset;
            }
            fos.write(bytes);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        cryptClass("F:\\Projects\\sber\\HomeworkGavrilov\\hw05\\src\\main\\java\\ru\\sberbank\\jschool\\homework\\gavrilov\\Test.class", 10);

    }
}
