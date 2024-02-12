import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IMessageGet, IMessageReq} from "../_interfaces/chat/message";
import {catchError, Observable} from "rxjs";
import {IPostGet, IPostReq, IPostTitleGet} from "../_interfaces/post";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  url = "http://localhost:8080/api/post"

  constructor(private http: HttpClient) { }

  getPosts(seePost: IPostReq): Observable<IPostGet[]> {
    const url = `${this.url}/posts`;
    return this.http.post<IPostGet[]>(url, seePost)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de get all posts', error);
          throw error;
        })
      );
  }

  getAutocomplete(currentValue: string, type: string) {
    const url = `${this.url}/${type.toUpperCase()}/autocomplete/${currentValue}`;
    return this.http.get<IPostTitleGet[]>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de get autocomplete post', error);
          throw error;
        })
      );
  }
}
