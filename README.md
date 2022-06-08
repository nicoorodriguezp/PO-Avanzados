# PO-Avanzados
Proyecto Integrador desarrollado por Matias Ezequiel Romero y Nicolas Gaston Rodriguez Perez, referente a la materia "Programacion con Objetos Avanzados" de la Universidad de Palermo.

En el repositorio se encuentran todos los archivos y librerias utilizadas para realizar el proyecto.

# Sobre el Proyecto Integrador

Se requiere realizar un sistema que permita llevar el control de stock de insumos para la reparación de celulares. La empresa cuenta con tres depósitos donde reciben todos los distintos insumos y luego son distribuidos según la necesidad de los distintos laboratorios con los que cuentan para la reparación de los celulares.

Los distintos insumos se van a distinguir por críticas y secundarias, teniendo en cuenta la importancia de cada uno. A su vez, cada depósito tiene un stock máximo de unidades por cada insumo. 

Teniendo en cuenta el stock máximo por insumo, los insumos categorizados como críticos deben tener un stock mínimo del 50% de capacidad en cada depósito y, los insumos categorizados como secundarios, deben mantener un stock mínimo del 20% de la capacidad de cada depósito referido a ese insumo en particular.

Por lo tanto, cuando algún insumo baje de su porcentaje mínimo, el sistema debe emitir una alerta al responsable de mantener el stock correspondiente de cada depósito, independientemente si esta persona física se encuentra o no ingresado al sistema.

Además, se requiere tener trazabilidad en cuanto al estado de cada insumo en particular, por lo tanto, el sistema debe ser capaz de reconocer cuál fue el depósito al cual ingresó, cual es el laboratorio que lo recibió posteriormente y el estado en el que se encuentra dentro del depósito. El estado puede tomar tres valores distintos: En stock, Usada, Descartada.

Esto último permitirá a los encargados correspondientes poder tomar decisiones según los movimientos de cada insumo, su consumo e incluso tener una tolerancia a fallos en cuanto a los insumos descartados.

Para lograr esto último, el sistema debe ser capaz de mostrar un Reporte de Consumo diario de los insumos por cada depósito, por cada insumo en sí y la posibilidad de emitir un informe diario global. Este informe debe ser solicitado por un usuario, que tenga los permisos necesarios, dentro del sistema.


# Contenido del Repositorio

## src/main/java/com

Aquí encontrarás todo el código relacionado con los modelos y las diferentes clases utilizadas en todo el sistema, así como las relativas a la capa de servicios.

## src/main/java/fxml

Aquí encontrarás todos los controladores de los documentos FXML relacionados con el front-end.

## src/main/main/resources/fxml/

Aquí encontrarás todos los archivos FXML que contienen el código de cada uno de los elementos que conciernen a la interfaz de usuario.

## src/main/main/resources/fxml/static

Aquí se encuentran todos los archivos estáticos dentro del sistema, como imágenes, archivos css e iconos.


# Ejemplos Ilustrativos

<h3> Login </h3>

![Login](https://github.com/nicoorodriguezp/PO-Avanzados/blob/main/src/main/resources/static/images/examples/1.png)

<h3> Home </h3>

![Home](https://github.com/nicoorodriguezp/PO-Avanzados/blob/main/src/main/resources/static/images/examples/2.png)

<h3> Stock </h3>

![Stock](https://github.com/nicoorodriguezp/PO-Avanzados/blob/main/src/main/resources/static/images/examples/3.png)

<h3> Repair </h3>

![Repair](https://github.com/nicoorodriguezp/PO-Avanzados/blob/main/src/main/resources/static/images/examples/4.png)


<h3> Replenish </h3>

![Replenish](https://github.com/nicoorodriguezp/PO-Avanzados/blob/main/src/main/resources/static/images/examples/5.png)


<h3> Report of Items Used/Discarded </h3>

![ItemsUsedDiscarded](https://github.com/nicoorodriguezp/PO-Avanzados/blob/main/src/main/resources/static/images/examples/6.png)


<h3> Report of Movements </h3>

![MovementsReport](https://github.com/nicoorodriguezp/PO-Avanzados/blob/main/src/main/resources/static/images/examples/7.png)

<h3> Users List </h3>

![UsersList](https://github.com/nicoorodriguezp/PO-Avanzados/blob/main/src/main/resources/static/images/examples/8.png)

<h3> Create User </h3>

![CreateUser](https://github.com/nicoorodriguezp/PO-Avanzados/blob/main/src/main/resources/static/images/examples/9.png)


PDF

[Sistema de Control de Insumos.pdf](https://github.com/nicoorodriguezp/PO-Avanzados/files/8865297/Sistema.de.Control.de.Insumos.pdf)


