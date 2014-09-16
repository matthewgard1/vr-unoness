package piggydev.unoness.vr

import org.lwjgl.{Sys, opengl, LWJGLException}
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.{GL11, Display, DisplayMode}
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
    val fullscreen = false

    val desired_mode: DisplayMode = {
      if(fullscreen) {
        println("loading modes...")
        val modes = org.lwjgl.opengl.Display.getAvailableDisplayModes
        modes.find(m => {
          m.isFullscreenCapable &&
          m.getWidth == 1920 &&
          m.getFrequency == freq
        })
      } else {
        None
      }
    }.getOrElse {
      new opengl.DisplayMode(640, 480)
    }


    println("Starting")
    var want_to_end = false

    try {
      Display.setDisplayModeAndFullscreen(desired_mode)
      Display.setTitle("Episode 1 - Display Test")
      Display.setFullscreen(true)
      Display.setVSyncEnabled(true)
      Display.create()
    } catch { case e: LWJGLException =>
      println("Display wasn't initialized correctly.")
      sys.exit(1)
    }

    GL11.glMatrixMode(GL11.GL_PROJECTION)
    GL11.glLoadIdentity()
    GL11.glOrtho(0, desired_mode.getWidth, 0, desired_mode.getHeight, 1, -1)
    GL11.glMatrixMode(GL11.GL_MODELVIEW)

    while (!Display.isCloseRequested && !want_to_end) {
      if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
        println("DOWN")
        want_to_end = true //sys.exit(0)
      }
      // Clear the screen and depth buffer
      GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT)

      // set the color of the quad (R,G,B,A)
      GL11.glColor3f(0.5f,0.5f,1.0f)

      // draw quad
      GL11.glBegin(GL11.GL_QUADS)
      GL11.glVertex2f(100,100)
      GL11.glVertex2f(100+200,100)
      GL11.glVertex2f(100+200,100+200)
      GL11.glVertex2f(100,100+200)
      GL11.glEnd()

      Display.update()

      Display.sync(freq)
    }

    Display.destroy()
    sys.exit(0)
  }

  def getTime: Long = Sys.getTime * 1000 / Sys.getTimerResolution

  /**
   * Calculate the FPS and set it in the title bar
   */
  var lastFPS = getTime
  var fps = 0

  def updateFPS() {
    if (getTime - lastFPS > 1000) {
      Display.setTitle("FPS: " + fps)
      fps = 0
      lastFPS += 1000
    }
    fps += 1
  }


}