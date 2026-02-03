import flet as ft

def main(page: ft.Page):

    class Boton(ft.Container):
        def __init__(self, color, texto):
            super().__init__()
            self.bgcolor = color
            self.content = texto
            self.width = 120
            self.height = 120
            self.border_radius = ft.BorderRadius.all(10)
            self.alignment = ft.Alignment.CENTER
            self.ink = True
            # self.on_click = lambda _: click_button(texto, color)
            self.on_click = click_blutton_event


#    def click_button(texto, color):
#        print(texto.value)
#
#        if color == ft.Colors.RED:
#            snack_bar = ft.SnackBar(f"ROJO: {texto.value}")
#        else:
#            snack_bar = ft.SnackBar(f"El botón {texto.value} no es Rojo")
#d
#        page.show_dialog(snack_bar)


    def click_blutton_event(e):
        print(e.control.content.value)

        if e.control.bgcolor == ft.Colors.RED:
            snack_bar = ft.SnackBar(f"ROJO: {e.control.content.value}")
            page.show_dialog(snack_bar)
        if e.control.bgcolor == ft.Colors.YELLOW:
            dialog.content = ft.Text(f"El botón es AMARILLO")
            page.show_dialog(dialog)
        if e.control.bgcolor != ft.Colors.RED:
            snack_bar = ft.SnackBar(f"El botón {e.control.content.value} no es Rojo")
            page.show_dialog(snack_bar)



    def cerrar_dialog(e):
        dialog.open = False


    dialog = ft.AlertDialog(title=ft.Text("Clicado"), actions=[ft.TextButton("OK", on_click=cerrar_dialog)])


    container1 = Boton(ft.Colors.RED, ft.Text("T", weight=ft.FontWeight.BOLD))
    container2 = Boton(ft.Colors.YELLOW, ft.Text("Y", weight=ft.FontWeight.BOLD))
    container3 = Boton(ft.Colors.BLUE, ft.Text("G", weight=ft.FontWeight.BOLD))
    container4 = Boton(ft.Colors.GREEN, ft.Text("H", weight=ft.FontWeight.BOLD))

    main_container = ft.Container(
        ft.Column(
            controls = [
                ft.Container(
                    ft.Text("Simon-Clon", weight=ft.FontWeight.BOLD, size=25, font_family="Consolas"),
                    bgcolor = ft.Colors.GREY,
                    padding=20,
                    border_radius=ft.BorderRadius.vertical(top=10, bottom=0),
                    width = float("inf"),
                    alignment = ft.Alignment.CENTER,
                ),
                ft.Container(
                    ft.Column(
                        controls=[
                            ft.Row(
                                controls = [container1, container2],
                                alignment = ft.MainAxisAlignment.SPACE_EVENLY,
                            ),
                            ft.Row(
                                controls = [container3, container4],
                                alignment = ft.MainAxisAlignment.SPACE_EVENLY
                            )
                        ],
                        alignment = ft.MainAxisAlignment.SPACE_EVENLY,
                        height = 300,
                    ),
                )
            ],
            spacing=0,
        )
    )


    card = ft.Card(
        content = main_container,
        elevation = 15,
        width = 370,
    )



    page.controls = [card]



    page.title = "SIMON"
    page.theme_mode = ft.ThemeMode.LIGHT
    page.horizontal_alignment = ft.MainAxisAlignment.CENTER
    page.vertical_alignment = ft.MainAxisAlignment.CENTER


if __name__ == "__main__":
    ft.run(main)