import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DemoPanel extends JPanel {

    // Screen settings
    final int maxCol = 15;
    final int maxRow = 10;
    final int nodeSize = 70;
    final int panelWidth = nodeSize * maxCol;
    final int panelHeight = nodeSize * maxRow;

    // Node
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    // OTHERS
    boolean goalReached = false;
    int step = 0;

    public DemoPanel() {
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        // PLACE NODES
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);
            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }

        // SET START AND GOAL NODE
        setStartNode(3, 6);
        setGoalNode(11, 3);

        // PLACE SOLID NODES
        setSolidNode(10, 2);
        setSolidNode(10, 3);
        setSolidNode(10, 4);
        setSolidNode(10, 5);
        setSolidNode(10, 6);
        setSolidNode(10, 7);
        setSolidNode(6, 2);
        setSolidNode(7, 2);
        setSolidNode(8, 2);
        setSolidNode(9, 2);
        setSolidNode(11, 7);
        setSolidNode(12, 7);
        setSolidNode(6, 1);

        // SET COST ON NODES
        setCostOnNodes();
    }

    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

    private void setSolidNode(int col, int row) {
        node[col][row].setSolid();
    }

    private void setCostOnNodes() {
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow) {
            getCost(node[col][row]);
            col++;
            if (col == maxCol) {
                col = 0;
                row++;
            }
        }
    }

    private void getCost(Node node) {

        // Get G Cost (THE DISTANCE FROM START NODE)
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // Get H Cost (THE DISTANCE FROM GOAL NODE)
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // Get F Cost (G COST + H COST)
        node.fCost = node.gCost + node.hCost;

        // Display the cost
        if (node != startNode && node != goalNode) {
            node.setText("<html>F: " + node.fCost + "<br>G: " + node.gCost + "</html>");
        }
    }

    public void search(){
        if(goalReached == false && step < 300){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // OPEN THE UP NODE
            if(row -1 >= 0){openNode(node[col][row-1]);}
            // OPEN THE DOWN NODE
            if(row + 1 < maxRow){openNode(node[col][row+1]);}
            // OPEN THE LEFT NODE
            if(col - 1 >= 0){openNode(node[col-1][row]);}
            // OPEN THE RIGHT NODE
            if(col + 1 < maxCol){openNode(node[col+1][row]);}

            // Find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for(int i= 0; i < openList.size(); i++){
                //CHECK IF NODE'S F COST IS BETTER
                if(openList.get(i).fCost < bestNodeFCost){
                    bestNodeFCost = openList.get(i).fCost;
                    bestNodeIndex = i;
                }     
                //If F cost is equal, check the G cost 
                else if(openList.get(i).fCost == bestNodeFCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                } 
            } 
            // After the loop, we get the best node wich is our next step
            currentNode = openList.get(bestNodeIndex);
            
            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }

    public void autoSearch(){
        while(goalReached == false && step < 300){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // OPEN THE UP NODE
            if(row -1 >= 0){openNode(node[col][row-1]);}
            // OPEN THE DOWN NODE
            if(row + 1 < maxRow){openNode(node[col][row+1]);}
            // OPEN THE LEFT NODE
            if(col - 1 >= 0){openNode(node[col-1][row]);}
            // OPEN THE RIGHT NODE
            if(col + 1 < maxCol){openNode(node[col+1][row]);}

            // Find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for(int i= 0; i < openList.size(); i++){
                //CHECK IF NODE'S F COST IS BETTER
                if(openList.get(i).fCost < bestNodeFCost){
                    bestNodeFCost = openList.get(i).fCost;
                    bestNodeIndex = i;
                }     
                //If F cost is equal, check the G cost 
                else if(openList.get(i).fCost == bestNodeFCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                } 
            } 
            // After the loop, we get the best node wich is our next step
            currentNode = openList.get(bestNodeIndex);
            
            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }


    private void openNode(Node node) {
        if (node.open == false && node.checked == false && node.solid == false) {

            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private void trackThePath() {
        //Backtrack and draw the best path
        Node current = goalNode;
        while(current != startNode){
            current = current.parent;
            if(current != startNode){
                current.setAsPath();
            }
        }
    }
}
