import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AntecedantService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getAntecedants(dossierMedicalId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/medecin/antecedant/${dossierMedicalId}`);
  }

  addAntecedant(antecedant: any): Observable<any>{
    return this.http.post(`${this.baseUrl}/medecin/antecedants`,antecedant)
  }
}
