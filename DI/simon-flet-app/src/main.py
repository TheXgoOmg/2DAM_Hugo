import flet as ft
from certifi import contents


class App(ft.Column):
    def __init__(self, page):
        super().__init__()

        self.card = ft.Card(
            margin=30,
            elevation=50,
            content=ft.Column(
                [
                    ft.Row(
                        controls=[
                            ft.Container(
                                content=ft.Text("Simon-Clon", size=30, weight=ft.FontWeight.W_600),
                                alignment=ft.alignment.center,
                                bgcolor=ft.Colors.GREY_300,
                                border_radius=ft.border_radius.only(10,10,0,0),
                                padding=10,
                                expand=True
                            )
                        ]
                    ),
                    ft.ResponsiveRow(
                        alignment=ft.MainAxisAlignment.CENTER,
                        spacing=20,
                        run_spacing=20,
                        controls=[
                            ft.Container(col = {"xs": 12, "md": 6,"lg": 6},content=Boton("T", ft.Colors.RED, 40), alignment=ft.alignment.center),
                            ft.Container(col = {"xs": 12, "md": 6,"lg": 6},content=Boton("Y", ft.Colors.YELLOW, 40), alignment=ft.alignment.center),
                            ft.Container(col = {"xs": 12, "md": 6,"lg": 6},content=Boton("G", ft.Colors.BLUE, 40), alignment=ft.alignment.center),
                            ft.Container(col = {"xs": 12, "md": 6,"lg": 6},content=Boton("H", ft.Colors.GREEN, 40), alignment=ft.alignment.center),
                        ]
                    )
                ]
            )
        )

        self.controls=[self.card]

class Boton(ft.Container):
    def __init__(self, letra, color, margin):
        super().__init__()
        self.content=ft.Text(letra.upper())
        self.padding=10
        self.margin=margin
        self.alignment=ft.alignment.center
        self.bgcolor=color
        self.width=150
        self.height=150
        self.border_radius=10
        self.ink=True
        self.on_click=lambda e: print("Clickable with Ink clicked!")


def main(page: ft.Page):
    page.title = "Simon-Clon"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE

    page.add(App(page))

ft.app(main)
