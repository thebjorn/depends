package depends.extractor.python3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import depends.entity.FunctionEntity;
import depends.extractor.python.py3.Python3FileParser;

public class PythonFunctionTest extends Python3ParserTest {
    @Before
    public void setUp() {
    	super.init();
    }
	
	@Test
	public void should_parse_methods() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/python-code-examples/func.py",
	    	    };
	    
	    for (String src:srcs) {
		    Python3FileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
        assertNotNull(repo.getEntity(withPackageName(srcs[0],"foo")));
	}
	
	@Test
	public void should_parse_method_parameters() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/python-code-examples/func.py",
	    	    };
	    
	    for (String src:srcs) {
		    Python3FileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
	    FunctionEntity func = (FunctionEntity)repo.getEntity(withPackageName(srcs[0],"foo"));
        assertEquals(2,func.getParameters().size());
	}
	


}
