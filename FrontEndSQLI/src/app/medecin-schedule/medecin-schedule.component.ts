import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthService} from "../Services/auth.service";

@Component({
  selector: 'app-medecin-schedule',
  standalone: true,
    imports: [
        RouterLink
    ],
  templateUrl: './medecin-schedule.component.html',
  styleUrl: './medecin-schedule.component.css'
})
export class MedecinScheduleComponent implements OnInit{
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
