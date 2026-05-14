# FastFix — Backend

> API y lógica de negocio de FastFix: la plataforma que conecta a clientes con técnicos especializados en electricidad, carpintería y otros trabajos de mantenimiento.

## Tabla de contenidos

- [Sobre este repositorio](#sobre-este-repositorio)
- [Tecnologías](#tecnologías)
- [Requisitos previos](#requisitos-previos)
- [Variables de entorno](#variables-de-entorno)
- [Ejecución local sin Docker](#ejecución-local-sin-docker)
- [Ejecución con Docker](#ejecución-con-docker)
- [Comprobar que el backend arrancó](#comprobar-que-el-backend-arrancó)
- [Recomendación de uso](#recomendación-de-uso)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Solución de problemas](#solución-de-problemas)
- [Contacto](#contacto)

## Sobre este repositorio

Este repositorio contiene el **backend** de FastFix. Su responsabilidad es exponer la API, aplicar la lógica de negocio (búsqueda de técnicos por sector, gestión de reseñas, integración con la API de WhatsApp) y gestionar el acceso a los datos y a los servicios externos.

Forma parte de una arquitectura hexagonal y puede ejecutarse de dos maneras:

- **De forma independiente**, para validar la API o revisar el comportamiento del servidor (lo que documenta este README).
- **Integrado en el launcher**, para arrancar la aplicación completa (frontend + backend) con un solo comando. Esta es la forma recomendada para probar FastFix completo.

## Tecnologías

- **Java 17** — lenguaje de programación.
- **Spring Boot 3.5** — framework para la API y la lógica de la aplicación.
- **Maven** — gestión de dependencias y construcción.
- **Neon** — base de datos relacional PostgreSQL alojada en la nube.
- **Cloudinary** — almacenamiento de imágenes.

## Requisitos previos

Para ejecutar el backend de forma local **sin Docker** necesitas:

- **Java 17** (JDK 17).
- **Maven** 3.8 o superior.

Si tu objetivo es levantar la aplicación completa con el menor número de pasos, lo recomendable es usar el **launcher** (repositorio fastfix-launcher) con Docker Desktop.

Este repositorio incluye `Dockerfile`, `.dockerignore` y `.env.example`. El archivo de ejemplo muestra las variables requeridas, pero **no contiene valores reales de acceso**.

## Variables de entorno

El backend necesita variables para la conexión con la base de datos en la nube (Neon) y con el servicio de almacenamiento de imágenes (Cloudinary). Estas variables se leen desde el entorno y **no deben escribirse directamente en el código fuente**.

| Variable | Descripción |
|----------|-------------|
| `NEON_URL` | URL de conexión a la base de datos Neon. |
| `NEON_USER` | Usuario de la base de datos. |
| `NEON_PASSWORD` | Contraseña de la base de datos. |
| `CLOUDINARY_URL` | URL de configuración de Cloudinary. |

> Consulta `.env.example` para ver la estructura exacta esperada.

Tienes dos formas de obtener valores válidos:

1. **Para una revisión o prueba con datos reales:** solicita el archivo `.env` al autor (ver [Contacto](#contacto)). Así usarás la base de datos y el almacenamiento ya poblados.
2. **Para una instancia independiente:** crea tus propias cuentas en [Neon](https://neon.tech) y [Cloudinary](https://cloudinary.com), y rellena las variables con tus propias credenciales. En este caso la base de datos arrancará vacía (sin técnicos ni reseñas).

Por seguridad, no se publican credenciales reales en GitHub.

## Ejecución local sin Docker

Spring Boot necesita las cuatro variables de entorno (`NEON_URL`, `NEON_USER`, `NEON_PASSWORD`, `CLOUDINARY_URL`) disponibles **antes** de arrancar. Tener el archivo `.env` en la carpeta no es suficiente para la ejecución local: ese archivo lo consume Docker mediante `--env-file`, pero Spring Boot por defecto no lee archivos `.env`. Para arrancar desde el IDE o la terminal sin Docker, define las variables de una de estas dos formas.

### Opción A — Desde IntelliJ IDEA (recomendada si usas este IDE)

1. Ve a **Run → Edit Configurations...**
2. Selecciona la configuración de ejecución de la aplicación (la clase con `@SpringBootApplication`). Si no existe, créala con el botón **+** como configuración de tipo *Spring Boot* o *Application*.
3. En el campo **Environment variables** añade las cuatro variables. Puedes escribirlas separadas por punto y coma:

```
   NEON_URL=<valor>;NEON_USER=<valor>;NEON_PASSWORD=<valor>;CLOUDINARY_URL=<valor>
```

   O pulsar el icono al final del campo para abrir el editor en forma de tabla.
4. Pulsa **Apply** y luego **Run**.

> Mantén esa configuración como local (no marques "Store as project file" y conserva `.idea/` en el `.gitignore`) para no subir las credenciales al repositorio.

### Opción B — Desde la terminal

En Linux o macOS, exporta las variables en la misma sesión de terminal:

```bash
export NEON_URL="<valor>"
export NEON_USER="<valor>"
export NEON_PASSWORD="<valor>"
export CLOUDINARY_URL="<valor>"
```

En Windows (PowerShell):

```powershell
$env:NEON_URL="<valor>"
$env:NEON_USER="<valor>"
$env:NEON_PASSWORD="<valor>"
$env:CLOUDINARY_URL="<valor>"
```

Después, compila y arranca la aplicación con Maven desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

Si prefieres generar primero el artefacto compilado:

```bash
mvn clean package -DskipTests
```

Esto genera un archivo `.jar` en la carpeta `target`. Para ejecutarlo (las variables de entorno deben seguir disponibles en la sesión):

```bash
java -jar target/<nombre-del-artefacto>.jar
```


## Ejecución con Docker

Construye la imagen desde la raíz del proyecto:

```bash
docker build -t fastfix-backend .
```

Para levantar el contenedor, pasa las variables de entorno mediante un archivo `--env-file` en lugar de escribirlas en la línea de comandos:

```bash
docker run --rm -p 8080:8080 --env-file .env fastfix-backend
```

> **Por qué `--env-file` y no `-e` en la línea de comandos:** pasar contraseñas con `-e NEON_PASSWORD="..."` deja las credenciales en el historial del shell y visibles con `docker inspect`. Usar un archivo `.env` (que debe estar en `.gitignore`) es más seguro y más cómodo.

El backend quedará expuesto localmente en `http://localhost:8080`.

## Comprobar que el backend arrancó

Si el arranque es correcto, la aplicación Spring Boot mostrará en consola los mensajes de inicio y la conexión con la base de datos. A partir de ahí, la API responde en `http://localhost:8080`.

## Recomendación de uso

Si solo quieres validar la API o revisar el comportamiento del servidor, este repositorio puede arrancarse de forma aislada. Si lo que quieres es ejecutar la aplicación completa con frontend y backend ya conectados, usa el **launcher** como punto principal de entrada: evita arrancar ambos proyectos manualmente y reduce los errores de configuración al trabajar en otro ordenador.

## Estructura del proyecto

```
fastfix-backend/
├── .mvn/                                    
├── src/
│   └── main/
│       └── java/
│           └── com/juanjose/backendfastfix/
│               ├── application/             # Capa de aplicación (casos de uso)
│               │   ├── port/
│               │   │   ├── in/              # Puertos de entrada
│               │   │   └── out/             # Puertos de salida
│               │   ├── service/             # Servicios de la capa de aplicación
│               │   
│               ├── domain/                  # Capa de dominio
│               │   ├── exception/           # Excepciones de dominio
│               │   └── model/               # Modelos / entidades de dominio
│               ├── infrastructure/          # Capa de infraestructura (adaptadores)
│               │   ├── adapter/
│               │   │   ├── in/
│               │   │   │   └── rest/        # Adaptador de entrada REST
│               │   │   │       ├── controller/ # Endpoints de la aplicación
│               │   │   │       ├── dto/        # Objetos de transferencia de datos
│               │   │   │       ├── exception/ # Excepciones y respuestas http
│               │   │   │       └── mapper/  # Traductores de solicitudes REST
│               │   │   └── out/             # Adaptadores de salida
│               │   │       ├── cloudinary/  # Integración con Cloudinary
│               │   │       ├── persistance/ # Acceso a la base de datos
│               │   │       └── security/    # Seguridad
│               │   └── config/              # Configuración de Spring
│               └── BackendFastFixApplication.java   # Clase principal de Spring Boot
├
```

## Solución de problemas

| Problema | Posible causa y solución |
|----------|--------------------------|
| Error de versión de Java al compilar | Comprueba que tienes instalado el JDK 17 (`java -version`). |
| `mvn` no se reconoce como comando | Maven no está instalado o no está en el `PATH`. |
| Falla la conexión a la base de datos | Las variables `NEON_*` no están definidas o son incorrectas. Revisa tu `.env`. |
| Error con las imágenes | `CLOUDINARY_URL` ausente o incorrecta. |
| Puerto 8080 ya en uso | Otra aplicación lo está ocupando. Ciérrala o cambia el puerto de exposición. |
| El contenedor Docker no lee las variables | Comprueba que el archivo `.env` existe y la ruta pasada a `--env-file` es correcta. |

## Contacto

Para solicitar el archivo `.env` con las credenciales reales o para cualquier consulta:

- **Autor:** Juan José Soto Alcaraz
- **Correo:** <jjsavixxxiii@gmail.com>
