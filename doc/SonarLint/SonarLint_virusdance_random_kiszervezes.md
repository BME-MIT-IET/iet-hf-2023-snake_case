Illés Ákos

A hiba: Az effect függvényben található a Random inicializálás ez nem effektív, mert minden egyes alkalommal új random kerül létrehozásra. Ki kellene szervezni az osztály-ba.

A random változót kiszerveztem a függvényen kívülre, hogy ne kelljen mindíg újra létrehozni és final-ra állítottam, mert az már nem fog változni.
