import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable} from "rxjs";
import {IProfileGet, IUsernameGet} from "../_interfaces/user";
import {IPostRes} from "../_interfaces/post";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = "http://localhost:8080/api/user"
  urlPost = "http://localhost:8080/api/post"

  constructor(private http: HttpClient) {
  }

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

  getProfile(userName: string): Observable<IProfileGet> {
    const url = `${this.url}/${userName}`;
    return this.http.get<IProfileGet>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de get current profile', error);
          throw error;
        })
      );
  }

  getOwnPosts() {
    const url = `${this.urlPost}/profile`;
    return this.http.get<IPostRes[]>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de get post profile', error);
          throw error;
        })
      );
  }

  getPostByUsername(userName: string) {
    const url = `${this.urlPost}/profile/${userName}`;
    return this.http.get<IPostRes[]>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de get post profile', error);
          throw error;
        })
      );
  }

  getPostsGuarding() {
    const url = `${this.urlPost}/guarding`;
    return this.http.get<IPostRes[]>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de get post profile', error);
          throw error;
        })
      );
  }
}
