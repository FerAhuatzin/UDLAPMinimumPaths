import java.util.Scanner;

public class Graph {

    private String[] nodes;
    private double[][] adjacencyMatrix;
    private int numberOfNodes;
    int filledNodes;

    public Graph (int n) {
        numberOfNodes = n;
        filledNodes = 0;
        nodes = new String[n];
        adjacencyMatrix =  new double[n][n];
        cleanMatrix();
    } //end constructor

    public void cleanMatrix() {
        for (int i = 0; i<numberOfNodes; i++) {
            for (int j = 0; j<numberOfNodes; j++) {
                if (i==j) {
                    adjacencyMatrix[i][j] = 0;
                } //end if
                else {
                    adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
                } //end else
            } //end for
        } //end for
    } //end cleanMatrix
    public void addNodeName (String name) {

        if (filledNodes!=(numberOfNodes)){
            nodes[filledNodes] = name;
            filledNodes++;
        } //end if
        else {
            System.out.println("You don't have more preassigned nodes.");
        } //end else

    } //end addNodeName

    public void addEdge(int initialNode, int connectedTo, double weight) {
        adjacencyMatrix[initialNode][connectedTo] = weight;
    } //end addEdge


    public void addEdgeByName(String initialNode, String connectedTo, double weight) {
        int x = 0;
        int y = 0;
        boolean found1 = false;
        boolean found2 = false;
        for (int i = 0; i<numberOfNodes && (!found1 || !found2);i++) {
            if (nodes[i].equals(initialNode)) {
                x = i;
                found1 = true;
            } //end if
            if (nodes[i].equals(connectedTo)) {
                y = i;
                found2 = true;
            } //end if
        } //end for
        if (x==y) {
            System.out.println("Unable to add edge from " + initialNode + " to " + connectedTo);
        } //end if
        else {
            adjacencyMatrix[x][y]=weight;
            adjacencyMatrix[y][x]=weight;
        } //end else

    } //end addEdgeByName

    public void deleteEdge(int initialNode, int disconnectedTo) {
        adjacencyMatrix[initialNode][disconnectedTo] = Double.POSITIVE_INFINITY;
    } //end deleteEdge

    int getNodeIndex (String node) {
        int index = 0;
        boolean found = false;
        for (int i = 0; i<numberOfNodes && !found; i++) {
            if (nodes[i].equals(node)) {
                index = i;
                found = true;
            } //end if
        } //end for
        return index;
    } //endGetNodeIndex

    public double[][] findMinimumPaths (String node) {

        //get the node we will calculate its minimum paths
        boolean found = false;
        int position = 0;
        int initialNode = 0;
        position = getNodeIndex(node);
        initialNode = position;

        //initialize the matrix we will work in
        double[][] dijkstraResult;
        dijkstraResult =  new double[numberOfNodes][3];
        for (int i = 0; i<numberOfNodes;i++) {
            dijkstraResult[i][0] = i;
            dijkstraResult[i][1] = Double.POSITIVE_INFINITY;
            dijkstraResult[i][2] = Double.NEGATIVE_INFINITY;
        } //end for
        dijkstraResult[position][1] = 0;

        //initialize array containing already visited nodes
        int[] visitedNodes;
        int positionOfVisitedNodes = 1;
        visitedNodes = new int[numberOfNodes];
        visitedNodes[0] = position;
        for (int x = 1; x<numberOfNodes;x++) {
            visitedNodes[x] = -1;
        } //end for

        //initialize array containing not visited nodes
        int[] notVisitedNodes;
        notVisitedNodes = new int[numberOfNodes];
        for (int x = 0; x<numberOfNodes;x++) {
            if (x==position) {
                notVisitedNodes[x] = -1;
            } //end if
            else {
                notVisitedNodes[x] = x;
            } //end else
        } //end for

        //DIJKSTRA'S ALGORITHM
        while (positionOfVisitedNodes!=numberOfNodes) {

            //establish the new distances comparing if the previous ones are greater
            for (int i = 0; i<numberOfNodes; i++) {
                //if the previous is greater update the shortest distance and the previous node
                if ((dijkstraResult[position][1] + adjacencyMatrix[position][i])<dijkstraResult[i][1]) {
                    dijkstraResult[i][1] = dijkstraResult[position][1] + adjacencyMatrix[position][i];
                    dijkstraResult[i][2] = position;
                } //end if
            } //end for

            //get the minimum path for the next node
            double minimum = Double.POSITIVE_INFINITY;
            int minimumPosition = numberOfNodes+1;
            for (int i = 0; i<numberOfNodes;i++) {
                if (notVisitedNodes[i]!=-1) {
                    if (dijkstraResult[notVisitedNodes[i]][1]<minimum) {
                        minimum = dijkstraResult[notVisitedNodes[i]][1];
                        minimumPosition = notVisitedNodes[i];
                    } //end if
                } //end if
            } //end for

            //update not visited and visited nodes arrays
            if (minimumPosition!=(numberOfNodes+1)) {
                notVisitedNodes[minimumPosition] = -1;
                visitedNodes[positionOfVisitedNodes] = minimumPosition;
                positionOfVisitedNodes++;
                //change position to start analyzing in the following iteration from that node
                position = minimumPosition;
            } //end if
            else {
                for (int i =0; i<numberOfNodes; i++) {
                    if (dijkstraResult[i][2]<0) {
                        dijkstraResult[i][2] = -1;
                    } //end if
                } //end for
                positionOfVisitedNodes=numberOfNodes;
            } //end else

        } //end while

        //TransformAndPrintDijkstrasResult(dijkstraResult,node, initialNode);
        return dijkstraResult;
    } //end findMinimumPaths

    private void TransformAndPrintDijkstrasResult (double[][] dijkstraResult, String node, int initialNode) {
        System.out.println("For node " + node + " the shortest paths are");
        System.out.printf("%-36S\t%-10S\t%-36S\n", "Node", "Weight", "Path");
        for (int i = 0; i<numberOfNodes; i++) {
            for (int j = 0; j<3;j++) {
                if (j==0) {
                    System.out.printf("%-36s\t",nodes[i]);
                } //end if
                if (j==1) {
                    System.out.printf("%-5f\t", dijkstraResult[i][j]);
                } //end if
                if (j==2 && i!=initialNode) {
                    if (dijkstraResult[i][2]==-1) {
                        System.out.printf("%-36s\t", "No path");
                    } //end if
                    else {
                        System.out.printf("%-36s\t", nodes[(int)dijkstraResult[i][j]]);
                    } //end else

                } //end if
            } //end for
            System.out.println(" ");
        } //end for
    }

    public void showMinimumPath (double[][] dijkstraResult, String origin, String destiny) {

        int originIndex = getNodeIndex(origin);
        int destiniesIndex = getNodeIndex(destiny);

        if (origin.equals(destiny)) {
            System.out.print("The minimum path for " + origin + " to " + destiny + " is: \n" + dijkstraResult[destiniesIndex][1] + "m (" + origin + ")");
        } //end if
        else {
            System.out.print("The minimum path for " + origin + " to " + destiny + " is: \n" + dijkstraResult[destiniesIndex][1] + "m (" + nodes[(int) dijkstraResult[destiniesIndex][0]]);

            while (dijkstraResult[destiniesIndex][2]!=originIndex) {
                System.out.print("<-"+nodes[(int) dijkstraResult[destiniesIndex][2]] );
                destiniesIndex = (int) dijkstraResult[destiniesIndex][2];
            } //end while

            System.out.println("<-"+origin + ")");
        } //end else

    } //end showMinimumPath
    public void showGraph() {
        System.out.printf("%36s"," ");
        for (int i = -1; i<numberOfNodes;i++) {
            for (int j = -1; j<numberOfNodes;j++) {
                if (i==-1) {
                    if (j>-1) {
                        System.out.printf("%-36s", nodes[j]);
                    } //end if
                } //end if
                else if (j==-1) {
                    System.out.printf("%-36s", nodes[i]);
                } //end if
                else {
                    System.out.printf("%-36f",adjacencyMatrix[i][j]);
                }
            } //end for
            System.out.println();
        } //end for
    } //end showGraph

    public boolean askMinimumPaths () {
        Scanner keyboard;
        keyboard = new Scanner(System.in);
        int option;
        int departureNode;
        int arrivalNode;

        System.out.println("\n");
        System.out.println("(1) How to get somewhere");
        System.out.println("(2) Finish");
        option = keyboard.nextInt();
        switch (option) {
            case 1 -> {
                departureNode = askDepartureNode();
                double[][] minimumPaths = findMinimumPaths(nodes[departureNode]);
                arrivalNode = askArrivalNode(departureNode, minimumPaths);
                showMinimumPath(minimumPaths, nodes[departureNode], nodes[arrivalNode]);
                askMinimumPaths();
                ;
            }
            case 2 -> {
                System.out.println("\n");
                return true;
            }
            default -> {
                System.out.println("Type a valid input...\n");
                askMinimumPaths();
            }
        }
        return true;
    } //end askMinimumPaths

    private int askDepartureNode () {
        Scanner keyboard;
        keyboard = new Scanner(System.in);
        boolean validInput = false;
        int departureNode = -1;
        while (!validInput) {
            System.out.println("\nWhere is your departing point?\n");
            for (int i = 0; i<numberOfNodes;i++) {
                System.out.printf("(%d) %s\n", i+1, nodes[i]);
            } //end for
            departureNode = keyboard.nextInt();
            if (departureNode<numberOfNodes+1 && departureNode>0) {
                departureNode = departureNode-1;
                validInput = true;
            } //end if
            else {
                System.out.println("Type a valid input...\n");
            } //end else
        } //end while
        return departureNode;
    } //end askDepartureNode

    private int askArrivalNode (int departureNode, double[][] minimumPaths) {
        Scanner keyboard;
        keyboard = new Scanner(System.in);
        boolean validInput = false;
        int arrivalNode = -1;
        while (!validInput) {
            System.out.println("\nWhere do you want to go?\n");
            for (int i = 0; i<numberOfNodes;i++) {
                if (i>departureNode) {
                    System.out.printf("(%d) %s\n", i, nodes[i]);
                } //end if
                else {
                    if (i<departureNode) {
                        System.out.printf("(%d) %s\n", i+1, nodes[i]);
                    } //end if
                } //end else
            } //end for
            arrivalNode = keyboard.nextInt();
            if (arrivalNode<numberOfNodes && arrivalNode>0) {
                if (arrivalNode<departureNode) {
                    arrivalNode=arrivalNode-1;
                }
                validInput = true;
            } //end if
            else {
                System.out.println("Type a valid input...\n");
            } //end else
        } //end while

        return arrivalNode;
    } //end askArrivalNode

} //end class

