import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IMessageReq, IMessageRes} from "../_interfaces/chat/message";
import {catchError, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  url = "http://localhost:8080/api/message/"

  constructor(private http: HttpClient) { }

  sendMessage(messsage: IMessageReq): Observable<IMessageRes> {
    const url = `${this.url}${messsage.userIdentifier}`;
    console.log(url)
    console.log({content: messsage.content})
    return this.http.post<any>(url, {content: messsage.content})
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de connexion', error);
          throw error;
        })
      );
  }

  getMessages(userIdentifier: String): Observable<IMessageRes[]> {
    const url = `${this.url}${userIdentifier}`;
    return this.http.get<IMessageRes[]>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de connexion', error);
          throw error;
        })
      );
  }
}
