package com.djad.mestestdata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class DataRegister {
    private static DataRegister instance;

    public List<DataItem> register;

    private DataRegister() {
        register = new ArrayList<>();
    }

    public static DataRegister getInstance() {
        instance = instance == null ? new DataRegister() : instance;
        return instance;
    }

    public void clearRegister() {
        this.register.clear();
    }

    public void addItems(DataItem... dataItems) {
        for (DataItem item : dataItems) {
            this.register.add(item);
        }
    }

    public void addItems(String type, String... values) {
        for (String value : values) {
            this.register.add(new DataItem(type, value));
        }
    }

    public Stream<DataItem> getDataStream() {
        return this.register.stream();
    }

    public List<String> getAllValuesByType(String type) {
        List<String> result = new ArrayList<>();
        this.getDataStream().filter(item -> item.getType().equals(type)).
            forEach(item -> {
                result.add(item.getValue());
            });
        return result;
    }
}
