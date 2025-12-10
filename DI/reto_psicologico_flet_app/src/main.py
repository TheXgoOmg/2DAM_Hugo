import random
from sys import prefix

import flet as ft
import asyncio

from flet.core.border_radius import horizontal
from pygments.token import String


class App(ft.Column):
    def __init__(self,page):
        super().__init__()
        self.page = page
        self.tiempo_respuesta_task = None

        self.jugando = False

        self.nivel = 0

        self.btn_blue=Boton("Q", ft.Colors.BLUE, 10)
        self.btn_red=Boton("P", ft.Colors.RED, 200)

        self.hink_text = None

        self.hink_row=ft.Row(
            [],
            alignment=ft.MainAxisAlignment.CENTER
        )

        self.cuenta_atras_text=ft.Row(
            [],
            alignment=ft.MainAxisAlignment.CENTER
        )

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
                        content=ft.Column(
                            [
                                self.hink_row,
                                self.cuenta_atras_text
                            ]
                        )
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

        self.radio_group = ft.RadioGroup(
            content=ft.Row(
                [
                    ft.Column([self.radio_opt1]),
                    ft.Column([self.radio_opt2])
                ]
            ),
            on_change=self.radio_changed
        )

        self.selected_radio = ""

        self.start_button = ft.Container(
            margin=20,
            content= ft.TextButton(
                "Confirmar selección y empieza a jugar",
                icon=ft.Icons.CHECK,
                style=ft.ButtonStyle(
                    bgcolor=ft.Colors.GREY_200,
                    elevation=100
                ),
                on_click=self.start_game
            ),
            shadow=ft.BoxShadow(
                blur_radius=15,
                spread_radius=-8,
                color=ft.Colors.with_opacity(0.2, ft.Colors.BLACK),
                offset=ft.Offset(0, 2),
            )
        )

        self.game_text = ft.Text("",size=20,weight=ft.FontWeight.W_600,text_align=ft.TextAlign.CENTER)

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
                            self.radio_group
                        ],
                        alignment=ft.MainAxisAlignment.CENTER
                    ),
                    ft.Row(
                        [
                            self.start_button
                       ],
                        alignment=ft.MainAxisAlignment.CENTER
                    ),
                    ft.Row(
                        [self.game_text],
                        alignment=ft.MainAxisAlignment.CENTER
                    )
                ]
            )
        )

        self.game_over_text = ft.Text("",color=ft.Colors.RED)

        self.controls=[self.card,self.menu_jugar,self.game_over_text]

    # ---- FLASH DEL BOTON ----
    async def flash(self, boton, duration=0.1):
        color_oscuro = ft.Colors.with_opacity(0.6, boton.color_original)
        boton.bgcolor = color_oscuro
        boton.update()
        await asyncio.sleep(duration)
        boton.bgcolor = boton.color_original
        boton.update()
        await asyncio.sleep(0.1)

    def radio_changed(self,e):
        self.selected_radio = self.radio_group.value

    async def start_game(self,e):
        self.jugando=True
        self.radio_group.disabled=True
        self.start_button.disabled=True
        game_text=f"Has seleccionado jugar a {self.selected_radio.capitalize()}"
        self.game_text.value=game_text
        await self.siguiente_ronda()

        self.update()

    async def cuenta_atras(self, segundos):
        try:
            for i in range(segundos, 0, -1):
                self.cuenta_atras_text.controls = [ft.Text(f"¡Responde rápido! {i}...")]
                self.update()
                await asyncio.sleep(1)

            if self.jugando:
                await self.game_over()

        except asyncio.CancelledError:
            self.cuenta_atras_text.controls = []
            self.update()

    async def siguiente_ronda(self):
        self.nivel += 1

        colores = [ft.Colors.BLUE,ft.Colors.RED]
        formas = ["circulo","cuadrado"]

        color_eleccion=random.choice(colores)
        forma_eleccion=random.choice(formas).upper()

        self.hink_text=HinkText(color_eleccion,forma_eleccion)

        self.hink_row.controls = [self.hink_text]
        self.update()

        if self.tiempo_respuesta_task:
            self.tiempo_respuesta_task.cancel()

        self.tiempo_respuesta_task = asyncio.create_task(self.cuenta_atras(5))

    async def registrar_click(self,boton):
        if not self.jugando:
            return

        if self.tiempo_respuesta_task:
            self.tiempo_respuesta_task.cancel()
            self.tiempo_respuesta_task = None

        await self.flash(boton)

        if self.selected_radio == "colores":
            if boton.color_original == self.hink_text.color:
                await self.siguiente_ronda()
            else:
                asyncio.create_task(self.game_over())
        elif self.selected_radio == "figuras":
            if self.hink_text.value.lower() == "circulo" and boton.border_radius > 50:
                await self.siguiente_ronda()
            elif self.hink_text.value.lower() == "cuadrado" and boton.border_radius < 50:
                await self.siguiente_ronda()
            else:
                asyncio.create_task(self.game_over())

    def reiniciar(self):
        self.radio_group.disabled = False
        self.start_button.disabled = False
        self.jugando = False
        self.nivel = 0
        self.game_over_text.value = ""
        self.cuenta_atras_text.controls = []

        self.hink_row.controls = []
        self.hink_text = None
        self.game_text.value = ""

        self.update()

    async def game_over(self):
        self.game_over_text.value=f"Game Over! Llegaste al nivel {self.nivel}"
        self.cuenta_atras_text.controls = []
        self.update()

        await asyncio.sleep(2)
        self.reiniciar()
        self.update()

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

class HinkText(ft.Text):
    def __init__(self, color, texto):
        super().__init__()
        self.color=color
        self.value=texto
        self.size=50
        self.height=ft.FontWeight.W_900

def main(page: ft.Page):
    page.title = "Reto Psicológico"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE

    # ---- EVENTO DE TECLADO ----
    async def on_key(e: ft.KeyboardEvent):
        key = (e.key or "").lower()

        if key == "q":
            print("Letra 'q' clicada")
            await app.registrar_click(app.btn_blue)
        elif key == "p":
            print("Letra 'p' clicada")
            await app.registrar_click(app.btn_red)

    page.on_keyboard_event = on_key

    app=App(page)
    page.add(app)

ft.app(main)
