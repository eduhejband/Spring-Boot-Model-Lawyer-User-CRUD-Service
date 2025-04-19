SELECT 'CREATE DATABASE banco'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'banco')\gexec
