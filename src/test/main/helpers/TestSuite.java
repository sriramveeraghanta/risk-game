package test.main.helpers;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AttackPhaseTest.class,
        FortificationPhaseTest.class,
        MapBuilderTest.class,
        ReinforcementPhaseTest.class,
        StartupPhaseTest.class

})
public class TestSuite {
}