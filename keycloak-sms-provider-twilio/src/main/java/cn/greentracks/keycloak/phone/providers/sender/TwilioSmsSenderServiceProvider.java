package cn.greentracks.keycloak.phone.providers.sender;

import cn.greentracks.keycloak.phone.providers.spi.FullSmsSenderAbstractService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import cn.greentracks.keycloak.phone.providers.exception.MessageSendException;
import org.jboss.logging.Logger;
import org.keycloak.Config.Scope;

public class TwilioSmsSenderServiceProvider extends FullSmsSenderAbstractService {

    private static final Logger logger = Logger.getLogger(TwilioSmsSenderServiceProvider.class);
    private final String twilioPhoneNumber;

    TwilioSmsSenderServiceProvider(Scope config, String realmDisplay) {
        super(realmDisplay);
        Twilio.init(config.get("account"), config.get("token"));
        this.twilioPhoneNumber = config.get("number");

    }

    @Override
    public void sendMessage(String phoneNumber, String message) throws MessageSendException {

        Message msg = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                message).create();

        if (msg.getStatus() == Message.Status.FAILED) {
            logger.error("message send failed!");
            throw new MessageSendException(msg.getStatus().ordinal(),
                    String.valueOf(msg.getErrorCode()),
                    msg.getErrorMessage());
        }
    }

    @Override
    public void close() {
    }
}
