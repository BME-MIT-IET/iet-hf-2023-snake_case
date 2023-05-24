Hérincs Bence Attila

A SonarCloud-ot az elmúlt körülbellül 2 napban beüzemeltem az egyik csapartásrsammal Cseszka Bencével. Voltak kisebb-nagyobb bukkanók.
Elsőnek a projectet fel kellett készítenünk arra, hogy maven-t tudjon használni. Ez relatíve fájdalom mentesen ment, az 5. gyakorlaton tanultak alapján elég gyorsan sikerült is.
Az elején csupán a pom.xml fájlt ronttuk el, de a gyakorlat példájára sikerült megoldani, hogy leforduljon helyesen maven segítségével githubon a project.

Ezek után jött az amivel órákat próbálkoztuk és az idő nagyrészét ez is vitte el, a SonarCloud.
Az első probléma az volt, hogy már maga a weboldalon történi organization létrehozását máshogy kellett csinálni, mint ahogy azt gyakorlaton megtanultuk, így ehhez elég sok utána járás votl szükséges. Ennek oka az volt, hogy a repo az technikailag nem a milyen, én pusztán csak admin vagyok, hanem a "BME-MIT-IET" a tulaj és nem szerettem volna semmi ostobaságot elkövetni, így a SonarCloud weboldalán egy egyéni szervezetet hoztam létre, amihez a repot hozzáadtam.
Itt azonban még mindig nem szeretett volna úgy működni a dolog, ahogy az a gyakorlaton volt és nem teljesen értettem, hogy miért. Másik csapatokat megkérdeztem, hogy amennyiben csináltak ilyet ők hogyan oldották meg azt. Több-kevesebb sikerrel és rengeteg dokumentáció olvasás után sikerült a projectet olyan szintre hozni, hogy van SonarCloud benne és összesítve látjuk is rajta a bugokat, code smelleket, stb...