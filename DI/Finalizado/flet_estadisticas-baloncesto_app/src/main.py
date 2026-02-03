import flet as ft
import time

# Sample data for Michael Jordan
player_data = {
    "Michael Jordan": {
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/a/ae/Michael_Jordan_in_2014.jpg",
        "points": [32, 35, 28, 40, 30],
        "rebounds": [8, 7, 6, 9, 7],
        "assists": [6, 5, 5, 4, 7],
        "steals": [2, 3, 2, 1, 2],
        "blocks": [1, 1, 0, 1, 0],
        "turnovers": [3, 2, 4, 3, 2],
        "fouls": [2, 3, 2, 4, 2],
        "minutes": [38, 40, 36, 42, 39],
    },
    "LeBron James": {
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/LeBron_James_%2851959977144%29_%28cropped2%29.jpg/640px-LeBron_James_%2851959977144%29_%28cropped2%29.jpg",
        "points": [28, 32, 25, 30, 29],
        "rebounds": [10, 8, 7, 12, 9],
        "assists": [8, 9, 6, 7, 8],
        "steals": [1, 2, 1, 1, 0],
        "blocks": [1, 1, 0, 2, 1],
        "turnovers": [3, 4, 2, 3, 2],
        "fouls": [2, 1, 3, 2, 2],
        "minutes": [36, 38, 35, 40, 37],
    },
    "Stephen Curry": {
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Stephen_Curry_%28Crop%29.jpg/640px-Stephen_Curry_%28Crop%29.jpg",
        "points": [30, 28, 35, 22, 31],
        "rebounds": [5, 4, 6, 3, 5],
        "assists": [7, 8, 5, 6, 9],
        "steals": [2, 1, 2, 1, 1],
        "blocks": [0, 0, 0, 0, 0],
        "turnovers": [4, 3, 2, 3, 4],
        "fouls": [1, 2, 1, 2, 1],
        "minutes": [34, 36, 33, 30, 35],
    },
    "Kevin Durant": {
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Kevin_Durant_Feb_2014.jpg/640px-Kevin_Durant_Feb_2014.jpg",
        "points": [25, 30, 28, 32, 27],
        "rebounds": [7, 8, 6, 7, 9],
        "assists": [4, 5, 3, 4, 5],
        "steals": [1, 1, 0, 1, 0],
        "blocks": [2, 1, 1, 2, 1],
        "turnovers": [2, 3, 2, 1, 2],
        "fouls": [2, 2, 1, 3, 2],
        "minutes": [36, 37, 35, 38, 36],
    },
    "Kobe Bryant": {
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Kobe_Bryant_2014.jpg/960px-Kobe_Bryant_2014.jpg?20220108175129",
        "points": [33, 30, 29, 36, 31],
        "rebounds": [6, 5, 4, 7, 5],
        "assists": [5, 6, 5, 4, 6],
        "steals": [1, 2, 1, 1, 0],
        "blocks": [0, 1, 0, 0, 0],
        "turnovers": [3, 3, 4, 2, 3],
        "fouls": [3, 2, 3, 2, 2],
        "minutes": [39, 40, 38, 41, 39],
    },
    "Shaquille O'Neal": {
        "image_url": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Shaquille_O%27Neal_%28cropped%29.jpg/640px-Shaquille_O%27Neal_%28cropped%29.jpg",
        "points": [22, 25, 20, 28, 23],
        "rebounds": [15, 12, 14, 10, 13],
        "assists": [2, 3, 1, 2, 2],
        "steals": [0, 0, 1, 0, 0],
        "blocks": [3, 2, 2, 4, 3],
        "turnovers": [4, 5, 3, 4, 4],
        "fouls": [4, 3, 5, 4, 5],
        "minutes": [32, 30, 28, 35, 31],
    },
}

def main(page: ft.Page):
    page.title = "Basketball Stats App"
    page.theme = ft.Theme(
        color_scheme_seed=ft.Colors.ORANGE,
    )
    page.vertical_alignment = ft.CrossAxisAlignment.CENTER
    page.horizontal_alignment = ft.MainAxisAlignment.CENTER

    # --- View Definitions ---

    def create_player_stats_view(player_name):
        player = player_data[player_name]
        return ft.Container(
            content=ft.Column(
                [
                    ft.Image(
                        src=player["image_url"],
                        width=150,
                        height=150,
                        fit=ft.BoxFit.COVER,
                        border_radius=ft.BorderRadius.all(75)
                    ),
                    ft.Text(player_name, size=30, weight=ft.FontWeight.BOLD, color=ft.Colors.WHITE),
                    ft.Row(
                        [
                            ft.Column(
                                [
                                    ft.Text("Avg Pts", size=16, color=ft.Colors.WHITE),
                                    ft.Text(
                                        f"{sum(player['points']) / len(player['points']):.1f}",
                                        size=24,
                                        weight=ft.FontWeight.BOLD,
                                        color=ft.Colors.WHITE,
                                    ),
                                ],
                                horizontal_alignment=ft.CrossAxisAlignment.CENTER,
                            ),
                            ft.Column(
                                [
                                    ft.Text("Avg Reb", size=16, color=ft.Colors.WHITE),
                                    ft.Text(
                                        f"{sum(player['rebounds']) / len(player['rebounds']):.1f}",
                                        size=24,
                                        weight=ft.FontWeight.BOLD,
                                        color=ft.Colors.WHITE,
                                    ),
                                ],
                                horizontal_alignment=ft.CrossAxisAlignment.CENTER,
                            ),
                            ft.Column(
                                [
                                    ft.Text("Avg Ast", size=16, color=ft.Colors.WHITE),
                                    ft.Text(
                                        f"{sum(player['assists']) / len(player['assists']):.1f}",
                                        size=24,
                                        weight=ft.FontWeight.BOLD,
                                        color=ft.Colors.WHITE,
                                    ),
                                ],
                                horizontal_alignment=ft.CrossAxisAlignment.CENTER,
                            ),
                        ],
                        alignment=ft.MainAxisAlignment.SPACE_AROUND,
                        expand=True,
                    ),
                    ft.Divider(height=10, color=ft.Colors.WHITE_30),
                    ft.Row(
                        [
                            ft.Chip(
                                label=ft.Text(f"{max(player['points'])} Max Pts", size=14),
                                leading=ft.Icon(ft.Icons.STAR),
                                bgcolor=ft.Colors.AMBER_500,
                            ),
                            ft.Chip(
                                label=ft.Text(f"{max(player['rebounds'])} Max Reb", size=14),
                                leading=ft.Icon(ft.Icons.SPORTS_BASKETBALL),
                                bgcolor=ft.Colors.LIGHT_GREEN_500,
                            ),
                            ft.Chip(
                                label=ft.Text(f"{max(player['assists'])} Max Ast", size=14),
                                leading=ft.Icon(ft.Icons.HANDSHAKE),
                                bgcolor=ft.Colors.BLUE_500,
                            ),
                        ],
                        alignment=ft.MainAxisAlignment.CENTER,
                        spacing=10,
                    ),
                    ft.Divider(height=10, color=ft.Colors.WHITE_30),
                    ft.Container(
                        content=ft.Column(
                            [
                                ft.Text("Points per Game", size=18, color=ft.Colors.WHITE),
                                ft.Row(
                                    [
                                        ft.Column(
                                            [
                                                ft.Container(
                                                    width=20,
                                                    height=points,
                                                    bgcolor=ft.Colors.BLUE_ACCENT_100,
                                                    alignment=ft.Alignment(0, 1),
                                                    border_radius=ft.BorderRadius.all(5),
                                                ),
                                                ft.Text(f"{points}", size=12, color=ft.Colors.WHITE),
                                                ft.Text(f"G{i+1}", size=12, color=ft.Colors.WHITE),
                                            ],
                                            horizontal_alignment=ft.CrossAxisAlignment.CENTER,
                                            spacing=5,
                                        )
                                        for i, points in enumerate(player["points"])
                                    ],
                                    alignment=ft.MainAxisAlignment.SPACE_EVENLY,
                                    expand=True,
                                ),
                            ],
                            horizontal_alignment=ft.CrossAxisAlignment.CENTER,
                            spacing=10,
                        ),
                        bgcolor=ft.Colors.BLUE_GREY_800,
                        border_radius=10,
                        padding=10,
                        margin=10,
                        height=200,
                        alignment=ft.Alignment(0, 1),
                        expand=True,
                    ),
                    ft.Container(
                        content=ft.Text(
                            "ADVERTISEMENT: Buy new basketball shoes!",
                            color=ft.Colors.BLACK,
                            weight=ft.FontWeight.BOLD,
                        ),
                        width=float('inf'),
                        padding=10,
                        bgcolor=ft.Colors.YELLOW_200,
                        alignment=ft.Alignment(0, 0),
                        border_radius=5,
                        margin=10,
                    ),
                ],
                scroll=ft.ScrollMode.ADAPTIVE,
                expand=True,
                horizontal_alignment=ft.CrossAxisAlignment.CENTER,
                spacing=20,
            ),
            alignment=ft.Alignment(0, 0),
            bgcolor=ft.Colors.DEEP_ORANGE_700,
        )

    # --- Event Handlers ---
    def on_player_change(e):
        selected_player = e.control.value
        page_views.controls[0] = create_player_stats_view(selected_player)
        page.update()

    def clean_screen():
        page.controls.clear()
        page.update()

    def show_stats_screen(e):
        clean_screen()
        time.sleep(2)
        page.add(
            ft.AppBar(
                title=player_dropdown,
                bgcolor=ft.Colors.BLUE_GREY_700,
            ),
            page_views,
        )
        page.update()

    def show_menu_screen(e):
        page.controls.clear()
        page.add(menu_screen)
        page.update()

    # --- UI Components ---
    player_dropdown = ft.Dropdown(
        options=[ft.dropdown.Option(player) for player in player_data.keys()],
        value=list(player_data.keys())[0],
        on_select=on_player_change,
        width=200,
    )

    page_views = ft.Stack(
        [
            create_player_stats_view(list(player_data.keys())[0]), # Initial view for the first player
        ],
        expand=True
    )

    menu_screen = ft.Container(
        content=ft.Column(
            [
                ft.Container(
                    content=ft.Column(
                        [
                            ft.Image(
                                src="assets/nba_logo.png",
                                width=150,
                                height=150,
                            ),
                            ft.Text("NBA Stats Viewer", size=25, weight=ft.FontWeight.BOLD, color=ft.Colors.WHITE),
                        ],
                        horizontal_alignment=ft.CrossAxisAlignment.CENTER,
                        spacing=10,
                    ),
                    width=float('inf'),
                    padding=ft.Padding.only(top=30, bottom=20),
                    bgcolor=ft.Colors.BLUE_GREY_900,
                    alignment=ft.Alignment(0, 0),
                ),
                ft.FilledButton(
                    "Ver Estadísticas",
                    icon=ft.Icons.QUERY_STATS,
                    on_click=show_stats_screen,
                    style=ft.ButtonStyle(
                        shape=ft.RoundedRectangleBorder(radius=10),
                        bgcolor=ft.Colors.ORANGE_ACCENT_700,
                        color=ft.Colors.WHITE,
                    )
                ),
            ],
            horizontal_alignment=ft.CrossAxisAlignment.CENTER,
            alignment=ft.MainAxisAlignment.CENTER,
            spacing=25,
        ),
        alignment=ft.Alignment(0, 0),
        expand=True,
    )

    stats_screen = ft.Column(
        [
            ft.AppBar(
                title=player_dropdown,
                bgcolor=ft.Colors.BLUE_GREY_700,
                leading=ft.IconButton(ft.Icons.ARROW_BACK, on_click=show_menu_screen),
            ),
            ft.Container(
                content=page_views,
                expand=True,
            ),
        ],
        visible=False,
        expand=True,
    )

    page.add(menu_screen) # Only add menu_screen initially
    page.update()
    page.theme = ft.Theme(
        color_scheme_seed=ft.Colors.ORANGE,
    )
    page.vertical_alignment = ft.CrossAxisAlignment.CENTER
    page.horizontal_alignment = ft.MainAxisAlignment.CENTER

    # --- Page Content ---

    page_views = ft.Stack(
        [
            create_player_stats_view(list(player_data.keys())[0]), # Initial view for the first player
        ],
        expand=True
    )

    def on_player_change(e):
        selected_player = e.control.value
        page_views.controls[0] = create_player_stats_view(selected_player)
        page.update()

    player_dropdown = ft.Dropdown(
        options=[ft.dropdown.Option(player) for player in player_data.keys()],
        value=list(player_data.keys())[0],
        on_select=on_player_change,
        width=200,
    )




if __name__ == "__main__":
    ft.run(main)