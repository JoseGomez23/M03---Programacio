# Practica de la UF5 collections!
## Classes 
- Productes (Abstracta) implementa Comparable.
- Aliments, classe filla de productes.
- Tèxtil, classe filla de productes.
- Electronica, classe filla de productes.
## Mètodes
- Main
- Menu (Escollir opcions)
- afegirProducte (Altre menu per afegir el diferent tipus de producte)
- afegirProducteAlimentacio 
- afegirProducteTextil
- afegirProducteElectronica
- cobrarCaixa 
- mostrarCarro
- exceptionsHandler
- arreglarPreus
- buscadorDeNoms
  
## afegirProducteAlimentacio();
Aquest mètode demana que introduim per teclat les següents variables:
- Preu
- Nom
- Data de caducitat
- Codi de barres

Una vegada introduides creara un objece de la clase Alimentacio, i depenent de la data de caducitat que haguem introduit pujara o rebaixara el preu.

## afegirProduteTextil();
Aquest metode demana que introduim per teclat les següents variables:
- Preu
- Nom
- Composició textil
- Codi de barres

Una vegada introduides creara un objece de la clase Textil amb la seva caracteristica propia, composició textil.

## afegirProducteElectronica();
Aquest metode demana que introduim per teclat les següents variables:
- Preu
- Nom
- Dies de garantia
- Codi de barres

Una vegada introduides creara un objece de la clase Electronica amb la seva caracteristica propia, dies de garantia.

## cobrarCaixa();
Metode que acabara amb els processos i buidara tots els arraylists i hashmaps possibles, tot i això es possible continuar comprant una vegada passat per caixa, com a una comanda diferent.

## mostrarCarro();
Mostrara tots els productes que hem guardat al carro, si tenim dos amb el mateix codi de barres els ajuntara i seguidament dira la quantitat de la que disposem

## exceptionsHandler();
Gestionara totes les excepcions produides al programa, des de inputMismatchExceptions fins a IOExceptions.

## arreglarPreus();
Metode que canvia el preu dels productes de la classe textil que tinguem guardats al fitxer UpdateTextilPrices.dat, funciona de la següent manera:
Llegim el fitxer i guardem el codi de barres i el preu a un Hashmap, que seguidament aquest comparara el seu contingut amb el carrito per veure si dins hi ha algun codi de barres igual, si troba un d'igual substituira el valor per el del document.

## buscadorDeNoms();
Metode per trobar un nom d'un producte arran del seu codi de barres, funciona de la següent manera:
Introduim el codi de barres del producte del que en volem saber-ne el nom, seguidament el guarda a una variable i despres el compara amb HashMap on es guarden el codis de barres en aquest cas "carrito". Despres si trobem el codi de barres, guardem a una llista amb el nom de "productesTrobat" el nom del producte, on despres aquesta llista sera printada i mostrara si hi el producte esta o no.



