import flet as ft
import random
import asyncio


class App(ft.Column):
    def __init__(self):
        super().__init__()

        self.btn_red=Boton("T", ft.Colors.RED)
        self.btn_yellow=Boton("Y", ft.Colors.YELLOW)
        self.btn_blue=Boton("G", ft.Colors.BLUE)
        self.btn_green=Boton("H", ft.Colors.GREEN)

        # Secuencias del juego
        self.secuencia_juego = []
        self.secuencia_usuario = []
        self.nivel = 0
        self.jugando = False  # Para controlar si estamos en medio de una ronda

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
                    ft.Container(
                        alignment=ft.alignment.top_center,
                        margin=40,
                        content=ft.ResponsiveRow(
                            alignment=ft.MainAxisAlignment.CENTER,
                            spacing=40,
                            run_spacing=40,
                            controls=[
                                ft.Container(col = {"xs": 12, "md": 6,"lg": 6}, content=self.btn_red),
                                ft.Container(col = {"xs": 12, "md": 6,"lg": 6}, content=self.btn_yellow),
                                ft.Container(col = {"xs": 12, "md": 6,"lg": 6}, content=self.btn_blue),
                                ft.Container(col = {"xs": 12, "md": 6,"lg": 6}, content=self.btn_green),
                            ]
                        )
                    )
                ]
            )
        )

        self.controls=[self.card]

    # ---- FLASH DEL BOTON ----
    async def flash(self, boton, duration=0.5):
        color_oscuro = ft.Colors.with_opacity(0.6, boton.color_original)
        boton.bgcolor = color_oscuro
        boton.update()
        await asyncio.sleep(duration)
        boton.bgcolor = boton.color_original
        boton.update()
        await asyncio.sleep(0.1)  # Pequeña pausa entre flashes

    # ---- INICIAR RONDA ----
    async def siguiente_ronda(self):
        self.nivel += 1
        self.secuencia_usuario = []
        self.secuencia_juego.append(random.choice([self.btn_red, self.btn_yellow, self.btn_blue, self.btn_green]))
        await self.mostrar_secuencia()

    # ---- MOSTRAR SECUENCIA ----
    async def mostrar_secuencia(self):
        self.jugando = False
        for boton in self.secuencia_juego:
            await self.flash(boton)
        self.jugando = True  # Ahora el jugador puede tocar botones

    # ---- REGISTRAR CLICK DEL USUARIO ----
    async def registrar_click(self, boton):
        if not self.jugando:
            return  # Ignorar clicks mientras se muestra la secuencia

        await self.flash(boton)
        self.secuencia_usuario.append(boton)

        # Comprobar si el último botón coincide
        index = len(self.secuencia_usuario) - 1
        if self.secuencia_usuario[index] != self.secuencia_juego[index]:
            await self.game_over(self.nivel)
            return

        # Si la secuencia del usuario coincide completamente
        if len(self.secuencia_usuario) == len(self.secuencia_juego):
            await asyncio.sleep(0.5)
            await self.siguiente_ronda()

    # ---- GAME OVER ----
    async def game_over(self, nivel):
        self.controls.append(ft.Text(f"Game Over! Llegaste al nivel {self.nivel}", color=ft.Colors.RED))
        self.update()
        self.secuencia_juego = []
        self.secuencia_usuario = []
        self.nivel = 0
        self.jugando = False
        await asyncio.sleep(1)

class Boton(ft.Container):
    def __init__(self, letra, color):
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
        self.border_radius=10
        self.ink=True


def main(page: ft.Page):
    page.title = "Simon-Clon"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE

    # ---- EVENTO DE TECLADO ----
    def on_key(e: ft.KeyboardEvent):
        key = (e.key or "").lower()

        if key == "t":
            print("Letra 't' clicada")
            asyncio.run(app.registrar_click(app.btn_red))
        elif key == "y":
            print("Letra 'y' clicada")
            asyncio.run(app.registrar_click(app.btn_yellow))
        elif key == "g":
            print("Letra 'g' clicada")
            asyncio.run(app.registrar_click(app.btn_blue))
        elif key == "h":
            print("Letra 'h' clicada")
            asyncio.run(app.registrar_click(app.btn_green))

    page.on_keyboard_event = on_key

    app=App()
    page.add(app)


    asyncio.run(app.siguiente_ronda())



ft.app(main)
