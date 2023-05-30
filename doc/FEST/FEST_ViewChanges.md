Prikler René

A Menu nézetben több helyen System hívás található. Ezek tesztelése érdekében létrehoztam egy SystemCall osztályt, amin keresztül elérhetőek a System hívások. A SystemCall osztályt pedig Dependency Injection segítségével példányosítom konstruktorhíváskor. Ez az osztályt elérhetővé teszi a standard kimenetet és az System.Exit parancsot meghívja az alkalmazás bezárásához.

Továbbá a FEST működése érdekében adtam neveket a felhasználói felület elemeinek. Ezzel már lekérdezhetőek a felület bemenetei, kódon keresztül tudjuk aktiválni azokat.

GameWindow.gamestart()-ból kiszedtem a menu paramétert, mivel csakis arra volt használva, hogy a menü ablakot eltüntesse a játék kezdetekor. Ez a menü felelőssége lenne, ezért refaktoráltam, hogy maga tüntesse el magát.

A GameWindow konstruktorában is volt a menu paraméter, amit eltárolt az osztály, de soha nem használt. Ezt töröltem és refaktoráltam a hozzá tartozó kódot.

A GameWindowra és a MapGraphicsre is érvényes, hogy a System hívásoktól függ, ezért ezeknél is a felül említett módon feloldottam a függőséget.

A MapGraphics osztály néhány property-je a GameWindowon belül volt inicializálva. Ezek a MapGraphics osztály feladatai, ezért átvittem ezeket a Mapgraphics osztályba.

A pálya betöltésének a kódját a Start.start() függvényben módosítani kellett, hogy tesztpályát is be lehessen tölteni, amiben a pálya minden eleme determinisztikus.

Nem volt konzisztens a játék kezdése ha több tesztet indítottunk, mivel a játékállás statikus változókkal van tárolva. Módosítani kellett az inicializálást a testben, hogy a pálya változóit lenullázza. 

Probléma volt a térképet lekérdező kódban több teszt futtatásánál, ez le lett cserélve egy konzisztense.