import flet as ft
from flet import control


class VentanaInicio(ft.Column):
    def __init__(self):
        super().__init__()

        self.alignment = ft.MainAxisAlignment.CENTER
        self.horizontal_alignment = ft.CrossAxisAlignment.CENTER
        self.expand = True

        logo = ft.Image(
            src="https://upload.wikimedia.org/wikipedia/en/0/01/Golden_State_Warriors_logo.svg",
            width=150,
            height=150
        )

        titulo = ft.Text("Panel de Estadísticas", size=24, weight=ft.FontWeight.BOLD)
        boton_entrar = ft.ElevatedButton("Ver Jugadores", width=200)

        self.controls=[logo,titulo,boton_entrar]

def main(page: ft.Page):
    page.title = ("App de Estadísticas de Baloncesto")
    page.width = 400
    page.height = 600
    page.theme_mode = ft.ThemeMode.LIGHT


    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER

    datos_warriors = {
        'Jugador': ['Stephen Curry', 'Klay Thompson', 'Draymond Green', 'Andrew Wiggins', 'Jonathan Kuminga'],
        'Puntos': [26.4, 17.9, 8.6, 13.2, 16.1],
        'Rebotes': [4.5, 3.3, 7.2, 4.5, 4.8],
        'Asistencias': [5.1, 2.3, 6.0, 1.7, 2.2],
        'Robos': [0.7, 0.6, 1.0, 0.6, 0.7],
        'Tapones': [0.4, 0.5, 0.9, 0.6, 0.5],
        'Perdidas': [2.8, 1.5, 2.3, 1.2, 1.4],
        'Minutos': [32.7, 29.7, 27.1, 27.0, 26.3]
    }

    page.add( VentanaInicio())


ft.app(target=main)
