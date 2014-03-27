package se.jtiden.ml.core.impl.nearestneighbor;

import se.jtiden.ml.core.api.AlgorithmStepPainter;
import se.jtiden.ml.core.api.Point;
import se.jtiden.ml.core.api.PointWithColor;
import se.jtiden.ml.core.impl.AbstractAlgorithmPainter;

import java.awt.*;
import java.util.*;
import java.util.List;


public class MonaLisaNearestNeighborHypothesisPainter extends AbstractAlgorithmPainter {

    private final MonaLisaNearestNeighborHypothesis hypothesis;
    private final int alpha;
    private final Map<Point, Color> colorCache = new TreeMap<Point, Color>();
    private int fakePixelSize;

    public MonaLisaNearestNeighborHypothesisPainter(MonaLisaNearestNeighborHypothesis hypothesis,
                                                    AlgorithmStepPainter innerPainter,
                                                    int alpha,
                                                    int fakePixelSize) {
        super(innerPainter);
        this.hypothesis = hypothesis;
        this.alpha = alpha;
        this.fakePixelSize = fakePixelSize;
    }


    public Color colorAt(final int x, final int y) {

        //int numNeighbors = hypothesis.getNumNeighborsForClassification();

        PointWithColor nearestPoint = onlyOneNeighbor(x, y);
        //List<Point> nearestPoints2 = findNearestNeighbors(x, y, numNeighbors + 1);

        //List<Color> weakClassifiedColors = new ArrayList<Color>() ;
        //weakClassifiedColors.add(meanColorFromPoints(nearestPoints1));
        //weakClassifiedColors.add(meanColorFromPoints(nearestPoints2));

        //return getMonaLisaColor(nearestPoint); //meanColor(weakClassifiedColors);
        return nearestPoint.color; //meanColor(weakClassifiedColors);
    }

    @Override
    public void paint(Graphics g) {

        if (innerPainter != null) {
            innerPainter.paint(g);
        }

        for (int y = 0; y < hypothesis.getMonaLisa().getHeight(); y += fakePixelSize) {
            for (int x = 0; x < hypothesis.getMonaLisa().getWidth(); x += fakePixelSize) {
                paintPixel(g, colorAt(x, y), x, y);
            }
        }
    }

    private void paintPixel(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, fakePixelSize, fakePixelSize);
    }

    private PointWithColor onlyOneNeighbor(int x, int y) {
        PointWithColor bestPoint = hypothesis.getPoints().get(0);
        for (PointWithColor p : hypothesis.getPoints()) {
            if (p.distanceSquaredFrom(x, y) < bestPoint.distanceSquaredFrom(x, y)) {
                bestPoint = p;
            }
        }

        return bestPoint;
    }

    private List<PointWithColor> realNearestNeighbors(final int x, final int y, int numNeighbors) {
        List<PointWithColor> nearestPoints = new ArrayList<PointWithColor>();

        Collections.sort(hypothesis.getPoints(), new Comparator<PointWithColor>() { // This is very badly optimized! But never optimize code too early.
            @Override
            public int compare(PointWithColor o1, PointWithColor o2) {
                if (o1 == o2) {
                    return 0;
                }
                double distance1 = o1.distanceSquaredFrom(x, y);
                double distance2 = o2.distanceSquaredFrom(x, y);
                if (distance1 == distance2) return 0;
                return distance1 > distance2 ? 1 : -1;
            }
        });

        for (int i = 0; i < numNeighbors; ++i) {
            nearestPoints.add(hypothesis.getPoints().iterator().next());
        }

        return nearestPoints;
    }

    @Override
    public int getWidth() {
        return innerPainter != null ? innerPainter.getWidth() : 0;
    }

    @Override
    public int getHeight() {
        return innerPainter != null ? innerPainter.getHeight() : 0;
    }
}
