package com.uniaccounts.prototype;

import com.uniaccounts.model.StudentAccount;
import java.util.ArrayList;
import java.util.List;

/** Modele de compte pour une promotion (meme niveau, meme specialite, memes modules). */
// ===================== Pattern: PROTOTYPE (role: ConcretePrototype) =====================
// Probleme resolu : des centaines d'etudiants de la meme promo -> on clone le modele
// et on change seulement l'identite (id, nom, email).
// Sans Prototype : reconstruire chaque compte de zero (code repete, risque d'oubli).
public class AccountTemplate implements Cloneable {
    private String level;
    private String major;
    private boolean scholarship;
    private List<String> courses = new ArrayList<>();

    public AccountTemplate(String level, String major, boolean scholarship, List<String> courses) {
        this.level = level; this.major = major; this.scholarship = scholarship;
        this.courses = new ArrayList<>(courses);
    }

    /** Copie profonde (la liste des modules n'est pas partagee entre copies). */
    @Override
    public AccountTemplate clone() {
        try {
            AccountTemplate copy = (AccountTemplate) super.clone();
            copy.courses = new ArrayList<>(this.courses); // deep copy
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public AccountTemplate withScholarship(boolean s) { this.scholarship = s; return this; }
    public AccountTemplate addCourse(String c) { this.courses.add(c); return this; }

    /** Cree un compte a partir du modele : seule l'identite change. */
    public StudentAccount createAccount(String id, String fullName, String email) {
        return new StudentAccount.Builder(id, fullName, email)
                .level(level).major(major).scholarship(scholarship).courses(courses).build();
    }
}
