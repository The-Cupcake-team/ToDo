# TODO App

This project is a full functional TODO app, that allows you to schedule and track tasks, both individually and as a team. It provides a login screen, a register screen, a personal Todo screen, and a team Todo screen.

## Functional Requirements

- **Login Screen:** Users can log in using their username and password. There are no options for email or social media login.
- **Register Screen:** Users can register using their username and password, with validation for username and password length and pattern.
- **Personal Todo Screen:** Users can add personal Todo items that include a "title," "content," "create time and date," and status, which can be "Todo," "in Progress," or "Done."
- **Team Todo Screen:** Similar to the personal todo screen, but with an additional field for the assignee's name.
- Users can only update the status of their Todo items, with no option to remove them.

## Technical Requirements

- The app uses the OKHttp library to handle networking.
- ViewBinding is used to manage UI states.

- Shared Preferences is used to store tokens.
- The Log Interceptor helps with debugging by logging all requests.
- A suitable Interceptor is created to authorize all requests.
- The app does not store passwords or usernames in Shared Preferences, and users stay logged in until the token expires.

## Credits

This project is created by
[@Tarek Idrees](https://github.com/TarekIdrees)
[@Mustafa Sabe Alarab](https://github.com/mustafa-sab3alarab)
[@Andrew Abo Alhana](https://github.com/AndrewAboalhana)
[@Ali mohamed ali](https://github.com/abuhussien28)
[@Ameer Amjed](https://github.com/AmeerAmjed)
[@Ali zainy](https://github.com/Ali873-debug)
[@Bilal Alkhatib](https://github.com/Belal-Alkhatib)
[@Asia Sama](https://github.com/Asiasama710)
[@Hassan Ayman](https://github.com/Hassan3Ayman)
[@Ethaar Hussein](https://github.com/Ethaar7)
[@David Samuel](https://github.com/davidsamuelx)
[@Yousef Salem](https://github.com/yousef-salem)

##  Screenshots

<img src="https://user-images.githubusercontent.com/54080527/233779996-fee6fdf5-40d6-4a11-a565-e8a63aadae8a.png" width="200">  <img src="https://user-images.githubusercontent.com/54080527/233780018-6bd0e870-2636-4b71-bac9-9d8856dd4e6d.png" width="200">   <img src="https://user-images.githubusercontent.com/54080527/233780007-c876ed7a-c1c4-4077-a66b-13af8ad25b7e.png" width="200">

<img src="https://user-images.githubusercontent.com/54080527/233780375-8a7ecd8d-4f7f-4efe-a630-93e445e4c1b9.png" width="200">   <img src="https://user-images.githubusercontent.com/54080527/233782048-41d0b541-82de-43b5-aa7b-43e4d1e71832.png" width="200">   <img src="https://user-images.githubusercontent.com/54080527/233780655-d595b02f-8d0e-4da9-a53f-1391dbcfffc9.png" width="200">

<img src="https://user-images.githubusercontent.com/54080527/233781568-0e4efed1-f4b8-43f0-bf8b-193d073252e2.png" width="200">  <img src="https://user-images.githubusercontent.com/54080527/233781844-4a47a8b3-70c4-4125-88bc-3c5c588109cc.png" width="200">

