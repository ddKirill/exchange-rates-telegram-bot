![](https://github.com/ddKirill/exchange-rates-telegram-bot/blob/dev/image/photo_2023-06-04%2010.03.02.jpeg) 

[English](README_ENG.md)


# 🤖 Exchange-rates-telegram-bot

----

Rates Bot - это телеграм-бот, который помогает вам получать ежедневные курсы валют на основе выбранной базовой валюты.
Бот автоматически извлекает актуальные курсы валют и отправляет их вам в удобном формате через Telegram. 
Для использования бота вам необходимо указать базовую валюту, валюты для сравнения и время, в которое вы хотели бы
получать ежедневные сообщения от бота.


----

## Процесс работы бота состоит из нескольких шагов:

1. Ознакомление с доступными валютами: Бот предоставляет информацию о доступных валютах.
2. Выбор базовой валюты: Пользователь выбирает одну валюту, относительно которой будут отображаться курсы других валют. 
Базовая валюта служит точкой отсчета для сравнения курсов других валют.
3. Выбор валют для сравнения: Пользователь выбирает три валюты, которые он хочет сравнивать с базовой валютой.
Бот будет предоставлять курсы этих валют относительно базовой валюты.
4. Выбор времени уведомления: Пользователь указывает удобное для него время, в которое он хочет получать ежедневные 
уведомления с курсами валют. Бот будет отправлять сообщение с актуальными курсами в указанное время каждый день.
5. Управление через меню бота: Пользователь может воспользоваться командами в меню бота для сброса выбранных валют
и времени уведомления, а также для получения актуальных курсов валют по запросу.


## Бот "Exchange-rates-telegram-bot" поддерживает следующие команды:

    /currency - список доступных валют.
    /pick - установить валюты для сравнения.
    /clear - очистить выбранные валюты для сравнения.
    /clearTime - удалить время уведомлений.
    /myRates - получить мои курсы валют.


----

## Используемые технологии:

- Java 17
- Spring DATA JDBC 3.0.4
- Библиотека Telegram Bots (6.0.5)
- БД PostgreSQL
- OpenExchangeRates API

## Техническое описание.

Для взаимодействия с Telegram API используется библиотека Telegram Bots, которая обеспечивает интеграцию с Telegram платформой.
С помощью этой библиотеки бот устанавливает подключение к Telegram API, используя указанный BOT_USERNAME и BOT_TOKEN.
BOT_USERNAME является именем бота, а BOT_TOKEN - уникальным токеном, который предоставляется при регистрации бота в Telegram.

#### Инициализация бота в BotInit.java

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

Для обмена данными с Open Exchange Rates API, бот использует HTTP-запросы. Он использует REST Template для создания
и отправки запросов к API. В URL-запросах бот передает app-id в качестве заголовка. app-id - это ключ доступа к API,
который также устанавливается в переменных окружения.

```java 
public OpenExchangeRatesResponse requestFor3Currency(String baseCurrency, List<String> compareCurrency) {
String uri = urlBuilder.buildOpenExchangeURL(baseCurrency, compareCurrency);
RestTemplate restTemplate = new RestTemplate();
return restTemplate.getForObject(uri, OpenExchangeRatesResponse.class);
}
```









