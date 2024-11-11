output "db_instance_endpoint" {
  value = aws_db_instance.example.endpoint
  description = "La URL del endpoint de la base de datos"
}
