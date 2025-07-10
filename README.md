# Prim's Algorithm Routing Simulator (JavaFX + GraphStream)
A modern, interactive JavaFX simulator for visualizing Prim's Algorithm step-by-step with GraphStream.

https://github.com/user-attachments/assets/2eb0f455-5493-4dc0-8f35-b687a4f7d3ee
(Example: Prim's Algorithm in action)

🚀 Key Features
✔ Interactive Graph Editor – Add nodes/edges, set weights, and reset the canvas.
✔ Step-by-Step Visualization – Pause, play, or adjust animation speed.
✔ Smart Edge Highlighting – Colors indicate MST edges, considered, and skipped edges.
✔ Real-Time Logging – Detailed console output for algorithm tracing.
✔ Random Graph Generator – Quick-start simulations with weighted graphs.

📌 What is Prim’s Algorithm?
Prim's Algorithm is a greedy method to find the Minimum Spanning Tree (MST) of a weighted graph:

Starts at an arbitrary node.

Grows the MST by adding the cheapest edge connecting the tree to a new node.

Repeats until all nodes are included.

Time Complexity: O(E log V) (with a priority queue).

🛠 Tech Stack
Component	Technology	Purpose
Core	Java 21	Backend logic & OOP structure
GUI	JavaFX	User interface & controls
Graph Engine	GraphStream (gs-core, gs-ui-javafx)	Graph rendering & MST logic
🖥️ Project Structure
plaintext
PRIMROUTING/
├── src/
│   ├── Main.java                    # Entry point
│   ├── PrimSimulatorApp.java        # JavaFX application setup
│   └── PrimAlgorithmExecutor.java   # Core Prim's logic
├── lib/
│   ├── gs-core-2.0.jar              # GraphStream core
│   ├── gs-ui-javafx-2.0.jar         # JavaFX integration
│   └── javafx-sdk-21/               # JavaFX SDK (manually added)
├── build.bat                        # Compiles + packages JAR
├── run.bat                          # Launches the app
└── README.md
🛠 Setup & Execution
📋 Prerequisites
Java JDK 21+ (Download)

JavaFX SDK 21 (Download)

⚠️ Place the extracted javafx-sdk-21 folder in PRIMROUTING/lib/.

🚀 Quick Start
Compile & Package:

bash
./build.bat   # Creates executable JAR
Run the Simulator:

bash
./run.bat     # Launches the JavaFX app
📸 Screenshots
Feature	Preview
Graph Editor	https://github.com/user-attachments/assets/40bdfd6e-3a1d-4eb1-8fe9-4e117d2df17f
MST Result	https://github.com/user-attachments/assets/bf8c196b-6129-4353-8148-506c4923a1bc
👥 Authors
Name	Role	Contact
Muhammad Rayan	Core Algorithm	muhammadraya182@gmail.com
Abdullah Khalid	GUI Design	abdullahkhalid8835@gmail.com
Muhammad Nassar	Documentation	Nassaransari@gmail.com
Affiliation: Software Engineering Students @ BUITEMS.

🔗 Extras
Flowchart:
https://github.com/user-attachments/assets/6796d92e-a626-4967-b572-6fb7cdcef095

Need Help? Open an issue or email us!

🎯 Why This Project?
Ideal for educators, students, or developers learning:

Greedy Algorithms

Graph Theory

JavaFX GUI Development

✨ Happy Coding! ✨
