Cseszka Bence

A projekt fájljainak elemzése során jelzett a SonarLint, hogy nagyon sok helyen van string ellenőrzés begépelt módon. Célszerű lenne ezeket a gyakran előforduló stringeket kiemelni változókba, esetleg egy külön osztályba is, és innen hivatkozni rájuk. Így megszűnne az a veszélyforrás, hogy további fejlesztés során esetlegesen elgépelünk egy ellenőrizendő stringet.

Megoldásként kiemeltem ezeket a stringeket egy külön osztályba. Először interfészbe terveztem, azonban a SonarLint erre is figyelmeztetett, hogy nem jó ötlet, "bad practice". Szóval maradtam annál hogy egy külön osztályba lesznek kiszervezve ezek a gyakran használt stringek.

Miután átfésültem az egész projektet, arra jöttem rá, hogy nagyon sok helyen jól jön ez a string referálás a begépelés helyett. (Lásd screenshoton: "usages") Elsősorban ott jó, ahol a funkcionalitást is befolyásolhatja egy esetlegesen elgépelt string. Például ha egy effektet akarunk lekérdezni és függvény paraméterként átadva "paraylze" nevű effektet keresnénk, akkor hibára futna a program, mert olyan nincsen.