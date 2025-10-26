# 🏧 ATM Machine (Java Swing GUI)

A simple and interactive **ATM Simulator Application** built using **Java Swing**.  
This project demonstrates basic **banking operations** such as Withdraw, Deposit, and Balance Inquiry through a clean, user-friendly graphical interface.

---

## 🚀 Features

✅ **Deposit Money** – Add money securely to your account balance  
✅ **Withdraw Money** – Withdraw funds with balance validation  
✅ **Check Balance** – Instantly view your updated account balance  
✅ **Error Handling** – Input validation and meaningful feedback messages  
✅ **Modern UI** – Beautifully designed interface using Swing components  
✅ **Smooth UX** – Includes Clear and Exit buttons for better control  

---

## 💡 How It Works

1. Start the ATM application.  
2. An account is created with an initial balance of ₹5000.  
3. Enter an amount in the input field.  
4. Choose one of the following actions:
   - 🏦 **Deposit** – Add the amount to your balance  
   - 💸 **Withdraw** – Deduct the amount (if sufficient balance)  
   - 💰 **Check Balance** – View current available balance  
5. Click **Clear** to reset the input field or **Exit** to close the application.  

---

## 🧮 Example

| Operation  | Input  | Output Message | Resulting Balance |
|-------------|--------|----------------|------------------|
| Deposit     | 1000   | ✅ Deposited successfully | ₹6000 |
| Withdraw    | 1500   | ✅ Withdrawal successful | ₹4500 |
| Withdraw    | 10000  | ❌ Insufficient balance | ₹5000 |
| Check Balance | — | 💰 Current Balance: ₹5000 | ₹5000 |

---

## 🖥️ Interface Preview

🎨 **Title:** "Welcome to ATM"  
💰 **Balance Display:** Shows current account balance  
💡 **Input Field:** Enter deposit or withdrawal amount  
🔘 **Buttons:** Withdraw | Deposit | Check Balance | Clear | Exit  
📋 **Message Label:** Displays transaction results or errors  

<img width="963" height="524" alt="Screenshot 2025-10-25 135554" src="https://github.com/user-attachments/assets/8d59b09f-febf-47e9-a1f2-25b03bdef446" />


## ⚙️ Requirements

- ☕ **Java JDK 8** or later  
- 🪟 **Java Swing** (included with JDK)  
- 🧰 Any IDE such as IntelliJ IDEA, Eclipse, or NetBeans  
- 💻 Works on Windows, macOS, and Linux  

---

## ▶️ How to Run

### Using an IDE:
1. Clone or download this repository:
   ```bash
   git clone https://github.com/ayushteli123/ATM-Machine-Java-Swing.git
Open the project in your preferred IDE.<br>

**2.  Run the main file:**<br>

ATMGUI.java<br>

**3.  Using Command Line:**<br>

javac ATMGUI.java<br>
java ATMGUI<br>

## 👤 Author

**Ayush Teli**<br>
📧 E-mail: ayushteli80@gmail.com<br>
🌐 GitHub: @ayushteli123<br>

## 🧾 License
**📜 MIT License**

Copyright (c) 2025 ayushteli123

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
