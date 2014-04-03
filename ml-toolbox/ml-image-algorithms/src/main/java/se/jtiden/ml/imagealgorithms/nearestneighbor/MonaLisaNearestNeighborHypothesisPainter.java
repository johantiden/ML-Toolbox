package se.jtiden.ml.imagealgorithms.nearestneighbor;

import se.jtiden.common.images.FastJTImage;
import se.jtiden.common.images.JTColor;
import se.jtiden.common.images.JTImage;
import se.jtiden.common.images.PointWithColor;
import se.jtiden.ml.imagealgorithms.painter.AlgorithmStepPainter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MonaLisaNearestNeighborHypothesisPainter implements AlgorithmStepPainter {

    private final MonaLisaNearestNeighborHypothesis hypothesis;
    private JTImage cachedImage;

    public MonaLisaNearestNeighborHypothesisPainter(MonaLisaNearestNeighborHypothesis hypothesis) {
        this.hypothesis = hypothesis;
    }


    public JTColor getColorAt(final int x, final int y) {

        //int numNeighbors = hypothesis.getNumNeighborsForClassification();

        PointWithColor nearestPoint = onlyOneNeighbor(x, y);
        //List<Point> nearestPoints2 = findNearestNeighbors(x, y, numNeighbors + 1);

        //List<Color> weakClassifiedColors = new ArrayList<Color>() ;
        //weakClassifiedColors.add(meanColorFromPoints(nearestPoints1));
        //weakClassifiedColors.add(meanColorFromPoints(nearestPoints2));

        //return getMonaLisaColor(nearestPoint); //meanColor(weakClassifiedColors);
        return nearestPoint.getColor(); //meanColor(weakClassifiedColors);
    }

    @Override
    public void paint() {
        if (cachedImage == null) {
            innerPaint();
        }
    }

    private void innerPaint() {
        cachedImage = new FastJTImage(hypothesis.getMonaLisa().getWidth(), hypothesis.getMonaLisa().getHeight());

        for (int y = 0; y < hypothesis.getMonaLisa().getHeight(); ++y) {
            for (int x = 0; x < hypothesis.getMonaLisa().getWidth(); ++x) {
                JTColor nearestNeighbor = getColorAt(x, y);
                cachedImage.setPixel(x, y, nearestNeighbor);
            }
        }
    }

    @Override
    public JTImage getImage() {
        paint();
        return cachedImage;
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
        return hypothesis.getMonaLisa().getWidth();
    }

    @Override
    public int getHeight() {
        return hypothesis.getMonaLisa().getHeight();
    }

}
