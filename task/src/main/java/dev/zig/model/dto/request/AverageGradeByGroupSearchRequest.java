package dev.zig.model.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AverageGradeByGroupSearchRequest {

    String number;

}
