package edu.hm;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

import edu.hm.cs.swa.projekt_2.logic.MediaService;
import edu.hm.cs.swa.projekt_2.logic.MediaServiceImpl;
import edu.hm.cs.swa.projekt_2.logic.ValidationService;
import edu.hm.cs.swa.projekt_2.logic.ValidationServiceImpl;
import edu.hm.cs.swa.projekt_2.persistence.DataStore;

public class Configuration extends AbstractModule {

    public static String AUTH_SERVER_URL = "127.0.0.1";
    public static int AUTH_SERVER_PORT = 8000;

    @Override
    protected void configure() {
        bind(MediaService.class).to(MediaServiceImpl.class);

        bind(ValidationService.class).toInstance(ValidationServiceImpl.INSTANCE);
        
        bind(DataStore.class).toInstance(DataStore.INSTANCE);

        //bind(Integer.class).annotatedWith(Names.named("auth_server_port")).toInstance(80);
        //bind(String.class).annotatedWith(Names.named("auth_server_url")).toInstance("shareit-summer-2017-team-x-a.herokuapp.com");
    
        // add this module to guice
       // Guice.createInjector(this);
    }
}
