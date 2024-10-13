import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthService} from "../Services/auth.service";

@Component({
  selector: 'app-collaborateur-dashboard',
  standalone: true,
    imports: [
        RouterLink
    ],
  templateUrl: './collaborateur-dashboard.component.html',
  styleUrl: './collaborateur-dashboard.component.css'
})
export class CollaborateurDashboardComponent implements OnInit{
  currentUser: any = {};

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
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

  logout() {
    this.authService.logout();
  }
}
