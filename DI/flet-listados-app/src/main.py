import flet as ft

def main(page: ft.Page):
    page.title = "TaskFlow - Design Thinking To-Do"
    page.theme_mode = ft.ThemeMode.LIGHT
    page.padding = 20

    # --- SCROLLBAR THEME  ---
    page.theme = ft.Theme(
        scrollbar_theme=ft.ScrollbarTheme(
            track_visibility=True,
            thumb_color=ft.Colors.BLUE_ACCENT_700,
            thickness=8,
            radius=10,
            main_axis_margin=5,
            cross_axis_margin=5,
        )
    )

    # --- DATOS DE EJEMPLO (Generados con bucles) [cite: 4] ---
    tasks = [
        {"id": i, "task": f"Tarea importante #{i}", "desc": "Descripción detallada de la tarea.", "status": "Pendiente"}
        for i in range(1, 6)
    ]

    def delete_task(e):
        # Función para manejar el Dismissible
        print(f"Tarea eliminada")

    # --- COMPONENTES DE LA UI ---

    # Titulo con Divider
    header = ft.Column([
        ft.Text("Mi Gestor de Tareas", size=32, weight=ft.FontWeight.BOLD, color=ft.Colors.BLUE_900),
        ft.Text("Enfoque centrado en el usuario (Design Thinking)", italic=True, color=ft.Colors.GREY_700),
        ft.Divider(height=20, thickness=2, color=ft.Colors.BLUE_100), # DIVIDER
    ])

    # DataTable para vista resumida
    data_table = ft.DataTable(
        columns=[
            ft.DataColumn(ft.Text("ID")),
            ft.DataColumn(ft.Text("Tarea")),
            ft.DataColumn(ft.Text("Estado")),
        ],
        rows=[
            ft.DataRow(cells=[
                ft.DataCell(ft.Text(str(t["id"]))),
                ft.DataCell(ft.Text(t["task"])),
                ft.DataCell(ft.Chip(label=ft.Text(t["status"]), bgcolor=ft.Colors.GREEN_100)),
            ]) for t in tasks[:3] # Mostramos solo las primeras 3 en la tabla
        ],
    )

    # Lista de Tareas con ExpansionTile y Dismissible
    task_list_view = ft.ListView(expand=1, spacing=10, padding=10)

    for t in tasks:
        task_list_view.controls.append(
            ft.Dismissible( # DISMISSIBLE
                           content=ft.ExpansionTile( # EXPANSION TILE
                                                    title=ft.Text(t["task"]),
                                                    subtitle=ft.Text(f"Estado: {t['status']}"),
                                                    leading=ft.Icon(ft.Icons.CHECK_CIRCLE_OUTLINE, color=ft.Colors.BLUE_400),
                                                    affinity=ft.TileAffinity.LEADING,
                                                    controls=[
                                                        ft.ListTile(
                                                            title=ft.Text(t["desc"]),
                                                            subtitle=ft.Text("Creado dinámicamente mediante bucles."),
                                                        )
                                                    ],
                                                    ),
                           on_dismiss=delete_task,
                           background=ft.Container(bgcolor=ft.Colors.RED_400, content=ft.Text("Eliminar", color="white", weight=ft.FontWeight.BOLD), alignment=ft.alignment.center_right, padding=20),
                           )
        )

    # --- DISEÑO RESPONSIVE (ResponsiveRow)  ---
    layout = ft.ResponsiveRow([
        ft.Column(
            col={"sm": 12, "md": 5},
            controls=[
                ft.Card(content=ft.Container(content=data_table, padding=10), elevation=4),
                ft.Text("Resumen rápido", weight=ft.FontWeight.BOLD, size=16)
            ]
        ),
        ft.Column(
            col={"sm": 12, "md": 7},
            controls=[
                ft.Text("Detalles de Actividad", weight=ft.FontWeight.BOLD, size=16),
                ft.Container(content=task_list_view, height=400, border=ft.border.all(1, ft.Colors.BLUE_50), border_radius=10)
            ]
        )
    ])

    page.add(header, layout)

ft.app(target=main)