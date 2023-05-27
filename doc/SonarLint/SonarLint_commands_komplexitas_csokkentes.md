Hérincs Bence Attila és Cseszka Bence

A commands mappában található komplex függvényeket kijavítottuk az alábbiak szerint:

**Craft.java**
A craft parancsban megadott paraméterek ellenőrzésére szolgáló több if kifejezést lecseréltük egy külön boolean függvényre, ami végrehatja az ellenőrzést. Ha hibás paramétert kap, akkor megszakítja a craft parancsot. Igyekeztünk beszédes nevet adni az ellenőrző függvénynek: "inputCheck".

Továbbá lecseréltünk egy nagyobb if-else tömböt egy switch-case-re, és észrevettük, hogy a bennük lévő for ciklusok ugyanazt csinálják, csak egy másik stringet vizsgálva. Ezt kiszerveztük egy külön boolean típusú függvénybe. Függvény neve: "craftAgent".

**EndTurn.java**
Az endturn parancsban kiszerveztük külön függvénybe azt a funkciót, ami az AI-t tesztelési módba állítja. Ez egy for ciklusokból, setter-ekből meg update-ekből álló függvény: "setAITest"

Egyébiránt az AI cselekvését végrehajtó if-else blokkot is külön függvénybe szedtük ki: "actAsAI". Tovább nem akartuk darabolni, mert bonyolultnak és törékenynek tűnik.

**Create.java**
Az addingNeighbours függvény hatalmas if-else blokkját lecseréltük egy switch-case szerkezetre, és a benne található for ciklust is kiemeltük külön funkicóba: "parseNeighbours". Ezzel jelentősen csökkentve a komplexitást.

A shelter létrehozás hatalmas if-else blokkját lecseréltük egy switch-case szerkezetre, és a benne található for ciklust is kiemeltük külön funkicóba: "createShelterPhysically". Ezzel tovább csökkentve a komplexitást.

Ezzel a két változtatással a komplexitást már 20-al csökkentettük: 111 --> 91.

A genetical code létrehozás nagy if-else blokkját lecseréltük egy switch-case szerkezetre, és a benne található logikát is kiemeltük külön funkicóba: "createGCodePhysically". Ezzel tovább csökkentve a komplexitást.

Az equipment létrehozás if-else blokkját lecseréltük switch-case szerkezetre.

Ezután a create függvény legfőbb if-else szerkezetét lecseréltük egy switch-case-re, illetve a shelter és a genetical code ágak belsejét kiszerveztük karbantarthatóság miatt külön függvényekbe: "createShelter" & "createGCode"

A komplexitást sikeresen lecsökkentettük 43-ra az eredeti 111-ről. Ez egy nagyon jó végeredmény, sokkal átláthatóbb és karbantarthatóbb lett a kód. Nem értük el a SonarLint által javasolt 15-ös komplexitási küszöbértéket, de úgy véljük, hogy ennek elérése indokolatlan. Ha ennél is tovább tördelnénk a kódot és mégtöbb függvényt hoznánk létre, az a olvashatóság rovására menne.

