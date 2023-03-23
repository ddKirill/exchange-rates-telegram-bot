CREATE TABLE users
(
    telegram_id INTEGER not null,
    notification_time TIME,
    base_currency VARCHAR(125),
    --saved List<CurrencyAlias>
    compared_currency VARCHAR(256),
    version INTEGER,
    PRIMARY KEY (telegram_id)
);



