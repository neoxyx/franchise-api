variable "db_username" {
  description = "El nombre de usuario para la base de datos"
  default     = "admin"
}

variable "db_password" {
  description = "La contraseña para la base de datos"
  sensitive   = true
  default     = "password123"
}

variable "region" {
  description = "La región donde se desplegarán los recursos de AWS"
  default     = "us-east-1"
}
