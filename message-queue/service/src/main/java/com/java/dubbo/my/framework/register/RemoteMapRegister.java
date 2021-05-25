package com.java.dubbo.my.framework.register;

import com.java.dubbo.my.framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过缓存到本地文件方式实现进程之间共享数据
 *
 * @author xuweizhi
 * @since 2021/05/22
 */
@SuppressWarnings("all")
public class RemoteMapRegister {

    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void regist(String interfaceName, URL url) {
        List<URL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();

        }
        list.add(url);
        REGISTER.put(interfaceName, list);
        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        REGISTER = getFile();
        List<URL> list = REGISTER.get(interfaceName);
        return list;
    }


    private static void saveFile() {
        try {
            File file = new File("target");
            if(!file.exists()){
                file.mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream("target/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("target/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
