import flet as ft
import json
from datetime import datetime
from certifi import contents
from flet.core.margin import Margin

class App(ft.Column):
    def __init__(self, page):
        super().__init__()
        self.listaVehiculos=ft.Column(
            controls=[
            ]
        )
        
        self.textFieldMatricula=  ft.TextField(label="Matrícula",width=200)
        self.textFieldPropietario = ft.TextField(label="Propietario",width=200)
        self.textFieldTipoVehiculo = ft.Dropdown(
            label="Tipo de vehículo",
            width=200,
            options=[
                ft.DropdownOption("Coche"),
                ft.DropdownOption("Moto")
            ]
        )

        self.registroVehiculo = ft.Column (
            [
                ft.Text("Registro de vehículos autorizados",size=18,weight=ft.FontWeight.W_900),
                self.textFieldMatricula,
                self.textFieldPropietario,
                self.textFieldTipoVehiculo

            ]
        )

        self.matriculaBuscada = ft.TextField(label="Introduce matrícula",width=200)

        self.mensajeAcceso = ft.Text()

        self.controlAcceso = ft.Column(
            controls=[
                ft.Text("Control de acceso",size=18,weight=ft.FontWeight.W_900),
                self.matriculaBuscada,
                ft.Container(
                    ft.TextButton("Comprobar acceso",on_click=self.buscarVehiculo),
                    border=ft.border.all(1, ft.Colors.GREY),
                    border_radius=50,
                    margin=ft.margin.symmetric(vertical=3),
                ),
                self.mensajeAcceso
            ]
        )

        self.divisor = ft.Divider()

        self.registro = ft.Row(
            [
                ft.Column(
                    alignment=ft.MainAxisAlignment.CENTER,
                    horizontal_alignment=ft.CrossAxisAlignment.CENTER,
                    expand=True,
                    controls =
                    [
                        ft.Container(
                            content=ft.Column(
                                [
                                    ft.Container(
                                        content=self.registroVehiculo,
                                        bgcolor=ft.Colors.GREY_200,
                                        padding=15,
                                        border_radius=10
                                    ),
                                    ft.Container(
                                        ft.TextButton("Añadir vehículo", on_click=lambda e: self.registrarVehiculo(page)),

                                        border=ft.border.all(1, ft.Colors.GREY),
                                        border_radius=50,
                                        margin=ft.margin.symmetric(vertical=3)
                                    )
                                ]
                            ),
                            bgcolor=ft.Colors.GREY_200,
                            padding=15,
                            border_radius=10,
                            shadow=ft.BoxShadow(
                                spread_radius=1,
                                blur_radius=3,
                                color=ft.Colors.BLUE_GREY_300,
                                blur_style=ft.ShadowBlurStyle.OUTER,
                            )
                        )
                    ]
                ),
                ft.Column(
                    alignment=ft.MainAxisAlignment.CENTER,
                    horizontal_alignment=ft.CrossAxisAlignment.CENTER,
                    expand=True,
                    controls =
                    [
                        ft.Container(
                            content=ft.Column(
                                [
                                    self.controlAcceso
                                ]
                            ),
                            bgcolor=ft.Colors.GREY_200,
                            padding=15,
                            border_radius=10,
                            shadow=ft.BoxShadow(
                                spread_radius=1,
                                blur_radius=3,
                                color=ft.Colors.BLUE_GREY_300,
                                blur_style=ft.ShadowBlurStyle.OUTER,
                            )
                        )
                    ]
                )
            ]
        )

        self.textTabla = ft.Text("Registro de accesos",size=18,weight=ft.FontWeight.W_900)

        self.tabla = ft.DataTable(
            columns=[
                ft.DataColumn(ft.Text("Fecha/Hora")),
                ft.DataColumn(ft.Text("Matrícula")),
                ft.DataColumn(ft.Text("Resultado"))
            ],
            rows=[]
        )

        self.tablaContainer = ft.Row(
            [
                ft.Column(
                    controls =
                    [
                        ft.Container(
                            content=ft.Column(
                                [
                                    ft.Container(
                                        content=self.tabla,
                                        bgcolor=ft.Colors.GREY_200,
                                        padding=15,
                                        border_radius=10
                                    )
                                ]
                            ),
                            bgcolor=ft.Colors.GREY_200,
                            margin= 10,
                            border_radius=10,
                            shadow=ft.BoxShadow(
                                spread_radius=1,
                                blur_radius=3,
                                color=ft.Colors.BLUE_GREY_300,
                                blur_style=ft.ShadowBlurStyle.OUTER,
                            )
                        )
                    ]
                )
            ]
        )

        self.controls = [self.registro,self.divisor,self.textTabla,self.tablaContainer]

    def registrarVehiculo(self,page):
        self.listaVehiculos.controls.append(ft.Text(self.textFieldMatricula.value))
        page.open(ft.SnackBar(ft.Text(f"Vehículo '{self.textFieldMatricula.value}' registrado")))
        self.textFieldMatricula.value=""
        self.textFieldPropietario.value=""
        self.textFieldTipoVehiculo.value=""
        self.update()

    def buscarVehiculo(self, e):
        matricula = self.matriculaBuscada.value
        encontrado = False

        if self.matriculaBuscada.value != "":
            for control in self.listaVehiculos.controls:
                if isinstance(control, ft.Text) and control.value == matricula:
                    encontrado = True
                    break

            fecha=datetime.now().strftime("%d/%m/%Y %H:%M:%S")
            matricula=self.matriculaBuscada.value
            resultado=None
            if encontrado:
                texto = f"Acceso permitido a {matricula}"
                self.mensajeAcceso.value=texto
                self.mensajeAcceso.color=ft.Colors.GREEN
                resultado="PERMITIDO"
                self.tabla.rows.insert(0,
                                       ft.DataRow(
                                           cells=[
                                               ft.DataCell(ft.Text(datetime.now().strftime("%d/%m/%Y %H:%M:%S"))),
                                               ft.DataCell(ft.Text(self.matriculaBuscada.value)),
                                               ft.DataCell(ft.Text(resultado, color=ft.Colors.GREEN))
                                           ]
                                       ))
            else:
                texto = f"Acceso denegado a {matricula}"
                self.mensajeAcceso.value=texto
                self.mensajeAcceso.color=ft.Colors.RED
                resultado="DENEGADO"
                self.tabla.rows.insert(0,
                                       ft.DataRow(
                                           cells=[
                                               ft.DataCell(ft.Text(fecha)),
                                               ft.DataCell(ft.Text(matricula)),
                                               ft.DataCell(ft.Text(resultado, color=ft.Colors.RED))
                                           ]
                                       ))

            with open("registros.json", "r") as f:
                datos = json.load(f)

            datos["registros"].append({"fecha/hora": fecha, "matricula": matricula, "resultado": resultado})

            with open("registros.json", "w") as f:
                json.dump(datos, f, indent=4)


        self.matriculaBuscada.value=""

        self.update()

    def cargar_datos(self):
        # Insertar datos ------
        with open("registros.json", "r") as f:
            datos = json.load(f)

        datos = datos.get("registros", [])
        datos.reverse()

        for item in datos:
            if item["resultado"] == "DENEGADO":
                resultado = ft.Text(item["resultado"], color=ft.Colors.RED)
            else:
                resultado = ft.Text(item["resultado"], color=ft.Colors.GREEN)

            fila = ft.DataRow(
                cells=[
                    ft.DataCell(ft.Text(item["fecha/hora"])),
                    ft.DataCell(ft.Text(item["matricula"])),
                    ft.DataCell(resultado),
                ]
            )
            self.tabla.rows.append(fila)

            self.listaVehiculos.controls.append(ft.Text(item["matricula"]))
        self.update()
        # ----------------------


def main(page: ft.Page):
    page.title = "Acceso Vehículos"
    page.bgcolor = ft.Colors.BLUE_50
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE

    app = App(page)
    page.add(app)
    app.cargar_datos()


ft.app(main)