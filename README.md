# Ecodim

Ecodim is a mobile application built to fetch and display lessons stored on Firebase. Users can mark lessons as favorites (stored locally using Room DB) and submit comments via anonymous authentication with Firebase Auth.

## Features

- 📖 **Fetch Lessons:** Retrieve all lessons stored on Firebase Firestore and display them to end-users.
- ⭐ **Favorite Lessons:** Save lessons as favorites and store them locally using Room DB.
- 💬 **Commenting System:** Users can submit comments using anonymous authentication via Firebase Auth.
- 🔄 **Offline Storage:** Favorited lessons are available offline thanks to Room DB.
- 📱 **Modern UI:** Built with Jetpack Compose and Material3 for a smooth and responsive user experience.

## Technologies Used

### Programming Language & Tools
- **Kotlin:** Modern language for Android development.
- **Gradle Kotlin DSL:** Type-safe build configuration.

### UI
- **Jetpack Compose:** Declarative UI framework.
- **Material3:** Google's Material Design 3 for consistent and beautiful UI.
- **New SplashScreen API:** Smooth launch experience.

### Architecture & State Management
- **MVVM (Model-View-ViewModel):** Clear separation of concerns for better maintainability.
- **ViewModel:** Retaining UI data across configuration changes.
- **Flow & Coroutines:** Asynchronous programming with structured concurrency.

### Data Storage
- **Room DB:** Local database for storing favorites, allowing offline access.
- **Firebase Firestore:** Cloud storage for lessons.

### Authentication
- **Firebase Auth:** Anonymous login to allow users to submit comments.

### Navigation
- **Jetpack Navigation:** Managing in-app navigation with a single-activity architecture.

### Lifecycle Management
- **Lifecycle:** Efficient handling of UI components' lifecycle.

### Dependency Injection
- **Koin:** Simple and lightweight dependency injection framework.

## Installation

1. Clone the repository:
```bash
    git clone https://github.com/your-username/ecodim-android.git
```

2. Open the project in Android Studio.

3. Build and run the application on an emulator or physical device.

## Usage

- View all lessons fetched from Firebase.
- Mark lessons as favorites for offline access.
- Submit comments via anonymous authentication.

## Contribution

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Make your changes and commit (`git commit -m 'Add your feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

Feel free to reach out for any questions or suggestions!

- Author: Josh-Muleshi
- Email: jmuleshi2@gmail.com
- GitHub: [your-github-profile](https://github.com/josh-Muleshi)

---

Happy coding with Ecodim! 🚀



