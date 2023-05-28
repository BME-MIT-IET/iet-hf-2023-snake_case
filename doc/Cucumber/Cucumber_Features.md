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
1. A virológus összegyüjt egy amino-acidot
1. A virológus összegyüjt egy nukleotidot