import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthService} from "../Services/auth.service";
import {HttpClient} from "@angular/common/http";
import {CommonModule, DatePipe} from "@angular/common";

@Component({
  selector: 'app-collaborateur-visits',
  standalone: true,
  imports: [
    RouterLink,
    DatePipe,
    CommonModule
  ],
  templateUrl: './collaborateur-visits.component.html',
  styleUrl: './collaborateur-visits.component.css'
})
export class CollaborateurVisitsComponent implements OnInit{
  currentUser: any = {};
  plannedVisits: any[] = [];
  awaitingConfirmationVisits: any[] = [];
  visitHistory: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
    this.loadVisits();
  }
  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {

        this.currentUser = data;
      },
      error: error => {

      }
    });
  }

  loadVisits(): void {
    this.authService.getCurrentUser().subscribe(user => {
      this.http.get<any[]>(`http://localhost:8080/collaborateur/visites/${user.idUser}`).subscribe({
        next: visits => {
          this.plannedVisits = visits.filter(v => v.statusVisite === 'Planifié');
          this.awaitingConfirmationVisits = visits.filter(v => !v.motif && !v.statusVisite && !v.typesVisite);
          this.visitHistory = visits.filter(v => v.statusVisite === 'Passé');
        },
        error: error => {

        }
      });
    });
  }

  logout() {
    this.authService.logout();
  }
}
