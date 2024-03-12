# Práctica 10. Fragmentación y reensamblado en IP

## Ejercico 1.

**Un router recibe un datagrama de 3500 bytes. La red de salida en la que debe transmitirlo para que llegue a su destino tiene una MTU de 1500 bytes, por lo que el router debe fragmentar el datagrama.**

**a) Calcula el número de fragmentos que se generarán y el tamaño de cada fragmento. Incluye en tu respuesta los cálculos realizados.**

Para calcular el número de fragmentos que se generarán, es necesario dividir el tamaño del datagrama original por el tamaño máximo de transmisión (MTU) menos la cabecera IP (20 bytes), y redondear hacia arriba al número entero más cercano:

```scss
Número de fragmentos = ceil(3500 / (1500 - 20)) = ceil(2.4) = 3 fragmentos
```

Por lo tanto, se generarán tres fragmentos de longitud distinta:

- Fragmento 1: 1500 bytes (20 bytes de cabecera IP + 1480 bytes de datos)
- Fragmento 2: 1500 bytes (20 bytes de cabecera IP + 1480 bytes de datos)
- Fragmento 3: 560 bytes (20 bytes de cabecera IP + 540 bytes de datos)

**b) Indica el valor que tiene el campo desplazamiento de la cabecera IP en cada uno de los fragmentos generados (Recuerda que el tamaño del campo de datos de todos los fragmentos exceptuando el último fragmento debe ser un valor divisible por 8).**

Para calcular el valor del campo de desplazamiento en cada fragmento, se debe multiplicar el índice del fragmento por el tamaño máximo de datos que puede contener un fragmento (1480 bytes) y dividirlo entre 8, ya que el campo de desplazamiento mide 13 bits y debe expresarse en unidades de 8 bytes:

- Fragmento 1: (0 * 1480) / 8 = 0
- Fragmento 2: (1 * 1480) / 8 = 185
- Fragmento 3: (2 * 1480) / 8 = 370

**c) Completa la tabla siguiente con los valores obtenidos.**

El bit MF (More Fragments) indica si hay más fragmentos que deben ser transmitidos después del fragmento actual. El bit MF está activado (1) en todos los fragmentos, excepto en el último fragmento. El último fragmento tiene el bit MF desactivado (0).

| Número de Fragmentos | Longitud total/fragmento | Desplazamiento | Bit MF |
|----------------------|--------------------------|----------------|--------|
| 1                    | 1500                     | 0              | 1      |
| 2                    | 1500                     | 185            | 1      |
| 3                    | 560                      | 370            | 0      |

## Ejercicio 2.

Captura de:
```
>_ ping -n 1 -l 3972 www.rediris.es
```

**a) Para el datagrama enviado por tu ordenador, compara las cabeceras de los fragmentos generados, fijándote especialmente en los campos longitud total, flags y desplazamiento del fragmento (fragment offset en la captura de Wireshark). Para ello ayúdate de la tabla siguiente, donde puedes anotar los valores de estos campos.**

| Número de Fragmento  | Bit DF | Bit MF | Desplazamiento | Longitud total/fragmento |
|----------------------|--------|--------|----------------|--------------------------|
| 1                    | 0      | 1      | 0              | 1500                     |
| 2                    | 0      | 1      | 185            | 1500                     |
| 3                    | 0      | 0      | 370            | 1040                     |

**b) ¿Cuál es el valor del campo protocolo de la cabecera de los tres fragmentos? ¿Debe ser el mismo para todos los fragmentos? Justifica la respuesta.**

Sí, en las cabeceras de los fragmentos el protocolo es siempre el mismo, ICMP.

**c) ¿Cuál es el valor del campo desplazamiento enviado en la cabecera IP del segundo fragmento? Wireshark muestra el valor del desplazamiento ya calculado, no el que realmente se envía. Comprueba en la pestaña inferior que muestra los bytes enviados en hexadecimal cuál ha sido el valor realmente enviado. Recuerda que el tamaño del campo de desplazamiento es de 13 bits.**

El valor enviado ha sido 185. Wireshark muestra (185 * 8) = 1480.
El valor en hexadecimal enviado es 0xB9 = 185 en base 10.

**d) Calcula el tamaño del mensaje que deberíamos enviar para que se generaran cuatro fragmentos de tamaño máximo. Para este cálculo hay que tener en cuenta cuánto ocupa la cabecera ICMP. La longitud de la cabecera ICMP hay que calcularla viendo cuánto ocupa cada uno de sus campos en la pestaña inferior de la captura.**

La cabecera IMCP tiene una longitud de 8 bytes. Por lo tanto, para que un mensaje de este protocolo ocupe exactamente 4 fragmentos, teniendo en cuenta que el tamaño máximo sea de 1500 bytes, tendría que ser de 5912 bytes. (1480 * 4) - 8.

**e) Comprueba que dicho tamaño de mensaje es correcto capturando el tráfico generado tras ejecutar nuevamente la orden ping sustituyendo 3972 por el tamaño de mensaje calculado.**

**f) ¿Cuántos bytes de datos IP viajan en cada paquete? ¿Y de datos ICMP? Para el cálculo puedes ayudarte de las cabeceras “Header Length” y “Total Length” del datagrama IP.**

En los 3 primeros paquetes viajan 1480 bytes de datos ICMP y 20 bytes de cabecera IP.
En el último en cambio, viajan 1480 bytes - 8 bytes de la cabecera ICMP. Es decir 1472 bytes de datos ICMP, 20 bytes de cabecera IP y 8 bytes de cabecera ICMP.

## Ejercicio 3.

Las MTUs de las redes 1 y 2 son 4500 y 800 respectivamente. En el computador B de la red 2 se han
recibido los siguientes datagramas IP. El emisor de dichos datagramas es el computador A de la red 1.

| Longitud total | Identificador | DF | MF | Desplazamiento |
| --- | --- | --- | --- | --- |
| 796 | 16 | 0 | 0 | 194 |
| 40 | 28 | 0 | 0 | 194 |
| 796 | 16 | 0 | 1 | 0 |
| 796 | 28 | 0 | 1 | 0 |
| 780 | 63 | 0 | 0 | 0 |
| 796 | 16 | 0 | 1 | 97 |
| 796 | 95 | 0 | 1 | 291 |
| 796 | 28 | 0 | 1 | 97 |
| 54 | 95 | 0 | 0 | 388 |

**a)  ¿Tienen alguna relación entre sí los distintos datagramas recibidos? Justifica la respuesta.**

Sí. Los datagramas recibidos con el mismo identificador son fragmentos del mismo datagrama emitido por el computador A.
Aquellos fragmentos con desplazamiento 0 son los primeros de ese datagrama. Mientras que los que tienen el flag MF a 0 son los últimos.

**b) Rellena la tabla con los valores de los datagramas cuando los emitió A.**

| Longitud total | Identificador | DF | MF | Desplazamiento |
| --- | --- | --- | --- | --- |
| 2348 | 16 | 0 | 0 | 0 |
| 1592 | 28 | 0 | 0 | 0 |
| 780 | 63 | 0 | 0 | 0 |
| 3138 | 95 | 0 | 0 | 0 |

**c) ¿Serán entregados al nivel superior todos los datagramas recibidos?**

No, el datagrama con identificador 95 no llega correctamente al host destino (host B), porque faltan fragmentos.
Este datagrama, al estar incompleto, no pasará al nivel superior y deberá ser reenviado.