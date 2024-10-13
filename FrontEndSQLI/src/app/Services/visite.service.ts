import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VisiteService {
  private apiBaseUrl = 'http://localhost:8080/medecin';

  constructor(private http: HttpClient) {}

  getPlannedVisits(medecinId: number): Observable<any> {
    return this.http.get<any>(`${this.apiBaseUrl}/ListVisitesCurrent?medecinId=${medecinId}`);
  }

  getPlannedVisitsCount(medecinId: number): Observable<number> {
    return this.http.get<number>(`${this.apiBaseUrl}/plannedVisitsCount?medecinId=${medecinId}`);
  }

  getPastVisits(medecinId: number): Observable<any> {
    return this.http.get<any>(`${this.apiBaseUrl}/ListVisitesPast?medecinId=${medecinId}`);
  }

}
