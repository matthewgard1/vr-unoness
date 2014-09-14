package piggydev.unoness.vr;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * If this application shows a blank and responsive window
 * and doesn't throw any errors, you know you have installed lwjgl
 * correctly.
 * If not add lwjgl/jars and lwjgl/native/[platform], to lib/  in your sbt project
 * @author Matthew G.
 * big thanks to Oskar Veerhoek for example java source
 *
 */
public class JavaDisplayTest {
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Episode 1 – Display Test");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("Display wasn't initialized correctly.");
            System.exit(1);
        }

        while (!Display.isCloseRequested()) {
            Display.update();
            Display.sync(60);
        }

        Display.destroy();
        System.exit(0);
    }
}