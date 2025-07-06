import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import java.util.*;
import java.util.function.Consumer;

/**
 * Prim's Algorithm Executor with Progress Tracking
 * - Finds Minimum Spanning Tree (MST)
 * - Step-by-step visualization
 * - Thread-safe progress updates
 */
public class PrimAlgorithmExecutor {
    private final Graph graph;
    private final Consumer<String> logger;
    private final boolean stepByStep;
    private final int animationSpeed;
    private final Consumer<Double> progressUpdater;
    
    public PrimAlgorithmExecutor(Graph graph, Consumer<String> logger, 
                               boolean stepByStep, int animationSpeed,
                               Consumer<Double> progressUpdater) {
        this.graph = graph;
        this.logger = logger;
        this.stepByStep = stepByStep;
        this.animationSpeed = animationSpeed;
        this.progressUpdater = progressUpdater;
    }
    
    public void execute(String sourceNodeId) {
        resetGraphStyles();
        
        Node startNode = graph.getNode(sourceNodeId);
        if (startNode == null) {
            logger.accept("Error: Source node not found");
            return;
        }
        
        // Highlight source node
        startNode.setAttribute("ui.class", "source");
        sleep(500);
        
        Set<Node> visitedNodes = new HashSet<>();
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(this::compareEdges);
        
        visitedNodes.add(startNode);
        logger.accept("Starting with node: " + startNode.getId());
        
        // Add all edges from starting node
        startNode.edges().forEach(edge -> {
            edgeQueue.add(edge);
            logEdge("Added to queue:", edge);
            highlightEdge(edge, "considered");
        });
        
        int totalNodes = graph.getNodeCount();
        int processedNodes = 1; // Start node already processed
        
        while (!edgeQueue.isEmpty()) {
            Edge minEdge = edgeQueue.poll();
            Node nodeA = minEdge.getNode0();
            Node nodeB = minEdge.getNode1();
            
            logEdge("\nProcessing edge:", minEdge);
            highlightEdge(minEdge, "considered");
            
            Node nextNode = null;
            if (!visitedNodes.contains(nodeA)) {
                nextNode = nodeA;
            } else if (!visitedNodes.contains(nodeB)) {
                nextNode = nodeB;
            }
            
            if (nextNode != null) {
                // Add edge to MST
                minEdge.setAttribute("ui.class", "mst");
                visitedNodes.add(nextNode);
                processedNodes++;
                updateProgress((double) processedNodes / totalNodes);
                
                logEdge("Added to MST:", minEdge);
                logger.accept("Now visiting node: " + nextNode.getId());
                
                // Add all edges from the new node
                nextNode.edges().forEach(edge -> {
                    if (!edgeQueue.contains(edge)) {
                        edgeQueue.add(edge);
                        logEdge("Added to queue:", edge);
                        highlightEdge(edge, "considered");
                    }
                });
                
                sleep(1000 / animationSpeed);
            } else {
                logger.accept("Skipped - both nodes already in MST");
                minEdge.removeAttribute("ui.class");
            }
        }
        
        logger.accept("\n=== Algorithm Complete ===");
        logger.accept("Total nodes in MST: " + visitedNodes.size());
        logger.accept("Total edges in MST: " + (visitedNodes.size() - 1));
        updateProgress(1.0); // Mark as complete
    }
    
    private void resetGraphStyles() {
        graph.edges().forEach(edge -> edge.removeAttribute("ui.class"));
        graph.nodes().forEach(node -> node.removeAttribute("ui.class"));
    }
    
    private void highlightEdge(Edge edge, String style) {
        if (stepByStep) {
            edge.setAttribute("ui.class", style);
            sleep(500 / animationSpeed);
        }
    }
    
    private void logEdge(String message, Edge edge) {
        logger.accept(String.format("%s %s ↔ %s (weight: %d)", 
            message,
            edge.getNode0().getId(), 
            edge.getNode1().getId(), 
            getWeight(edge)));
    }
    
    private int compareEdges(Edge e1, Edge e2) {
        return Integer.compare(getWeight(e1), getWeight(e2));
    }
    
    private int getWeight(Edge edge) {
        Object weight = edge.getAttribute("weight");
        return weight instanceof Integer ? (int) weight : 0;
    }
    
    private void sleep(int millis) {
        if (stepByStep) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private void updateProgress(double progress) {
        if (progressUpdater != null) {
            progressUpdater.accept(progress);
        }
    }
}