# M223: Punchclock
## Über das Backend
Dies ist das Backend des M223 Projekts.
## Über die Applikation
### Was tut die Applikation
1. In dieser Applikation kann man sich als USER oder MODERATOR einloggen und auch wieder ausloggen.
2. Das Login funktioniert mit JWT, welcher nur für 1 Minute lang gültig ist. In den application.properties kann
man die Gültigkeit des JWT zu jeder Zeit ändern.
3. Hat man sich in dieser Applikation einloggt, kann man noch eigene Mottos erstellen und Mottos von anderen Benutzern kaufen.
4. Man kann sein eigenes Passwort ändern.
5. Als MODERATOR kann man noch die Passwörter von den anderen Benutzern ändern und auch ander Benutzer komplett löschen.
### Wie startet man die Applikation?
#### Backend
1. Klonen Sie dieses Backend Repository
2. Öffnen Sie das Backend in Intelij oder einer anderen Entwicklungsumgebnung
3. Starten Sie das Backend in Ihrer Entwicklungsumgebnung.
#### Frontend
1. Klonen Sie das Frontend Repository.
2. Öffnen Sie das Frontend in Visual Studio Code oder in einer anderen Entwicklungsumgebnung.
3. Installieren Sie mit "npm install" die node_modules.
4. Starten Sie das Frontend mit "npm start".
## Beispieldaten laden
Die Beispieldaten werden in die H2-Datenbank geschrieben, sobald man das Backend mit zbs. Intlij startet.
### Benutzer
Unter resources gibt es die Data.sql-Datei, in der es 3 (gipfeli, croissant und silsergipfeli) Benutzer gibt und alle
Benutzer haben das Passwort 1234.
### Rollen
Die Beispieldatei erstellt noch zwei Rollen (USER und MODERATOR).
### Kategorien
Es gibt im ganzen 5 Kategorien, welche im Voraus erstellt werden. Diese sind: funny, not-funny, motivation, random und melancholic.
### Mottos
Die Data.sql-Datei erstellt auch noch 4 verschiedene Mottos.