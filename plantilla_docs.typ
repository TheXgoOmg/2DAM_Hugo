#let proyecto(titulo: "", asignatura: "", imagen_portada: none, body) = {
  let autor = "Hugo Tarín González"
  let curso = "2025/26"

  // Configuración general del documento
  set document(title: titulo, author: autor)
  set text(lang: "es", size: 11pt)
  
  // Configuración de página y reglas de numeración
  set page(
    paper: "a4",
    header: context {
      set text(8pt, style: "italic")
      titulo
      line(length: 100%, stroke: 0.5pt)
    },
    footer: context {
      let n_pag = counter(page).at(here()).first()
      set text(9pt)
      autor
      if n_pag >= 3 {
        h(1fr)
        str(n_pag) // Empieza a contar desde 1 en la página 3
      }
    }
  )

  // --- REGLA 1: PORTADA ---
  align(center + horizon)[
    #block(text(weight: "bold", 26pt, titulo))
    #v(1cm)
    #if imagen_portada != none {
      block(width: 60%, imagen_portada)
      v(1cm)
    }
    #text(14pt)[#asignatura \ Curso #curso]
    #v(0.5cm)
    #text(12pt)[#autor]
  ]
  pagebreak()

  // --- REGLA 2: ÍNDICE ---
  outline(title: "Índice", indent: auto)
 pagebreak()

  // El contenido del trabajo se inserta aquí
  body
}
