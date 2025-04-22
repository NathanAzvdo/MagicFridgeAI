
CREATE TYPE food_item_categoria AS ENUM (
    'FRUTAS',
    'VEGETAIS',
    'CARNES',
    'LATICINIOS',
    'GRAOS',
    'BEBIDAS',
    'DOCES',
    'CONGELADOS',
    'ENLATADOS',
    'TEMPEROS',
    'PÃES_E_MASSAS',
    'OLEOS_E_GORDURAS',
    'SNACKS',
    'OVOS',
    'MARISCOS'
);

-- Criação da tabela food_items
CREATE TABLE food_items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category food_item_categoria NOT NULL,
    quantity INTEGER,
    validity TIMESTAMP
);
