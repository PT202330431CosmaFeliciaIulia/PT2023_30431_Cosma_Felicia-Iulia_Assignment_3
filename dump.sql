CREATE TABLE Client (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(200),
    email VARCHAR(100)
);

CREATE TABLE Product (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10, 2),
    stockQuantity INT
);

CREATE TABLE OrderT (
    id INT PRIMARY KEY,
    clientId INT,
    productId INT,
    quantity INT,
    FOREIGN KEY (clientId) REFERENCES Client(id),
    FOREIGN KEY (productId) REFERENCES Product(id)
);

SELECT * FROM Client;
SELECT * FROM Product;
SELECT * FROM OrderT;


INSERT INTO Client (id, name, address, email)
VALUES (1, 'John Doe', '123 Main St Anytown', 'john.doe@email.com');

INSERT INTO Client (id, name, address, email)
VALUES (2, 'Jane Smith', '456 Elm St Otherville', 'jane.smith@email.com');

INSERT INTO Client (id, name, address, email)
VALUES (3, 'Michael Johnson', '789 Oak Ave Anycity', 'michael.johnson@email.com');

INSERT INTO Product (id, name, price, stockQuantity)
VALUES (1, 'Widget A', 9.99, 100);

INSERT INTO Product (id, name, price, stockQuantity)
VALUES (2, 'Widget B', 14.99, 50);

INSERT INTO Product (id, name, price, stockQuantity)
VALUES (3, 'Widget C', 19.99, 75);

INSERT INTO OrderT (id, clientId, productId, quantity)
VALUES (1, 1, 1, 5);

INSERT INTO OrderT (id, clientId, productId, quantity)
VALUES (2, 2, 2, 2);

INSERT INTO OrderT (id, clientId, productId, quantity)
VALUES (3, 3, 3, 3);
