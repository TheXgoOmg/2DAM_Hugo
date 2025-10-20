import flet as ft

class MyButton(ft.ElevatedButton):
    def __init__(self, text, on_click):
        super().__init__()
        self.bgcolor = ft.Colors.BLUE_300
        self.color = ft.Colors.RED_100
        self.text = text
        self.on_click = on_click

def main(page: ft.Page):
    def add_clicked(e):
        print("Task added.")
        tasks_view.controls.append(ft.Checkbox(label=new_task.value))
        new_task.value = ""
        page.update()

    new_task = ft.TextField(hint_text="Que debes hacer?",expand=True)

    tasks_view = ft.Column()
    view=ft.Column(
        width=600,
        controls=[
            ft.Row(
                controls=[
                    new_task,
                    MyButton(text="Addddd", on_click=add_clicked),
                ],
            ),
            tasks_view,
        ],
    )

    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.add(view)

ft.app(main)
