package tools;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import character.Character;
import java.util.Collections;
import java.util.Comparator;

public class MiscUtils {

    private MiscUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Image[] loadImages(String dir) {
        List<Image> imageList = Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory()).sorted()
                .map(file -> Toolkit.getDefaultToolkit().getImage(file.getPath()))
                .collect(Collectors.toList());
      
        return imageList.toArray(new Image[0]);
    }

    public static double distance(Character c1, Character c2) {
        return Math.sqrt(Math.pow((double) c1.getXPosition() - c2.getXPosition(), 2) + Math.pow((double) c1.getYPosition() - c2.getYPosition(), 2));
    }
}
