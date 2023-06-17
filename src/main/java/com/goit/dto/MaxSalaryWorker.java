package com.goit.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MaxSalaryWorker {
    private int id;
    private String name;
    private String birthday;
    private int salary;
    private String level;

    public MaxSalaryWorker(int id, String name, String birthday, String level, int salary) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.salary = salary;
        this.level = level;
    }

    @Override
    public String toString() {
        return "MaxSalaryWorker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", salary=" + salary +
                ", level='" + level + '\'' +
                '}';
    }
}
