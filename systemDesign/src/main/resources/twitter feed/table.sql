create table user(
    user_id bigint,
    first_name string,
    last_name string,
    sex int,
    birthday date,
    address string
)

create table subscribe_status (
    user_id bigint,
    subscriber_id bigint,
    -- 0: subscribe, 1: black list
    status int
)

create table twit (
    twit_id bigint,
    text string,
    pics array(string)
)

create table post (
    twit_id bigint,
    time datetime,
    address string,
    device string,
    -- web/iphone/android
    platform string
)
