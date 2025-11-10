import flet as ft

class Workflow(ft.Column):
    def __init__(self, nombre, descripcion, comprado, activado, url, page):
        super().__init__()
        self.nombre=nombre
        self.descripcion=descripcion
        self.activado=activado
        self.height=200
        self.width=380
        self.url=url

        self.img_container = ft.Container(
            image=ft.DecorationImage(src=self.url,fit=ft.ImageFit.COVER),
            width=380,
            height=200,
            border=ft.border.all(1, ft.Colors.BLACK)
        )



        self.etiqueta_workflow_comprado = ft.Row(
            controls=
            [
                ft.TextField(
                    value="COMPRADO",
                    disabled=True,
                    width=150,
                    bgcolor=ft.Colors.WHITE,
                    prefix_icon=ft.Icon(name="circle",
                                 color=ft.Colors.BLUE),
                ),
                ft.Switch(value=False, on_change=lambda e: self.switch_clicado(page))
            ],
            alignment=ft.MainAxisAlignment.SPACE_BETWEEN
        )

        self.etiqueta_workflow_alquilado = ft.Row(
            controls=
            [
                ft.TextField(
                    value="ALQUILADO",
                    disabled=True,
                    width=150,
                    bgcolor=ft.Colors.WHITE,
                    prefix_icon=ft.Icon(name="circle",
                                 color=ft.Colors.ORANGE),
                ),
                ft.Switch(value=False, on_change=lambda e: self.switch_clicado(page)),
            ],
            alignment=ft.MainAxisAlignment.SPACE_BETWEEN
        )

        self.etiqueta_workflow=self.asignar_etiqueta(comprado)

        self.tarjeta = ft.Container(border=ft.border.all(0),
                                    height=200,
                                    width=380)

        self.controls=[ft.Stack([self.img_container,self.tarjeta,self.etiqueta_workflow])]

    def switch_clicado(self, page):
        page.open(ft.SnackBar(ft.Text(f"Workflow '{self.nombre}' se ha actualizado")))
        page.update()

    def asignar_etiqueta(self, boolean_comprado):
        if (boolean_comprado):
            return self.etiqueta_workflow_comprado
        else:
            return self.etiqueta_workflow_alquilado




class App(ft.Column):
    def __init__(self, page):
        super().__init__()
        self.width = 440
        self.spacing = 30
        self.page=page

        self.buscar_workflow = ft.Column(
            [
                ft.Container(
                    margin=ft.Margin(0,30,0,0),
                    content=
                        ft.TextField(
                            width=380,
                            bgcolor=ft.Colors.WHITE,
                            border_width=0,
                            prefix_icon=ft.Icon(name="search",color="#c4c4c4"),
                            suffix_icon=ft.Icon(name="settings",color="#c4c4c4"),
                            hint_text="Buscar workflow...",
                            expand=True,
                            border_radius=10,
                            content_padding=ft.padding.Padding(0,25,0,25))
                ),

                ft.Container(
                    margin=ft.Margin(0,75,0,0),
                    content=ft.Text(
                        spans=[
                            ft.TextSpan(text="3", style=ft.TextStyle(weight=ft.FontWeight.BOLD)),
                            ft.TextSpan(text=" workflows encontrados")]
                    )
                )
            ]
        )

        self.workflows = ft.Column(
            [
                Workflow("Etiquetador de Emails","Organiza tu bandeja de entrada",False,False, "https://images.unsplash.com/photo-1620287341401-e2945a4b9daa?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=870",page),
                Workflow("Asistencia al cliente","Responde FAQs automáticamente",True,True, "https://images.unsplash.com/photo-1632435499152-18838be77960?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=870",page),
                Workflow("Gestión de Calendar","Agenda tus eventos rápidamente",True,False, "https://images.unsplash.com/photo-1616530834117-9167fb0d8ebc?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1331",page)
            ]
        )

        self.controls = [self.buscar_workflow, self.workflows]


def main(page: ft.Page):
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.title = "App Workflow Switch"
    page.bgcolor = ft.Colors.BLUE_GREY_50
    page.appbar = ft.AppBar(
        toolbar_height=110,
        bgcolor=ft.Colors.WHITE,
        center_title=True,
        title=ft.Text("Workflows"))
    page.add(App(page))


ft.app(main)
