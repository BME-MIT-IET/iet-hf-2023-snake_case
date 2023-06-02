Hérincs Bence Attila

Ha minden igaz elkészítettem, hogy SonarCloud is legyen a repo-ban ami a main branchet figyeli. Ennek tesztelése érdekében most egy egyszerűbb code smellt javítok egy másik branchen, majd PR-al mergeltetem a main-el, így megnézve, hogy a SonarCloud hogyan viselkedik.

Itt a Create.java fájlt nézem át bugokat, code smelleket keresve.

Itt is megtaláltam a szokásos problémát, miszerint olyan változókat amiket azonosításra használnak a kódban, pl.: egy adott mező az "field" vagy "laboratory", azokat nem szerevezik ki. Erről Issue-t is készítettem: "Create class-ban nincsenek kiszervezve a stringket konstansokban." néven. Ezek után létrehoztam egy új branchet, és orvosoltam a problémát.


Úgy néz ki, hogy nem volt sikeres a próbálkozásom, így változtattam a yaml fájlon és most egy másik hibát keresek, amit "könnyen" ki tudok javítani a tesztelés gyanánt.
Ezt a hibát ugyan úgy a Create fájlban találtam: Ez pedig az, hogy üres listák helyett null objektumokat adnak vissza a függvények, hogy egy üres listát adnának, ami plussz komplexitást ad a kódnak.

Így se jártam sikerrel...
