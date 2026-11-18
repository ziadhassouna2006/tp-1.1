package com.uniaccounts.repository;

import com.uniaccounts.model.StudentAccount;
import java.util.*;

/** Depot central des comptes. */
// ===================== Pattern: SINGLETON (role: Singleton) =====================
// Probleme resolu : une seule source de verite pour les comptes.
// Sans Singleton : chaque partie du programme aurait son propre depot -> comptes perdus.
public class AccountRepository {
    private final Map<String, StudentAccount> accounts = new LinkedHashMap<>();

    private AccountRepository() {}

    // Initialization-on-demand holder : thread-safe et paresseux
    private static class Holder { static final AccountRepository INSTANCE = new AccountRepository(); }
    public static AccountRepository getInstance() { return Holder.INSTANCE; }

    /** L'id doit etre unique dans le depot. */
    public synchronized void add(StudentAccount a) {
        if (accounts.containsKey(a.getId()))
            throw new IllegalStateException("id deja existant : " + a.getId());
        accounts.put(a.getId(), a);
    }
    public synchronized Optional<StudentAccount> findById(String id) { return Optional.ofNullable(accounts.get(id)); }
    public synchronized List<StudentAccount> findAll() { return new ArrayList<>(accounts.values()); }
    public synchronized int count() { return accounts.size(); }
    public synchronized void clear() { accounts.clear(); }
}
