package cn.greentracks.keycloak.phone.providers.sender;

import cn.greentracks.keycloak.phone.providers.spi.MessageSenderService;
import cn.greentracks.keycloak.phone.providers.spi.MessageSenderServiceProviderFactory;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class DummyMessageSenderServiceProviderFactory implements MessageSenderServiceProviderFactory {

    @Override
    public MessageSenderService create(KeycloakSession keycloakSession) {
        return new DummySmsSenderService(keycloakSession.getContext().getRealm().getDisplayName());
    }

    @Override
    public void init(Config.Scope scope) {
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getId() {
        return "dummy";
    }
}
