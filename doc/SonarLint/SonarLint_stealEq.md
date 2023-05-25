Hérincs Bence Attila és Cseszka Bence

A probléma megoldását a CognitiveComplexity dokumentáció elolvasásával kezdtük, annak érdkében, hogy jobban megértsük a porblémát.

**Tanulság:**
Minnél több if, for, while, stb.. van egy függvényben annál nagyobb a Cognitive Complexity és annál nehezebb felfogni, értelmezni azt. Amennyiben egymásba is vannak ezek ágyazva, úgy tovább növeljük a komplexitást.

A switchek sokkal könnyebben olvashatóak, mint az if-else if ágak.

**Megoldási lehetőség:**
Elsőre számomra az a gondolot jutott eszembe, hogy mi lenne, ha az olyan ellenőrzéseket, amik pusztán return-re kényszerítik a függényt kiszervezzük egy külön függvénybe.

Ez a megoldás sikeresen egyszerűsítette a komplexitást és szerintünk az olvashatóság valóban növelhető abban az esetben, ha helyesen vannak a függvénynevek meghatározva.