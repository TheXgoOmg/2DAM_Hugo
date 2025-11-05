import flet as ft


class App(ft.Column):
    def __init__(self):
        super().__init__()
        self.width = 600
        self.spacing = 30

        self.new_workflow = ft.Column(
            [
                ft.Text("Crear Workflow", size=20),ft.Column([
                    ft.TextField(hint_text="Nombre...", expand=True, border_radius=10),
                    ft.Row([
                        ft.Container(content = ft.TextField(hint_text="Descripción...", scale=0.8, expand=True, border_radius=10)),
                        ft.ElevatedButton("Crear Workflow", style=ft.ButtonStyle(padding=15,shape=ft.RoundedRectangleBorder(radius=10)))
                    ])
                ])
            ]
        )

        self.workflows = ft.Column(
            horizontal_alignment=ft.CrossAxisAlignment.START
        )

        self.controls = [self.new_workflow, self.workflows]


def main(page: ft.Page):
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.title = "App Workflow Switch"
    page.scroll = ft.ScrollMode.ADAPTIVE
    page.add(App())


ft.app(main)
