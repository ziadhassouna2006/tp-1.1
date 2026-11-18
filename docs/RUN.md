# Comment executer (Travail 1)

Prerequis : JDK 17+ (`java -version`)

```bash
# depuis la racine du projet
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.uniaccounts.Main
```
