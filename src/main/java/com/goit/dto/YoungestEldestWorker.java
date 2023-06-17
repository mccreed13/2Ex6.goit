package com.goit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YoungestEldestWorker {
    private Type type;
    private String name;
    private String birthday;

    public YoungestEldestWorker(Type type, String name, String birthday) {
        this.type = type;
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "YoungestEldestWorker{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
