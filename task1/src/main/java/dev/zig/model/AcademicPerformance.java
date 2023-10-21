package dev.zig.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AcademicPerformance {

    private Integer physics;
    private Integer mathematics;
    private Integer rus;
    private Integer literature;
    private Integer geometry;
    private Integer informatics;
    private Double averageGrade;

    public static AcademicPerformance from(String[] csvArray) {
        AcademicPerformance obj = new AcademicPerformance();
        obj.setPhysics(Integer.valueOf(csvArray[4]));
        obj.setMathematics(Integer.valueOf(csvArray[5]));
        obj.setRus(Integer.valueOf(csvArray[6]));
        obj.setLiterature(Integer.valueOf(csvArray[7]));
        obj.setGeometry(Integer.valueOf(csvArray[8]));
        obj.setInformatics(Integer.valueOf(csvArray[9]));
        obj.setAverageGrade((double) (obj.getPhysics() + obj.getMathematics() + obj.getRus() + obj.getLiterature() + obj.getGeometry() + obj.getInformatics()) / 6);
        return obj;
    }
}
