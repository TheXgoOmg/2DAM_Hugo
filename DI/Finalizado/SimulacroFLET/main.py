import flet as ft


def main(page: ft.Page):

    def mostrar(texto):
        print(texto)

        if texto == "X":
            snack_bar = ft.SnackBar(ft.Text("Empate!!"))
        else:
            snack_bar = ft.SnackBar(ft.Text("Bien hecho!!"))

        page.show_dialog(snack_bar)


    juego_layout = ft.Column(
        controls=[
            ft.Row(
                [
                    ft.Container(
                        content=ft.Text("1", size=30, color=ft.Colors.BLACK),
                        width=120,
                        height=120,
                        margin=ft.Margin.all(10),
                        padding=10,
                        border_radius=ft.BorderRadius.all(10),
                        ink=True,
                        bgcolor=ft.Colors.RED_400,
                        on_click=lambda _: mostrar("1"),
                    ),
                    ft.Container(
                        content=ft.Text("X", size=30, color=ft.Colors.BLACK),
                        width=120,
                        height=120,
                        margin=ft.Margin.all(10),
                        padding=10,
                        border_radius=ft.BorderRadius.all(10),
                        ink=True,
                        bgcolor=ft.Colors.YELLOW_400,
                        on_click=lambda _: mostrar("X"),
                    ),
                    ft.Container(
                        content=ft.Text("2", size=30, color=ft.Colors.BLACK),
                        width=120,
                        height=120,
                        margin=ft.Margin.all(10),
                        padding=10,
                        border_radius=ft.BorderRadius.all(10),
                        ink=True,
                        bgcolor=ft.Colors.GREEN_400,
                        on_click=lambda _: mostrar("2"),
                    )
                ],
                alignment=ft.MainAxisAlignment.CENTER,
            ),
        ]
    )

    contenido_tarjeta = ft.Container(
        content=ft.Column(
            controls=[
                # Primer Container
                ft.Container(
                    content=ft.Text("Simulación", size=20, color=ft.Colors.RED_400),
                    padding=10,
                    bgcolor=ft.Colors.BLUE_GREY_100,
                    border_radius=ft.BorderRadius.vertical(top=10),
                    alignment=ft.Alignment.CENTER,
                    width=float("inf"),
                    # Hace que el elemento ocupe todo el ancho disponible
                ),
                
                # Segundo Container
                ft.Container(
                    content=juego_layout,
                    padding=10,
                    bgcolor=ft.Colors.BLUE_400,
                    margin=ft.Margin.symmetric(vertical=5),
                ),
            ],
            alignment=ft.MainAxisAlignment.CENTER,
            horizontal_alignment=ft.CrossAxisAlignment.STRETCH,
            # Hace que los hijos ocupen todo el ancho
        ),
    )

    # Creamos...
    card = ft.Card(
        elevation=15, # Añade una sombra
        content=contenido_tarjeta,
        width=470,
    )

    page.add(card)
    page.title = "SIMULACRO"
    page.theme_mode = ft.ThemeMode.DARK
    page.horizontal_alignment=ft.MainAxisAlignment.CENTER
    page.vertical_alignment=ft.MainAxisAlignment.CENTER

ft.run(main)

