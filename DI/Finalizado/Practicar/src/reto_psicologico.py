import flet as ft
from flet.controls.border_radius import horizontal


def main(page: ft.Page):

    def switch_clicado(e):
        page.theme_mode = (
            ft.ThemeMode.LIGHT
            if page.theme_mode == ft.ThemeMode.DARK
            else ft.ThemeMode.DARK
        )
        switch.label = (
            "Dark Mode"
            if page.theme_mode == ft.ThemeMode.DARK
            else "Light Mode"
        )

    def mostrar_texto(e):

        if e.control.border_radius.top_left > 50:
            texto_layout.value = "CIRCULO"
            texto_layout.color = ft.Colors.RED
        else:
            texto_layout.value = "CUADRADO"
            texto_layout.color = ft.Colors.BLUE

        layout_column.controls = [container_texto_layout,botones_row]

        botones_row.padding = ft.Padding.only(left=0, top=20, right=0, bottom=40)

        page.update()


    def mostrar_informacion(e):
        print(e.control.content.value)

        page.show_dialog(ft.SnackBar(f"{e.control.bgcolor.value}"))

        mostrar_texto(e)

    class Boton(ft.Container):
        def __init__(self, radius, color, texto):
            super().__init__()
            self.border_radius = ft.BorderRadius.all(radius)
            self.bgcolor = color
            self.content = ft.Text(texto)
            self.width = 120
            self.height = 120
            self.alignment = ft.Alignment.CENTER
            self.ink = True
            self.on_click = mostrar_informacion

    titulo = ft.Container(
        ft.Text("RETO", weight=ft.FontWeight.BOLD),
        bgcolor = ft.Colors.GREY,
        padding = 10,
        border_radius = ft.BorderRadius.vertical(top=10, bottom=0),
        width = float("inf"),
        alignment = ft.Alignment.CENTER,
    )

    texto_layout = ft.Text("", weight=ft.FontWeight.BOLD, size = 40)

    container_texto_layout = ft.Container(
        texto_layout,
        padding = ft.Padding.only(left=0, top=30, right=0, bottom=0),
    )

    boton1 = Boton(10, ft.Colors.BLUE, "Q")

    boton2 = Boton(100, ft.Colors.RED, "P")

    botones_row = ft.Container(
        ft.Row(
            [
                boton1,
                boton2,
            ],
            alignment=ft.MainAxisAlignment.SPACE_EVENLY,
        ),
        padding = ft.Padding.symmetric(vertical=40, horizontal=0),
    )

    layout_column = ft.Column(
        controls = [
            botones_row
        ],
        horizontal_alignment = ft.CrossAxisAlignment.CENTER,
    )

    layout = ft.Container(
        content = layout_column,
    )

    switch = ft.Switch(label = "Dark Mode", value = True, on_change = switch_clicado)

    main_container = ft.Container(
        ft.Column(
            [
                switch,
                titulo,
                layout,
            ],
            horizontal_alignment = ft.CrossAxisAlignment.CENTER,
            spacing = 0
        )
    )

    card = ft.Card(
        content = main_container,
        elevation = 15,
        width=500,
    )


    page.add(card)


    page.title = "Reto Psicologico"
    page.theme_mode = ft.ThemeMode.DARK
    page.horizontal_alignment = ft.MainAxisAlignment.CENTER
    page.vertical_alignment = ft.MainAxisAlignment.CENTER


if __name__ == "__main__":
    ft.run(main)