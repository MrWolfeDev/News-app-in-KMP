# 📰 News App in Kotlin Multiplatform (Desktop Only)

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?style=for-the-badge&logo=kotlin)
![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-%F0%9F%92%BC-orange?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)
![Platform](https://img.shields.io/badge/Desktop-macOS%20%7C%20Linux%20%7C%20Windows-lightgrey?style=for-the-badge)

> **A cross-platform desktop news reader** built with **Kotlin Multiplatform**, powered by [NewsAPI.org](https://newsapi.org/) 🗞️ for real-time headlines and articles.

---

## ✨ Features

- **🖥️ Desktop-Only** — Works on **macOS**, **Linux**, and **Windows**
- **⚡ Fast & Modern UI** — Built with **Compose Multiplatform**
- **🧩 Modular Architecture** — Shared logic via **KMP**
- **🛠️ Dependency Injection** — Using **Koin**
- **🖼️ Smooth Image Loading** — Via **Kamel**
- **🧭 Navigation** — Powered by **Voyager**
- **📰 Live News Feeds** — `/v2/everything` & `/v2/top-headlines`

---

## 📡 API Usage

This app integrates with [NewsAPI.org](https://newsapi.org/) and uses:

| Endpoint             | Description                         |
|----------------------|-------------------------------------|
| `GET /v2/everything` | Search articles across the web 🕵️‍♂️ |
| `GET /v2/top-headlines` | Fetch top headlines from trusted sources 🧠 |

---

## 🔑 Set Up Your API Key

> 🚨 **Important:** You **must** add your API key to run the app!

📝 **Step-by-step:**

1. 🔐 **Get your key:** [Sign up on NewsAPI.org](https://newsapi.org/register)  
2. 🔧 **Open this file:**  commonMain/di/sharedModule.kt
3.    ** change the api key**



## 🖥️ Supported Platforms

| Platform     | OS           | Supported |
|--------------|--------------|-----------|
| 🖥️ Desktop   | macOS        | ✅ Yes    |
| 🐧 Desktop   | Linux        | ✅ Yes    |
| 🪟 Desktop   | Windows      | ✅ Yes    |
| 📱 Mobile    | Android/iOS  | ❌ Not yet |

> ⚠️ **Note:** Mobile support is planned for the future 🚧 Stay tuned!

## 🚀 Getting Started

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/MrWolfeDev/News-app-in-KMP.git
cd News-app-in-KMP
```

### 2️⃣ Open in IntelliJ IDEA

- ✅ Install the **Kotlin Multiplatform** plugin  
- 🧠 Use the **KMP-aware project structure**

### 3️⃣ Run the App

```bash
./gradlew run
```

---

## 📷 Screenshots

<table>
  <tr>
    <th>🏠 Home Screen</th>
    <th>📰 Headlines</th>
    <th>📄 Article Details</th>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/856394fb-6aa0-442d-a1e5-5890dd61a42f" alt="Home Screen" width="300" />
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/33c0ca0e-961d-43ca-99af-67e5046724c8" alt=" Home Screen" width="300" />
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/1f4f07f8-44a8-4564-8a84-073422666ece" alt="Article Details Screen" width="300" />
    </td>
  </tr>
</table>

## 📜 License

This project is licensed under the **MIT License**.  
👉 See the [LICENSE](LICENSE) file for details.

---

## 🙌 Acknowledgments

- 💡 Powered by [Kotlin Multiplatform](https://kotlinlang.org/lp/multiplatform/)
- 🛠️ Built with ❤️ nice and clean architecture principles

---

## 🔗 Quick Links

- 📜 [License (MIT)](LICENSE)
- 🧪 [NewsAPI Docs](https://newsapi.org/docs)
- 🚀 [Kotlin Multiplatform](https://kotlinlang.org/)
- ✨ [Voyager Navigation](https://github.com/adrielcafe/voyager)


