CREATE TABLE invoice_detail (
	id INT AUTO_INCREMENT NOT NULL,
	price FLOAT NOT NULL,
	quantity INT NOT NULL,
	invoice_id INT NOT NULL,
	product_id INT NOT NULL,
	PRIMARY KEY (id)
);
