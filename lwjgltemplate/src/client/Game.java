package client;


import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.stb.STBImage;

import util.Utility;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Game {
	
	int WIDTH;
	int HEIGHT;
	GLFWKeyCallback keyCallback;
	
	private long window;
	
	public void init(){
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();
 
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
 
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
 
        WIDTH = Config.winWidth;
        HEIGHT = Config.winHeight;
 
        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "CHANGE ME!!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
 
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                    glfwSetWindowShouldClose(window, true);
                }
            }
        };
 
        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (vidmode.width() - WIDTH) / 2,
            (vidmode.height() - HEIGHT) / 2
        );
 
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
 
        // Make the window visible
        glfwShowWindow(window);
		
	}
	
	public void loop(){
		GL.createCapabilities();
		/*int buffer = GL45.glCreateBuffers();
		ByteBuffer bbfr = BufferUtils.createByteBuffer(9);
		bbfr.putFloat(-1.0f);
		bbfr.putFloat(-1.0f);
		bbfr.putFloat(0.0f);
		
		bbfr.putFloat(0.0f);
		bbfr.putFloat(1.0f);
		bbfr.putFloat(0.0f);
		
		bbfr.putFloat(1.0f);
		bbfr.putFloat(-1.0f);
		bbfr.putFloat(0.0f);
		
		bbfr.flip();*/
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		while (glfwWindowShouldClose(window) != true) {
		    
			
			
			//********************************************************************
			//Initialize
			double time = glfwGetTime();
			

			glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
			
			
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}
	
	
	public void shutdown(){
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
	}
	
}
