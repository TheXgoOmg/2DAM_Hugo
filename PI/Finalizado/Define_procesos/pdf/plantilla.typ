#let proyecto(titulo: "", asignatura: "", imagen: none, body) = {
  // Enumeración automática
  set heading(numbering: "a).")

  // Estilo para títulos de NIVEL 1
  show heading.where(level: 1): set text(size: 1.1em, weight: "semibold", fill: luma(30))
  show heading.where(level: 1): set block(above: 1.3em, below: 0.8em)

  show raw.where(block: true): it => pad(left: 2em, it)   

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
    #if imagen != none {
      set image(height: 4cm) // Ajusta el tamaño de la imagen
      imagen
    }
    #v(1cm)
    #block(text(weight: "bold", 26pt, titulo))
    #v(1cm)
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
