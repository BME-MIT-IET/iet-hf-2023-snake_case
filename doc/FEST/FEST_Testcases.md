Prikler René

A UI tesztekhez a FEST swing és a mockito könyvtárat használtam. A tesztek pedig JUnit tesztek.
A mockito a SystemCall osztály mockolására használom, mivel eredetileg a nézet erősen függött a System osztálytól. Ez bizonyos teszteknél problémákat is okozna, mint például a programból való kilépés ellenőrzése.

UI Tesztesetek:

Menu:
1. Kilépés gomb megnyomása: Ellenőrizzük, hogy megnyomás után meghívódott-e a SystemCall Exit metódusa. Ha egyszer igen, akkor sikeres a teszt.
2. Indítás gomb megnyomása: Ellenőrizzük, hogy megnyomás után eltűnik-e az ablak.

GameWindow:
1. Kilépés gomb megnyomása: Ellenőrizzük, hogy megnyomás után meghívódott-e a SystemCall Exit metódusa. Ha egyszer igen, akkor sikeres a teszt.

2. Craft tesztelése: Elnavigálunk egy laborhoz, ahol megtanuljuk az ott lévő genetikai kódot, majd abbahagyjuk a kört, mert nincs több lépésünk. Ezután készítünk egy ágenst a megtanult genetikai kód alapján.