-- Insert initial records for development

INSERT INTO users (username, email, password, role)
VALUES ('admin', 'admin@example.com', 'test', 'ADMIN')
    ON CONFLICT DO NOTHING;
