Illés Ákos

Hiba: még sok helyen nincsenek bezárva Stream-ek

Megoldás: ezek megoldása nagyon egyszerű, csupán be kell tenni őket egy try-with-resources blokkba, és ezzel át is adom a folyamok kezelését a fordítónak. Ennek a kijavításához csak 2 osztály kódját kellett kijavítanom: Menu.java és Load.java.