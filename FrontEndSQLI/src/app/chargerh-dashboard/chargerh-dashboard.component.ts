import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {DatePipe, NgForOf, NgIf, NgClass} from "@angular/common";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthService} from "../Services/auth.service";

@Component({
  selector: 'app-chargerh-dashboard',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    NgClass,
    RouterLink,
    DatePipe
  ],
  templateUrl: './chargerh-dashboard.component.html',
  styleUrl: './chargerh-dashboard.component.css'
})
export class ChargerhDashboardComponent implements OnInit{
  currentUser: any = {};
  totalAppointmentsToday: number = 0;
  totalAppointmentsWeek: number = 0;
  todayAppointments: any[] = [];
  availableDoctors: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
    this.loadDashboardData();
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

  loadDashboardData(){
    this.getTotalAppointmentsToday();
    this.getTotalAppointmentsWeek();
    this.getTodayAppointments();
    this.getAvailableDoctors();
  }

  getTotalAppointmentsToday(){
    this.http.get<number>('http://localhost:8080/chargerh/appointments/today').subscribe({
      next: data => {
        this.totalAppointmentsToday = data;
      },
      error: error => {

      }
    });
  }

  getTotalAppointmentsWeek(){
    this.http.get<number>('http://localhost:8080/chargerh/appointments/week').subscribe({
      next: data => {
        this.totalAppointmentsWeek = data;
      },
      error: error => {

      }
    });
  }

  getTodayAppointments() {
    this.http.get<any[]>('http://localhost:8080/chargerh/appointments/today/list').subscribe({
      next: data => {
        this.todayAppointments = data;
      },
      error: error => {

      }
    });
  }

  getAvailableDoctors(){
    this.http.get<any[]>('http://localhost:8080/chargerh/availableDoctors').subscribe({
      next: data => {
        this.availableDoctors = data;
      },
      error: error => {

      }
    });
  }

  logout() {
    this.authService.logout();
  }

}
