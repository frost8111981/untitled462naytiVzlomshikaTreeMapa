package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File usersDB = new File("C:\\Users\\timur.yuldashev\\Desktop\\П\\JAVA\\JAVA и Android Ярик\\3.Основы Java\\java-homeworks-master\\java-homeworks-master\\4.6.1 - 4 in-memory-treemap\\4.6.2/users.db");
        Scanner scanDB = new Scanner(usersDB);
        Map<Integer, Set<String>> idMap = new HashMap<>(); // для айди и айпи
        Map<Integer, User> usersMap = new HashMap<>(); // для айди и юзера

        scanDB.nextLine(); // пропускаем заголовок, потому что у нас под данные одни переменные а в заголовке другие.

        while (scanDB.hasNextLine()) { //Построчно читаем файл в цикле, пока файл не закончится
            String line = scanDB.nextLine();
            String[] part = line.split(";");
            String ip = part[0];
            int id = Integer.parseInt(part[1]);
            String fio = part[2];
            String address = part[3];
            User user = new User(ip, id, fio, address);
            usersMap.put(id, user);

            if (idMap.containsKey(user.getId())) {  // Проверяем добавлен ли такой айди
                Set<String> ipSet = idMap.get(user.getId()); // Код позволяет получить множество IP-адресов, привязанных к идентификатору пользователя user.getId()
                ipSet.add(user.getIp()); // Если добавлен такой айди то него же добавляем айпишник
            } else {
                Set<String> ipSet = new HashSet<>(); // Если нет то создаем пустой сет и добавляем
                ipSet.add(user.getIp());
                idMap.put(user.getId(), ipSet); // В итоговую мапу добавляем просто айди и его айпи
            }
        }
        scanDB.close(); //Закрываем scanner после чтения, для освобождения файла и ресурсов
        for (Map.Entry<Integer, Set<String>> entrySet : idMap.entrySet()) {
            System.out.println(entrySet);
        }
        System.out.println("-----------------------------------------------------------------------------");


        // Находим количество одинаковых айпи
        File serverLog = new File("C:\\Users\\timur.yuldashev\\Desktop\\П\\JAVA\\JAVA и Android Ярик\\3.Основы Java\\java-homeworks-master\\java-homeworks-master\\4.6.1 - 4 in-memory-treemap\\4.6.2/server.log");
        Scanner scanLog = new Scanner(serverLog);
        Map<String, Integer> logMap = new HashMap<>();

        while (scanLog.hasNextLine()) {
            String line2 = scanLog.nextLine();
            String[] part2 = line2.split(":");

            if (logMap.containsKey(part2[0])) { // если находим похожий айпи в ключе logMap.get(part2[0])
                int count = logMap.get(part2[0]); // то если count = 0
                logMap.put(part2[0], count + 1); // увеличиваем на 1
            } else {
                logMap.put(part2[0], 1); // если нет то у ключа значение так и остается 1
            }
        }
        scanLog.close();

        // Вывод для себя
        for (Map.Entry<String, Integer> entrySet : logMap.entrySet()) {
            System.out.println(entrySet);
        }


        // Находим большее количество вхождений и находим сразу айпи его
        int ipMax = 0;
        String villainIp = ""; // Когда найдем максм кол.,то присвоим этой переменной айпи(ключ)
        for (Map.Entry<String, Integer> entryMaxIp : logMap.entrySet()) {
            if (ipMax < entryMaxIp.getValue()) {
                ipMax = entryMaxIp.getValue(); // находим макс кол айпи
                villainIp = entryMaxIp.getKey(); // присваиваем айпишник этого ключа
            }
        }
        System.out.println(ipMax); // для себя
        System.out.println(villainIp); // для себя


        // Находим айди по айпи villainIp
        int villainId = 0;
        for (Map.Entry<Integer, Set<String>> entryMap : idMap.entrySet()) {
            Set<String> values = entryMap.getValue(); // entry.getValue() возвращает значение, ассоциированное с ключом, представленным объектом entry. В данном случае, тип значения в нашем случае - Set<String>, то есть множество строк.             С помощью оператора = мы сохраняем это значение в переменную values с типом Set<String>, чтобы мы могли обращаться к нему и выполнять различные операции, такие как проверка наличия конкретного элемента в множестве.
            if (values.contains(villainIp)) {
                villainId = entryMap.getKey();
                break;
            }
        }
        System.out.println(villainId);

        if (usersMap.containsKey(villainId)) {
            User user = usersMap.get(villainId);
            System.out.println("Предполагаемый злоумышленник:");
            System.out.println("ФИО: " + user.getFio());
            System.out.println("Адрес: " + user.getAddress());
            System.out.println("Количество входов: " + ipMax);
        }
    }
}


//  Эта версия Set с User
//            if(ipMap.containsKey(user.getId())){  // Проверяем добавлен ли такой айди
//                Set<User> ipSet = ipMap.get(user.getId()); // Код позволяет получить множество IP-адресов, привязанных к идентификатору пользователя user.getId()
//                ipSet.add(user); // Если добавлен такой айди то него же добавляем айпишник
//            }else{
//                Set<User> ipSet = new HashSet<>(); // Если нет то создаем пустой сет и добавляем
//                ipSet.add(user);
//                ipMap.put(user.getId(),ipSet); // В итоговую мапу добавляем просто айди и его айпи
//            }
