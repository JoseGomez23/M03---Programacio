# PRÀCTICA UF6
## Carpetes
El projecte té un total de 4 carpetes: 
arxiusDades: Conté el fitxer necessari per fer l'apartat 6.
controlador: Estan els mètodes que controlen el projecte.
model: Estan definides totes les classes i DAO necessaris per que el programa funcioni bé.
vista: Mostra els missatges que veu l'usuari.

## Interfaç
### Interfaç DAO
És la estructura que ens permet fer la gestió de la base de dades.
Té el necessari per les classes que l'implementen.

# Classes
### Classe controlador
Aquesta classe contè un menú per poder escollir quina opció vol fer l'usuari. 
Estan dividides totes les opcions, de la 1 fins la 9 (La opció 0 es per sortir del programa).
En cas de que l'usuari no posi un numero correcte tornarà a preguntar per la opcio que vol.

### Classe Equip, estadistiques_jugadors, Historic, Jugador, JugadorHistoric, Model i Partit
Totes aquestes classes tenen constructors corresponents ademés de getters i setter que s'utilitzen (o no) per poder modificar les dades que pot l'usuari.

### Classe EquipDAO, estadistiques_jugadorsDAO, HistoricDAO, JugadorDAO, JugadorHistoricDAO i PartitDAO
Aquestes són les classes que implementen la estructura del DAO. Tenen els metodes CRUD que estan presents en el DAO.

### Classe Vista
Aquesta classe és la encarregada de mostrar els missatges que veurà per pantalla l'usuari.

## Aclariment dels excercicis
# Excercici 1
Al demanar el nom d'un equip, mostrarà tots els jugadors que pertanyin al equip corresponent.

# Excercici 2
Donat el nom i cognom del jugador, retorna la mitjana de les estadistiques corresponents del jugador.

# Excercici 3 
Retorna els partits que s'han jugat i retorna els partits que ha jugat l'equip amb el resultat (W = Win | L = Lose) i l'enfrontament (DAL VS MAV).
(Les @ són partits amistosos i els VS són partits de lliga).

# Excercici 4
Al passar el nom i cognom del jugador, demanarà un equip_id per afegirlo al equip amb aquest id. Si el jugador ja està en algun equip, cridara a l'excercici 5 per traspassar-lo.

# Excercici 5
Demana nom i cognom del jugador i el traspassa a un altre equip.

# Excercici 6
Actualitza 2 jugadors (definits al CSV) mitjançant la ruta del fitxer que té.

# Excercici 7
Demana el nom del jugador i un id de partit. Una vegada inserit, demanarà que s'introdueixen totes les estadistiques de nou.

# Excercici 8
Donat un nom i cognom d'un jugador, totes les seves estadistiques seran transferides a una taula anomenada historic i el jugador serà tranferit a una taula anomenada jugadorHistoric.
Quan s'hagi transferit, es borraran les estadistiques i el perfil del jugador.

# Excercici 9
Demana el nom d'un equip (Nuggets) i canvia la ciutat/franquicia. (Denver Nuggets --> Blanes Nuggets)

