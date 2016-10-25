package Service;

import Domain.DomainModule;
import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new DomainModule());
    }
}
