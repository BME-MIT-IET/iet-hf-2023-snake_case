Hérincs Bence Attila

Kaptam egy olyan issue-t, melyben az alébbi 3 helyen csökkentem kell a kognitív komplexitást: **OtherAttackActionListner**, **OtherStealActionListener**, **ApplyEffect**. Erről a feladatkörről már olvastam egy előző issue-nál, így ebben az md fájlban részletesen nem fejteném ki, csak amennyiben valami újat csinálok.

1. Lépésként kerestem, hogy van-e olyan rész, amit egy switch-el lehetne helyettesíteni. Találtam is az **OtherAttackActionListner**-ben.

A SonarLint az OtherStealActionListener-ben talált egy másik code smell-t:

<p align="center" width="100%">
    <img width="100%" src="SonarLint_complexitas_csokkentes_H9PB1F_OtherStealActionListener_merge_if.png"> 
</p>

Ez alapján azzal, ha összeolvasztom a két if-et(amiben semmi nem állít meg, mivel más amúgy se történne abban az ágban, csakis akkor, ha a második if is teljesül), akkor azzal növelhetem a kódnak a megbízhatóságát.

Amennyiben az alsó if-nek a feltételét egy külön, beszédesebb nevű függvénybe szervezem ki, akkor igen, ez valóban egy könyebben olvasható, könnyebben karbantartható kód lesz. (A SonarLint által mutattott példakódban is így szerepel)

Az **OtherStealActionListener**-ben találtam egy **kód duplikálást**, aminek kiszereverzésével a komplexitást is megoldottam. A kiszervezést a "setWhichButtonShouldBeVisible" függvényben valósítottam meg.