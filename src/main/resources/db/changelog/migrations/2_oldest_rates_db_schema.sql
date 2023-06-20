CREATE TABLE oldest_rates
(
    user_id INTEGER not null references users(telegram_id),
    base_currency_alias VARCHAR(3) not null,
    currency_alias VARCHAR[] not null,
    currency_rate DOUBLE PRECISION[] not null,
    --the time at which send request
    time_stamp TIMESTAMP not null,
    PRIMARY KEY (user_id)
);