import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthService} from "../Services/auth.service";
import {VisiteService} from "../Services/visite.service";
import {CommonModule, DatePipe} from "@angular/common";

@Component({
  selector: 'app-medecin-visits',
  standalone: true,
  imports: [
    RouterLink,
    DatePipe,
    CommonModule
  ],
  templateUrl: './medecin-visits.component.html',
  styleUrl: './medecin-visits.component.css'
})
export class MedecinVisitsComponent implements OnInit{
  currentUser: any = {};
  pastVisits: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private visiteService: VisiteService
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
  }
  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {

        this.currentUser = data;
        this.loadPastVisits();
      },
      error: error => {

      }
    });
  }

  loadPastVisits() {
    if (this.currentUser?.idUser) {
      this.visiteService.getPastVisits(this.currentUser.idUser).subscribe({
        next: data => {
          this.pastVisits = data;

        },
        error: error => {

        }
      });
    }
  }

  logout() {
    this.authService.logout();
  }
}
