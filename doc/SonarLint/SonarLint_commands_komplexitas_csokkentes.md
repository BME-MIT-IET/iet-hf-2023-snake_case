Hérincs Bence Attila és Cseszka Bence

A commands mappában található komplex függvényeket kijavítottuk az alábbiak szerint:

**Craft.java**
A craft parancsban megadott paraméterek ellenőrzésére szolgáló több if kifejezést lecseréltük egy külön boolean függvényre, ami végrehatja az ellenőrzést. Ha hibás paramétert kap, akkor megszakítja a craft parancsot. Igyekeztünk beszédes nevet adni az ellenőrző függvénynek: "inputCheck".

Továbbá lecseréltünk egy nagyobb if-else tömböt egy switch-case-re, és észrevettük, hogy a bennük lévő for ciklusok ugyanazt csinálják, csak egy másik stringet vizsgálva. Ezt kiszerveztük egy külön boolean típusú függvénybe. Függvény neve: "craftAgent".