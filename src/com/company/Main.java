package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Математический словарь\n");
        Scanner in=new Scanner(System.in);
        try {
            FileReader file=new FileReader("vocabulary.txt");
            Scanner vocablScan=new Scanner(file);
            Hash hash=new Hash();
            while (vocablScan.hasNext()){
                String str=vocablScan.nextLine();
                hash.put(str,vocablScan.nextLine());
            }
            file.close();
            int action = 0;
            do {
                do {
                    System.out.println("Сделайте выбор.\n0-выйти;\n1-просмотреть ключи;\n2-просмотреть;\n3-добавить/изменить;\n4-удалить;\n5-найти");
                    while (!in.hasNextInt())
                        in.nextLine();
                    action=in.nextInt();
                    in.nextLine();
                } while (action > 5);
                switch (action){
                    case 1: {
                        int i=1;
                        LinkedList<Element> list=hash.showAll();
                        for (Element x :
                                list) {
                            System.out.println(x.getKey());
                        }
                        break;
                    }
                    case 2: {
                        LinkedList<Element> list=hash.showAll();
                        for (Element x :
                                list) {
                            System.out.println();
                            System.out.print(x.getKey()+" -- ");
                            System.out.println(x.getValue());
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("Введите термин  ");
                        String key=in.nextLine();
                        System.out.println("Введите описание  ");
                        String val=in.nextLine();
                        hash.put(key,val);
                        break;
                    }
                    case 4: {
                        System.out.print("Введите термин  ");
                        String key=in.nextLine();
                        if (hash.remove(key))
                            System.out.println("Успешно удалено.");
                        else System.out.println("Ошибка.Термина не существовало.");
                        break;
                    }
                    case 5: {

                        System.out.print("Введите термин  ");
                        String key=in.nextLine();
                        if (hash.hasKey(key))
                            System.out.println(hash.getValue(key).getValue());
                        else System.out.println("Ошибка. Такого термина нет.");
                        break;
                    }
                }
            }while (action!=0);
            System.out.println("Хотите сохранить изменеия 1-да");
            action=in.hasNextInt()?in.nextInt():0;
            if (action==1){
                FileWriter filewr=new FileWriter("vocabulary.txt");
                LinkedList<Element> list=hash.showAll();
                for (Element x :
                        list) {
                    filewr.write(x.getKey()+"\n"+x.getValue()+"\n");
                }
                filewr.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Отсутствует словарь");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
