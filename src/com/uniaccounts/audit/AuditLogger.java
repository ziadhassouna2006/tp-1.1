package com.uniaccounts.audit;

import java.time.LocalDateTime;
import java.util.*;

/** Journal d'audit : enregistre toutes les operations. */
// ===================== Pattern: SINGLETON (role: Singleton) =====================
// Probleme resolu : un seul journal d'audit pour tout le systeme.
public class AuditLogger {
    private final List<String> entries = new ArrayList<>();

    private AuditLogger() {}

    private static class Holder { static final AuditLogger INSTANCE = new AuditLogger(); }
    public static AuditLogger getInstance() { return Holder.INSTANCE; }

    public synchronized void log(String action) {
        entries.add(LocalDateTime.now().withNano(0) + " | " + action);
    }
    public synchronized List<String> getEntries() { return Collections.unmodifiableList(new ArrayList<>(entries)); }
    public synchronized void printAll() { entries.forEach(System.out::println); }
}
