
public class Assignment2 {
    public static void main(String[] args) {
        /*-----------------------
         *| Part A - tasks 1-11 |
         * ----------------------*/
    }

    // task 1
    public static boolean isSquareMatrix(boolean[][] matrix) {
        boolean isSquare = true;
        if (matrix == null || matrix.length == 0)
            isSquare = false;
        else
            //Loop for checking if the matrix is square (nXn)
            for (int i = 0; i < matrix.length & isSquare; i++) {
                if (matrix[i] == null || matrix.length != matrix[i].length)
                    isSquare = false;
            }
        return isSquare;
    }

    // task 2
    public static boolean isSymmetricMatrix(boolean[][] matrix) {
        //Assume matrix is square
        boolean isSymmetric = true;
        //Loop for checking if the matrix is symmetric. The check is only for the upper right side of the matrix
        for (int i = 0; i < matrix.length - 1 & isSymmetric; i++) {
            for (int j = i + 1; j < matrix.length & isSymmetric; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    isSymmetric = false;
                }
            }
        }
        return isSymmetric;
    }

    // task 3
    public static boolean isAntiReflexiveMatrix(boolean[][] matrix) {
        //Assume matrix is anti reflexive
        boolean isAntiReflexive = true;
        //Loop for checking if the matrix is anti reflexive
        for (int i = 0; i < matrix.length & isAntiReflexive; i++) {
            if (matrix[i][i])
                isAntiReflexive = false;
        }
        return isAntiReflexive;
    }

    // task 4
    public static boolean isLegalInstance(boolean[][] matrix) {
        //Assume is legal instance
        boolean isLegalInstance = true;
        //Condition for checking if the instance is legal
        if (!isSquareMatrix(matrix) || !isSymmetricMatrix(matrix) | !isAntiReflexiveMatrix(matrix))
            isLegalInstance = false;
        return isLegalInstance;
    }


    // task 5
    public static boolean isPermutation(int[] array) {
        //Assume matrix is permutation
        //Assume matrix is not null
        boolean isPermutation = true;
        //Loop for checking if array is permutation
        for (int i = 0; i < array.length & isPermutation; i++) {
            boolean numExist = false;
            //Loop for checking if all numbers between 0 to array.length-1 are exist in the array
            for (int j = 0; j < array.length & !numExist; j++) {
                if (array[j] == i)
                    numExist = true;
            }
            if (!numExist)
                isPermutation = false;
        }
        return isPermutation;
    }

    // task 6
    public static boolean hasLegalSteps(boolean[][] flights, int[] tour) {
        //Assume matrix is permutation
        boolean hasLegalSteps = true;

        if (!flights[tour[0]][tour[tour.length - 1]]) //condition for checking if there is a flight between n-1 to 0
            hasLegalSteps = false;

        //Loop  for checking if there is a flight between i to i+1
        for (int i = 0; i < tour.length - 1 & hasLegalSteps; i++) {
            if (!flights[tour[i]][tour[i + 1]])
                hasLegalSteps = false;
        }
        return hasLegalSteps;
    }

    // task 7
    public static boolean isSolution(boolean[][] flights, int[] tour) {
        boolean isSolution = true;
        // condition for checking that tour array length isn't different than n
        if (tour == null || tour.length != flights.length)
            throw new IllegalArgumentException("Tour array is null or tour array length is different than n");
        //condition for checking if array tour meet the conditions of definition 2
        if (tour[0] != 0 | !isPermutation(tour) | !hasLegalSteps(flights, tour))
            isSolution = false;

        return isSolution;
    }

    // task 8
    public static boolean satisfiesLiteral(int literal, boolean[] assign) {
        //check if every literal satisfies
        boolean satisfiesLiteral = (literal > 0 && assign[literal]) |
                (literal < 0 && !assign[-literal]);

        return satisfiesLiteral;
    }

    public static boolean satisfiesClause(int[] clause, boolean[] assign) {
        //check if every clause satisfies
        boolean satisfiesClause = false;
        for (int literalIdx = 0; literalIdx < clause.length & !satisfiesClause; literalIdx++) {
            satisfiesClause = satisfiesLiteral(clause[literalIdx], assign);
        }
        return satisfiesClause;
    }

    public static boolean evaluate(int[][] cnf, boolean[] assign) {
        //check if the cnf is satisfies
        boolean satisfiesCnf = true;
        for (int clauseNum = 0; clauseNum < cnf.length & satisfiesCnf; clauseNum++) {
            satisfiesCnf = satisfiesClause(cnf[clauseNum], assign);
        }

        return satisfiesCnf;

    }

    // task 9
    public static int[][] atLeastOne(int[] lits) {
        int[][] cnfAtLeastOne = new int[1][lits.length];
        //loop for check if at least one literal satisfies
        for (int i = 0; i < cnfAtLeastOne[0].length; i++) {
            cnfAtLeastOne[0][i] = lits[i];
        }
        return cnfAtLeastOne;
    }

    // task 10
    public static int[][] atMostOne(int[] lits) {
        int[][] cnfAtMostOne = new int[(lits.length * (lits.length - 1) / 2)][2]; // source - https://he.wikipedia.org/wiki/%D7%9E%D7%A1%D7%A4%D7%A8_%D7%9E%D7%A9%D7%95%D7%9C%D7%A9%D7%99
        int currentIdx = 0;
        //loop for running all over the lits array
        for (int i = 0; i < lits.length; i = i + 1) {
            //loop for giving the cnf array all the possible numbers of pairs in lits array *-1
            for (int j = i + 1; j < lits.length; j++) {
                int[] clause = {-lits[i], -lits[j]};
                cnfAtMostOne[currentIdx] = clause;
                currentIdx = currentIdx + 1;
            }
        }
        return cnfAtMostOne;
    }

    // task 11
    public static int[][] exactlyOne(int[] lits) {
        int[][] cnfExactlyOne = new int[atMostOne(lits).length + atLeastOne(lits).length][lits.length];
        //loop for running all over the cnfExactlyOne and putting cnfAtMostOne in cnfExactlyOne
        for (int i = 0; i < cnfExactlyOne.length - 1; i++) {
            cnfExactlyOne[i] = atMostOne(lits)[i];
        }
        cnfExactlyOne[cnfExactlyOne.length - 1] = atLeastOne(lits)[0]; //the last place in the array is same like the cnfAtLeastOne

        return cnfExactlyOne;
    }

    /*------------------------
     *| Part B - tasks 12-20 |
     * -----------------------*/

    // task 12a
    public static int map(int i, int j, int n) {
        //whether we visited at the step number i in the city j

        return n * i + j + 1;
    }

    // task 12b
    public static int[] reverseMap(int k, int n) {
        //what is our index when we visited at the step number i in the city j
        int[] pair = new int[2];
        int i = (k - 1) / n;
        int j = (k - 1) % n;
        pair[0] = i;
        pair[1] = j;

        return pair;
    }

    // task 13
    public static int[][] oneCityInEachStep(int n) {
        int[][] cnfOneCityInEachStep = new int[n * ((n * (n - 1)) / 2 + 1)][];
        int counter = 0;
        // running all over al possibilities of i and j
        for (int i = 0; i < n; i++) {
            int[] literals = new int[n];
            // the loop puts in array literal all combination from each step
            for (int j = 0; j < n; j++) {
                int k = map(i, j, n);
                literals[j] = k;
            }
            // the loop puts in the new CNF all the clauses from each step
            for (int j = 0; j < exactlyOne(literals).length; j++) {
                cnfOneCityInEachStep[counter] = exactlyOne(literals)[j];
                counter = counter + 1;
            }
        }
        return cnfOneCityInEachStep;

    }

    // task 14
    public static int[][] eachCityIsVisitedOnce(int n) {
        int[][] cnfVisitedOnce = new int[n * ((n * (n - 1)) / 2 + 1)][];
        int counter = 0;
        // running all over al possibilities of i and j
        for (int j = 0; j < n; j++) {
            int[] literals = new int[n];
            // the loop puts in array literal all combination from each step
            for (int i = 0; i < n; i++) {
                int k = map(i, j, n);
                literals[i] = k;
            }
            // the loop puts in the new CNF all the clauses from each step
            for (int l = 0; l < exactlyOne(literals).length; l++) {
                cnfVisitedOnce[counter] = exactlyOne(literals)[l];
                counter = counter + 1;
            }
        }
        return cnfVisitedOnce;
    }

    // task 15
    public static int[][] fixSourceCity(int n) {
        int[][] cnfFixSourceCity = {{1}};
        return cnfFixSourceCity;
    }

    // task 16
    public static int[][] noIllegalSteps(boolean[][] flights) {
        int count = 0;
        // loop for counting the cnfNoIllegalSteps length
        for (int j = 0; j < flights.length - 1; j++) {
            for (int k = j + 1; k < flights.length; k++) {
                if (!flights[j][k])
                    count = count + 1;
            }
        }

        int[][] cnfNoIllegalSteps = new int[count * 2 * flights.length][2];
        int counter = 0;
        //loops for running all the possibilities of city j and city k
        for (int j = 0; j < flights.length - 1; j++) {
            for (int k = j + 1; k < flights.length; k++) {
                //condition for checking only the cities that not have a flight between them
                if (!flights[j][k] | !flights[k][j]) {
                    //loop for insert to the CNF the cases for running all over possibles steps and vice versa
                    for (int i = 0; i < flights.length - 1; i++) {
                        int kMap1 = -map(i, j, flights.length);
                        int kMap2 = -map(i + 1, k, flights.length);
                        cnfNoIllegalSteps[counter] = new int[]{kMap1, kMap2};
                        counter = counter + 1;
                        kMap1 = -map(i, k, flights.length);
                        kMap2 = -map(i + 1, j, flights.length);
                        cnfNoIllegalSteps[counter] = new int[]{kMap1, kMap2};
                        counter = counter + 1;
                    }
                    //here we insert to the CNF the cases of the last step back to home and vice versa
                    int kMap1 = -map(flights.length - 1, j, flights.length);
                    int kMap2 = -map(0, k, flights.length);
                    cnfNoIllegalSteps[counter] = new int[]{kMap1, kMap2};
                    counter = counter + 1;
                    kMap1 = -map(0, j, flights.length);
                    kMap2 = -map(flights.length - 1, k, flights.length);
                    cnfNoIllegalSteps[counter] = new int[]{kMap1, kMap2};
                    counter = counter + 1;
                }
            }
        }
        return cnfNoIllegalSteps;
    }

    // task 17
    public static int[][] encode(boolean[][] flights) {
        int cnfEncodeLength = oneCityInEachStep(flights.length).length + eachCityIsVisitedOnce(flights.length).length
                + fixSourceCity(flights.length).length + noIllegalSteps(flights).length;
        int[][] cnfEncode = new int[cnfEncodeLength][];
        int counter = 0;
        //loop for running all over the cnfEncode and putting oneCityInEachStep in cnfEncode
        for (int i = 0; i < oneCityInEachStep(flights.length).length; i++) {
            cnfEncode[counter] = oneCityInEachStep(flights.length)[i];
            counter = counter + 1;
        }
        //loop for running all over the cnfEncode and putting eachCityIsVisitedOnce in cnfEncode
        for (int i = 0; i < eachCityIsVisitedOnce(flights.length).length; i++) {
            cnfEncode[counter] = eachCityIsVisitedOnce(flights.length)[i];
            counter = counter + 1;
        }
        //loop for running all over the cnfEncode and putting fixSourceCity in cnfEncode
        for (int i = 0; i < fixSourceCity(flights.length).length; i++) {
            cnfEncode[counter] = fixSourceCity(flights.length)[i];
            counter = counter + 1;
        }
        //loop for running all over the cnfEncode and putting noIllegalSteps in cnfEncode
        for (int i = 0; i < noIllegalSteps(flights).length; i++) {
            cnfEncode[counter] = noIllegalSteps(flights)[i];
            counter = counter + 1;
        }


        return cnfEncode;
    }

    // task 18
    public static int[] decode(boolean[] assignment, int n) {
        if (assignment == null || assignment.length != (n * n + 1))
            throw new IllegalArgumentException(" array assignment is null or array assignment length different then n*n+1");
        int[] tourDecode = new int[n];
        // the loop running all over i and j possibilities and create an array (tourDecode) according array assignment
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (assignment[map(i, j, n)])
                    tourDecode[i] = j;
            }
        }
        return tourDecode;
    }

    // task19
    public static int[] solve(boolean[][] flights) {
        // condition for check whether if flight array s legal according definition 1
        if (!isLegalInstance(flights))
            throw new IllegalArgumentException("Flights show isn't legal by definition 1 ");
        // from here we check if we have a solution for our big trip
        int nVars = flights.length * flights.length;
        SATSolver.init(nVars);
        SATSolver.addClauses(encode(flights));
        boolean[] assignment = SATSolver.getSolution();
        int[] tour = new int[flights.length];
        // condition for Time Out
        if (assignment == null)
            throw new IllegalArgumentException("TIME OUT");
            // condition for checking if we got "SAT"
        else if (assignment.length == nVars + 1) {
            tour = decode(assignment, flights.length);
            // condition for checking if the solution is legal
            if (!isSolution(flights, tour))
                throw new IllegalArgumentException("The solution isn't legal");
        }
        // else- mean that we got an empty array from SAT SOLVER
        else
            tour = null;

        return tour;
    }

    // This function is an auxiliary function just like function 19 except that if the solution isn't legal so it will return null and not throw exception
    public static int[] solve20(boolean[][] flights) {
        // condition for check whether if flight array s legal according definition 1
        if (!isLegalInstance(flights))
            throw new IllegalArgumentException("Flights show isn't legal by definition 1 ");
        // from here we check if we have a solution for our big trip
        int nVars = flights.length * flights.length;
        SATSolver.init(nVars);
        SATSolver.addClauses(encode(flights));
        boolean[] assignment = SATSolver.getSolution();
        int[] tour = new int[flights.length];
        // condition for Time Out
        if (assignment == null)
            throw new IllegalArgumentException("TIME OUT");
            // condition for checking if we got "SAT"
        else if (assignment.length == nVars + 1) {
            tour = decode(assignment, flights.length);
            // condition for checking if the solution is legal
            if (!isSolution(flights, tour))
                tour = null;
        }
        // else- mean that we got an empty array from SAT SOLVER
        else
            tour = null;

        return tour;
    }

    // task20
    public static boolean solve2(boolean[][] flights) {
        // condition for check whether if flight array s legal according definition 1
        if (!isLegalInstance(flights))
            throw new IllegalArgumentException("flights show isn't legal by definition 1");
        boolean has2Solution = true;
        int[] tour1 = solve20(flights);
        // condition for checking if our first solution (if there is) is null
        if (tour1 == null)
            has2Solution = false;
        else {
            int[] reverseTour1 = new int[flights.length];
            int[] mapClause = new int[flights.length];
            int[] reversMapClause = new int[flights.length];
            // loop for getting reverseTour1 array
            int first = 1;
            for (int last = tour1.length - 1; last > 0; last--) {
                reverseTour1[last] = tour1[first];
                first++;
            }
            // loop for getting mapClause array and reversMapClause array
            for (int i = 0; i < flights.length; i++) {
                mapClause[i] = -map(i, tour1[i], flights.length);
                reversMapClause[i] = -map(i, reverseTour1[i], flights.length);
            }
            // from here we check if we have at least two solution
            int nVars = flights.length * flights.length;
            SATSolver.init(nVars);
            SATSolver.addClauses(encode(flights));
            SATSolver.addClause(mapClause);
            SATSolver.addClause(reversMapClause);
            boolean[] assignment = SATSolver.getSolution();
            int[] tour2 = new int[flights.length];
            // condition for Time Out
            if (assignment == null)
                throw new IllegalArgumentException("TIME OUT");
                // condition for checking if we got "SAT"
            else if (assignment.length == nVars + 1) {
                tour2 = decode(assignment, flights.length);
                // condition for checking if the solution is legal
                if (!isSolution(flights, tour2))
                    has2    Solution = false;
            }
            // else- mean that we got an empty array from SAT SOLVER
            else
                has2Solution = false;
        }

        return has2Solution;
    }

}