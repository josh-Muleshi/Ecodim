# Ecodim (Android)

Ecodim is a mobile application built with modern Android technologies to fetch and display lessons stored on Firebase. Users can mark lessons as favorites (stored locally using Room DB) and submit comments via anonymous authentication with Firebase Auth.

## Features

- 📖 **Fetch Lessons:** Retrieve all lessons stored on Firebase Firestore and display them to end-users.
- ⭐ **Favorite Lessons:** Save lessons as favorites and store them locally using Room DB.
- 💬 **Commenting System:** Users can submit comments using anonymous authentication via Firebase Auth.
- 🔄 **Offline Storage:** Favorited lessons are available offline thanks to Room DB.
- 📱 **Modern UI:** Built with Jetpack Compose and Material3 for a smooth and responsive user experience.

## Technologies Used

### Programming Language
- **Kotlin:** The official language for Android development, providing a modern and expressive syntax.

### UI Framework
- **Jetpack Compose:** A modern toolkit for building native Android UI with declarative components and powerful state handling.

### Architecture
- **MVVM (Model-View-ViewModel):** Separation of concerns for better maintainability and testability.

### Dependency Injection
- **Koin:** A lightweight dependency injection framework that simplifies application setup and dependency management.

### Markdown Support
- **Markdown:** Lessons are written and displayed using Markdown formatting for better readability and styling.

### Navigation
- **Jetpack Navigation:** Handling in-app navigation with a single-activity architecture.

### Splash Screen
- **New SplashScreen API:** Provides a smooth and consistent launch experience for the application.

### Design System
- **Material3:** Implementation of Google's Material Design 3 for enhanced UI consistency and accessibility.

### Local Storage
- **Room DB:** Storing favorite lessons locally, allowing offline access.

### Lifecycle Management
- **Lifecycle:** Handling UI components lifecycle-aware, improving performance and robustness.

### Asynchronous Programming
- **Flow & Coroutines:** Managing asynchronous data streams and operations in a structured, Kotlin-friendly manner.

### ViewModel
- **ViewModel:** Retaining UI-related data across configuration changes.

### Build System
- **Gradle Kotlin DSL:** Using Kotlin for build scripts, providing type-safety and better IDE support.

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

