# Práctica 1 (Unidad 2.2)

## 1. Ejercicios sobre la base de datos CINE

![Esquema de la estructura de la base de datos](.pics/CINE.png "Esquema CINE")

## 1.1 Consultas sobre una sola tabla

1. **Obtener ordenados ascendentemente los códigos de los países de donde son los actores**

```sql
SELECT DISTINCT cod_pais
FROM actor
ORDER BY cod_pais;
```

2. **Obtener el código y el título de las películas de año anterior a 1970 que no estén basadas en ningún libro ordenadas por título.**

```sql
SELECT cod_peli, titulo
FROM pelicula
WHERE anyo < 1970 AND cod_lib IS NULL
ORDER BY titulo;
```

3. **Obtener el código y el nombre de los actores cuyo nombre incluye “John”.**

```sql
SELECT cod_act, nombre
FROM actor
WHERE nombre LIKE '%John%'
```

4. **Obtener el código y el título de las películas de más de 120 minutos de la década de los 80.**

```sql
SELECT cod_peli, titulo
FROM pelicula
WHERE duracion > 120 AND anyo BETWEEN 1980 AND 1989
ORDER BY titulo
```

5. **Obtener el código y el título de las películas que estén basadas en algún libro y cuyo director se apellide ‘Pakula’.**

```sql
SELECT cod_peli, titulo
FROM pelicula
WHERE cod_lib is not null and director LIKE '% Pakula%'
```

6. **¿Cuántas películas hay de más de 120 minutos de la década de los 80?**

```sql
SELECT count(*)
FROM pelicula
WHERE duracion > 120 AND anyo BETWEEN 1980 AND 1989
ORDER BY titulo
```

7. **¿Cuántas películas se han clasificado de los géneros de código 'BB5' o 'GG4' o'JH6'.**

```sql
SELECT count(DISTINCT cod_peli)
FROM clasificacion
WHERE cod_gen IN('BB5','GG4','JH6')
```

8. **¿De qué año es el libro más antiguo?**

```sql
SELECT MIN(anyo)
FROM libro_peli
```

9. **¿Cuál es la duración media de las películas del año 1987?**

```sql
SELECT AVG(duracion)
FROM pelicula
WHERE anyo=1987
```

10. **¿Cuántos minutos ocupan todas las películas dirigidas por ‘Steven Spielberg’?**

```sql
SELECT SUM(duracion)
FROM pelicula
WHERE director='Steven Spielberg'
```

## 1.2 Consultas sobre varias tablas

11. **Obtener el código y el título de las películas en las que actúa un actor con el mismo nombre que el director de la película (ordenadas por título).**

```sql
SELECT p.cod_peli, p.titulo
FROM pelicula p, actua act, actor atr
WHERE act.cod_act=atr.cod_act and act.cod_peli=p.cod_peli and p.director=atr.nombre
ORDER BY p.titulo
```

12. **Obtener el código y el título de las películas clasificadas del género de nombre ‘Comedia’ (ordenadas por título)**

```sql
SELECT p.cod_peli, p.titulo
FROM pelicula p, clasificacion c, genero g
WHERE g.nombre='Comedia' and g.cod_gen=c.cod_gen and c.cod_peli=p.cod_peli
ORDER BY p.titulo
```

13. **Obtener el código y el título de las películas basadas en algún libro anterior a 1950.**

```sql
SELECT cod_peli, pelicula.titulo
FROM pelicula, libro_peli l
WHERE pelicula.cod_lib = l.cod_lib AND libro.anyo < 1950
```

14. **Obtener el código y el nombre de los países de los actores que actúan en películas clasificadas del género de nombre ‘Comedia’ (ordenados por nombre).**

```sql
SELECT DISTINCT pa.cod_pais, pa.nombre
FROM pais pa, actor atr, actua act, pelicula pe, clasificacion c, genero g
WHERE g.nombre='Comedia' and g.cod_gen=c.cod_gen and c.cod_peli=act.cod_peli and act.cod_act=atr.cod_act and atr.cod_pais=pa.cod_pais
ORDER BY pa.nombre
```