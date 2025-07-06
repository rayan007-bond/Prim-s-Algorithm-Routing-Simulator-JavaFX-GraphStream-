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
