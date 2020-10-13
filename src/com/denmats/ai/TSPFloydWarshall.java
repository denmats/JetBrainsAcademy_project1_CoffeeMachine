package com.denmats.ai;

import java.util.*;

public class TSPFloydWarshall {

    protected static double[][] parentArr;
    protected static List<PointTSP> parentList = new ArrayList<>();


    public static void main(String[] args) {
       algorithmStart();
    }

    public static void algorithmStart() {
        TSPFloydWarshall algorithm = new TSPFloydWarshall();
        //read data from user. get nodes coordinates.
        algorithm.userInput();
        System.out.println();
        //create matrix filled with distance between pair nodes
        parentArr = Arrays.copyOf(fillInParentMatrix(parentList), fillInParentMatrix(parentList).length);
        printOutMatrix(parentArr);
        //read data from user regarding starting node
        char start = getStartPoint(parentArr);
        //calculate new matrix which represents the shortest distance between pair nodes
        double[][] resultArr = calculateShortestPathBetweenAllPoints(parentArr);
        printOutMatrix(resultArr);
        System.out.println();
        double dist = distanceFromLastVisitedNodeToStart(start, resultArr);
        System.out.println("The whole path is "+dist);
    }

    //Reading user input. Saving initial points in list
    public void userInput() {
        int counter = 65;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Searching for the shortest path  - Floyd Warshall algorithm implementation");
            System.out.println("Do you want to add a new point? yes - any key, no - n");
            if (scanner.nextLine().equalsIgnoreCase("n")) break;
            if (counter > 75) {
                System.out.println("Sorry, the maxim amount of items covered by the algorithm is 10");
                break;
            }
            parentList.add(getListOfInitData(counter));
            counter++;
        }
        parentList.forEach(System.out::println);
    }

    //Returning one point given by user
    public PointTSP getListOfInitData(int counter) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the coordinates X for point " + (char) counter + ": ");
        double x = scanner.hasNextDouble() ? scanner.nextDouble() : 0.00;
        System.out.println("Enter the coordinates Y for point " + (char) counter + ": ");
        double y = scanner.hasNextDouble() ? scanner.nextDouble() : 0.00;

        return new PointTSP((char) counter, x, y);
    }

    //Calculating distance between given point and filling out the initial matrix with distances calculated
    public static double[][] fillInParentMatrix(List<PointTSP> list) {
        int numOfEl = list.size();
        double[][] arr = new double[numOfEl][numOfEl];

        double scale = Math.pow(10, 2);

        for (int i = 0; i < numOfEl; i++) {
            for (int j = 0; j < numOfEl; j++) {
                double x = list.get(j).getX() - list.get(i).getX();
                double y = list.get(j).getY() - list.get(i).getY();
                arr[i][j] = Math.round(Math.sqrt(x * x + y * y) * scale) / scale;
            }
        }
        return arr;
    }

    //Printing out matrix
    public static void printOutMatrix(double[][] arr) {
        int len = arr.length;
        for (double[] doubles : arr) {
            for (int j = 0; j < len; j++) {
                System.out.print(String.format("%.2f  ",doubles[j]));
            }
            System.out.println();
        }
    }

    //Ask user to set a point where the route is going to start from
    public static char getStartPoint(double[][] parentArr) {
        Scanner scanner = new Scanner(System.in);
        int counter = parentArr.length;
        System.out.println(counter);

        System.out.print("Where do you want to start from? Choose a letter from the list: ");
        for (PointTSP e : parentList) {
            System.out.print(e.getCounter() + " ");
        }
        System.out.println();

        char start = 0;
        boolean flag = true;
        while (flag) {
            start = scanner.nextLine().charAt(0);

            if (start >= 65 && start < 76) {
                System.out.println("The route will start from " + start);
                flag = false;
            } else {
                System.out.println("There is no such a letter in the list! Try again!");
            }
        }
        return start;
    }

    //calculating the shortest path connected all points visited only once
    public static double[][] calculateShortestPathBetweenAllPoints(double[][] arr) {
        int numOfVertex = arr.length;
        double[][] childArr = new double[numOfVertex][numOfVertex];

        for (int i = 0; i < numOfVertex; i++) {
            for (int j = 0; j < numOfVertex; j++) {
                childArr[i][j] = arr[i][j];
            }
        }

        for (int k = 0; k < numOfVertex; k++) {
            for (int i = 0; i < numOfVertex; i++) {
                for (int j = 0; j < numOfVertex; j++) {
                    if (childArr[i][j] > childArr[i][k] + childArr[k][j]) {
                        childArr[i][j] = childArr[i][k] + childArr[k][j];
                    }
                }
            }
        }

        return childArr;
    }


    //calculating the path from the last visited point to start point
    public static double distanceFromLastVisitedNodeToStart(char start, double[][] arr) {
        int numOfVertex = arr.length;
        String theWholePathLiterally = "" + start;
        double dist = 0;

        //the set will be filled in with all nodes visited
        Set<Integer> set = new HashSet<>();
        int currPoint = start - 65;
        set.add(currPoint);
        int index = 0;

        //calculating the minimum value in every row. Zero value and value from the set are excluded from the calculating
        while (true) {
            double minimum = Integer.MAX_VALUE;
            for (int i = 0; i < numOfVertex; i++) {
                if (minimum > arr[currPoint][i] && arr[currPoint][i] != 0 && !set.contains(i)) {
                    minimum = arr[currPoint][i];
                    index = i;
                }
            }
            dist += arr[currPoint][index];
            set.add(index);
            currPoint = index;
            theWholePathLiterally = theWholePathLiterally + "->" + (char) (currPoint + 65);

            //once all nodes are in the set, the whole path will be calculated
            //the last distance from the endpoint to the start point will be added
            if (set.size() == numOfVertex) {
                System.out.println(theWholePathLiterally);
                dist += arr[currPoint][start-65];
                break;
            }
        }

        return dist;
    }
}




