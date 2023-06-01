Zoltai Dániel

Cucumber használatával létrehoztam teszteseteket amelyek az átlagos felhasználó számára is olvashatóak, ezek a .feature kiterjesztésű fájlok.
A bennük feltüntetett lépések definiciója a StepDefs mappában találhatóak.
A leggyakrabban használt lépéseket kiszerveztem egy külön fájl-ba (InitializeBasicsDef) ahol a változók statikusak.

Tesztesetek:

-A Virológus mozog
1. A virológus átlép egy szomszédos mezőre.
2. A virológus megpróbál átlépni egy nem szomszédos mezőre.
3. A virológus megpróbál lépni amikor le van bénulva.

-A Virológus alapanyagot gyűjt
1. A virológus összegyüjt egy amino-acidot.
2. A virológus összegyüjt egy nukleotidot.
3. A virológus lebénulva próbál gyüjteni.

-A Virológus Ágenseket Kraftol
1. Egy Forget Ágenst kraftol
2. Egy Paralyze Ágenst kraftol
3. lebénulva próbál kraftolni
4. nincs elég nyersanyaga a kraftoláshoz.