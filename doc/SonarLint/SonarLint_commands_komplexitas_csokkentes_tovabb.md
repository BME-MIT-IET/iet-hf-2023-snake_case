Cseszka Bence

A commands mappában található osztályok közül kettőben komplexitási problémák voltak, ezeket oldottam meg sikeresen. Továbbá az effects mappa egy osztályában volt még ilyen probléma.

**BearEffect.java**
Az osztályban a tényleges támadást végrehajtó parancsot kiemeltem egy külön függvénybe olvashatóság miatt, két for ciklust is tartalmaz. Kijavítottam az osztályban található Random típusú objektum függvényen kívüli létrehozását is.

**Load.java**
Az osztályban található nagy if-else blokkot lecseréltem egy átláthatóbb switch-case szerkezetre. Továbbá a lépési lehetőséget figyelő és állító setterek-et kiemeltem külön függvényekbe: "moveCheck", "actionCheck". Ezen lépések hatására lecsökkent a komplexitás, és sokkal szebb lett a kód is.

**Move.java**
Az osztályban található mozgást végző if-else blokkot kiemeltem külön függvénybe az átláthatóság érdekében: "movePhysically". Itt sajnos nem volt lehetőség switch-case szerkezetre való átírásra. Létrehoztam még egy többször felhasználható segédfüggvényt, amely a mozgás pontos helyét határozza meg. 