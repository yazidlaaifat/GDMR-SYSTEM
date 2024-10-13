import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Router} from "@angular/router";


@Injectable({
  providedIn: 'root'
})
export class DossierMedicalService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private router:Router) {}

  getDossierMedicalByCollaborateurId(collaborateurId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/medecin/dossierMedical/${collaborateurId}`);
  }

}
