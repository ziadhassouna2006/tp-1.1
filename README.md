# UniAccounts — TP Design Patterns (Java)

Systeme de gestion des comptes etudiants.

## Etat d'avancement
| Travail | Patterns | Tag | Statut |
|---|---|---|---|
| 1 — Noyau | Builder, Singleton, Prototype | `work1` | fait |
| 2 — Extension | State, Observer, Strategy, Composite, Adapter, Decorator, Facade | `work2` | a faire |

## Structure
```
src/com/uniaccounts/
 ├── model/StudentAccount.java        # Builder
 ├── repository/AccountRepository.java # Singleton
 ├── audit/AuditLogger.java            # Singleton
 ├── prototype/AccountTemplate.java    # Prototype
 └── Main.java
docs/   # RUN.md, UML, captures
```

## Execution
Voir [docs/RUN.md](docs/RUN.md).

## Patterns -> classes
| Pattern | Classe(s) |
|---|---|
| Builder | `StudentAccount.Builder` |
| Singleton | `AccountRepository`, `AuditLogger` |
| Prototype | `AccountTemplate` |

## Equipe
| Membre | Parties |
|---|---|
| (nom) | ... |
