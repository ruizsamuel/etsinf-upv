# Práctica 13. NAT

## Ejercicio 1.

**1. ¿Por qué nos dice el dispositivo NAT que la dirección IP de la puerta de enlace es incorrecta?**

Porque las direcciones no pertenecen a la misma red.

**2. ¿Qué parámetro habría que cambiar para que la configuración fuera correcta? Propón un valor para dicho parámetro que haga correcta la configuración.**

La máscara de red a 255.255.254.0

**3. ¿Están los servidores DNS en la misma red que la interfaz configurada? En caso de no estarlo, ¿sería posible un correcto funcionamiento? Justifica la respuesta.**

No lo están. Funcionan correctamente porque el NAT se encarga de establecer la conexión a través de Internet.

## Ejercicio 2. HTTP_LAN_1

**1. ¿Cuántas conexiones TCP se realizan?**

Dos

**2. Localiza el datagrama que contiene la primera petición web “GET / HTTP/1.1”. Anota las direcciones IP y números de puerto TCP utilizados. Lógicamente,origen y destino corresponderán a cliente y servidor web, respectivamente.**

- Cliente: 192.168.1.181:52467
- Servidor: 158.42.4.23:80

**3. Localiza el datagrama que contiene la respuesta "HTTP 200 OK" enviada por el servidor web ¿Las direcciones IP y números de puerto corresponden a los de cliente y servidor encontrados en la petición web anterior?**

Sí, coinciden

**4. Respecto de la segunda conexión TCP, ¿intervienen el mismo cliente y servidor? Anota las direcciones IP y números de puerto TCP utilizados.**

Interviene el mismo cliente y distinto servidor. Es otra conexión distinta.

## Ejercicio 3. HTTP_ISP_1

**1. ¿En qué momento se transmite este mensaje? Anota las direcciones IP y números de puerto TCP utilizados ¿Corresponden origen y destino a cliente y servidor web, respectivamente?**

Despúes de que el host origen haya pedido al servidor los datos. 

- Cliente: 158.42.180.22:52467
- Servidor: 158.42.4.23:80

Sí, coincide el destino. No obstante el origen es el NAT, no el cliente.

**2.¿Cuál es la dirección IP pública del router NAT?**

158.42.180.22

**3. Respecto del datagrama correspondiente de la intranet ¿ha cambiado algún campo del mensaje HTTP? ¿y de la cabecera del datagrama IP? Para aquellos campos que se han modificado ¿cuál es el motivo?**

No ha cambiado ningún campo HTTP.

De la cabecera IP han cambiado el TTL, puesto que el datagrama ya ha dado un salto y la IP de origen.

**4. ¿En qué momento se recibe el mensaje? Basándote en las direcciones IP de este datagrama, ¿a quién va dirigida esta respuesta del servidor? Justifica por qué.**

Va dirigido al cliente original. La IP de destino coincide con la IP pública del NAT y el puerto con el socket abierto por el NAT asociado a esa conexión. Cuando el datagrama llegue a través de internet al router, traducirá ese destino y enviará la respuesta del servidor al host origen inicial que solicitó el recurso, cuyo socket también escucha en el mismo puerto (52467)

## Ejercicio 4.

**Con la información observada en los dos ejercicios anteriores, rellena la tabla de traducciones del router NAT, donde se recogen las correspondencias entre direcciones IP y números de puerto a ambos lados del router en las dos conexiones TCP.**
Conexión | Entrada desde LAN | Salida a red ISP |
|--|--|--|
| 1 | 192.168.1.181:52467 | 158.42.180.22:52467 |
| 2 | 192.168.1.181:39137 | 158.42.180.22:39137 |

## Ejercicio 5. HTTP_LAN_2 y HTTP_ISP_2

**1. ¿Cuáles son las direcciones IP y números de puerto usados por cliente y servidor?**

- Cliente: 192.168.1.2:4000
- Servidor: 158.42.53.72:80

**2. ¿Qué número de puerto origen asigna el router NAT a los segmentos de salida a la red pública en cada una de las dos conexiones?**

40000 y 40016

**3. ¿Qué cabeceras ha tenido que modificar el router NAT?**

En la segunda conexión ha modificado el puerto de origen (40000 -> 40016)

**4. ¿Cómo se puede saber cuál de las dos conexiones TCP corresponde a la contenida en el fichero HTTP_LAN_2?**

Se puede saber revisando la cabecera IDENTIFICATION del protocolo IP.
En este caso, la captura del LAN se corresponde con la segunda conexión de la captura ISP.

Conexión | Entrada desde LAN | Salida a red ISP |
|--|--|--|
| 1 | 192.168.1.1:40000 | 158.42.180.22:40016 |
| 2 | 192.168.1.2:40000 | 158.42.180.22:40000 |

## Ejercicio 6. FTP_LAN_1 y FTP_ISP_1

**1. ¿Cuáles son las direcciones IP de cliente y servidor?**

- Cliente: 192.168.1.2
- Servidor: 158.42.180.62

**2. Fíjate en uno cualquiera de los dos ficheros. Identifica las conexiones de control y datos e indica qué puertos utilizan cliente y servidor en cada una de ellas. En la conexión de datos, ¿se ha llevado a cabo una apertura en modo activo o pasivo?**

| Conexión | Puerto Cliente  | Puerto Servidor |
|--|--|--|
| Control | 40831 | 21 |
| Datos | 45162 | 20 |

La apertura de conexión se ha realizado en modo **activo** puesto que es el servidor el que inicia la conexión de datos y a continuación el cliente envía la orden PORT con los detalles de la conexión.

**3. En la siguiente tabla indica el orden de envío de los segmentos de inicio de conexión (flag SYN), transferencia de datos (mensaje FTP Data) y fin de conexión (flag FIN) de ambas conexiones (obvia los segmentos de reconocimiento).**

| Orden | Acción | Tipo de conexión | Flag TCP |
|--|--|--|--|
| 1 | Inicio de conexión | control | SYN |
| 2 | Inicio de conexión | datos | SYN |
| 3 | Transferencia de datos | datos | PSH |
| 4 | Fin de conexión | datos | FIN |
| 5 | Fin de conexión | control | FIN |

**4. Fíjate en el datagrama que se genera en la red privada en el instante 4.307125 y compáralo con su correspondiente en la red externa. ¿Qué cambia entre ambos? ¿Qué significan los valores 176 y 106 y qué valor decimal representan?**

- LAN: PORT 192,168,1,2,176,106
- ISP: PORT 152,42,180,22,176,106

Cambian los 4 primeros numeros, que se corresponden con la IP del cliente a la que se debe conectar el servidor para transferir los datos.
Los últimos dos valores, 176 y 106, representan el puerto, que en este caso, coincide tanto dentro como fuera del NAT.
El puerto se calcula de la siguiente manera, n5 * 256 + n6:

```
puerto = 176 * 256 + 106 = 45162
```

Puede observarse que efectivamente coincide con el puerto antes anotado que se utilizó para la conexión de datos FTP.

## Ejercicio 7. SSH_LAN_1 y SSH_ISP_1

**1. ¿Cuáles son las direcciones IP de cliente y servidor ssh y los números de puerto utilizados por cada uno de ellos?**

- Cliente: 158.42.180.21
- Servidor (dentro del NAT): 192.168.1.2

**2. ¿Qué diferencias observas en los datagramas y segmentos capturados dentro y fuera de la intranet? ¿Se está modificando el contenido de los mensajes ssh, como ocurría, como en el caso de ftp?**

A diferencia del caso de FTP (donde se modifican las direcciones IP y los números de puerto en el contenido del mensaje), en general, los mensajes SSH no se modifican en su contenido cuando atraviesan un NAT con port forwarding. SSH utiliza encriptación de extremo a extremo, lo que significa que el contenido del mensaje permanece cifrado y no se altera durante el enrutamiento a través del NAT.