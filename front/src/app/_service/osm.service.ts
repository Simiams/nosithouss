import { Injectable } from '@angular/core';
import {catchError, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OsmService {
  url = "https://nominatim.openstreetmap.org/search?format=json&q="

  constructor(private http: HttpClient) { }

  getAutocomplete(search: string): Observable<any> {
    const url = `${this.url}/${search}`;
    return this.http.get<any>(url)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête de get all posts', error);
          throw error;
        })
      );
  }
}
