# Order Simulator

## 📌 Overview
This is an Android application built with **Kotlin** using **Jetpack Compose** and **Clean Architecture** principles. The app allows users to manage sales orders, including creating orders, adding items, calculating totals, and viewing order details.

## 🎯 Features
- Create new orders with multiple items.
- Calculate total price dynamically.
- Store orders persistently using **Room Database**.
- View all orders in a list format.
- Click on an order to view its details.

## 🏗 Architecture
The project follows **Clean Architecture** and is divided into three main layers:

```
com.yourapp.orders
│── data
│── domain
│── ui
```

## 🛠 Technologies Used
- **Kotlin**
- **Jetpack Compose** (UI)
- **Room Database** (Persistence)
- **MVVM + Clean Architecture** (Best practices)
- **Coroutines & Flow** (Asynchronous processing)
- **Material Design 3**

## 📂 Project Setup
### 1️⃣ Clone the Repository
```sh
git clone https://github.com/joaoeudes7/OrderSimulator.git
```

### 2️⃣ Open in Android Studio
- Ensure you have the latest **Android Studio Giraffe** or later.
- Open the project and let Gradle sync.

### 3️⃣ Run the App
- Connect an emulator or a physical device.
- Click on **Run ▶** inside Android Studio.

## 🚀 How to Use
1. **Create an Order**:
    - Click the "+" button on the home screen.
    - Add items (name, quantity, unit price).
    - Click "Save" to store the order.

2. **View Orders**:
    - Orders are displayed in a **LazyColumn** list.
    - Click an order to see its details.

3. **Delete Orders**:
    - Inside order details, a **delete button** is available.

## 🏆 Future Improvements
- Implement search functionality.
- Add support for exporting order data.
- Implement dark mode.
