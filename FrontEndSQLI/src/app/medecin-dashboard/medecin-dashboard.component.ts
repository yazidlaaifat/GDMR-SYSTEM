import { Component, OnInit } from '@angular/core';
import {CommonModule} from "@angular/common";
import {HttpClient} from "@angular/common/http";
import {Router, RouterLink} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {AuthService} from "../Services/auth.service";
import {VisiteService} from "../Services/visite.service";

@Component({
  selector: 'app-medecin-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './medecin-dashboard.component.html',
  styleUrl: './medecin-dashboard.component.css'
})
export class MedecinDashboardComponent implements OnInit{
  currentUser: any = {};
  selectedFile: File | null = null;
  plannedVisitsCount: number = 0;
  plannedVisits: any[] = [];

  constructor(private http: HttpClient, private authService: AuthService, private router: Router, private visiteService: VisiteService) {}

  ngOnInit() {
    this.loadCurrentUser();
  }

  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {

        this.currentUser = data;
        this.fetchPlannedVisitsData();
      },
      error: error => {

      }
    });
  }

  fetchPlannedVisitsData() {
    if (!this.currentUser.idUser) {

      return;
    }

    this.visiteService.getPlannedVisits(this.currentUser.idUser).subscribe({
      next: (data: any) => {
        this.plannedVisits = data;
      },
      error: error => {

      }
    });

    this.visiteService.getPlannedVisitsCount(this.currentUser.idUser).subscribe({
      next: (count: number) => {
        this.plannedVisitsCount = count;
      },
      error: error => {

      }
    });
  }

  navigateToActiveVisitsList() {
    this.router.navigate(['/activevisits-list']);
  }


  logout(){
    this.authService.logout();
    this.router.navigate(['login']);
  }

  onFileSelected(event: any){
    this.selectedFile = event.target.files[0];
  }

  uploadFile() {
    if (this.selectedFile && this.currentUser?.idUser) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      formData.append('medecinId', this.currentUser.idUser);

      this.http.post('http://localhost:8080/medecin/uploadPlanning', formData, { responseType: 'text' }).subscribe({
        next: (response: string) => {

        },
        error: (error) => {

        }
      });
    } else {
      if (!this.selectedFile) {

      }
      if (!this.currentUser?.idUser) {

      }
    }
    window.location.reload();
  }

  selectFile(){
    document.getElementById('file')?.click();
  }
}
