# Proyecto web_SassScss

## Requisitos

### 1. Tres Botones

**Descripción**: Hay tres tipos de botones con estilos diferenciados: primario, outline y submit.
**Ubicación**: `web_SassScss/sass/components/_buttons.scss`
**Líneas**:
*   **`.btn--primary`**: ~ Líneas 17-21
*   **`.btn--outline`**: ~ Líneas 24-29
*   **`.btn--submit`**: ~ Líneas 32-38

---

### 2. Nesting (Anidamiento)

**Descripción**: Se utiliza para organizar estilos de forma jerárquica, mejorando la legibilidad y el mantenimiento.
**Ubicación**: Se utiliza en casi todos los archivos de componentes y diseño, por ejemplo:
*   `web_SassScss/sass/base/_typography.scss` (ej. `.hero__title span`, ~ Línea 18)
*   `web_SassScss/sass/layout/_hero.scss` (ej. `&__actions`, ~ Línea 21; `.code-window__header`, ~ Línea 37)
*   `web_SassScss/sass/components/_buttons.scss` (ej. `&:hover`, ~ Línea 13; `&--primary`, ~ Línea 17)
*   `web_SassScss/sass/components/_forms.scss` (ej. `.contact-info .contact-method i`, ~ Línea 72)

---

### 3. Variables Globales y Locales (con `!default`)

**Descripción**: He creado variables globales utilizando `!default` para permitir su sobrescritura, y se ha utilizado una variable local en un componente.
**Ubicación**:
*   **Variables Globales**: `web_SassScss/sass/abstracts/_variables.scss` (~ Líneas 2-11)
*   **Variable Local**: `web_SassScss/sass/components/_buttons.scss` (`$submit-color`, ~ Línea 34)

---

### 4. Selector Placeholder (uno o más)

**Descripción**: He creado selectores placeholder para reutilizar conjuntos de propiedades sin generar duplicados en el CSS final, y se han extendido en componentes.
**Ubicación**:
*   **Definidos en**: `web_SassScss/sass/abstracts/_placeholders.scss`
    *   `%card-skeleton` (~ Líneas 4-13)
    *   `%text-gradient` (~ Líneas 16-20)
*   **Usados en (`@extend`)**:
    *   `web_SassScss/sass/base/_typography.scss` (`@extend %text-gradient;`, ~ Línea 18)
    *   `web_SassScss/sass/components/_cards.scss` (`@extend %card-skeleton;`, ~ Línea 5)
    *   `web_SassScss/sass/components/_forms.scss` (`@extend %card-skeleton;`, ~ Línea 5)

---

### 5. Función `calc()`

**Descripción**: La función `calc()` se utiliza para realizar cálculos dinámicos en propiedades CSS, combinando unidades fijas y relativas.
**Ubicación**:
*   `web_SassScss/sass/abstracts/_placeholders.scss` (`padding: calc(vars.$spacing-unit * 2);`, ~ Línea 7)
*   `web_SassScss/sass/base/_reset.scss` (`font-size: calc(14px + 0.25vw);`, ~ Línea 11)
*   `web_SassScss/sass/base/_typography.scss` (`font-size: calc(2rem + 2vw);`, ~ Línea 15; `margin-bottom: calc(vars.$spacing-unit * 0.5);`, ~ Línea 35)
*   `web_SassScss/sass/layout/_grid.scss` (`padding: 0 calc(vars.$spacing-unit * 2);`, ~ Línea 8; `gap: calc(vars.$spacing-unit * 2);`, ~ Línea 15; `margin-top: calc(vars.$spacing-unit * 3);`, ~ Línea 16; `padding: calc(vars.$spacing-unit * 5) 0;`, ~ Línea 19)
*   `web_SassScss/sass/components/_buttons.scss` (`margin-top: calc(vars.$spacing-unit * 1.5);`, ~ Línea 37)
*   `web_SassScss/sass/components/_forms.scss` (`gap: calc(vars.$spacing-unit * 0.8);`, ~ Línea 51)

---

### 6. Lista de Elementos y Funciones de Lista

**Descripción**: Hay una lista de redes sociales y se utilizan funciones de lista de Sass para procesarla y mostrar información dinámica.
**Ubicación**:
*   **Definición de lista**: `web_SassScss/sass/abstracts/_variables.scss` (`$social-channels`, ~ Línea 11)
*   **Uso de funciones `list.length()` y `list.join()`**: `web_SassScss/sass/layout/_footer.scss` (~ Líneas 16-17 para `$count` y `$list-text`)

---

### 7. Mapa (Map) y Funciones de Mapa

**Descripción**: Existe un mapa para almacenar colores de estado y se han utilizado funciones de mapa para acceder a sus valores de forma segura.
**Ubicación**:
*   **Definición del Mapa en**: `web_SassScss/sass/abstracts/_maps.scss` (`$status-colors`, ~ Líneas 3-8)
*   **Función `get-status()` (con `map.has-key()` y `map.get()`):** `web_SassScss/sass/abstracts/_maps.scss` (~ Líneas 11-17)
*   **Uso de `maps.get-status()` en**:
    *   `web_SassScss/sass/components/_buttons.scss` (`$submit-color: maps.get-status("success");`, ~ Línea 34)
    *   `web_SassScss/sass/layout/_footer.scss` (`color: maps.get-status("info");`, ~ Línea 20)
    *   `web_SassScss/sass/components/_forms.scss` (`i { color: maps.get-status("info"); }`, ~ Línea 72)
