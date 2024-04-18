# Enunciat buscamines

##Part 1/3

Fes el joc del pescamines en mode consola.

En aquesta primera part només has d'implementar l'estructura de paquets i classes, i alguns mètodes que es detallen a continuació.
De moment no cal documentar res, ni generar l'arxiu JAR.

Has de  crear un projecte que es digui MinesXXX (XXX són les teves inicials en majúscula).
El paquet base s'ha de dir minesxxx (xxx són les teves inicials en minúscula).

Crea a dins la classe principal Mines i els paquets model, vista i controlador.
Dins de cada paquet, crea una classe amb les funcions que es detallen a continuació.

##Part 2/3

Ara s'han de crear mètodes en la classe Controlador perquè l'usuari pugui indicar al programa què vol fer, i en la classe Model per executar les accions de l'usuari.


Controlador
Dades privades

Un objecte de la classe Scanner per demanar dades a l'usuari
Funcions públiques

jugar
Cridar a inicialitzar el joc de la classe Model (ja s'ha fet en la pràctica anterior)
Repetir fins que s'acabi el joc o fins que el jugador abandoni:
- Preguntar si es vol Trepitjar, posar Bandera o Acabar (si es tria Acabar, sortir)
- Demanar fila i columna fins que siguin correctes
- Executar acció: ha de cridar el mètode trepitjar o posar bandera de la classe Model
- Si s'ha acabat el joc, sortir (es pot cridar el mètode consultar si ha acabat de la classe Model)

Opcionalment (si et sobra temps) pots crear un mètode per preguntar en quin nivell es vol jugar:
   1 - 8x8 (10 bombes)
   2 - 16x16 (40 bombes)
   3 - 16x30 (99 bombes)
   S - Sortir del programa
Si ho fas, des de la classe principal s'ha de cridar aquest mètode, i aquest mètode ha de cridar a jugar passant com a paràmetres el número de files, columnes i bombes segons l'opció triada.

 

Model
Funcions públiques

trepitjar
Paràmetres: fila i columna
Mostrar missatge si ja s'ha trepitjat aquesta casella o conté una bandera
Si hi ha una bomba, acabar el joc i mostrar solució (i errors)
Si no hi ha bomba, destapar la casella mostrant quantes bombes té al seu voltant (destapar una casella vol dir copiar en el camp visible el què hi ha en la mateixa casella del camp ocult)
- Comprovar si ha guanyat (ha trepitjat totes les caselles i ha posat correctament totes les banderes)
posar bandera
Paràmetres: fila i columna
Mostrar missatge si ja s'ha trepitjat aquesta casella
Si no hi ha bandera, posar-la
Si té una bandera, treure-la
- Comprovar si ha guanyat (ha trepitjat totes les caselles i ha posat correctament totes les banderes)
consultar si el joc s'ha acabat
Retorn: si el joc s'ha acabat (true), si no (false)
Funcions privades

comprovar si ha guanyat
Retorn: si el camp ocult és igual al camp visible (true), si no (false)
mostrar solució (i errors)
Es pot copiar tota la solució en el camp del jugador i mostrar-la
O només mostrar les bombes que falten i posar un caràcter especial per indicar si hi ha alguna bandera mal posada 

## Part 3/3

Només falta millorar una mica el joc afegint unes poques funcions, documentar i crear el programa executable (.jar).


Classe Model
Funcions privades

Des de la funció trepitjar, si no hi ha bomba en la casella s'ha de cridar una d'aquestes funcions:

Versió senzilla (fins a 4 punts):

trepitjar al voltant
Paràmetres: fila i columna
Si la casella no està tapada, no fer res.
Destapar la casella.
Si la casella trepitjada no té cap bomba al voltant, destapar les caselles del voltant.
Versió recursiva (fins a 6 punts):

trepitjar recursivament
Paràmetres: fila i columna
Si la fila i columna no són vàlides, no fer res!
Si la casella no està tapada, no fer res.
Destapar la casella.
Si la casella trepitjada no té cap bomba al voltant...
... per cada una de les caselles del voltant, cridar a trepitjar recursivament.
 

Documentació (fins a 2 punts)
Documenta totes les classes i les funcions públiques de cada classe.
També has de generar la documentació amb JavaDoc en el directori   _documentacio.

Si al generar JavaDoc dóna error amb algun caràcter especial, s'ha de posar la següent configuració:
Other command line arguments: -encoding utf8 -docencoding utf8 -charset utf8

 

Crear l'executable (2 punts)
Empaqueta el projecte en un arxiu JAR i comprova que el pots executar des de la consola.
