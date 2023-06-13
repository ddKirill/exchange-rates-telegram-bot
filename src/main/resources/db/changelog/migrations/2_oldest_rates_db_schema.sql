CREATE TABLE oldest_rates
(
    user_id INTEGER not null references users(telegram_id),
    currency_alias VARCHAR[],
    currency_rate DOUBLE PRECISION[],
    --the time at which send request
    time_stamp TIMESTAMP not null,
    PRIMARY KEY (user_id)
);