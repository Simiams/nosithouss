import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {catchError, Observable} from 'rxjs';
import { ICredential } from '../_interfaces/credential';
import { IToken } from '../_interfaces/token';


@Injectable({
  providedIn: 'root'
})

export class AuthService {

  url = 'http://localhost:8080/api/auth/login'

  constructor(private http: HttpClient) { }

  login(credentials: ICredential): Observable<IToken> {
    return this.http.post<IToken>(this.url, credentials)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de connexion', error);
          throw error;
        })
      );
  }
}
