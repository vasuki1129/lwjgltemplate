package util;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL12;
import org.lwjgl.stb.STBImage;

public class Utility {

	
	public static ByteBuffer stringBuffer(String s){
		ByteBuffer bb = ByteBuffer.allocate(s.length());
		for(int i =0;i<s.length();i++){
			bb.put((byte) s.charAt(i));
		}
		bb.put((byte) 0);
		bb.flip();
		return bb;
	}
	
	
	
	public static int Texicute(String path){
		int x[] ={128};
		int y[] ={128};
		int n[] = {0};
		
		IntBuffer xb = IntBuffer.wrap(x);
		IntBuffer yb =IntBuffer.wrap(y);
		IntBuffer nb =IntBuffer.wrap(n);
		xb.flip();
		yb.flip();
		nb.flip();

		
		ByteBuffer tex = STBImage.stbi_load(Utility.stringBuffer(path),xb,yb,nb,0);
		tex.flip();
	
     
        
        // You now have a ByteBuffer filled with the color data of each pixel.
        // Now just create a texture ID and bind it. Then you can load it using 
        // whatever OpenGL method you want, for example:
        int textureID = glGenTextures(); //Generate texture ID
        glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID
        
        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        
        //Send texel data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, 128,128, 0, GL_RGBA, GL_UNSIGNED_BYTE, tex);
        //Return the texture ID so we can bind it later again
    	STBImage.stbi_image_free(tex);
        return textureID;

	}
	
	public static int Texicute(int texid){
	       glBindTexture(GL_TEXTURE_2D, texid); //Bind texture ID
	        
	        //Setup wrap mode
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

	        //Setup texture scaling filtering
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	        
	        //Return the texture ID so we can bind it later again
	        return texid;
	}
	
	

}
