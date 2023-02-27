# BinChecker

Тестовое приложение для проверки информации по части номера банковской карты.

<table>
<tr>
<td><img src="https://user-images.githubusercontent.com/2891320/221530022-86135758-4042-4080-9250-a168c0714f27.png" width="300"></td>
<td><img src="https://user-images.githubusercontent.com/2891320/221530016-c405cab0-536b-4414-b172-f6d745bd4658.png" width="300">
</td>
</tr>
</table>


Пользователь вводит BIN банковской карты и видит всю доступную информацию о нём, загруженную с https://binlist.net/
История предыдущих запросов выводится списком на главном экране, данные хранятся в базе данных с использованием ORM Room
Нажатие на URL банка, телефон банка, координаты страны отправляет пользователя в
приложение, которое может обработать эти данные (браузер, диалер, карты)

Стек: **Kotlin, Coroutines, Flow, Navigation Component, Retrofit, Room, Koin, Clean Architecture**
