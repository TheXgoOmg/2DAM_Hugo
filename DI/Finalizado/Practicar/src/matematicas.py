import flet as ft

class App(ft.Column):
    def __init__(self, pagina: ft.Page):
        self.pagina = pagina
        super().__init__()


        self.switch = ft.Switch(label="Dark Mode", on_change=lambda e: self.switch_change(self.pagina))

        self.enunciado = ft.Column(
            width=500,
            controls=[
                ft.Text("Encuentra 1 número, PAR de cifras mayor que 20, que al dividirlo entre dos, la suma de las dos cifras de número inicial sea menor o igual a la suma de las cifras del número resultante de la división:",
                                 style=ft.TextStyle(weight=ft.FontWeight.BOLD),
                                 text_align=ft.TextAlign.JUSTIFY)
            ]
        )

        self.leyenda = ft.Text("Númeor de dos cifras inicial:",
                               style=ft.TextStyle(weight=ft.FontWeight.BOLD, size=12))

        self.number_field1 = NumberField()

        self.number_field2 = NumberField()

        self.input = ft.Row(
            alignment=ft.MainAxisAlignment.CENTER,
            controls=[
                self.number_field1,
                self.number_field2
            ]
        )

        self.boton = ft.Button(
            "¿Es correcto?",
            on_click=lambda e: self.corregir(self.number_field1.value, self.number_field2.value)
        )

        self.solucion = ft.Text()

        self.app = ft.Column(
            horizontal_alignment=ft.CrossAxisAlignment.CENTER,
            controls=[self.switch, self.enunciado, self.leyenda, self.input, self.boton]
        )

        self.controls=[self.app]

    @staticmethod
    def corregir(a, b):
        texto = ""

        a = a
        b = b

        num = (a * 10 + b) / 2

        a2 = num / 10
        b2 = num % 10

        texto += f"Vamos a comprobar si se cumple para el número %d%d%n",a,b
        texto += f"Cálculo: %d + %d = {{%d+%d}}%n",a,b,a,b
        num2 = (a*10+b)/2
        texto += f"La mitad de %d%d es %d, --> %d + %d = %d",a,b,num2,num2/10,num2%10

        if a + b < a2 + b2:
            return f"El número %d%d cumple con lo esperado. Enhorabuena!!!", a, b
        else:
            return f"El número %d%d no cumple coun lo esperado. Prueba con otro número", a, b

    def switch_change(self: ft.Event[ft.Switch], pagina: ft.Page):
        pagina.theme_mode = (
            ft.ThemeMode.DARK
            if pagina.theme_mode == ft.ThemeMode.LIGHT
            else ft.ThemeMode.LIGHT
        )
        self.control.label = (
            "Light Mode"
            if pagina.theme_mode == ft.ThemeMode.LIGHT
            else "Dark Mode"
        )
        pagina.update()




class NumberField(ft.TextField):
    def __init__(self):
        super().__init__()

        self.max_length=1
        self.counter=""
        self.multiline=False
        self.input_filter=ft.NumbersOnlyInputFilter()
        self.width=50
        self.text_align=ft.TextAlign.CENTER

def main(page: ft.Page):
    page.add(App(page))
    page.theme=ft.Theme(font_family="Consolas")
    page.theme_mode=ft.ThemeMode.LIGHT
    page.horizontal_alignment=ft.CrossAxisAlignment.CENTER


if __name__ == "__main__":
    ft.run(main)
