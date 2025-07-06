import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.javafx.FxGraphRenderer;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Prim's Algorithm Routing Simulator (Enhanced Version)
 * Features:
 * - Interactive graph construction
 * - Random graph generation
 * - Step-by-step visualization
 * - Progress tracking
 * - Error handling
 * - Thread-safe UI updates
 */
public class PrimSimulatorApp extends Application {
    
    // UI Components
    private Graph graph;
    private FxViewer viewer;
    private TextField node1Field, node2Field, weightField;
    private ComboBox<String> sourceNodeCombo;
    private Button addEdgeBtn, runPrimBtn, resetBtn, generateBtn;
    private Label statusLabel;
    private TextArea algorithmLog;
    private CheckBox stepByStepCheck;
    private Slider speedSlider;
    private ProgressBar progressBar;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prim's Algorithm Routing Simulator (Enhanced)");
        
        initializeGraph();
        
        BorderPane root = new BorderPane();
        root.setCenter(createGraphView());
        root.setBottom(createControlPanel());
        root.setRight(createLogPanel());
        
        Scene scene = new Scene(root, 1100, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void initializeGraph() {
        graph = new SingleGraph("Prim's MST");
        graph.setAttribute("ui.stylesheet", 
            "node { fill-color: #4682B4; size: 20px; text-alignment: above; }" +
            "node.source { fill-color: #FF6347; }" +
            "edge { fill-color: #777; size: 2px; text-size: 14; }" +
            "edge.mst { fill-color: #32CD32; size: 3px; }" +
            "edge.considered { fill-color: #FFD700; size: 3px; }");
        graph.setAutoCreate(true);
    }
    
    private FxViewPanel createGraphView() {
        viewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();
        return (FxViewPanel) viewer.addDefaultView(false, new FxGraphRenderer());
    }
    
    private VBox createControlPanel() {
        // Input fields
        node1Field = new TextField();
        node1Field.setPromptText("Node A");
        node2Field = new TextField();
        node2Field.setPromptText("Node B");
        weightField = new TextField();
        weightField.setPromptText("Weight (≥1)");
        
        // Source node selection
        sourceNodeCombo = new ComboBox<>();
        sourceNodeCombo.setPromptText("Select source node");
        
        // Buttons
        addEdgeBtn = new Button("Add Edge");
        addEdgeBtn.setOnAction(e -> addEdge());
        
        runPrimBtn = new Button("Run Prim's Algorithm");
        runPrimBtn.setOnAction(e -> runPrimsAlgorithm());
        
        resetBtn = new Button("Reset Graph");
        resetBtn.setOnAction(e -> resetGraph());
        
        generateBtn = new Button("Generate Random Graph");
        generateBtn.setOnAction(e -> generateRandomGraph());
        
        // Step-by-step controls
        stepByStepCheck = new CheckBox("Step-by-step visualization");
        stepByStepCheck.setSelected(true);
        
        speedSlider = new Slider(1, 10, 5);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setBlockIncrement(1);
        
        // Progress bar
        progressBar = new ProgressBar(0);
        progressBar.setVisible(false);
        
        // Status label
        statusLabel = new Label("Ready to build graph");
        statusLabel.setStyle("-fx-font-weight: bold;");
        
        // Layout
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.addRow(0, new Label("Node 1:"), node1Field);
        inputGrid.addRow(1, new Label("Node 2:"), node2Field);
        inputGrid.addRow(2, new Label("Weight:"), weightField);
        inputGrid.addRow(3, new Label("Source Node:"), sourceNodeCombo);
        
        HBox buttonBox = new HBox(10, addEdgeBtn, runPrimBtn, resetBtn, generateBtn);
        HBox stepBox = new HBox(10, stepByStepCheck, new Label("Speed:"), speedSlider);
        
        VBox controlPanel = new VBox(15, inputGrid, buttonBox, stepBox, progressBar, statusLabel);
        controlPanel.setPadding(new Insets(15));
        controlPanel.setStyle("-fx-background-color: #f0f0f0;");
        
        return controlPanel;
    }
    
    private ScrollPane createLogPanel() {
        algorithmLog = new TextArea();
        algorithmLog.setEditable(false);
        algorithmLog.setWrapText(true);
        algorithmLog.setPrefWidth(350);
        
        ScrollPane scrollPane = new ScrollPane(algorithmLog);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        return scrollPane;
    }
    
   private void addEdge() {
    try {
        String nodeA = node1Field.getText().trim();
        String nodeB = node2Field.getText().trim();
        String weightStr = weightField.getText().trim();
        
        // Validate inputs
        if (nodeA.isEmpty() || nodeB.isEmpty() || weightStr.isEmpty()) {
            statusLabel.setText("Error: All fields required");
            return;
        }
        
        int weight = Integer.parseInt(weightStr);
        if (weight <= 0) {
            statusLabel.setText("Error: Weight must be positive");
            return;
        }

        // Create nodes if they don't exist
        if (graph.getNode(nodeA) == null) {
            graph.addNode(nodeA);
        }
        if (graph.getNode(nodeB) == null) {
            graph.addNode(nodeB);
        }

        // Create edge
        String edgeId = nodeA + "-" + nodeB + "-" + weight;
        Edge edge = graph.addEdge(edgeId, nodeA, nodeB);
        edge.setAttribute("weight", weight);
        edge.setAttribute("ui.label", weight);

        statusLabel.setText("Added edge: " + nodeA + " ↔ " + nodeB);
        updateNodeComboBox();
        
    } catch (Exception e) {
        statusLabel.setText("Error: " + e.getMessage());
    }
}
    private Edge findEdgeBetweenNodes(String nodeA, String nodeB) {
        Node n1 = graph.getNode(nodeA);
        Node n2 = graph.getNode(nodeB);
        if (n1 == null || n2 == null) return null;
        
        return n1.getEdgeBetween(n2); // Works for undirected graphs
    }
    
    private void runPrimsAlgorithm() {
        if (graph.getNodeCount() < 1) {
            statusLabel.setText("Error: Graph needs at least one node");
            return;
        }
        
        String selectedSource = sourceNodeCombo.getValue();
        if (selectedSource == null) {
            statusLabel.setText("Error: Please select a source node");
            return;
        }

        // Disable controls during execution
        setControlsDisabled(true);
        progressBar.setVisible(true);
        progressBar.setProgress(0);

        // Run in background thread
        new Thread(() -> {
            try {
                PrimAlgorithmExecutor executor = new PrimAlgorithmExecutor(
                    graph, 
                    this::logMessage,
                    stepByStepCheck.isSelected(),
                    (int) speedSlider.getValue(),
                    this::updateProgress
                );
                
                executor.execute(selectedSource);
                
                Platform.runLater(() -> {
                    statusLabel.setText("Prim's algorithm completed");
                    progressBar.setVisible(false);
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    statusLabel.setText("Error: " + e.getMessage());
                    progressBar.setVisible(false);
                });
            } finally {
                Platform.runLater(() -> setControlsDisabled(false));
            }
        }).start();
    }
    
    private void updateProgress(double progress) {
        Platform.runLater(() -> progressBar.setProgress(progress));
    }
    
    private void setControlsDisabled(boolean disabled) {
        Platform.runLater(() -> {
            addEdgeBtn.setDisable(disabled);
            runPrimBtn.setDisable(disabled);
            generateBtn.setDisable(disabled);
            resetBtn.setDisable(disabled);
            node1Field.setDisable(disabled);
            node2Field.setDisable(disabled);
            weightField.setDisable(disabled);
            sourceNodeCombo.setDisable(disabled);
        });
    }
    
    private void resetGraph() {
        graph.clear();
        algorithmLog.clear();
        sourceNodeCombo.getItems().clear();
        progressBar.setProgress(0);
        progressBar.setVisible(false);
        statusLabel.setText("Graph reset");
    }
    
    private void generateRandomGraph() {
        resetGraph();
        Random rand = new Random();
        int nodeCount = 5 + rand.nextInt(6); // 5-10 nodes
        int edgeCount = nodeCount + rand.nextInt(nodeCount * 2); // n to 3n edges
        
        // Create nodes
        for (int i = 0; i < nodeCount; i++) {
            graph.addNode("N" + i);
        }
        
        // Create edges
        for (int i = 0; i < edgeCount; i++) {
            String nodeA = "N" + rand.nextInt(nodeCount);
            String nodeB = "N" + rand.nextInt(nodeCount);
            
            if (!nodeA.equals(nodeB)) {
                int weight = 1 + rand.nextInt(20); // Weights 1-20
                String edgeId = String.format("%s-%s-%d", nodeA, nodeB, weight);
                
                if (findEdgeBetweenNodes(nodeA, nodeB) == null) {
                    try {
                        Edge edge = graph.addEdge(edgeId, nodeA, nodeB, true);
                        edge.setAttribute("weight", weight);
                        edge.setAttribute("ui.label", weight);
                    } catch (Exception e) {
                        // Skip if edge creation fails
                    }
                }
            }
        }
        
        updateNodeComboBox();
        statusLabel.setText(String.format("Generated random graph with %d nodes and %d edges", nodeCount, edgeCount));
        logMessage("Generated new random graph topology");
    }
    
    private void updateNodeComboBox() {
        Platform.runLater(() -> {
            sourceNodeCombo.getItems().clear();
            graph.nodes().forEach(node -> sourceNodeCombo.getItems().add(node.getId()));
            if (!sourceNodeCombo.getItems().isEmpty()) {
                sourceNodeCombo.getSelectionModel().select(0);
            }
        });
    }
    
    private void logMessage(String message) {
        Platform.runLater(() -> {
            algorithmLog.appendText(message + "\n");
            algorithmLog.setScrollTop(Double.MAX_VALUE);
        });
    }
}