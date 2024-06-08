<image src='https://github.com/Sanyaalexandr/WeatherApp/assets/168339484/0388bf48-1f2f-4f83-a103-d0236c4ec347' width=100 /> 

# WeatherApp

Мобильное приложение погоды

## Экран списка городов
* стартовый экран приложения
* реализованы индикатор загрузки и сообщение об ошибке
* реализован стики лейбл
* загрузка данных происходит по запросу https://gist.githubusercontent.com/Stronger197/764f9886a1e8392ddcae2521437d5a3b/raw/65164ea1af958c75c81a7f0221bead610590448e/cities.json
* дополнительно реализована анимация появления / скрытия ```FloatingActionButton``` для возможности проскролить список наверх
* при нажати на город присутствует стандартная анимация нажатия, после нажатия открывается экран погоды

### Скриншоты
<image src='https://github.com/Sanyaalexandr/WeatherApp/assets/168339484/20143224-be3c-4597-ab22-94aec583c780' width=170 />
<image src='https://github.com/Sanyaalexandr/WeatherApp/assets/168339484/165e1c1a-90b7-4b28-a7fe-9141a1737f48' width=170 />
<image src='https://github.com/Sanyaalexandr/WeatherApp/assets/168339484/f8dbea3b-8483-4327-8165-012f00a1f5ec' width=170 />
<image src='https://github.com/Sanyaalexandr/WeatherApp/assets/168339484/a334ff67-ba18-4470-8d78-0dd83d0e874e' width=170 />

## Экран погоды
* для получения погоды используется [OpenWeatherMap API](https://openweathermap.org/current)
* при нажатии на кнопку **Обновить** происходит повторный запрос погоды

### Скриншоты
<image src='https://github.com/Sanyaalexandr/WeatherApp/assets/168339484/7827fbae-4735-4d51-ac15-855672d201c0' width=170 />
<image src='https://github.com/Sanyaalexandr/WeatherApp/assets/168339484/a0e382ad-0906-4aa4-8e07-db31d5007cf5' width=170 />
<image src='https://github.com/Sanyaalexandr/WeatherApp/assets/168339484/54d55708-d54d-445d-a978-49227413bef0' width=170 />

## Технологический стек
* Kotlin
* Jetpack compose
* Kotlin Coroutines
* Ktor
* ViewModel
* MVI
* Compose Navigation
* Single Activity
* Clean Architecture
* Multimodule

