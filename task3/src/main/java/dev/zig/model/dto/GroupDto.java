package dev.zig.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GroupDto {

    String number;

    public static GroupDto from(String csvLine) {
        String[] data = csvLine.split(";");
        return GroupDto.builder()
                .number(data[3])
                .build();
    }
}
