Drop database if exists DBRautomotriz;
Create Database DBRautomotriz;
use DBRautomotriz;

create table clientes (
    id_cliente int not null auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    telefono varchar(15) not null,
    email varchar(50) not null,
    primary key PK_id_cliente(id_cliente)
);

-- Tabla Proveedores
CREATE TABLE proveedores (
    id_proveedor int not null auto_increment,
    nombre varchar(50) not null,
    telefono varchar(15) not null,
    email varchar(50) not null,
    direccion varchar(100) not null,
    primary key PK_id_proveedor(id_proveedor)
);

-- Tabla Productos
CREATE TABLE productos (
    id_producto int not null auto_increment,
    nombre varchar(50) NOT NULL,
    descripcion varchar(100) NOT NULL,
    precio decimal(10,2) NOT NULL,
    stock int not null,
    id_proveedor int not null,
    primary key PK_id_producto(id_producto),
    foreign key FK_id_proveedor(id_proveedor) references proveedores(id_proveedor)
);

-- Tabla Ventas
CREATE TABLE ventas (
    id_venta int not null auto_increment,
    id_cliente int not null,
    id_producto int not null,
    cantidad int not null,
    fecha_venta date not null,
    total decimal(10,2) not null,
    primary key PK_id_venta(id_venta),
    foreign key FK_id_cliente(id_cliente) references clientes(id_cliente),
    foreign key FK_id_producto(id_producto) references productos(id_producto)
);

select * from clientes;
select * from proveedores;
select * from productos;
select * from ventas;
