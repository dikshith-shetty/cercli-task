terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0.1"
    }
  }
}

provider "docker" {
  host    = "npipe:////.//pipe//docker_engine"
}

resource "docker_image" "postgresql" {
  name         = "postgres"
  keep_locally = false
}

resource "docker_container" "postgresql" {
  image = "postgres:latest"
  name  = "my-postgres"
  ports {
    internal = 5432
    external = 5432
  }
  env = [
    "POSTGRES_DB=cercliDB",
    "POSTGRES_USER=cercli",
    "POSTGRES_PASSWORD=mysecretpassword"
  ]
}