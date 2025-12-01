import flet as ft

class App(ft.Column):
    def __init__(self, page):
        super().__init__()
        self.page = page
        self.width = 600
        self.spacing = 30
        self.alignment = ft.MainAxisAlignment.CENTER

        # Título principal
        self.titulo = ft.Text(
            value="🔢 Encuentra los números",
            size=40,
            weight=ft.FontWeight.BOLD,
            color=ft.Colors.BLUE_800,
            text_align=ft.TextAlign.CENTER,
            font_family="Consolas"
        )

        # Enunciado del problema
        self.quest = ft.Text(
            value="Encuentra 1 número PAR de 2 cifras mayor que 20, que al dividirlo entre dos, la suma de las 2 cifras del número inicial sea menor o igual a la suma de las cifras del número resultante de la división:",
            expand=True,
            size=18,
            text_align=ft.TextAlign.JUSTIFY
        )

        # Etiqueta y campos de entrada
        self.enunciado = ft.Text(
            value="Número de dos cifras inicial:",
            size=16,
            weight=ft.FontWeight.BOLD,
            text_align=ft.TextAlign.CENTER
        )

        self.op1 = ft.TextField(
            width=60,
            text_align=ft.TextAlign.CENTER,
            max_length=1,
            input_filter=ft.NumbersOnlyInputFilter(),
            border_color=ft.Colors.BLUE_400,
            border_radius=10
        )
        self.op2 = ft.TextField(
            width=60,
            text_align=ft.TextAlign.CENTER,
            max_length=1,
            input_filter=ft.NumbersOnlyInputFilter(),
            border_color=ft.Colors.BLUE_400,
            border_radius=10
        )

        # Botones
        self.boton_comprobar = Boton("Comprobar", self.comprobar, ft.Colors.GREEN_600)
        self.boton_limpiar = Boton("Limpiar", self.limpiar, ft.Colors.AMBER_500)
        self.boton_salir = Boton("Salir", self.salir, ft.Colors.RED_600)

        # Zona de salida de texto
        self.texto = ft.Column(spacing=5)

        # Contenedor principal
        self.ventana = ft.Container(
            padding=20,
            alignment=ft.alignment.center,
            bgcolor=ft.Colors.WHITE,
            border_radius=15,
            shadow=ft.BoxShadow(
                spread_radius=1,
                blur_radius=12,
                color=ft.Colors.with_opacity(0.3, ft.Colors.BLACK)
            ),
            content=ft.Column(
                controls=[
                    self.quest,
                    ft.Divider(),
                    self.enunciado,
                    ft.Row([self.op1, self.op2], alignment=ft.MainAxisAlignment.CENTER),
                    ft.Row(
                        [self.boton_comprobar, self.boton_limpiar, self.boton_salir],
                        alignment=ft.MainAxisAlignment.SPACE_AROUND
                    ),
                    ft.Divider(),
                    self.texto
                ],
                horizontal_alignment=ft.CrossAxisAlignment.CENTER
            )
        )

        # Estructura principal
        self.controls = [self.titulo, self.ventana]

    # === FUNCIONES ===

    def comprobar(self, e):
        self.texto.controls.clear()
        try:
            a = int(self.op1.value)
            b = int(self.op2.value)
            num = a * 10 + b

            if num <= 20 or num % 2 != 0:
                self.texto.controls.append(ft.Text("Debe ser un número PAR mayor que 20.", color=ft.Colors.RED_600))
            else:
                mitad = num // 2
                suma_original = a + b
                suma_mitad = sum(map(int, str(mitad)))

                self.texto.controls.append(ft.Text(f"Comprobando número {num}..."))
                self.texto.controls.append(ft.Text(f"Suma de cifras del número: {suma_original}"))
                self.texto.controls.append(ft.Text(f"Mitad: {mitad} → suma de cifras: {suma_mitad}"))

                if suma_original <= suma_mitad:
                    self.texto.controls.append(ft.Text(f"✅ El número {num} cumple la condición.", color=ft.Colors.GREEN_600))
                else:
                    self.texto.controls.append(ft.Text(f"❌ El número {num} NO cumple la condición.", color=ft.Colors.RED_600))

        except ValueError:
            self.texto.controls.append(ft.Text("Por favor, introduce dos cifras válidas.", color=ft.Colors.RED_600))

        self.update()

    def limpiar(self, e):
        self.op1.value = ""
        self.op2.value = ""
        self.texto.controls.clear()
        self.update()

    def salir(self,e):
        self.page.window.close()

# === Clase de botón personalizado ===
class Boton(ft.ElevatedButton):
    def __init__(self, texto, accion, color):
        super().__init__(
            text=texto,
            on_click=accion,
            bgcolor=color,
            color=ft.Colors.WHITE,
            style=ft.ButtonStyle(shape=ft.RoundedRectangleBorder(radius=12))
        )

# === MAIN ===
def main(page: ft.Page):
    page.title = "Math App"
    page.bgcolor = ft.Colors.BLUE_50
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE

    page.add(App(page))

ft.app(main)
