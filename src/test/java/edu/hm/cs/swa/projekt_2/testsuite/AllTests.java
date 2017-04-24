package edu.hm.cs.swa.projekt_2.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.hm.cs.swa.projekt_2.datamodel.BookTest;
import edu.hm.cs.swa.projekt_2.datamodel.CopyTest;
import edu.hm.cs.swa.projekt_2.datamodel.Disc;
import edu.hm.cs.swa.projekt_2.datamodel.DiscTest;
import edu.hm.cs.swa.projekt_2.datamodel.MediumTest;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;
import edu.hm.cs.swa.projekt_2.persistence.DataStoreTest;
import edu.hm.cs.swa.projekt_2.rest.MediaResource;

@RunWith(Suite.class)
@SuiteClasses({BookTest.class,
				MediumTest.class,
				DiscTest.class,
				CopyTest.class,
				DataStoreTest.class,
				MediaResource.class})
public class AllTests {

}
