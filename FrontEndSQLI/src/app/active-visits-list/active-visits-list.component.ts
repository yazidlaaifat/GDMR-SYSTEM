import { Component, OnInit } from '@angular/core';
import { VisiteService } from '../Services/visite.service';
import { CommonModule, DatePipe } from '@angular/common';
import {DossierMedicalComponent} from "../dossier-medical/dossier-medical.component";
import {Router, RouterLink} from "@angular/router";
import {PdfService} from "../Services/pdf.service";
import {catchError, of, tap} from "rxjs";
import {AuthService} from "../Services/auth.service";
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-active-visits-list',
  standalone: true,
  imports: [
    CommonModule,
    DatePipe,
    DossierMedicalComponent,
    RouterLink
  ],
  templateUrl: './active-visits-list.component.html',
  styleUrls: ['./active-visits-list.component.css']
})
export class ActiveVisitsListComponent implements OnInit {
  Visites: any[] = [];
  currentUser: any = {};

  constructor(
    private visiteService: VisiteService,
    private router:Router,
    private pdfService: PdfService,
    private authService: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
  }

  viewDossierMedical(collaborateurId: number): void {
    this.router.navigate([`/dossier-medical/${collaborateurId}`]);
  }

  downloadPdf(collaborateurId: number, collaborateurName: string, collaborateurLastName: string): void {
    this.pdfService.generatePdf(collaborateurId, collaborateurName, collaborateurLastName).pipe(
      tap({
        next: (response: Blob) => {
          const blob = new Blob([response], { type: 'application/pdf' });
          const url = window.URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = url;
          link.download = 'visitor-profile.pdf';
          link.click();
          window.URL.revokeObjectURL(url);

        },
        error: (error) => {

        }
      }),
      catchError(error => {

        return of(null);
      })
    ).subscribe();
  }

  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {
        this.currentUser = data;
        this.loadVisitsForMedecin(data.idUser);
      },
      error: error => {
      }
    });
  }

  loadVisitsForMedecin(medecinId: number) {
    this.visiteService.getPlannedVisits(medecinId).subscribe({
      next: (data: any) => {
        this.Visites = data;
      },
      error: (error) => {
      }
    });
  }

  endVisit(visitId: number) {
    if (confirm('Are you sure you want to end this visit?')) {
      this.http.put(`http://localhost:8080/medecin/end/${visitId}`, {}).pipe(
        tap(() => {
          alert('Visit ended successfully');
          this.loadVisitsForMedecin(this.currentUser.idUser); // Reload visits
        }),
        catchError(error => {
          //alert('Failed to end visit');
          return of(null);
        })
      ).subscribe();
    }
    window.location.reload();
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['login']);
  }
}
