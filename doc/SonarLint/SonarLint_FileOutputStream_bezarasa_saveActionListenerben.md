Illés Ákos

A GameWindow osztály saveActionListener metódusában nem volt bezárva egy FileOutputStream. Ezt a SonarLint jelezte ki.
A SonarLint adott ötleteket a megoldásra, pl.: használjak try-with-resources blokkot, vagy finally blokkban zárjam be a folyamot.
Én az előbbit választottam, mivel ez saját magától be tudja zárni a folyamot és egyszerűbb szerintem.
Továbbá beleraktam ebbe a blokkba az ObjectOutputStreamet is.