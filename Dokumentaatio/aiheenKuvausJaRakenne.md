**Aihe:** Breakout peli
Aion luoda kaikkien tunteman Breakout -pelin. Lähden luomaan peliä aluksi palikoiden tekemisestä ja niiden liikkumisen toteuttamisesta
Kun olen saanut perustoiminnot kuntoon aion jatkaa kohti kehittyneempiä ominaisuuksia.

**Käyttäjät:** Pelaaja

**Pelaajan toiminnot:**
* Laatan liikuttaminen ja kaikkien tiilten tuhoaminen
  * Peli läpi kun kaikki tiilet tuhottu ilman että pallo tippunut laatan ohi & elämiä jäljellä


**Lisätoiminnot:**
* Powerupit
  * mm. leveämpi laatta väliaikaisesti, pallon nopeuden vaihtelu jne.
* Useampia kenttiä
* Kaksinpeli(?)


**Luokkakaavio:**
![Alt text](/Dokumentaatio/kaaviot/luokkakaavio.png)

**Sekvenssikaaviot:**
![Alt text](/Dokumentaatio/kaaviot/sekvenssi1.png)
![Alt text](/Dokumentaatio/kaaviot/sekvenssi2.png)

**Rakenne**
Ohjelman rakenne toimii että GameDrawer piirtää 10ms välein uuden pelitilanteen. Game luokka hoitaa kaiken logiikan. Game luokalla on monta
eri itemiä (tiilet, pelilauta, pallo, powerup) ja se hallinnoi mitä jokaiselle itemille käy pelin eri vaiheissa. GameDrawer luokka kuuntelee
käyttäjän näppäinten painalluksia ja lähettää ne Game luokalle joka siten tietää mitä tehdä milloinkin. Itemit omat vain palikoita jotka näkyvät ruudulla eikä niillä suoranaisesti ole muuta toiminnallisuutta kuin liikkuminen.
