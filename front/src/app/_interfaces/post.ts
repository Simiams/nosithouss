import {EPostType} from "./ennums";

export interface IPostGet {
  type: EPostType,
  id: number,
  authorUserName: string,
  createdAt: number,
  content?: string,
  title: string,
  lastVersion?: IPostGet,
  nbLike: number,
  nbDislike: number,
  coordinates: ICoordinate,
  guardingAt?: number,
  endGuardingAt?: number,
  img: string
}

export interface IPostRes {
  type: EPostType,
  id: number,
  authorUserName: string,
  createdAt: number,
  content?: string,
  title: string,
  lastVersion?: IPostGet,
  nbLike: number,
  nbDislike: number,
  coordinates: ICoordinate,
  guardingAt?: number,
  endGuardingAt?: number,
  img: string,
  liked?: boolean,
}

interface ICoordinate{
  c: number,
  y: number
}

export interface IPostReq {
  number: number,
  createdAt: string,
}
