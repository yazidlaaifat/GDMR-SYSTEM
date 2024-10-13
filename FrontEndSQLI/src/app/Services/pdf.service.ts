import {Injectable} from "@angular/core";
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PdfService{
  private baseUrl = 'http://localhost:8080';
  constructor(private http:HttpClient) {}

  generatePdf(collaborateurId: number, collaborateurName: string, collaborateurLastName: string): Observable<Blob>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/pdf'
    });

    return this.http.get(`${this.baseUrl}/medecin/generate-pdf/${collaborateurId}/${collaborateurName}/${collaborateurLastName}`, { headers: headers, responseType: 'blob' });
  }
}
