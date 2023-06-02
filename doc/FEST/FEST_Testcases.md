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

3. Győzelem tesztelése: Elnavigálunk az összes laborhoz, megtanuljuk az ott lévő genetikai kódot. Ezzel megnyerjük a játékot.

4. Támadás teszt: Megtanulunk egy genetikai kódot, kraftolunk belőle egy ágenst, amit felhasználva megtámadjuk magunkat.(Azért magunkat, mert az ellenséges virológusok mozgása véletlenszerű, mindig más mezőn vannak) ~~Jelen pillanatban a teszt sikertelen, mert a támadás nem működik.~~