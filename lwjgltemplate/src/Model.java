import static org.lwjgl.opengl.GL20.*;




public class Model {

	//VERTEX DATA
	
	
	
	
	
	
	
	
	
	
	//SHADERS
	private final int programId;
	private int vertexShaderId;
	private int fragmentShaderId;
	
	public Model() throws Exception{
		programId = glCreateProgram();
		if(programId == 0){
			throw new Exception("[ERR] Could not create shader!");
		}
		
	}
	
	
	public void createVertexShader(String shaderCode) throws Exception {
		vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER);
	}
	
    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER);
    }
	
	
	 protected int createShader(String shaderCode, int shaderType) throws Exception {
	        int shaderId = glCreateShader(shaderType);
	        if (shaderId == 0) {
	            throw new Exception("Error creating shader. Code: " + shaderId);
	        }

	        glShaderSource(shaderId, shaderCode);
	        glCompileShader(shaderId);

	        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
	            throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
	        }

	        glAttachShader(programId, shaderId);

	        return shaderId;
	    }
	
	
	 public void link() throws Exception {
	        glLinkProgram(programId);
	        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
	            throw new Exception("Error linking Shader code: " + glGetShaderInfoLog(programId, 1024));
	        }

	        glValidateProgram(programId);
	        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
	            System.err.println("Warning validating Shader code: " + glGetShaderInfoLog(programId, 1024));
	        }

	    }
	 
	 public void bind() {
	        glUseProgram(programId);
	 }

	 public void unbind() {
	        glUseProgram(0);
	 }

	 public void cleanup() {
	        unbind();
	        if (programId != 0) {
	            if (vertexShaderId != 0) {
	                glDetachShader(programId, vertexShaderId);
	            }
	            if (fragmentShaderId != 0) {
	                glDetachShader(programId, fragmentShaderId);
	            }
	            glDeleteProgram(programId);
	        }
	 }
	
	
}
