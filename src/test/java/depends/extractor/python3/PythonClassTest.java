package depends.extractor.python3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import depends.deptypes.DependencyType;
import depends.entity.TypeEntity;
import depends.extractor.python.py3.Python3FileParser;

public class PythonClassTest extends Python3ParserTest {
    @Before
    public void setUp() {
    	super.init();
    }
	
	@Test
	public void should_parse_class() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/python-code-examples/class.py",
	    	    };
	    
	    for (String src:srcs) {
		    Python3FileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
        assertNotNull(repo.getEntity(withPackageName(srcs[0],"Foo")));
	}
	
	@Test
	public void should_parse_method_of_class() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/python-code-examples/class.py",
	    	    };
	    
	    for (String src:srcs) {
		    Python3FileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
	    TypeEntity type = (TypeEntity)repo.getEntity(withPackageName(srcs[0],"Foo"));
        assertEquals(2,type.getFunctions().size());
	}
	
	@Test
	public void should_parse_baseclass_of_class() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/python-code-examples/class.py",
	    	    };
	    
	    for (String src:srcs) {
		    Python3FileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
	    TypeEntity type = (TypeEntity)repo.getEntity(withPackageName(srcs[0],"Bar"));
	    this.assertContainsRelation(type, DependencyType.INHERIT, withPackageName(srcs[0],"Foo"));
	}


}
