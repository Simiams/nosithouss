import {Injectable} from '@angular/core';
import {catchError, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AssetsService {
  url = "http://localhost:8080/api/assets"

  constructor(private http: HttpClient) {
  }

  uploadFile(file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    const url = `${this.url}/upload`;

    return this.http.post(url, formData)
      .pipe(
        catchError((error) => {
          console.error('Erreur lors de la requête d\'upload du fichier', error);
          throw error;
        })
      );
  }
}
