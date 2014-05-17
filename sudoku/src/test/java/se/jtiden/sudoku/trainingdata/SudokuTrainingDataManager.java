package test.java.se.jtiden.sudoku.trainingdata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SudokuTrainingDataManager {

    private final static int x = 0;

    public List<SudokuTrainingData> getAll() {
        List<SudokuTrainingData> trainingData = new ArrayList<>();

        trainingData.add(new SudokuTrainingDataStringImpl("", 3, Difficulty.MODERATE, new String[]{
                "xxxxxxx2x",
                "x63xxx8xx",
                "9xxxx6x57",
                "12x35xx68",
                "xx9xxx5xx",
                "63xx48x19",
                "27x5xxxx4",
                "xx4xxx18x",
                "x9xxxxxxx",
        }, new String[]{
                "718435926",
                "563972841",
                "942816357",
                "127359468",
                "489261573",
                "635748219",
                "271583694",
                "354697182",
                "896124735",
        }));

        trainingData.add(new SudokuTrainingDataIntImpl("Needs Locked Candidates", 3, Difficulty.INTRICATE, new int[][]{
                {x,7,x,x,4,8,9,x,x},
                {x,6,x,3,x,x,x,8,x},
                {x,x,x,x,x,9,x,x,4},
                {x,x,x,x,x,1,x,2,x},
                {7,4,x,x,x,x,x,5,1},
                {x,3,x,4,x,x,x,x,x},
                {9,x,x,2,x,x,x,x,x},
                {x,2,x,x,x,7,x,3,x},
                {x,x,8,5,6,x,x,7,x},
        }, new int[][]{
                {2,7,3,6,4,8,9,1,5},
                {4,6,9,3,1,5,7,8,2},
                {1,8,5,7,2,9,3,6,4},
                {5,9,6,8,7,1,4,2,3},
                {7,4,2,9,3,6,8,5,1},
                {8,3,1,4,5,2,6,9,7},
                {9,5,7,2,8,3,1,4,6},
                {6,2,4,1,9,7,5,3,8},
                {3,1,8,5,6,4,2,7,9},
        }));



        trainingData.add(new SudokuTrainingDataIntImpl("", 3, Difficulty.EASIEST, new int[][]{
                {1,3,7 , x,5,9 , 4,x,2},
                {8,6,4 , x,3,2 , 9,5,x},
                {5,9,2 , 8,4,7 , 3,x,6},

                {3,8,x , 5,x,4 , 6,x,9},
                {6,x,x , 3,x,1 , 8,4,5},
                {7,x,5 , 9,x,x , 2,3,1},

                {9,7,8 , x,1,3 , 5,6,4},
                {4,x,6 , 7,x,x , x,x,3},
                {2,1,3 , 4,6,5 , 7,9,8}
        }, new int[][]{
                {1,3,7 , 6,5,9 , 4,8,2},
                {8,6,4 , 1,3,2 , 9,5,7},
                {5,9,2 , 8,4,7 , 3,1,6},

                {3,8,1 , 5,2,4 , 6,7,9},
                {6,2,9 , 3,7,1 , 8,4,5},
                {7,4,5 , 9,8,6 , 2,3,1},

                {9,7,8 , 2,1,3 , 5,6,4},
                {4,5,6 , 7,9,8 , 1,2,3},
                {2,1,3 , 4,6,5 , 7,9,8}
        }));

        trainingData.add(new SudokuTrainingDataIntImpl("", 3, Difficulty.EASY_AS_PIE, new int[][]{
                {x,2,5 , x,7,6 , 8,1,x},
                {8,7,3 , x,x,5 , 6,2,9},
                {6,x,1 , x,x,2 , 5,4,7},

                {1,8,2 , 7,x,x , x,x,5},
                {x,x,x , x,5,9 , x,x,1},
                {9,x,x , 8,1,3 , 2,7,x},

                {2,x,9 , 4,3,x , 7,5,8},
                {x,4,x , 5,9,x , 1,x,x},
                {5,1,7 , 6,x,8 , 9,3,x},
        }, new int[][]{
                {4,2,5 , 9,7,6 , 8,1,3},
                {8,7,3 , 1,4,5 , 6,2,9},
                {6,9,1 , 3,8,2 , 5,4,7},

                {1,8,2 , 7,6,4 , 3,9,5},
                {7,3,6 , 2,5,9 , 4,8,1},
                {9,5,4 , 8,1,3 , 2,7,6},

                {2,6,9 , 4,3,1 , 7,5,8},
                {3,4,8 , 5,9,7 , 1,6,2},
                {5,1,7 , 6,2,8 , 9,3,4},
        }));

        trainingData.add(new SudokuTrainingDataIntImpl("", 3, Difficulty.EASY_AS_PIE, new int[][]{
                {x,x,3,2,5,9,x,x,4},
                {x,1,x,6,7,x,x,x,8},
                {2,7,x,x,1,4,x,3,9},

                {9,3,x,x,x,5,7,8,2},
                {x,2,x,9,8,x,x,x,x},
                {x,x,8,3,x,7,x,x,6},

                {x,x,x,7,4,8,x,5,3},
                {x,x,4,x,9,x,8,6,7},
                {x,x,2,5,x,x,x,9,x},
        },
                null));

        trainingData.add(new SudokuTrainingDataIntImpl("", 3, Difficulty.SIMPLE, new int[][]{
                {x,x,x,x,x,x,1,8,x},
                {x,x,x,1,x,3,x,4,7},
                {x,3,9,8,x,x,x,2,5},

                {x,x,6,x,8,7,2,x,x},
                {x,7,x,x,x,x,x,9,x},
                {x,x,2,4,9,x,7,x,x},

                {7,8,x,x,x,1,4,6,x},
                {3,6,x,5,x,2,x,x,x},
                {x,2,4,x,x,x,x,x,x},
        },
                null));


        trainingData.add(new SudokuTrainingDataIntImpl("", 3, Difficulty.EASY, new int[][]{
                {3,x,x,x,9,2,6,x,x},
                {9,x,x,5,x,x,x,x,x},
                {1,5,x,x,x,x,x,4,x},

                {x,x,x,x,2,x,8,9,x},
                {x,x,9,x,x,x,2,x,x},
                {x,1,8,x,3,x,x,x,x},

                {x,3,x,x,x,x,x,5,8},
                {x,x,x,x,x,8,x,x,2},
                {x,x,4,3,7,x,x,x,9},
        },
                null));

        trainingData.add(new SudokuTrainingDataIntImpl("", 3, Difficulty.MODERATE, new int[][]{
                {x,x,x,6,3,2,5,x,7},
                {x,7,x,x,5,x,x,6,x},
                {x,x,x,x,1,x,x,9,x},

                {3,5,x,x,x,x,x,x,x},
                {1,x,x,x,x,x,x,x,9},
                {x,x,x,x,x,x,x,5,1},

                {x,6,x,x,8,x,x,x,x},
                {x,1,x,x,6,x,x,4,x},
                {4,x,8,3,7,1,x,x,x},
        },
                null));


        trainingData.add(new SudokuTrainingDataIntImpl("", 3, Difficulty.DIFFICULT, new int[][]{
                {x,x,x,5,x,1,x,2,6},
                {x,x,x,x,2,x,x,x,x},
                {x,5,x,8,x,x,7,9,x},

                {x,x,1,x,6,x,x,3,7},
                {x,6,x,x,x,x,x,5,x},
                {2,7,x,x,1,x,6,x,x},

                {x,2,5,x,x,9,x,7,x},
                {x,x,x,x,7,x,x,x,x},
                {7,4,x,3,x,8,x,x,x},
        },
                null));

        trainingData.add(new SudokuTrainingDataIntImpl("", 2, Difficulty.PROTOTYPES, new int[][]{
                {1,2,3,4},
                {4,3,1,2},
                {2,1,0,3},
                {3,4,2,1}
        }, new int[][]{
                {1,2,3,4},
                {4,3,1,2},
                {2,1,4,3},
                {3,4,2,1}
        }));

        return trainingData;
    }

    public Stream<SudokuTrainingData> getAllOfDifficulty(Difficulty difficulty) {
        return getAll().stream().filter(
                d -> d.getDifficulty() == difficulty);
    }

}
