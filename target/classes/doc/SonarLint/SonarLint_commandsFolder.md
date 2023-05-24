Hérincs Bence Attila

Átnézem a commands mappát kisebb code smelleket kereseve, hogy az elvileg sikeresen telepített SonarCloud-ot teszteljem.
Találtam is egy komolyabbat. Az egyik switch-nek nincs default ága. Ez komoly problémákat okozhat, ha bővítésre kerül a sor, mivel ha ezt elfelejtik bővíteni, akkor futásidejű hibát dobhat az alkalmazás.