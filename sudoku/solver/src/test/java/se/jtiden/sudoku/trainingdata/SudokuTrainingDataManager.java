package se.jtiden.sudoku.trainingdata;

import java.util.ArrayList;
import java.util.List;

import se.jtiden.sudoku.struct.CollectionDecorator;
import se.jtiden.sudoku.struct.CollectionDecoratorImpl;

public class SudokuTrainingDataManager {

    private final static int x = 0;

    public CollectionDecorator<SudokuTrainingData> getAll() {
        List<se.jtiden.sudoku.trainingdata.SudokuTrainingData> trainingData = new ArrayList<>();

        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataStringImpl("", 3, se.jtiden.sudoku.trainingdata.Difficulty.MODERATE, new String[]{
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

        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataStringImpl("Needs Locked Candidates", 3, se.jtiden.sudoku.trainingdata.Difficulty.INTRICATE, new String[]{
                "x7xx489xx",
                "x6x3xxx8x",
                "xxxxx9xx4",
                "xxxxx1x2x",
                "74xxxxx51",
                "x3x4xxxxx",
                "9xx2xxxxx",
                "x2xxx7x3x",
                "xx856xx7x",
        }, new String[]{
                "273648915",
                "469315782",
                "185729364",
                "596871423",
                "742936851",
                "831452697",
                "957283146",
                "624197538",
                "318564279",
        }));



        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataIntImpl("", se.jtiden.sudoku.trainingdata.Difficulty.EASIEST, new int[][]{
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

        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataIntImpl("", se.jtiden.sudoku.trainingdata.Difficulty.EASY_AS_PIE, new int[][]{
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

        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataIntImpl("", se.jtiden.sudoku.trainingdata.Difficulty.EASY_AS_PIE, new int[][]{
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

        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataIntImpl("", se.jtiden.sudoku.trainingdata.Difficulty.SIMPLE, new int[][]{
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


        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataIntImpl("", se.jtiden.sudoku.trainingdata.Difficulty.EASY, new int[][]{
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

        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataIntImpl("", se.jtiden.sudoku.trainingdata.Difficulty.MODERATE, new int[][]{
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


        trainingData.add(new Ignore(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataIntImpl("Difficult 2", se.jtiden.sudoku.trainingdata.Difficulty.DIFFICULT, new int[][]{
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
                null)));

        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataStringImpl("", 2, se.jtiden.sudoku.trainingdata.Difficulty.PROTOTYPES, new String[]{
                "1234",
                "4312",
                "21x3",
                "3421",
        }, new String[]{
                "1234",
                "4312",
                "2143",
                "3421"
        }));


        trainingData.add(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataStringImpl("", 3, se.jtiden.sudoku.trainingdata.Difficulty.EASY, new String[]{
                "x7xxxxxx1",
                "4xx9xx8xx",
                "x2xxxx95x",
                "x451x7xx9",
                "xxx6x9xxx",
                "9xx3x826x",
                "x64xxxx2x",
                "xx8xx3xx4",
                "7xxxxxx1x",
        }, new String[]{
                "579862431",
                "436915872",
                "821734956",
                "645127389",
                "283659147",
                "917348265",
                "364591728",
                "158273694",
                "792486513",
        }));

        trainingData.add(new Ignore(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataStringImpl("Need naked pair and unique rectangle", 3, se.jtiden.sudoku.trainingdata.Difficulty.DIFFICULT, new String[]{
                "xx9x347xx",
                "3xxx76x2x",
                "xxx1xxxx6",
                "xx76xxxx5",
                "x8xxxxx1x",
                "1xxxx59xx",
                "6xxxx8xxx",
                "x1x32xxx8",
                "xx356x1xx",
        }, null)));

        trainingData.add(new Ignore(new se.jtiden.sudoku.trainingdata.SudokuTrainingDataStringImpl("Need naked pair", 3, se.jtiden.sudoku.trainingdata.Difficulty.INTRICATE, new String[]{
                "76xxx24x9",
                "2x14x3xxx",
                "xx4x7xxxx",
                "xxx3xxxx4",
                "xx9xxx8xx",
                "6xxxx8xxx",
                "xxxx3x7xx",
                "xxx1x63x2",
                "1x29xxx85",
        }, new String[]{
                "763852419",
                "281493567",
                "954671238",
                "528369174",
                "319724856",
                "647518923",
                "896235741",
                "475186392",
                "132847685",
        })));

        return new CollectionDecoratorImpl<>(trainingData);
    }
}
