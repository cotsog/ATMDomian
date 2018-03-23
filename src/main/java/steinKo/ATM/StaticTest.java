package steinKo.ATM;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vafer.jdependency.Clazz;
import org.vafer.jdependency.Clazzpath;

import jdepend.framework.DependencyConstraint;
import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;

public class StaticTest {
	
	private final Logger logger = LoggerFactory.getLogger(StaticTest.class);
	
    @Test
    @Disabled
	public void reportDependency() {
		final File jar1 = new File("/target");
		final Clazzpath cp = new Clazzpath();
		try {
			cp.addClazzpathUnit(jar1, "Domain-0.0.1.jar");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final Clazz clazz = new Clazz("dependnecies");
		final Set<Clazz> dependencies = clazz.getDependencies();
		for(Clazz dependency : dependencies) {
			  System.out.println("dependency:" + dependency );
			}
	}
	
    @Test
	public void match() {
    	
    	DependencyConstraint constraint = new DependencyConstraint();
    	JavaPackage domain = constraint.addPackage("steinKo.ATM.doamin");
    JavaPackage repository = constraint.addPackage("steinKo.ATM.repository");
    repository.dependsUpon(domain);
    JDepend jdepend = new JDepend();
    Collection<?> analysis = jdepend.analyze();
    for(Object analyze : analysis) 
    {
          	logger.info(analyze.toString());
            
     }
    
    
    	
    }
	

}
