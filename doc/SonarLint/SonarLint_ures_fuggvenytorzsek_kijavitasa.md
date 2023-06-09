Illés Ákos

Hiba: Az effect absztrakt osztályt implementáló osztályok egy részének nincs szüksége a removeEffect függvényre, ezért üresen állnak. Ezeket soha nem fognak csinálni semmit, ezért valami hibát kéne dobniuk, ha véletlenül meghívnák őket. 
![](SonarLint_ures_fuggvenytorzsek_kijavitasa_hiba.png)

Megoldás: Mivel ezek a függvények üresek, ezért nem is kéne, hogy valaki meghívja őket. Inkább figyelmeztetni kell azt, aki ezt a jövőben meg akarná hívni, hogy ez nem csinál semmit. Erre jó megoldás dobni egy kivételt.
![](SonarLint_ures_fuggvenytorzsek_kijavitasa_javitott.png)

Ezeknek a függvényeknek azért van szükségük a removeEffect implementálására mert az effect abstract osztályból származnak le és ez az osztály tartalamaz egy removeEffect függvényt, ám pár effect ezt nem használja.

Megoldás javított: miután megcsináltam a fenti módon a javításokat, a tesztelés során egyes effectek elromlottak. Jobban utánajártam miért lehet ez, és arra jutottam, hogy ezeket a függvényeket polimorffizmussal meghívja az Effect osztály, ezért üresnek kell lenniük és nem is kell semmit csinálniuk, ezért csak egy kommenttel jelzem, hogy legyenek üresek.

![](SonarLint_ures_fuggvenytorzsek_kijavitasa_kommenttel.png)