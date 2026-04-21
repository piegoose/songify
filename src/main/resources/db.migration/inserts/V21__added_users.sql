INSERT INTO users (email, password, authorities, confirmation_token, enabled)
VALUES
    (
        'bartek',
        '$2a$12$zxfsSr3aBjzBnXVRwOkX7OkioEUPKyj2/v.aKHJe.PYTf2/bEvQn2',
        ARRAY['ROLE_ADMIN','ROLE_USER']::text[],
        NULL,
        true
    ),
    (
        'janek',
        '$2a$12$LaKErUr/ypej/AVIiQoHc.bZohzW9nuDS25Jna4c61K5GmZX.tMuq',
        ARRAY['ROLE_USER']::text[],
        NULL,
        true
    );