package com.uniaccounts.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/** Compte etudiant. Objet immuable construit uniquement via le Builder. */
public class StudentAccount {
    private static final Set<String> LEVELS = Set.of("L1", "L2", "L3", "M1", "M2");

    private final String id;
    private final String fullName;
    private final String email;
    private final String phone;
    private final String level;
    private final String major;
    private final boolean scholarship;
    private final List<String> courses;

    private StudentAccount(Builder b) {
        this.id = b.id; this.fullName = b.fullName; this.email = b.email;
        this.phone = b.phone; this.level = b.level; this.major = b.major;
        this.scholarship = b.scholarship;
        this.courses = Collections.unmodifiableList(new ArrayList<>(b.courses));
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLevel() { return level; }
    public String getMajor() { return major; }
    public boolean hasScholarship() { return scholarship; }
    public List<String> getCourses() { return courses; }

    @Override
    public String toString() {
        return "StudentAccount{id=" + id + ", name=" + fullName + ", email=" + email
                + ", phone=" + phone + ", level=" + level + ", major=" + major
                + ", scholarship=" + scholarship + ", courses=" + courses + "}";
    }

    // ===================== Pattern: BUILDER (role: ConcreteBuilder) =====================
    // Probleme resolu : constructeur a 8 parametres illisible / erreurs d'ordre.
    public static class Builder {
        // obligatoires
        private final String id, fullName, email;
        // optionnels (valeurs par defaut)
        private String phone = null;
        private String level = "L1";
        private String major;
        private boolean scholarship = false;
        private final List<String> courses = new ArrayList<>();

        public Builder(String id, String fullName, String email) {
            this.id = id; this.fullName = fullName; this.email = email;
        }
        public Builder phone(String v) { this.phone = v; return this; }
        public Builder level(String v) { this.level = v; return this; }
        public Builder major(String v) { this.major = v; return this; }
        public Builder scholarship(boolean v) { this.scholarship = v; return this; }
        public Builder addCourse(String c) { this.courses.add(c); return this; }
        public Builder courses(List<String> list) { this.courses.clear(); this.courses.addAll(list); return this; }

        /** Validation centralisee : on ne peut pas creer un compte invalide. */
        public StudentAccount build() {
            if (id == null || id.isBlank()) throw new IllegalArgumentException("id obligatoire et non vide");
            if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("fullName obligatoire");
            if (email == null || !email.contains("@")) throw new IllegalArgumentException("email invalide (doit contenir @)");
            if (!LEVELS.contains(level)) throw new IllegalArgumentException("level invalide : " + level);
            return new StudentAccount(this);
        }
    }
}
