![](https://github.com/ddKirill/exchange-rates-telegram-bot/blob/dev/image/photo_2023-06-04%2010.03.02.jpeg)


[Русский](README.md)

# 🤖 Exchange-rates-telegram-bot

----

Rates Bot - a Telegram bot that helps you get daily currency rates based on a selected base currency.
The bot automatically retrieves up-to-date currency rates and sends them to you in a convenient format through Telegram.
To use the bot, you need to specify a base currency, comparison currencies, and the time at which you would like to 
receive daily messages from the bot.

----

The bot's workflow consists of several steps:

1. Familiarization with available currencies: The bot provides information about the available currencies.
2. Selection of a base currency: The user chooses one currency against which the exchange rates of other currencies
will be displayed. The base currency serves as a reference point for comparing the rates of other currencies.
3. Selection of comparison currencies: The user selects three currencies that they want to compare to the base currency.
   The bot will provide the exchange rates of these currencies relative to the base currency.
4. Selection of notification time: The user specifies a convenient time to receive daily notifications with currency rates.
   The bot will send a message with the current rates at the specified time every day.
5. Menu-based bot management: The user can use commands in the bot's menu to reset the selected currencies and notification time,
   as well as to retrieve up-to-date currency rates upon request.


## The "Exchange-rates-telegram-bot" supports the following commands:


    /currency - get a list of available currencies.
    /pick - set comparison currencies.
    /clear - clear selected comparison currencies.
    /clearTime - remove notification time.
    /myRates - get my currency rates.

----

## Technologies used:

    Java 17
    Spring DATA JDBC 3.0.4
    Telegram Bots library (6.0.5)
    PostgreSQL database
    OpenExchangeRates API

## Technical description.

The Telegram Bots library is used to interact with the Telegram API, providing integration with the Telegram platform.
With the help of this library, the bot establishes a connection to the Telegram API using the specified BOT_USERNAME and BOT_TOKEN.
BOT_USERNAME is the bot's username, and BOT_TOKEN is a unique token provided during the bot registration on Telegram.

```java
@Component
public class BotInit {

    private ExchangeRatesSmartBot telegramBot;

    public BotInit(ExchangeRatesSmartBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init()throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try{
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e){

        }
    }
}
```

To exchange data with the Open Exchange Rates API, the bot utilizes HTTP requests. It employs REST Template to create
and send requests to the API. In the URL requests, the bot includes the app-id as a header. The app-id serves
as the access key to the API and is also set as an environment variable.


```java 
public OpenExchangeRatesResponse requestFor3Currency(String baseCurrency, List<String> compareCurrency) {
String uri = urlBuilder.buildOpenExchangeURL(baseCurrency, compareCurrency);
RestTemplate restTemplate = new RestTemplate();
return restTemplate.getForObject(uri, OpenExchangeRatesResponse.class);
}
```
