terraform {
  required_version = ">= 0.12.0"
}
provider "aws" {
  version = ">=2.28.1"
  region = var.region
}

data "aws_eks_cluster" "cluster" {
  name = "module.eks.cluster_id"
}

data "aws_eks_cluster_auth" "cluster" {
  name = module.eks.cluster_id
}

data "aws_availability_zone" "available" {

}

resource "aws_security_group" "worker_group_mgmt_one" {
  name_prefix = "worker_group_mgmt_one"
  vpc_id = module.vpc.vpc_id
  ingress {
    from_port = 22
    protocol  = "tcp"
    to_port   = 22
    cidr_blocks = [
      "10.0.0.0/8"
    ]
  }
}

resource "aws_security_group" "all_worker_mgmt" {
  name_prefix = "all_worker_management"
  vpc_id = module.vpc.vpc_id
  ingress {
    from_port = 22
    protocol  = "tcp"
    to_port   = 22
    cidr_blocks = [

      "10.0.0.0/8",
      "172.16.0.0/12",
      "192.168.0.0/16"
    ]
  }
}


module "vpc" {
  source = "terraform-aws-modules/vpc/aws"
  version = "2.6.0"
  name = "eks-vpc"
  cidr = "10.0.0.0/16"
  azs = data.aws_availability_zone.available.name
  private_subnets = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
  public_subnets = ["10.0.4.0/24", "10.0.5.0/24", "10.0.6.0/24"]
  enable_nat_gateway = true
  single_nat_gateway = true
  enable_dns_hostnames = true

  public_subnet_tags = {

    "kubernetes.io/cluster/${var.cluster_name}" = "shared"  
    "kubernetes.io/role/elb" = "1"

    }
    private_subnet_tags = {
        
        "kubernetes.io/cluster/${var.cluster_name}" = "shared"  
        "kubernetes.io/role/internal-elb" = "1"
        }
  
}

module "eks" {
  source = "terraform-aws-modules/eks/aws"
  version = "2.6.0"
  cluster_name = var.cluster_name
  subnets = module.vpc.private_subnets
  vpc_id = module.vpc.vpc_id
  cluster_create_timeout = "1h"
  cluster_endpoint_private_access = true

  worker_groups = [
    {
    name = "worker_group_mgmt_one"
    instance_type = "t2.medium"
    additional_user_data = "echo foo bar"
    asg_desired_capacity = 1
    additional_security_group_ids = [
      aws_security_group.worker_group_mgmt_one.id
      },
    ]

    worker_additional_security_group_ids = [
      aws_security_group.all_worker_mgmt.id
      ]
      map_roles =  var
    }


  }
