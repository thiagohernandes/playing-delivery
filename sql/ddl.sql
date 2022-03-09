CREATE TABLE `tbl_product` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `stock` integer NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tbl_provider` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tbl_customer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `cpf` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tbl_order` (
  `id` bigint(20) NOT NULL,
  `date_order` date NOT NULL,
  `provider_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`provider_id`)
        REFERENCES `tbl_provider` (`id`),
  FOREIGN KEY (`customer_id`)
        REFERENCES `tbl_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tbl_order_item` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` integer NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`order_id`)
        REFERENCES `tbl_order` (`id`),
  FOREIGN KEY (`product_id`)
        REFERENCES `tbl_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tbl_order_history` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`order_id`)
        REFERENCES `tbl_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
