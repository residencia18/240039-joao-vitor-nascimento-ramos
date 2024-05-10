CREATE TABLE audit_log (
    id bigint not null auto_increment primary key,
    event_name VARCHAR(150) NOT NULL,
    event_description VARCHAR(255),
    timestamp DATETIME,
    user_id VARCHAR(50),
    user_name VARCHAR(100),
    affected_resource VARCHAR(255),
    origin VARCHAR(255)
);