package test;
/**
 * @author Christian & Oliver
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountTest.class, GameControllerTest.class })
public class AllTests {

}
