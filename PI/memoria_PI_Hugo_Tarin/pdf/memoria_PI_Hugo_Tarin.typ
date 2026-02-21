// ============================================================
// DOCUMENTO DE EJEMPLO — Memoria Proyecto Final 2º DAM
// ============================================================
// 1. Copia plantilla-dam.typ a la misma carpeta que este archivo
// 2. Sustituye los valores de configuración por los tuyos
// 3. Si tienes logo: pon la ruta, ej: logo: "logo.png"
// 4. Escribe tu memoria sustituyendo el contenido de ejemplo
// ============================================================

#import "plantilla.typ": proyecto, figura, tabla

#show: proyecto.with(
  titulo: "Baldur's Bones",
  alumno: "Hugo Tarín González",
  tutor: "Borja Aparisi Navarro",
  curso: "2025/2026",
  ciclo: "Desarrollo de Aplicaciones Multiplataforma",
  centro: "CEIPFP Cheste",
  logo: none,   // Cambia a: logo: "logo.png"  cuando tengas el logo
)

// ============================================================
// A partir de aquí escribe tu memoria normalmente
// Usa = para capítulos, == para apartados, === para subapartados
// ============================================================

= Introducción

Este proyecto consiste en un videojuego multiplataforma y multimedia, desarrollado en Unity, que contiene sistemas de movimiento basado en vectores, de gestión de escenas y de sonidos, además de scripts dedicados a detalles para que sea lo más cómodo y visual para el usuario.

== Justificación

Al comienzo del curso iba a desarrollar junto con otro alumno una plataforma de gestión de flujos de trabajo, pero con el paso de las semanas decidimos cambiar y hacer proyectos por separado, yo me decanté por llevar más allá el videojuego de PMDM, ya que ya había tenido la ilusión de programar un videojuego, y decidí que era el momento idóneo.

== Objetivos

Los objetivos principales del proyecto son los siguientes:
- Desarrollar una UI/UX satisfactoria para el usuario.
- Implementar un sistema de guardado de progreso para los datos del videojuego.
- Crear un flujo de juego equilibrado y con avances en el tiempo.
- Generar una ambientación y entorno inmersivo.
- Tener la opción de jugar en PC, móvil y con mando.

= Tecnologías

== Unity

La principal herramienta de desarrollo es Unity, ya que facilita infinitamente la tarea de desarrollar un videojuego, con muchas facilidades que incorpora que ha hecho viable el desarrollo y el cumplimiento de  de este proyecto.

Algunas de estas tecnologías que ofrece Unity y he implementado en el proyecto son las siguientes:

=== NavMesh

Es un sistema basado en IA que genera una malla sobre un objeto con una capa específica que utilizaremos como terreno (NavMesh), para permitir que nuestros enemigos (NavMeshAgent), lleguen a nuestro personaje (`agent.SetDestination(personaje)`), usando algoritmos de IA para encontrar el camino más rápido evitando obstaculos (NavMeshObstacle) o simplemente objetos demasiado grandes como para superarlos (configuración de los Agents). Además de crear links (OffMeshLink) para ir de un lado del NavhMesh a otro atajando.

En esta imagen se puede observar perfectamente la malla que crea NavMesh y como afectan los demás elementos del sistema.

#figura(
  image("img/img_navmesh.svg"),
  "Imagen visual de los elementos del NavMesh",
  width: 55%
)

Esto ha facilitado el apartado de la jugabilidad, que afecta directamente al objetivo de una UX satisfactoria.

#pagebreak()

=== UINavegation

Es el sistema que controla la selección de elementos en la pantalla que pueden ser accionados (Interactable) en la escena, teniendo la opción de establecer los cambios a elementoss vecinos de manera automática o manual.

#figura(
  image("img/img_uinavigation.png"),
  "Imagen real del flujo entre botones generados por UI Navigation del proyecto"
)

Esta función me hizo extremadamente fácil añadir la funcionalidad de los menús con el mando.

=== InputSystem

Es el último sistema para detectar entradas de cualquier dispositivo de Unity. Funciona con un concepto de acciones, las cuales se usan para realizar acciones en el código.

#figura(
  image("img/img_inputsystem.png"),
  "Imagen explicativa del uso de InputSystem"
)

En esta imagen se explican los conceptos de Maps, que agrupan una serie de Actions, las cuales tienen asociadas unos Bindings, que son las entradas de diferentes dispositivos que activan esa Action.

Se puede llamar al valor que devuelve cada acción por la siguiente estructura:
```csharp
  InputSystem_Actions.<Map>.<Action>.<Method>
```
Pudiendo ser Method un `isPressed()` o `ReadValue()`, según el tipo de dato que devuelva esa acción.

Esta tecnología me permitió implementar la funcionalidad de multiplataforma de una manera mucho más sencilla, ya que para implementar el ataque de mi personaje con el mando, por ejemplo, simplemente tenia que agregar el Binding correspondiente del botón que quiero que active la Action de Ataque.


=== Blend Tree

Es una tecnología que vive dentro del Animator, que es el sistema que gestiona las animaciones de los objetos. Específicamente Blend Tree se encarga de mezclar animaciones según unos parámetros. Si solo se utiliza un parámetro es un Blend Tree 1D, si utiliza 2 es un Blend Tree 2D.

Un ejemplo de esto puede ser el cambio de animación entre una animación Caminar y Correr, basándose en parámetro Velocidad. Por defecto es proporcional el aumento del uso de una animación u otra, en 0.5 de Velocidad utilizará el 50% de los datos de cada animación, pero esto puede cambiarse.

#figura(
  image("img/img_blendtree.png"),
  "Imagen sobre otro ejemplo de uso de Blend Tree 1D"
)

Esta tecnología me ayudo a crear una UX mucho más amigable y visual para el usuario, que de otra manera habría sido imposible, sabiendo mi poca experiencia en este sector.

=== PlayerPrefs

Este es el sistema más simple que ofrece Unity para guardar y cargar datos de forma persistente en la memoria del dispositivo. Tiene un formato clave-valor, aunque solo cuenta con tres tipos de datos, `Int`, `Float` y `String`.

Así se guarda un valor en la memoria del dispositivo:
```csharp
PlayerPrefs.SetInt("monedas", 150);
// Asina a la clave "monedas" el valor de 150 y la guarda en el sistema
```
Y así se carga un valor de la memoria al código:
```
int monedas = PlayerPrefs.GetInt("monedas", 0); // Devuelve 0 por defecto
// Busca el valor de la clave "monedas" y si no la encuentra devuelve el valor por defecto
```

Esta funcionalidad fue vital para poder hacer de manera simple y eficaz uno de los grandes objetivos del proyecto, un sistema de guardado de datos para mantener el progreso del usuario.

#pagebreak()

== Relación con asignaturas

=== Desarrollo de Interfaces

Las interfaces están en cada parte del juego, sinceramente les he puesto esmero, sobretodo en el apartado de multiplataforma, quería que la versión para ordenadores fuese jugable y cómoda, sin elementos que estorben, a la vez de que la de móviles fuese simple e intuitiva, sin dificultades.

Por lo que decidí hacer una versión única para cada dispositivo únicamente por esta funcionalidad, y mejorar la UX.

En cada una de las escenas del videojuego hay una estructura de interfaces, las cuales os mostraré más adelante, que se adapta dinámicamente a la pantalla desde la que se ejecuta, para que no hubiesen cambios imprevistos entre unas y otras.

=== Acceso a Datos

La aplicación del módulo de Acesso a Datos iba a ser más tedioso y complejo en mi cabeza de lo que acabó siendo, únicamente gracias a la funcionalidad de PlayerPrefs todos los problemas se fueron.

Como ya he explicado sobre esta tecnología, Unity guarda y carga los datos en los archivos del sistema mediante un formato clave-valor de la manera más simple posible, y únicamente con dos comandos puedes acceder y actualizar la información del progreso del usuario.

Así cumpliendo con los requisitos de este módulo.

=== Programación Multimedia y Dispositivos Móviles

Este es el módulo que más presente está en el proyecto, todos los conocimientos de scripts, programación, lógica de Unity, conceptos de desarrollo de videojuegos...

Todo esto lo he tenido que volcar en este proyecto. Uniendo todos los componentes que hacen de un videojuego algo multiplataforma y multimedia, además de mi búsqueda de hacerlo cómodo y entretenido, no únicamente un museo de buenas prácticas y código limpio y funcional.

=== Programación de Servicios y Procesos

La funcionalidad de este módulo en el proyecto es para ir al detalle con la elegancia y lo visual, ya que los hilos principales que hacen que el videojuego funcione los ejecuta únicamente Unity.

Aún así Unity también facilita la creación de hilos, usando Coroutinas.

El mayor uso que le he dado a estos hilos han sido como temporizador, que el sistema espere 3 segundos de manera paralela para ejecutar una acción.

Por ejemplo:

```csharp
  private IEnumerator MostrarGameOver(string escena, float velocidad)
  {
    yield return new WaitForSeconds(1.3f);
    
    SceneManager.LoadScene(escena, LoadSceneMode.Additive);
    CambiarVelocidad(velocidad);
  }
```
Aquí la función del hilo es hacer que cuando se ejecute MostrarGameOver espere 1.3 segundos antes de ejecutar el código después del `yield return`, lo que hace que quede más estético. Otro uso que le doy es hacer un `yield return` es hacer que espere 3 segundos antes de destruir el objeto de un enemigo, con un código que genera una desaparición muy visual.

= Desarrollo del proyecto

Ahora vamos a hablar más profundamente de la estructura y la implementación de todo lo que hace que el videojuego funcione correctamente.

== Ambientación

Al comienzo tenía muchas ideas que me ilusionaban para un videojuego, muchos enemigos, consumibles, objetos, equipaciones... Rápidamente me di cuenta de que no era realista, por lo que decidí comenzar por un solo enemigo, el cual casualmente fue el esqueleto que me dió la idea de la ambientación del entorno de mi videojuego... un cementerio.

#figura(
  image("img/img_ambientacion.png"),
  "Imagen sobre la ambientación del mapa en la que se desarrolla el videojuego.",
  width: 70%
)

Un cementerio, un gran cementerio avallado por grandes verjas, sin escapatoria. Dentro de él podemos ver un par de edificios con la misma temática tenebrosa y antigua, además de unos caminos de piedra y unas verjas más pequeñas destrozadas por 'algo'. Resaltando todo, hay cuatro estructuras de piedra, demasiado simétricas y estratégicamente colocadas como para ser casualidad ¿verdad?

Más adelante veremos su función.

#pagebreak()

== Sonido

El sonido también es una muy buena fuente de ambientación, tanto de sonidos naturales, generados por el entorno, como generados por otros objetos.

*Antes destacar* \
Para ambos tipos de sonido he creado un script que hace más fácil gestionar las pistas de audio de cada tipo de sonido, con la información necesaria para reproducirlas correctamente y asignándoles la pista de audio original. Además de añadirlas como una opción en Unity del apartado Create, para crear un objeto de estos scripts.

=== MusicManager

Este es el encargado de gestionar la música de fondo, que concuerde con la situación en la que estás. Cada menú tiene su música predeterminada, por lo que al cambiar de menú la música cambia.

Al estar presente en todas las escenas del juego decidí apli:car el patrón de diseño Singleton, lo cual es crear una instancia estática, muy llamada Instance en el mundo de C\#, para que solo existiese un objeto con este script en todo el proyecto, el cuál será el encargado de gestionar toda la música de fondo de todo el videojuego.

Para reproducir estos sonidos se usa un AudioSource general de toda la escena, no es un sonido 3D.

=== SoundManager

Este es el script que se encarga de gestionar efectos de sonido, una única vez por ejecución. Por la forma en el que lo he diseñado cada escena que tendrá la posibilidad de generar este tipo de sonidos tiene este script asignado a un objeto, además de un AudioSource que tendrá cada objeto con la capacidad de ejecutar estos sonidos.

He modificado los valores del Spatial Blend en todos los AudioSource de objetos externos al personaje de la escena de Juego, lo cual hace que tenga un cierto porcentaje de sonido 3D, para que tenga cierto punto de realismo e inmersión. Manteniendo los sonidos de menú completamente 2D.
