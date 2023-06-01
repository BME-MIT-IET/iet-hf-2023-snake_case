Hérincs Bence Attila és Cseszka Bence

## Bevezető

Első lépésként készítettünk egy "UnitTests" nevü mappát, amiben a Unit tesztek fognak elhelyezkedni.
Ezek után végiggondoltuk, hogy melyik alapvető funkcióval lenne érdemes kezdeni, ehhez elővettük a dokumentációnkat, amit még az eredeti projecthez csináltunk és ott végignéztük az akkori teszteket.

Ezen tesztek közül elsőnek a move parancsot választottuk 1. tesztként.
Ezt a UnitTests/MoveTest.java fájlban a "move" függvényben teszteljük.

A tesztesetek lebonyolítására elsőnek arra gondoltunk, hogy felhasználjuk a már megírt teszt .txt fájlokat, illetve a régebben megírt load függvényt. Azonban problémákba ütköztünk, mivel a load függvény nem a UnitTest-ekhez lett írva, így elég nehéz lenne összehozni, hogy működjön velük.

A load függvényt tanulmányozva azonban támadt egy olyan ötletünk, hogy a már előre megírt test .txt-ket fel tudnánk használni a unit tesztek során is. Beolvassuk a fájlt, feldaraboljuk a sorait, majd betápláljuk a testbe.

## Move teszt 1

Miután jobban végiggondoltuk, rájöttünk, hogy nem érné meg ez a megoldás, sokkal bonyolultabb lenne és nem nyernénk vele eleget, emiatt elvetettük és egy ilyen megoldással jöttünk helyette:

<p align="center" width="100%">
    <img width="100%" src="UnitTesztek_MoveSikeresTeszt.png"> 
</p>

## Move teszt 2

Következőnek egy sikeretelen move-ot szerettünk volna tesztelni.

Tervezés közben észrevettük, hogy a boardban(Board.java) lévő arraylistek nem nullázódnak, amikor új board jön létre. Ez a program futása szempontjából fölösleges, viszont a teszteléseket megnehezíti, emiatt kivettük a "static" kulcsszót az arraylistek elöl.

Ezek után megírtuk a sikeretelen move tesztet:

<p align="center" width="100%">
    <img width="100%" src="UnitTesztek_MoveSikertelenTeszt.png"> 
</p>

## Collect equipment teszt 1

Ebben a tesztben azt teszteljuk, hogy ha a virologus egy olyan mezon all, amiben van valami (esetünkben egy felszerelés), akkor azt fel tudja venni és utána bekerül az invertoryába.

<p align="center" width="100%">
    <img width="100%" src="UnitTesztek_CollectSikeresTeszt.png"> 
</p>

## Collect equipment teszt 2

Ebben a tesztben azt szeretnénk tesztelni, hogy ha van egy mezo amiben van valami, azonban a virológus nem azon collectel, akkor véletlenül se kerüljön be az invertoryába az a dolog (esetünkben egy felszerelés)

<p align="center" width="100%">
    <img width="100%" src="UnitTesztek_CollectSikertelenTeszt.png"> 
</p>

## AttackTest.java

A támadást tesztelő osztály, amelyben tesztelve van a sikeres és sikertelen támadási kísérlet is. Létrejön egy ellenséges virológus, akinek a dodge chance-e szándékosan 0-ra van állítva, hogy a támadás biztosan sikeres legyen. Közben saját virológusunkkal felszedjük és elkészítjük az ágenst, amit az ellenségre fogunk kenni. A támadást követően megvizsgáljuk a célponton lévő aktív effektek listáját, amelynek már tartalmaznia kell az imént rákent effektet/ágenst.

Sikertelen támadást úgy van tesztelve, hogy az ellenséges virológus 1-es dodge chance-el van létrehozva. Ez gyakorlatilag azt jelenti, hogy nem lehet semmivel sem eltalálni. A támadási kísérlet után megvizsgáljuk az ellenségen lévő aktív effektek listáját, amelynek üresnek kell lennie.

## CraftTest.java

A craftolást tesztelő osztály, amelyben tesztelve van a sikeres és sikertelen craftolási kísérlet is. Létrehozunk egy virológust a lehető legtöbb nyersanyaggal, ami a craftoláshoz szükséges. Szükséges mezők generálása után elnavigálunk a laborba, megtanuljuk a kódot, majd le is craftoljuk sikeresen az ágenst. A virológusunk által birtokolt ágensek száma tükrözi a művelet siekres eredményét.

Sikertelen craftolás úgy van tesztelve, hogy a virológust abszolút 0 nyersanyaggal hozzuk létre, és úgy próbáljuk meg vele lecraftolni az ágenst. Ekkor a karakterünk ágenseinek a száma továbbra is nulla marad.

## Drop teszt 1

A sikeres tesznek a lényege, hogy a virológus maga veszi fel az itemet és dobja el később.

## Drop teszt 2

A sikertelen tesztnek a lényege, hogy nem vesz fel semmit, így el se tudja dobni, tehát végig üres marad az inventory-ja.