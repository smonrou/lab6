# Practica de Laboratorio 6: JAVA PERSISTENCE API

## Descripción de la Practica
La administraci ́on de una editorial digital solicita crear una API REST para mantener la infor-
maci ́on de los art ́ıculos publicados en sus revistas. La API debe desarrollarse en Spring Boot. La

base de datos se debe crear en MySQL y para el acceso a los datos se usar ́a Spring Data JPA.
De la l ́ogica del negocio se conoce:
1. Una revista se identifica con un ISSN (International Standard Serial Number). Interesa almacenar el nombre de la revista, su tem ́atica principal, n ́umero de edici ́on, a ̃no de publicaci ́on, idioma, periodicidad (mensual, trimestral, semestral, anual) y una URL de portada.
2. Un autor se identifica con un c ́odigo interno autogenerado. De cada autor se registra:
nombre completo, correo electr ́onico institucional, afiliaci ́on (universidad o instituci ́on a la
que pertenece), pa ́ıs de origen y un resumen biogr ́afico.
3. Un art ́ıculo se identifica con un DOI (Digital Object Identifier). De cada art ́ıculo se necesita
conocer: t ́ıtulo, resumen (abstract), fecha de recepci ́on, fecha de publicaci ́on, n ́umero de
p ́aginas, idioma y estado (recibido, en revisi ́on, aceptado, publicado, rechazado). Cada
art ́ıculo pertenece a exactamente una revista, pero puede tener m ́ultiples autores
4. Cada autor puede escribir muchos art ́ıculos y un art ́ıculo puede ser escrito por varios
autores. Debiendo registrarse el orden de autor ́ıa (primer autor, segundo autor, etc.) y si
el autor es el autor corresponsal.

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/revistas` | Listar todas las revistas. |
| GET | `/api/revistas/{issn}/articulos` | Listar artículos de una revista específica (con paginación). |
| GET | `/api/autores` | Listar todos los autores. |
| GET | `/api/autores/{id}/articulos` | Listar artículos en los que participa un autor. |
| POST | `/api/articulos` | Crear un nuevo artículo con sus autores y palabras clave. |
| GET | `/api/articulos` | Buscar artículos con filtro sobre título usando LIKE. |
| GET | `/api/articulos/{doi}` | Obtener el detalle de un artículo, incluyendo autores. |
| PUT | `/api/articulos/{doi}` | Actualizar datos de un artículo existente. |
| PUT | `/api/autores` | Actualizar datos de un autor existente. |
| DELETE | `/api/articulos/{doi}` | Eliminar un artículo. |

## Objetivos cumplidos por el estudiante
- Se comprendió la funcionalidad de JPA
- Se reforzaron los conociemientos sobre paginación
- Manejo de excepciones globales en respuestas http
- Reforzamiento de manejo e implementación del modelo MVC

| Método | Endpoint | Descripción | Estado |
|--------|----------|-------------|--------|
| GET | `/api/revistas` | Listar todas las revistas. | Completado |
| GET | `/api/revistas/{issn}/articulos` | Listar artículos de una revista específica (con paginación). | Completado |
| GET | `/api/autores` | Listar todos los autores. | Completado |
| GET | `/api/autores/{id}/articulos` | Listar artículos en los que participa un autor. | Completado |
| POST | `/api/articulos` | Crear un nuevo artículo con sus autores y palabras clave. | Completado |
| GET | `/api/articulos` | Buscar artículos con filtro sobre título usando LIKE. | Completado |
| GET | `/api/articulos/{doi}` | Obtener el detalle de un artículo, incluyendo autores. | Completado |
| PUT | `/api/articulos/{doi}` | Actualizar datos de un artículo existente. | Completado |
| PUT | `/api/autores` | Actualizar datos de un autor existente. | Completado |
| DELETE | `/api/articulos/{doi}` | Eliminar un artículo. | Completado |

## Tests
En la capeta `src/main/resources/Bruno` se encuentran todos los folders con los tests con los que se verificó la funcionalidad del laboratorio.
Extraer o abrir desde Bruno para probarlos.

## Aclaración
Los inserts de la base de datos fueron realizados con IA con el proposito de tener suficientes datos de prueba.

## Fecha de Entrega: 17/04/2026
