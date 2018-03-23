package steinKo.ATM.test.integration.framework;




import steinKo.ATM.Domain;
import steinKo.ATM.Category.IntegrationTest;

import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;



@IntegrationTest

@SpringJUnitConfig(classes = Domain.class)

public abstract class AbstractIntegration {

}
