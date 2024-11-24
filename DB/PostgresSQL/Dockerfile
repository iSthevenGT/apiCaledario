# Utilizar la imagen oficial de PostgreSQL desde Docker Hub
FROM postgres:latest

# Copia los scripts de DDL y DML al directorio de Docker
COPY  DDL.sql /docker-entrypoint-initdb.d/
