export interface IUser {
  id:number,
  nom: string,
  prenom: string,
  email: string,
  password: string,
  createdAt: string,
  updatedAt: string,
  deletedAt: null | string
}

export interface ISingleUser{
  data: IUser
}

export interface IDataUser{
  data: IUser[]
}

export interface ITokenUser{
  id: number,
  nom: string,
  prenom: string,
  email: string,
  iap?: number,
  exp?: number
}

export interface IUsernameGet{
  userName:string,
  pdp: string
}

export interface IProfileGet {
  userName: string,
  firstName: string,
  lastName: string,
  roles: any, //todo
  pdp: string
}


export const defaultIProfileGet : IProfileGet  = {
  userName: "userName",
  firstName: "firstName",
  lastName: "lastName",
  roles: "roles", //todo
  pdp: "42de9a00-1830-4f56-bea1-12216a0d1477"
}
