import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../Services/auth.service";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-chargerh-doctors',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    FormsModule
  ],
  templateUrl: './chargerh-doctors.component.html',
  styleUrl: './chargerh-doctors.component.css'
})
export class ChargerhDoctorsComponent implements OnInit{
  currentUser: any = {};
  doctors: any[] = [];
  filteredDoctors: any[] = [];
  searchOption: string = 'speciality';
  searchValue: string = '';
  experienceRange: string = '';

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
    this.getDoctors();
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

  getDoctors() {
    this.http.get<any[]>('http://localhost:8080/chargerh/doctors').subscribe({
      next: data => {
        this.doctors = data;
        this.filteredDoctors = data;
      },
      error: error => {

      }
    });
  }


  filterDoctors() {
    if (!this.searchOption) {
      this.filteredDoctors = this.doctors;
      return;
    }

    this.filteredDoctors = this.doctors.filter(doctor => {
      if (this.searchOption === 'site_travail') {
        return doctor.site.toLowerCase().includes(this.searchValue.toLowerCase());
      } else if (this.searchOption === 'speciality') {
        return doctor.specialite.toLowerCase().includes(this.searchValue.toLowerCase());
      } else if (this.searchOption === 'experience') {
        if (this.experienceRange === '0-5') {
          return doctor.exp >= 0 && doctor.exp < 5;
        } else if (this.experienceRange === '5-10') {
          return doctor.exp >= 5 && doctor.exp < 10;
        } else if (this.experienceRange === '10+') {
          return doctor.exp >= 10;
        }
      }
      return false;
    });
  }


  logout() {
    this.authService.logout();
  }


}
