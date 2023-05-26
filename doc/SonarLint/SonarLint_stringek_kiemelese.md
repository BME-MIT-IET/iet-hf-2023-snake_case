Cseszka Bence

A projekt fájljainak elemzése során jelzett a SonarLint, hogy nagyon sok helyen van string ellenőrzés begépelt módon. Célszerű lenne ezeket a gyakran előforduló stringeket kiemelni változókba, esetleg egy külön osztályba is, és innen hivatkozni rájuk. Így megszűnne az a veszélyforrás, hogy további fejlesztés során esetlegesen elgépelünk egy ellenőrizendő stringet.

Megoldásként kiemeltem ezeket a stringeket egy külön osztályba. Először interfészbe terveztem, azonban a SonarLint erre is figyelmeztetett, hogy nem jó ötlet, "bad practice". Szóval maradtam annál hogy egy külön osztályba lesznek kiszervezve ezek a gyakran használt stringek.