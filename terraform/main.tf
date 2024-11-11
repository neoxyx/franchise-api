resource "aws_db_instance" "example" {
  allocated_storage    = 20
  storage_type         = "gp2"
  engine               = "postgres"
  engine_version       = "13.4"  # Versión de PostgreSQL que deseas usar
  instance_class       = "db.t3.micro"  # Tipo de instancia de la base de datos
  name                 = "mydb"  # Nombre de la base de datos
  username             = "admin"  # Usuario de la base de datos
  password             = "password123"  # Contraseña de la base de datos
  db_subnet_group_name = aws_db_subnet_group.example.name
  vpc_security_group_ids = [aws_security_group.example.id]

  # Habilitar acceso público para pruebas (no recomendado para producción)
  publicly_accessible = true

  tags = {
    Name = "example-db"
  }
}

# Crear un grupo de subredes para RDS
resource "aws_db_subnet_group" "example" {
  name        = "example-db-subnet-group"
  subnet_ids  = [aws_subnet.example.id]  # Debes configurar las subredes
  description = "My DB subnet group"
}

# Crear un grupo de seguridad para la base de datos
resource "aws_security_group" "example" {
  name        = "example-db-sg"
  description = "Allow access to RDS"
  vpc_id      = aws_vpc.example.id  # Debes tener configurado un VPC

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]  # Permite el acceso desde cualquier IP (ajustar según necesidad)
  }
}

# Crear un VPC (Virtual Private Cloud) si no tienes uno
resource "aws_vpc" "example" {
  cidr_block = "10.0.0.0/16"
  enable_dns_support = true
  enable_dns_hostnames = true
}

# Crear una subred para el VPC
resource "aws_subnet" "example" {
  vpc_id                  = aws_vpc.example.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true
}
