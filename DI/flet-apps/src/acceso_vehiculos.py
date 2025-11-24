import flet as ft

class App(ft.Column):
    def __init__(self, page):
        super().__init__()
        



def main(page: ft.Page):
    page.title = "Acceso Vehículos"
    page.bgcolor = ft.Colors.BLUE_50
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE

    page.add(App(page))

ft.app(main)