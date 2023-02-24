# proyectoHotel
Proyecto para el manejo de las reservas de un hotel

Este proyecto fue realizado usando Spring Boot y MySQL, siguiendo los patrones de arquitectura hexagonal, con pruebas
unitarias con JUnit, Mockito y Jacoco.

## Instalacion

# Con una base de datos local
Para el funcionamiento y descarga de este proyecto se debe tener:
* Un servidor MySQL con una base de datos llamada hotel comunicandose con el puerto 3306.
* Jdk 17.
* Descargar este proyecto y ejecutar el archivo ServiciosHotelApplication

# Usando docker o docker desktop
Solo se necesita tener instalado:
* Descargar el archivo hotel.jar ubicado en la raiz de este proyecto.
* Descargar los archivos Dockerfile y docker-compose.yaml ubicados en la raiz de este proyecto
* Ejecutar el comando: docker build -t hotel-imagen:1 .
* Ejecutar el comando: docker-compose up -d

## Documentacion

Documentacion de los test disponible en: 
http://localhost:63342/demo/build/reports/tests/test/index.html?_ijt=dg8od33ldj9niej7q7het0q2ud&_ij_reload=RELOAD_ON_SAVE

Documentación disponible en la ruta: 
http://localhost:8080/swagger-ui.html

## Autor 
Yesid Ortiz
