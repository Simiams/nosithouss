import {MessageType} from "../ennums";

export interface IMessageRes {
  senderIdentifier: string,
  content: string,
  createdAt: string,
  type: string | null,
  accept: boolean | null,
  id: string | null,
}
export interface IMessageGet {
  senderIdentifier: string,
  content: string,
  createdAt: number,
  type: string,
  accept: boolean | null,
  id: string | null,
}
export interface IMessageReq {
  userIdentifier: string,
  content: string,
}

export interface IMessageGuardReq {
  content: string,
  type: "GUARD_CLAIM",
  accept: boolean | null
  postId: number
}


