# Práctica 9. Configuración TCP/IP en Windows y Linux

## Ejercico 1. 
**Ejecuta la orden ipconfig /all e identifica la información siguiente relativa al adaptador Ethernet 4.**

**Dirección física**: E8:EA:6A:09:76:DF

**Dirección IPv4**: 158.42.180.18

**Máscara subred**: 255.255.254.0

**Gateway**: 158.42.181.250

**Servidores DNS**: 158.42.250.65 / 158.42.250.195

**Servidor DHCP**: 158.42.250.5

**Dirección de red**: 158.42.100.0/23

**¿Servidores DNS y DHCP en la misma subred?**: Sí, porque sus direcciones de red son iguales (158.42.250.XXX)

### Comprueba el contenido de la caché DNS. Anota en la tabla los valores de uno de los registros que aparecen que sea de tipo 1:

```
>_ ipconfig /displaydns
```
|                   |                |
|-------------------|----------------|
| Tipo registro     | 1              |
| Nombre de registro| LEVC.cam.upv.es|
| Valor registro    | 158.42.212.10  |

## Ejercicio 3. 
**Visualiza la tabla de encaminamiento (apartado IPv4) del ordenador en el que estás trabajando. Averigua las direcciones IP de los destinos siguientes (recuerda los ejercicios anteriores) y analiza qué ruta de la tabla se seleccionaría para cada uno de ellos:**

a) **zoltar.upv.redes.upv**: 158.42.180.62 -> 158.42.180.0 // 255.255.254.0
b) **www.upv.es**: 158.42.4.23 -> 0.0.0.0
b) **www.usc.edu**: 18.154.22.81 -> 0.0.0.0

## Ejercicio 4. 
**La orden netstat –e proporciona estadísticas sobre el número de bytes y tramas enviadas y recibidas por el adaptador de red. Se detalla el número de tramas unicast (un solo destino), no unicast (múltiples destinos y difusiones), paquetes erróneos y descartados. Ejecuta esta orden y observa los resultados (paquetes enviados y recibidos) que cumplan las condiciones siguientes:**

```
>_ netstat -e
```

**Paquetes unicast**:       421980      84115

**Paquetes no unicast**:    339575      2418

**Descartados**:            0           0

**Errores**:                0           0

## Ejercicio 5.
**La orden netstat –sp IP produce estadísticas sobre el tráfico IP. Ejecuta esta orden y analiza la respuesta para averiguar la cantidad de:**

```
>_ netstat -sp IP
```

**Paquetes recibidos**: 54671

**Errores de encabezado recibidos**: 0

**Errores de dirección recibidos**: 1

**Datagramas reenviados**: 0

**Protocolos desconocidos recibidos**: 0

**Datagramas correctamente fragmentados**: 0

## Ejercicio 6. 
**Análogamente la orden netstat –sp TCP produce estadísticas sobre el tráfico TCP (también se pueden solicitar estadísticas sobre los protocolos ICMP y UDP). Ejecuta esta orden e identifica la cantidad de:**

```
>_ netstat -sp TCP
```

**Activos abiertos**: 693

**Pasivos abiertos**: 1

**Intentos de conexión erróneos**: 23

**Conexiones actuales**: 10

## Ejercicio 7. 
**Utiliza la orden ip para averiguar cuántos adaptadores de red existen en tu computador, cual es la dirección IP y máscara de red de cada uno de ellos. Interpreta si permiten difusiones (broadcast), y multidifusión (multicast) y cuál es la MTU de la red accesible a través de cada uno de ellos. Verifica, además, si son direcciones permanentes o deben ser renovadas periódicamente, así como si son globales o locales.**

```bash
$ ip address show
```

```
6: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc mq state UP group default qlen 1000
    link/ether 00:15:5d:b1:23:5c brd ff:ff:ff:ff:ff:ff
    inet 172.27.37.97/20 brd 172.27.47.255 scope global eth0
       valid_lft forever preferred_lft forever
    inet6 fe80::215:5dff:feb1:235c/64 scope link
       valid_lft forever preferred_lft forever
```

En este caso, la interfaz eth0 permite BROADCAST y MULTICAST. El MTU es de 1500. Su IP es 172.27.37.97 y el prefijo de red es 20.
La dirección IPv4 es de alcance global y duración permanente (forever).

## Ejercicio 8. 
**Accede a la página web principal de la UPV (www.upv.es) empleando un navegador como Firefox. A continuación, utiliza la orden ip para asignar la dirección IP correspondiente (158.42.4.23/16) a tu adaptador lo. Puedes instalar tu propio “servidor web” para ver la petición del cliente empleando “sudo nc -l 80” en otro terminal. Repite el acceso web (asegúrate de que empleas http://) y comprueba la respuesta del terminal. Revierte el cambio, eliminando esta dirección IP de tu adaptador, y comprueba nuevamente el acceso.**

Al asignarle la dirección IP de la web de la UPV a la dirección de loopback, al acceder con el navegador a esta, el tráfico se redirige a la interfaz lo, transmitiendo la petición HTTP al servidor de netcat, mostrando por el terminal el siguiente texto:

```
GET / HTTP/1.1
Host: www.upv.es
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/110.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8
Accept-Language: es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3
Accept-Encoding: gzip, deflate
Connection: keep-alive
Upgrade-Insecure-Requests: 1
```

## Ejercicio 9.
**Utiliza la orden ip -s link show para averiguar cuántos adaptadores de red existen y el número de bytes transmitidos y recibidos a través de cada uno de ellos. Comprueba que las cifras se incrementan en el adaptador adecuado tras ejecutar ping -c 2 localhost y ping -c 2 www.rediris.es**

Comprobado. :)

## Ejercicio 10.
Incompleto...