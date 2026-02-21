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

Este proyecto consiste en el desarrollo de una aplicación de gestión de inventario para pequeñas y medianas empresas. La aplicación permite controlar el stock de productos en tiempo real desde dispositivos Android.

== Motivación

La motivación principal surge de la necesidad detectada en el entorno empresarial local de disponer de herramientas ágiles y accesibles para la gestión del inventario sin depender de hardware específico.

== Objetivos

Los objetivos principales del proyecto son los siguientes:

- Desarrollar una aplicación Android funcional e intuitiva.
- Implementar un backend REST con Spring Boot.
- Integrar una base de datos relacional MySQL.
- Garantizar la seguridad mediante autenticación JWT.

