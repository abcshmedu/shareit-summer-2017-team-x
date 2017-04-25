package edu.hm.cs.swa.projekt_2.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.hm.cs.swa.projekt_2.datamodel.BookTest;
import edu.hm.cs.swa.projekt_2.datamodel.CopyTest;
import edu.hm.cs.swa.projekt_2.datamodel.DiscTest;
import edu.hm.cs.swa.projekt_2.datamodel.MediumTest;
import edu.hm.cs.swa.projekt_2.logic.MediaServiceImplTest;
import edu.hm.cs.swa.projekt_2.persistence.DataStoreTest;
import edu.hm.cs.swa.projekt_2.rest.MediaResourceTest;

@RunWith(Suite.class)
@SuiteClasses({BookTest.class,
				MediumTest.class,
				DiscTest.class,
				CopyTest.class,
				DataStoreTest.class,
				MediaResourceTest.class,
				MediaServiceImplTest.class})
public class AllTests {

}
