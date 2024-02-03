export interface IMessageRes {
  senderIdentifier: string,
  content: string,
  createdAt: Date
}
export interface IMessageReq {
  userIdentifier: string,
  content: string,
}
