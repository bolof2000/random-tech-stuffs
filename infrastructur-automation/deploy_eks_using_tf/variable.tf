variable "region" {
  default = "us-east-1"
  description = "AWS REGION"
}
variable "cluster_name" {
  default = "getting-started-eks"
}

varibale "map_accounts" {
  description = "Map of accounts"
  type =  list(string)

  default = [
    "777777777777",
    "888888888888",
  ]
}

variable "map_roles" {
  description = " additional IAM roles to add to the aws-auth configmap"
  type = list(object({
    rolearn: string,
    username: string,
    groups: list(string),
  
  }))

  default = [
    {
      "rolearn": "arn:aws:iam::66666666666:role/role1",
      "username": "role1",
      "groups": ["system:masters"],
   
  ]
}

variable "map_users" {
  description = " additional IAM users to add to the aws-auth configmap"
  type = list(object({
    username: string,
    userarn: string,
    groups: list(string),
 
  }))


  default = [
    {
      "username": "user1",
      "groups": ["system:masters"],
      "userarn": "arn:aws:iam::66666666666:user/user1",
    
    },
    {
      "name": "user2",
      "groups": ["system:masters"],
      "userarn": "arn:aws:iam::66666666666:user/user2",
    },
  ]
}