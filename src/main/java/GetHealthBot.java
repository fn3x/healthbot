import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class GetHealthBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                String enToRusTranslatedText = GoogleTranslator.translate(update.getMessage().getText());
                message.setText(FoodData.sumCalories(enToRusTranslatedText));
                execute(message); // Call method to send the message
            } catch (TelegramApiException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "gethealth_bot";
    }

    public String getBotToken() {
        return "1490694563:AAHc7ke2bDhKaT4jwsOZRIM-BZJj8fIqeJM";
    }
}