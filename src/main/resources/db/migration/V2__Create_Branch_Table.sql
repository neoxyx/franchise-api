-- V2__Create_Branch_Table.sql
CREATE TABLE branches (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    franchise_id BIGINT NOT NULL,
    CONSTRAINT fk_franchise
        FOREIGN KEY (franchise_id)
        REFERENCES franchise(id)
        ON DELETE CASCADE
);
