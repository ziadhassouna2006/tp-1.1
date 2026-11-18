package com.uniaccounts;

import com.uniaccounts.audit.AuditLogger;
import com.uniaccounts.model.StudentAccount;
import com.uniaccounts.prototype.AccountTemplate;
import com.uniaccounts.repository.AccountRepository;
import java.util.List;

/** Demonstration du Travail 1 : Builder + Singleton + Prototype. */
public class Main {
    public static void main(String[] args) {
        AccountRepository repo = AccountRepository.getInstance();
        AuditLogger log = AuditLogger.getInstance();

        System.out.println("=== 1) BUILDER ===");
        StudentAccount a1 = new StudentAccount.Builder("20240001", "Ali Ben", "ali@univ.dz")
                .phone("0555000000").level("L2").major("Informatique")
                .scholarship(true).addCourse("POO").addCourse("BD").build();
        repo.add(a1); log.log("CREATE " + a1.getId());
        System.out.println(a1);

        System.out.println("\n--- Validation (cas invalides) ---");
        tryBuild(() -> new StudentAccount.Builder("", "X", "x@u.dz").build());
        tryBuild(() -> new StudentAccount.Builder("1", "X", "sans-arobase").build());
        tryBuild(() -> new StudentAccount.Builder("2", "X", "x@u.dz").level("L9").build());

        System.out.println("\n=== 2) SINGLETON ===");
        System.out.println("Meme depot ? " + (repo == AccountRepository.getInstance()));
        System.out.println("Meme journal ? " + (log == AuditLogger.getInstance()));
        try { repo.add(a1); } catch (IllegalStateException e) { System.out.println("Doublon refuse : " + e.getMessage()); }

        System.out.println("\n=== 3) PROTOTYPE ===");
        AccountTemplate promoL3 = new AccountTemplate("L3", "Informatique", false, List.of("POO", "Reseaux", "IA"));
        AccountTemplate copie = promoL3.clone().addCourse("Design Patterns"); // la copie ne touche pas l'original
        StudentAccount s1 = promoL3.createAccount("20240101", "Nadia Ouali", "nadia@univ.dz");
        StudentAccount s2 = promoL3.createAccount("20240102", "Rida Benbordi", "rida@univ.dz");
        StudentAccount s3 = copie.createAccount("20240103", "Hoda Triqui", "hoda@univ.dz");
        for (StudentAccount s : List.of(s1, s2, s3)) { repo.add(s); log.log("CREATE (template) " + s.getId()); System.out.println(s); }

        System.out.println("\n=== Depot : " + repo.count() + " comptes | Journal d'audit ===");
        log.printAll();
    }

    private static void tryBuild(Runnable r) {
        try { r.run(); } catch (IllegalArgumentException e) { System.out.println("Refuse : " + e.getMessage()); }
    }
}
