package test.main.helpers;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AttackPhaseTest.class,  //5
        FortificationPhaseTest.class,   //2
        MapBuilderTest.class,   //9
       // ReinforcementPhaseTest.class,   //8
        StartupPhaseTest.class, //6
        GameHelperTest.class    //5

})
public class TestSuite {
}
