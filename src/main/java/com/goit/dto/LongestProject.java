package com.goit.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LongestProject {
    private int id;
    private int durationInMonth;

    public LongestProject(int id, int durationInMonth) {
        this.id = id;
        this.durationInMonth = durationInMonth;
    }

    @Override
    public String toString() {
        return "LongestProject{" +
                "id=" + id +
                ", durationInMonth=" + durationInMonth +
                '}';
    }
}
