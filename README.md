# 🌐 Prim's Algorithm Routing Simulator (JavaFX + GraphStream)

This project is a **JavaFX-based graphical simulator** for **Prim’s Algorithm**, a classic greedy algorithm used to find the **Minimum Spanning Tree (MST)** of a connected weighted graph.

It allows users to **build, visualize, and simulate** Prim’s algorithm **step-by-step** with a modern and interactive GUI powered by **GraphStream** and **JavaFX**.

---
## What is Prim’s Algorithm?

**Prim's Algorithm** is a greedy algorithm used to find the **Minimum Spanning Tree (MST)** of a connected, weighted graph. It works as follows:

- **Starts from a source node**
- **Repeatedly adds the lowest-weight edge** that connects a new node to the MST
- **Stops when all nodes are included** in the MST

This simulator helps visualize this step-by-step with **animations**, **color-coded edges**, and a **log window** to observe the algorithm's progress.

---
## 📚 Technologies Used

- **Java 21** – Core programming language
- **JavaFX** – For building GUI components and controls
- **GraphStream** – For graph modeling, MST logic, and visualizations
---
## Features

- Add custom nodes and edges with weights.
- Automatically generate random graphs.
- Visualize each step of Prim's Algorithm.
- Real-time edge highlighting (`MST`, `considered`, `skipped`).
- Adjustable speed control & step-by-step mode.
- Logger window for detailed algorithm progress.
- Smooth JavaFX and GraphStream-based visualization.
---
## Flow Chart
![image](https://github.com/user-attachments/assets/6796d92e-a626-4967-b572-6fb7cdcef095)

---

## Screenshots

![image](https://github.com/user-attachments/assets/2eb0f455-5493-4dc0-8f35-b687a4f7d3ee)
![image](https://github.com/user-attachments/assets/40bdfd6e-3a1d-4eb1-8fe9-4e117d2df17f)
![image](https://github.com/user-attachments/assets/bf8c196b-6129-4353-8148-506c4923a1bc)
![image](https://github.com/user-attachments/assets/8eb4359a-80ca-4594-9391-bbe427c3fde8)

---
## 📁 Project Structure
PRIMROUTING/
- ├── src/
- │ ├── Main.java
- │ ├── PrimSimulatorApp.java
- │ └── PrimAlgorithmExecutor.java
- ├── lib/
- │ ├── gs-core-2.0.jar
- │ ├── gs-ui-javafx-2.0.jar
- │ └── javafx-sdk-21/ # JavaFX SDK directory
- ├── manifest.mf # Jar manifest file
- ├── build.bat # Compiles and packages the app
- ├── run.bat # Runs the app
- └── README.md


---

## How to Run the Project

### Prerequisites:

- Java JDK 21 or later installed
- [JavaFX SDK 21](https://gluonhq.com/products/javafx/) downloaded
- **Note:** The `javafx-sdk-21` folder is too large to be uploaded to this GitHub repository.  
To run this project, you need to manually download the JavaFX SDK (version 21) and place it inside the `lib/` directory of the project.
After downloading:
Extract the folder (e.g., `javafx-sdk-21/`)
Place it inside the project's `lib/` folder.
- `JAVA_HOME` and `PATH` correctly set (especially for `javac` and `jar` commands)

### ⚙Step-by-Step Setup

#### 1. Compile & Create JAR

Simply double-click (or run in terminal):

```bash
build.bat
```
---

## Author

**Muhammad Rayan**  
🎓 Student of Software Engineering @ BUITEMS  
📧 [muhammadraya182@gmail.com](mailto:muhammadraya182@gmail.com)
