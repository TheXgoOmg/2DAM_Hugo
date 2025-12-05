import flet as ft
import asyncio

from flet.core.border_radius import horizontal


class App(ft.Column):
    def __init__(self):
        super().__init__()

        self.btn_blue=Boton("Q", ft.Colors.BLUE, 10)
        self.btn_red=Boton("P", ft.Colors.RED, 200)

        self.card = ft.Card(
            margin=30,
            elevation=50,
            content=ft.Column(
                [
                    ft.Row(
                        controls=[
                            ft.Container(
                                content=ft.Text("RETO", size=30, weight=ft.FontWeight.W_600),
                                alignment=ft.alignment.center,
                                bgcolor=ft.Colors.GREY_300,
                                border_radius=ft.border_radius.only(10,10,0,0),
                                padding=10,
                                expand=True
                            )
                        ]
                    ),
                    ft.Container(
                        alignment=ft.alignment.top_center,
                        margin=40,
                        content=ft.Row(
                            alignment=ft.MainAxisAlignment.CENTER,
                            spacing=40,
                            run_spacing=40,
                            controls=[
                                ft.Container(content=self.btn_blue),
                                ft.Container(content=self.btn_red)
                            ]
                        )
                    )
                ]
            )
        )

        self.radio_opt1 = RadioOption("figuras","Figuras")
        self.radio_opt2 = RadioOption("colores","Colores")

        self.menu_jugar = ft.Container(
            content= ft.Column(
                [
                    ft.Row(
                        [
                            ft.Text("Elige una opción:",size=25,weight=ft.FontWeight.BOLD,text_align=ft.TextAlign.CENTER)
                        ],
                        alignment=ft.MainAxisAlignment.CENTER
                    ),
                    ft.Row(
                        [
                            ft.Column([self.radio_opt1]),
                            ft.Column([self.radio_opt2])
                        ],
                        alignment=ft.MainAxisAlignment.CENTER
                    )
                ]
            )
        )

        self.controls=[self.card,self.menu_jugar]

    # ---- FLASH DEL BOTON ----
    async def flash(self, boton, duration=0.5):
        color_oscuro = ft.Colors.with_opacity(0.6, boton.color_original)
        boton.bgcolor = color_oscuro
        boton.update()
        await asyncio.sleep(duration)
        boton.bgcolor = boton.color_original
        boton.update()
        await asyncio.sleep(0.1)


class Boton(ft.Container):
    def __init__(self, letra, color, border_radius):
        super().__init__()
        self.content=ft.Text(letra.upper())
        self.padding=10
        self.color_original = color
        self.alignment=ft.alignment.center
        self.bgcolor=color
        self.width=150
        self.height=150
        self.shadow=ft.BoxShadow(
            spread_radius=1,
            blur_radius=15,
            color=ft.Colors.BLUE_GREY_300,
            offset=ft.Offset(0, 0),
            blur_style=ft.ShadowBlurStyle.OUTER,
        )
        self.border_radius=border_radius
        self.ink=True

class RadioOption(ft.Radio):
    def __init__(self, value, label):
        super().__init__()
        self.value = value
        self.label = label

def main(page: ft.Page):
    page.title = "Reto Psicológico"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE

    # ---- EVENTO DE TECLADO ----
    def on_key(e: ft.KeyboardEvent):
        key = (e.key or "").lower()

        if key == "q":
            print("Letra 'q' clicada")
            asyncio.run(app.flash(app.btn_blue))
        elif key == "p":
            print("Letra 'p' clicada")
            asyncio.run(app.flash(app.btn_red))

    page.on_keyboard_event = on_key

    app=App()
    page.add(app)

ft.app(main)
