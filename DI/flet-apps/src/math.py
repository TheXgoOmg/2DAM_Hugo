import flet as ft

class App(ft.Column):
    def __init__(self):
        super().__init__()
        self.width=600
        self.titulo = ft.Text(
            value="Encuentra los números",
            size=40,
            weight=ft.FontWeight.BOLD,
            font_family="Consolas"
        )
        self.quest = ft.Text(
            value="Encuentra 1 número, PAR de 2 cifras mayor que 20, que al dividirlo entre dos, la suma de las 2 cifras del número inicial sea menos o igual a la suma de las cifras del número resultante de la división:",
            expand=True,
            weight=ft.FontWeight.BOLD,
            size=18,
            text_align=ft.TextAlign(value="justify")
        )
        self.enunciado = ft.Text(
            value="Número de dos cifras inicial:",
            weight=ft.FontWeight.BOLD,
            size=16
        )
        self.op1 = ft.TextField(
            width=50,
            text_align=ft.TextAlign.CENTER,
            max_length=1,
            input_filter=ft.NumbersOnlyInputFilter()
        )
        self.op2 = ft.TextField(
            width=50,
            text_align=ft.TextAlign.CENTER,
            max_length=1,
            input_filter=ft.NumbersOnlyInputFilter()
        )
        self.boton_comprobacion = Boton("Comprobar",self.comprobar)

        self.texto = ft.Column()

        self.ventana = ft.Container(
            alignment=ft.alignment.center,
            padding=20,
            border_radius=10,
            border=ft.border.all(2, ft.Colors.BLACK),
            content=ft.Column(
                controls = [ft.Row([self.quest]),
                    ft.Row([self.enunciado],alignment = ft.MainAxisAlignment.CENTER),
                    ft.Container(
                        content=ft.Row([self.op1,self.op2],alignment = ft.MainAxisAlignment.CENTER)
                    ),
                    ft.Row([self.boton_comprobacion],alignment = ft.MainAxisAlignment.CENTER),
                    ft.Row([self.texto],alignment = ft.MainAxisAlignment.CENTER)],
                horizontal_alignment=ft.CrossAxisAlignment.CENTER),
        )

        self.controls = [
            self.titulo,
            self.ventana
        ]

    def comprobar(self,e):
        self.texto.controls.clear()
        a = int(self.op1.value)
        b = int(self.op2.value)
        num = a * 10 + b
        num2 = (num//10) + (num-(num%10))
        self.texto.controls.append(ft.Text(f"Vamos a comprobar si se cumple para el número {a}{b}"))
        self.texto.controls.append(ft.Text(f"Cálculo: {a} + {b} = {(a+b)}"))
        self.texto.controls.append(ft.Text(f"La mitad de {num} es {num/2} --> {num//10} + {num-(num%10)} = {num2}"))
        if num2 >=num:
            self.texto.controls.append(ft.Text(f"El {num} cumple con lo esperado. Enhorabuena!!"))
        else:
            self.texto.controls.append(ft.Text(f"El {num} NO cumple con lo esperado."))
            self.texto.controls.append(ft.Text("Lo sentimos en lo más profundo de nuestro corazón :("))
        self.update()

class Boton(ft.ElevatedButton):
    def __init__(self,texto,accion):
        super().__init__()
        self.text=texto
        self.on_click=accion

def main(page: ft.Page):
    page.title = "Math App"
    page.vertical_alignment = ft.MainAxisAlignment.START
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE
    page.bgimage = ft.DecorationImage(
        src="https://unsplash.com/photos/grand-hotel-building-with-flags-on-roof-r4uyTvVEgC8",  # URL o ruta local
        fit=ft.ImageFit.COVER,  # Ajusta la imagen para cubrir toda la pantalla
    )

    page.add(App())
ft.app(main)