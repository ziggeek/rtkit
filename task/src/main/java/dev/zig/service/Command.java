package dev.zig.service;

public interface Command {

    void execute(String option);
    void execute();

    default void printHelp() {
        System.out.println("\n Usage: java -jar App.jar <command> <option> [args]");
        System.out.println("Commands:");
        System.out.println("  help - Display help");
        System.out.println("  statistic <option> - Display statistics for an option");
        System.out.println("Options:");
        System.out.println("  --first - Cредние оценки по предметам в старших классах (10 и 11)");
        System.out.println("  --second - Cписок всех отличников старше 14 лет");
        System.out.println("  --third - Cредняя оценка ученика по указанной фамилии \n");
    }
}
