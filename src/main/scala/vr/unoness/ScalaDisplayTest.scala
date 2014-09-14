package piggydev.unoness.vr

import org.lwjgl.LWJGLException
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.DisplayMode
/**
 * If this application shows a blank and responsive window
 * and doesn't throw any errors, you know you have installed lwjgl
 * correctly.
 * If not add lwjgl/jars and lwjgl/native/[platform], to lib/  in your sbt project
 **/

object ScalaDisplayTest {
  def main (args: Array[String]) {
    println("live")
    val freq = 60
    println("loading modes...")
    val modes = org.lwjgl.opengl.Display.getAvailableDisplayModes
    val desired_mode = modes.filter( m => {
      m.isFullscreenCapable() &&
      m.getWidth() == 1920 &&
      m.getFrequency() == freq
    })

    println("Starting")
    try {
      Display.setDisplayModeAndFullscreen(desired_mode.head)
      Display.setTitle("Episode 1 - Display Test")
      Display.setFullscreen(true)
      Display.setVSyncEnabled(true)
      Display.create()
    } catch { case e: LWJGLException =>
      println("Display wasn't initialized correctly.")
      sys.exit(1)
    }

    while (!Display.isCloseRequested) {
      Display.update()
      if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
        println("DOWN")
        sys.exit(0)
      }
      Display.sync(freq)
    }

    Display.destroy()
    sys.exit(0)
  }



}