import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {IContactGet, IContactRes} from "../_interfaces/chat/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  url = "http://localhost:8080/api/contact"

  constructor(private http: HttpClient) { }

  getContacts():Observable<IContactGet[]> {
    return this.http.get<IContactGet[]>(this.url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête des contacts', error);
          throw error;
        })
      );
  }
}
