public class Run {

    /**
     * Main methods of all the algorithm classes packed in a single class so it can be easily called from the GUI
     */

    private Truck TRUCK = new Truck(33, 8, 5);
    private int amountOfA;
    private int amountOfB;
    private int amountOfC;

    private static final int[][][] TRUCKSPACE = new int[5][8][33];
    private static int[][][][] piece =
            {{{{1,1},{1,1},{1,0}}}, //P
                    {{{1,0},{1,0},{1,1}}},
                    {{{1,1},{1,1},{0,1}}},
                    {{{0,1},{1,1},{1,1}}},

                    {{{1,1,1},{1,1,0}}},
                    {{{1,1,1},{0,1,1}}},
                    {{{0,1,1},{1,1,1}}},
                    {{{1,1,0},{1,1,1}}},

                    {{{1},{1}},{{1},{1}},{{1},{0}}},
                    {{{1},{1}},{{1},{1}},{{0},{1}}},
                    {{{1},{0}},{{1},{1}},{{1},{1}}},
                    {{{0},{1}},{{1},{1}},{{1},{1}}},

                    {{{1},{1},{1}},{{0},{1},{1}}},
                    {{{1},{1},{1}},{{1},{1},{0}}},
                    {{{0},{1},{1}},{{1},{1},{1}}},
                    {{{1},{1},{0}},{{1},{1},{1}}},

                    {{{1,1,1}},{{1,1,0}}},
                    {{{1,1,1}},{{0,1,1}}},
                    {{{0,1,1}},{{1,1,1}}},
                    {{{1,1,0}},{{1,1,1}}},

                    {{{1,1}},{{1,1}},{{1,0}}},
                    {{{1,1}},{{1,1}},{{0,1}}},
                    {{{0,1}},{{1,1}},{{1,1}}},
                    {{{1,0}},{{1,1}},{{1,1}}},

                    {{{0,2},{0,2},{0,2},{2,2}}}, //L
                    {{{2,0},{2,0},{2,0},{2,2}}},
                    {{{2,2},{0,2},{0,2},{0,2}}},
                    {{{2,2},{2,0},{2,0},{2,0}}},

                    {{{2,2,2,2},{0,0,0,2}}},
                    {{{2,2,2,2},{2,0,0,0}}},
                    {{{0,0,0,2},{2,2,2,2}}},
                    {{{2,0,0,0},{2,2,2,2}}},

                    {{{2},{2}},{{2},{0}},{{2},{0}},{{2},{0}}},
                    {{{2},{2}},{{0},{2}},{{0},{2}},{{0},{2}}},
                    {{{2},{0}},{{2},{0}},{{2},{0}},{{2},{2}}},
                    {{{0},{2}},{{0},{2}},{{0},{2}},{{2},{2}}},

                    {{{2},{2},{2},{2}},{{2},{0},{0},{0}}},
                    {{{2},{2},{2},{2}},{{0},{0},{0},{2}}},
                    {{{0},{0},{0},{2}},{{2},{2},{2},{2}}},
                    {{{2},{0},{0},{0}},{{2},{2},{2},{2}}},

                    {{{2,2,2,2}},{{2,0,0,0}}},
                    {{{2,2,2,2}},{{0,0,0,2}}},
                    {{{2,0,0,0}},{{2,2,2,2}}},
                    {{{0,0,0,2}},{{2,2,2,2}}},

                    {{{2,2}},{{2,0}},{{2,0}},{{2,0}}},
                    {{{2,2}},{{0,2}},{{0,2}},{{0,2}}},
                    {{{2,0}},{{2,0}},{{2,0}},{{2,2}}},
                    {{{0,2}},{{0,2}},{{0,2}},{{2,2}}},


                    {{{3,3,3},{0,3,0},{0,3,0}}}, //T
                    {{{0,3,0},{0,3,0},{3,3,3}}},
                    {{{3,0,0},{3,3,3},{3,0,0}}},
                    {{{0,0,3},{3,3,3},{0,0,3}}},

                    {{{3},{3},{3}},{{0},{3},{0}},{{0},{3},{0}}},
                    {{{0},{3},{0}},{{0},{3},{0}},{{3},{3},{3}}},
                    {{{3},{0},{0}},{{3},{3},{3}},{{3},{0},{0}}},
                    {{{0},{0},{3}},{{3},{3},{3}},{{0},{0},{3}}},

                    {{{3,3,3}},{{0,3,0}},{{0,3,0}}},
                    {{{0,3,0}},{{0,3,0}},{{3,3,3}}},
                    {{{3,0,0}},{{3,3,3}},{{3,0,0}}},
                    {{{0,0,3}},{{3,3,3}},{{0,0,3}}}
            };

    public void setBoxAmounts(int amountOfA, int amountOfB, int amountOfC) {
        this.amountOfA = amountOfA;
        this.amountOfB = amountOfB;
        this.amountOfC = amountOfC;
    }

    public void greedyB() {
        //Truck TRUCK = new Truck(33, 5, 8);
        //Truck TRUCK = new Truck(8, 33, 5);
        //Truck TRUCK = new Truck(8, 5, 33);
        //Truck TRUCK = new Truck(5, 8, 33);
        //Truck TRUCK = new Truck(5, 33, 8);

        Boxes[] boxes = {new Boxes("A", 3, amountOfA), new Boxes("B", 4, amountOfB), new Boxes("C", 5, amountOfC)};
        GreedyB algorithm = new GreedyB(boxes, TRUCK);
        System.out.println();
        for(Boxes b: boxes)
        {
            System.out.println("Density of box " + b.type +": " + b.density + "\n" + b.amountOfBoxes);
        }
        System.out.println();
        algorithm.solve();

        for(int x = 0; x< TRUCK.space.length; x++)
        {
            if(x % 2==0)
            {
                System.out.println((x/2) + " m");
                System.out.println();
            }
            for(int y = 0; y < TRUCK.space[0].length; y++)
            {
                for(int z = 0; z < TRUCK.space[0][0].length; z++)
                {
                    System.out.print(TRUCK.space[x][y][z] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        //System.out.println(Arrays.deepToString(TRUCK.space));
        System.out.println("The total value of boxes in the TRUCK is " + TRUCK.totalValue);
    }

    public void greedyP() {
        GreedyP algorithm = new GreedyP(piece);
        algorithm.setAmountOfL(amountOfB);
        algorithm.setAmountOfP(amountOfC);
        algorithm.setAmountOfT(amountOfA);
        algorithm.solve();

        for(int x = 0; x < TRUCKSPACE.length; x++)
        {
            if(x % 2==0)
            {
                System.out.println((x/2) + " m");
                System.out.println();
            }
            for(int y = 0; y < TRUCKSPACE[0].length; y++)
            {
                for(int z = 0; z < TRUCKSPACE[0][0].length; z++)
                    System.out.print(TRUCKSPACE[x][y][z] + " ");
                System.out.println();
            }
            System.out.println();
        }
        //System.out.println(Arrays.deepToString(TRUCKSPACE));	//TRUCK.space
        System.out.println("The total value of boxes in the TRUCK is " + GreedyP.getTotalValue());
        System.out.println("T: " + GreedyP.getCounterOfT() + ", L: " + GreedyP.getCounterOfL() + ", P: " + GreedyP.getCounterOfP());
    }

    public void randomB() {
       // shuffles the array
        Boxes[] boxes = {new Boxes("A", 3, amountOfA), new Boxes("B", 4, amountOfB), new Boxes("C", 5, amountOfC)};
        for (int i = 0; i < boxes.length; i++)
        {
            int s = (int)(Math.random()* (boxes.length -i));

            Boxes temp = boxes[s];
            boxes[s] = boxes[i];
            boxes[i] = temp;
        }

        RandomB algorithm = new RandomB(boxes, TRUCK);

        System.out.println();
        for(Boxes b: boxes)
        {
            System.out.println("Density of box " + b.type +": " + b.density);
        }
        System.out.println();
        algorithm.solve();

        for(int x = 0; x < TRUCK.space.length; x++)
        {
            if(x % 2==0)
            {
                System.out.println((x/2) + " m");
                System.out.println();
            }
            for(int y = 0; y < TRUCK.space[0].length; y++)
            {
                for(int z = 0; z < TRUCK.space[0][0].length; z++)
                {
                    System.out.print(TRUCK.space[x][y][z] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        //System.out.println(Arrays.deepToString(TRUCK.space));
        System.out.println("The total value of boxes in the TRUCK is " + TRUCK.totalValue);
        System.out.println(boxes.length);
    }

    public void randomP() {
        Boxes[] boxes = {new Boxes("A", 3, amountOfA), new Boxes("B", 4, amountOfB), new Boxes("C", 5, amountOfC)};
        for (int i = 0; i < piece.length; i++)
        {
            int s = (int)(Math.random()* (piece.length -i));

            int[][][] temp = piece[s];
            piece[s] = piece[i];
            piece[i] = temp;
        }

        RandomP algorithm = new RandomP(piece);

        algorithm.solve();

        for(int x = 0; x < TRUCKSPACE.length; x++)
        {
            if(x % 2==0)
            {
                System.out.println((x/2) + " m");
                System.out.println();
            }
            for(int y = 0; y < TRUCKSPACE[0].length; y++)
            {
                for(int z = 0; z < TRUCKSPACE[0][0].length; z++)
                {
                    System.out.print(TRUCKSPACE[x][y][z] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        //System.out.println(Arrays.deepToString(truckspace));	//truck.space
        System.out.println("The total value of pentominoes in the truck is " + TRUCK.totalValue);
    }
}
