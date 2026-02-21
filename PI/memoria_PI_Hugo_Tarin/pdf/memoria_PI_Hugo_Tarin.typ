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

Es un sistema basado en IA que genera una malla sobre un objeto con una capa específica que utilizaremos como terreno, para permitir que nuestros enemigos (NavMeshAgent), lleguen a nuestro personaje (agent.SetDestination(personaje)), usando algoritmos de IA para encontrar el camino más rápido evitando obstaculos (NavMeshObstacle) o simplemente objetos demasiado grandes como para superarlos (configuración de los Agents).

Esto ha facilitado el apartado de la jugabilidad, que afecta directamente al objetivo de una UX satisfactoria.

=== UINavegation

Es el sistema que controla la selección de elementos en la pantalla que pueden ser accionados (Interactable) en la escena, teniendo la opción de establecer los cambios a elementoss vecinos de manera automática o manual.

Esta función me hizo extremadamente fácil añadir la funcionalidad de los menús con el mando.

=== InputSystem

Es el último sistema para detectar entradas de cualquier dispositivo de Unity. Funciona con un concepto de acciones, las cuales se usan para realizar acciones en el código.


=== BlendTree
