# Book Nest (formerly E-Book)

Book Nest is a native Android application built with modern Android development practices. It serves as an online bookstore and reading application where users can browse, discover, and read books in various categories seamlessly.

## 🚀 Features

- **Browse by Category:** Explore books across multiple genres including Programming, Classic Literature, Mystery, Psychology, and more.
- **Read In-App:** Directly read and view PDF books right within the application.
- **Dynamic Content:** Books are synced and served dynamically from Firebase Realtime Database.
- **Modern UI:** A clean, responsive, and beautiful UI built entirely with Jetpack Compose using Material Design 3 guidelines.
- **Data Processor:** Includes Python scripts to easily manage, categorize, and deploy large lists of book data into Firebase.

## 🛠 Tech Stack

**Architecture:** MVVM (Model-View-ViewModel) + Clean Architecture principles
**UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
**Programming Languages:** 
- **Kotlin** (Android App)
- **Python** (Data processing scripts)

**Libraries & Tools:**
- **[Hilt](https://dagger.dev/hilt/)** - Dependency Injection
- **[Firebase](https://firebase.google.com/)** - Realtime Database & Firestore for backend data
- **[Navigation Compose](https://developer.android.com/jetpack/compose/navigation)** - App navigation and routing
- **[Coil](https://coil-kt.github.io/coil/compose/)** - Asynchronous image loading
- **[Retrofit & Gson](https://square.github.io/retrofit/)** - Type-safe HTTP client & JSON serialization
- **[Bouquet](https://github.com/Grizzi91/Bouquet)** - Open-source PDF viewer for Jetpack Compose
- **Pandas** - Python library for CSV data manipulation

## 📂 Project Structure

- `app/` - The main Android application code.
  - `Ui_layer/` - Contains Jetpack Compose UI components, Screens, and Navigation graphs.
  - `ViewModel/` - Contains the ViewModels binding the UI and data layer.
  - `Data/` - Contains Repositories and Response models.
  - `di/` - Contains Hilt modules for Dependency Injection.
- `books.py` - A Python script designed to process raw CSV book data (`new data1.csv`), categorize them intelligently, and convert them to Firebase-compatible `books.json`.
- `books.json` / `e-book-763b5-default-rtdb-export.json` - JSON backups for the Firebase Realtime Database.

## ⚙️ Getting Started

### Prerequisites

- [Android Studio Ladybug or later](https://developer.android.com/studio) recommended.
- JDK 17
- Python 3.x (Optional, only if you want to run the data scripts).

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Tarun-sharma05/Book-Nest.git
   ```

2. **Open the project in Android Studio:**
   - Select `Open an existing Android Studio project` and point to the cloned directory.

3. **Firebase Setup:**
   - Make sure you have your own `google-services.json` inside the `app/` folder connected to your Firebase project.
   - Import the `e-book-763b5-default-rtdb-export.json` file into your Firebase Realtime database.

4. **Run the App:**
   - Sync the project with Gradle files.
   - Choose your emulator or physical device.
   - Click `Run 'app'`.

### Uploading New Data (Python)

If you have a customized CSV of book data and want to convert it to a Firebase-ready JSON file:
1. Ensure Python `pandas` is installed.
2. Edit `books.py` with the correct path to your CSV.
3. Run `python books.py`.
4. Import the generated `books.json` straight into your Firebase Realtime Database.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

## 📝 License

This project is open-source and available under the terms of the MIT License.
