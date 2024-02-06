import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {IMessageGet} from "../_interfaces/chat/message";
import {IProfileGet, IUsernameGet} from "../_interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = "http://localhost:8080/api/user"
  constructor(private http: HttpClient) { }

  getAutocomplete(userNamePrefix: string): Observable<IUsernameGet[]> {
    const url = `${this.url}/autocomplete/${userNamePrefix}`;
    return this.http.get<IUsernameGet[]>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête d\'autocompletion', error);
          throw error;
        })
      );
  }

  getCurrentProfile(): Observable<IProfileGet> {
    const url = `${this.url}`;
    return this.http.get<IProfileGet>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de get current profile', error);
          throw error;
        })
      );
  }
}
