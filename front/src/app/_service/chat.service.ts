import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IMessageGet, IMessageGuardReq, IMessageReq} from "../_interfaces/chat/message";
import {catchError, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  url = "http://localhost:8080/api/message/"

  constructor(private http: HttpClient) { }

  sendMessage(messsage: IMessageReq): Observable<IMessageGet> {
    const url = `${this.url}${messsage.userIdentifier}`;
    return this.http.post<any>(url, {content: messsage.content})
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de connexion', error);
          throw error;
        })
      );
  }

  getMessages(userIdentifier: string): Observable<IMessageGet[]> {
    const url = `${this.url}${userIdentifier}`;
    return this.http.get<IMessageGet[]>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de connexion', error);
          throw error;
        })
      );
  }

  sendGuardRequest(message: IMessageGuardReq, userIdentifier: string): Observable<any> {
    const url = `${this.url}guard-request/${userIdentifier}`;
    console.log(url)
    return this.http.post<any>(url, message)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de connexion', error);
          throw error;
        })
      );
  }

  acceptGuardRequest(accept: boolean, postId: string): Observable<any> {
    const url = `${this.url}guard-request/${postId}/accept`;
    console.log(url)
    return this.http.post<any>(url, {accept: accept})
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de connexion', error);
          throw error;
        })
      );
  }
}
