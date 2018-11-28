package testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestAdicionaUsuarioLogica.class, TestModelo.class,
		TestMvcLogica.class, TestUtil.class})

public class AllTests {

}
