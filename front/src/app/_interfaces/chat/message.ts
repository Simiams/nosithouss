export interface IMessageRes {
  senderIdentifier: string,
  content: string,
  createdAt: string,
}
export interface IMessageGet {
  senderIdentifier: string,
  content: string,
  createdAt: number,
}
export interface IMessageReq {
  userIdentifier: string,
  content: string,
}
