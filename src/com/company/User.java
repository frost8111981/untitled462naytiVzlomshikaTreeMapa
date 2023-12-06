package com.company;

public class User {
    private String ip;
    private int id;
    private String fio;

    private String address;

    public User(String ip, int id, String fio, String address) {
        this.ip = ip;
        this.id = id;
        this.fio = fio;
        this.address = address;
    }

    public User(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getAddress() {
        return address;
    }

//    @Override
//    public String toString() {
//        return String.format("ip: %s, id: %s, ФИО: %s, адрес: %s", ip, id, fio, address);
//    }

    @Override
    public String toString() {
        return  ip + " " + id + " " + fio + " " + address;
    }


}
