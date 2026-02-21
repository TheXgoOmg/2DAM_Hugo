// ============================================================
// PLANTILLA MEMORIA PROYECTO FINAL - 2º DAM
// ============================================================
// USO: Rellena las variables de configuración de abajo y
//       llama a #show: proyecto.with(...) en tu documento.
// ============================================================

// --- IMPORTACIONES ---
#import "@preview/numbly:0.1.0": numbly   // numeración decimal

// ============================================================
// FUNCIÓN PRINCIPAL DE LA PLANTILLA
// ============================================================
#let proyecto(
  // [REQ: Portada] Datos del alumno y proyecto
  titulo: "Título del Proyecto",
  alumno: "Nombre del Alumno",
  tutor: "Nombre del Tutor/a",
  curso: "2024/2025",
  ciclo: "Desarrollo de Aplicaciones Multiplataforma",
  centro: "Nombre del Centro",
  // Logo del centro (ruta a la imagen, ej: "logo.png")
  // [REQ: Logo] Logotipo del centro en portada
  logo: none,
  // Contenido del documento
  body
) = {

  // ==========================================================
  // [REQ: Tipo de letra] Arial (similar: "Libertinus Sans")
  // Tamaño 11pt para el cuerpo del texto
  // ==========================================================
  set text(
    font: ("Arial", "Liberation Sans", "Helvetica", "Libertinus Sans"),
    size: 11pt,
    lang: "es",
  )

  // Resetea el estilo del código para que el resaltado funcione
  show raw: set text(
      font: ("Courier New", "DejaVu Sans Mono", "Consolas", "monospace"),
      size: 10pt,
      fill: black,  // evita que herede colores de los headings
  )

  show raw.where(block: true): it => block(
      fill: rgb("#f5f5f5"),
      inset: 1em,
      radius: 4pt,
      width: 100%,
      it,
  )

  // ==========================================================
  // [REQ: Márgenes] Izquierdo 2.5cm, resto 2cm
  // ==========================================================
  set page(
    paper: "a4",
    margin: (
      left: 2.5cm,
      right: 2cm,
      top: 2cm,
      bottom: 2cm,
    ),
    // [REQ: Numeración] Páginas numeradas (excepto portada)
    // El número se añade después de la portada (ver más abajo)
    numbering: none,  // se activa tras la portada
  )

  // ==========================================================
  // [REQ: Justificado] Texto justificado
  // ==========================================================
  set par(
    justify: true,
    // [REQ: Interlineado] 1.5 líneas
    leading: 0.65em,   // ~1.5 líneas para tamaño 11pt
    spacing: 1.4em,
  )

  // ==========================================================
  // [REQ: Estilos de capítulos/apartados] Headings numerados
  // con jerarquía decimal (1, 1.1, 1.1.1...)
  // ==========================================================
  set heading(numbering: "1.1.1.")

  show heading.where(level: 1): it => {
    pagebreak(weak: true)
    v(1.2em)
    text(size: 16pt, weight: "bold", fill: rgb("#1a3a5c"), it)
    v(0.6em)
    line(length: 100%, stroke: 0.5pt + rgb("#1a3a5c"))
    v(0.4em)
  }

  show heading.where(level: 2): it => {
    v(0.8em)
    text(size: 13pt, weight: "bold", fill: rgb("#2c5f8a"), it)
    v(0.3em)
  }

  show heading.where(level: 3): it => {
    v(0.6em)
    text(size: 11pt, weight: "bold", fill: rgb("#3a3a3a"), it)
    v(0.2em)
  }

  // ==========================================================
  // [REQ: Portada] Página de portada con todos los datos
  // ==========================================================
  page(
    margin: (left: 2.5cm, right: 2cm, top: 2cm, bottom: 2cm),
    numbering: none,
    header: none,
    footer: none,
  )[
    #set align(center)

    // [REQ: Logo] Logo del centro (si se proporciona)
    #if logo != none {
      image(logo, width: 6cm)
      v(0.5cm)
    } else {
      rect(
        width: 6cm, height: 3cm,
        stroke: 1pt + gray,
        fill: rgb("#f0f4f8"),
      )[
        #set align(center + horizon)
        #text(size: 9pt, fill: gray)[Logo del Centro]
      ]
    }

    #text(size: 13pt, fill: rgb("#2c5f8a"), style: "italic")[#centro]


    #line(length: 80%, stroke: 2pt + rgb("#1a3a5c"))
    #text(size: 22pt, weight: "bold", fill: rgb("#1a3a5c"))[#titulo]
    #line(length: 80%, stroke: 2pt + rgb("#1a3a5c"))


    #text(size: 12pt)[*Memoria del Proyecto Intermodular*]
    #v(0.3cm)
    #text(size: 11pt, fill: rgb("#555555"))[
      Ciclo Formativo de Grado Superior \
      #ciclo
    ]


    #grid(
      columns: (1fr, 1fr),
      gutter: 1cm,
      align(left)[
        #text(fill: rgb("#1a3a5c"), weight: "bold")[Alumno/a:]
        #linebreak()
        #text()[#alumno]
      ],
      align(left)[
        #text(fill: rgb("#1a3a5c"), weight: "bold")[Tutor/a:]
        #linebreak()
        #text()[#tutor]
      ],
    )


    #align(left)[
      #text(fill: rgb("#1a3a5c"), weight: "bold")[Curso académico:]
      #text()[ #curso]
    ]
  ]

  // ==========================================================
  // [REQ: Numeración de páginas] Activa numeración a partir
  // de aquí (después de la portada). Footer centrado.
  // ==========================================================
  set page(
    numbering: "1",
    number-align: center,
    footer: context {
      set text(size: 9pt, fill: gray)
      line(length: 100%, stroke: 0.3pt + gray)
      v(-0.3em)
      align(center)[#counter(page).display("1")]
    },
    header: context {
      set text(size: 9pt, fill: gray, style: "italic")
      if counter(page).get().first() > 1 {
        grid(
          columns: (1fr, 1fr),
          align(left)[#titulo],
          align(right)[#alumno],
        )
        line(length: 100%, stroke: 0.3pt + gray)
      }
    },
  )

  // Reset contador de páginas para que empiece en 1 tras portada
  counter(page).update(1)

  // ==========================================================
  // [REQ: Índice / Tabla de contenidos]
  // ==========================================================
  {
    set heading(numbering: none, outlined: false)
    heading(level: 1, supplement: none, outlined: false)[Índice]
  }
  outline(
    title: none,
    indent: 1.5em,
    depth: 3,
  )

  pagebreak()

  // ==========================================================
  // [REQ: Índice de figuras y tablas]
  // Se muestra al final automáticamente si se usan #figura()
  // y #tabla() definidas más abajo.
  // ==========================================================

  // Contenido del documento
  body

  // --- Índice de figuras ---
  pagebreak()
  {
    set heading(numbering: none, outlined: false)
    heading(level: 1, supplement: none, outlined: false)[Índice de Figuras e Imágenes]
  }
  outline(
    title: none,
    target: figure.where(kind: image),
  )

  v(1.5em)

  // --- Índice de tablas ---
  {
    set heading(numbering: none, outlined: false)
    heading(level: 1, supplement: none, outlined: false)[Índice de Tablas]
  }
  outline(
    title: none,
    target: figure.where(kind: table),
  )
}

// ============================================================
// FUNCIONES DE AYUDA PARA FIGURAS Y TABLAS
// ============================================================

// [REQ: Figuras numeradas] Inserta una figura con caption
// Uso: #figura(image("img.png"), "Descripción de la figura")
#let figura(contenido, caption-text, width: 80%) = {
  figure(
    align(center, block(width: width, contenido)),
    caption: caption-text,
    kind: image,
    supplement: "Figura",
  )
}

// [REQ: Tablas numeradas] Inserta una tabla con caption
// Uso: #tabla(columns: ..., [Col1], ..., caption: "Descripción")
#let tabla(caption: "", ..args) = {
  figure(
    table(..args),
    caption: caption,
    kind: table,
    supplement: "Tabla",
  )
}

// ============================================================
// FIN DE LA PLANTILLA
// ============================================================
