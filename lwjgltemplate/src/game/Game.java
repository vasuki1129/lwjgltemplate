package game;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import static org.lwjgl.stb.STBEasyFont.*;


public class Game {

	private GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);
	private GLFWKeyCallback keyCallback;
	long window;
	double frame = 0;
	
	public static void main(String[] args) {
		new Game().start();

	}
	
	
	
	public void start(){
		glfwSetErrorCallback(errorCallback);
		
		if(!glfwInit()){
			throw new IllegalStateException("[ERR] FATAL: Unable to initialize GLFW");
		}
		
		window = GLFW.glfwCreateWindow(1024, 768, "Grigak -- The Great Desert", NULL, NULL);
		if(window == NULL){
			glfwTerminate();
			throw new RuntimeException("Failed to create GLFW window");
		}
		
		//KEY CALLBACK
		keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods){
				
				switch(key){
				case GLFW_KEY_ESCAPE:
					if(action == GLFW_PRESS){
						glfwSetWindowShouldClose(window, true);
					}
					break;
				default:
					break;
				}
				
			}
		}
		//END KEY CALLBACK
		
		glfwSetKeyCallback(window, keyCallback);
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		this.loop();
		
	}
	
	
	public void loop(){
		
		while(!glfwWindowShouldClose(window)){
			frame++;
			//START RENDER CALLS
			
			
			
			
			
			
			
			
			//draw frame counter
			
			
			//END RENDER CALLS
			glfwSwapBuffers(window);
			glfwPollEvents();
	
			this.sync();
		}
	}
	

	
	
    private long variableYieldTime, lastTime;
    
    /**
     * An accurate sync method that adapts automatically
     * to the system it runs on to provide reliable results.
     * 
     * @param fps The desired frame rate, in frames per second
     * @author kappa (On the LWJGL Forums)
     */
    public void sync(int fps) {
        if (fps <= 0) return;
          
        long sleepTime = 1000000000 / fps; // nanoseconds to sleep this frame
        // yieldTime + remainder micro & nano seconds if smaller than sleepTime
        long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000*1000));
        long overSleep = 0; // time the sync goes over by
          
        try {
            while (true) {
                long t = System.nanoTime() - lastTime;
                  
                if (t < sleepTime - yieldTime) {
                    Thread.sleep(1);
                }else if (t < sleepTime) {
                    // burn the last few CPU cycles to ensure accuracy
                    Thread.yield();
                }else {
                    overSleep = t - sleepTime;
                    break; // exit while loop
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lastTime = System.nanoTime() - Math.min(overSleep, sleepTime);
             
            // auto tune the time sync should yield
            if (overSleep > variableYieldTime) {
                // increase by 200 microseconds (1/5 a ms)
                variableYieldTime = Math.min(variableYieldTime + 200*1000, sleepTime);
            }
            else if (overSleep < variableYieldTime - 200*1000) {
                // decrease by 2 microseconds
                variableYieldTime = Math.max(variableYieldTime - 2*1000, 0);
            }
        }
    }
	
	
	
	
	
}
