import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink, Router} from "@angular/router";
import {AuthService} from "../Services/auth.service";
import {HttpClient} from "@angular/common/http";
import {CommonModule, DatePipe} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-collaborateur-schedule',
  standalone: true,
  imports: [
    RouterLink,
    DatePipe,
    FormsModule,
    CommonModule
  ],
  templateUrl: './collaborateur-schedule.component.html',
  styleUrl: './collaborateur-schedule.component.css'
})
export class CollaborateurScheduleComponent implements OnInit{
  currentUser: any = {};
  creneaux: any[] = [];
  selectedCreneauId: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
  }
  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {

        this.currentUser = data;
        //this.loadCreneaux();
        this.checkDossier();
      },
      error: error => {

      }
    });
  }

  checkDossier() {
    this.http.get<boolean>(`http://localhost:8080/collaborateur/checkDossier/${this.currentUser.idUser}`).subscribe({
      next: isComplete => {
        if (!isComplete) {
          alert('Your profile is not yet complete. Please insert your profile information.');
          this.router.navigate(['/collaborateur-profile']);
        } else {
          this.loadCreneaux();
        }
      },
      //error: error => console.error('Error checking dossier completeness:', error)
    });
  }

  loadCreneaux(): void {
    this.http.get<any[]>(`http://localhost:8080/collaborateur/availableCreneaux/${this.currentUser.idUser}`)
      .subscribe({
        next: (data: any[]) => {
          this.creneaux = data.filter(creneau => creneau.disponibilité);
        },
        error: (error) => {

        }
      });
  }

  confirmCreneau(): void {
    if (!this.selectedCreneauId){
      alert('Please select a creneau first');
      return;
    }
    const url = `http://localhost:8080/collaborateur/confirmCreneau?creneauId=${this.selectedCreneauId}&collaborateurId=${this.currentUser.idUser}`;
    this.http.post(url, {}).subscribe({
      next: (response: Object) => {

      },
      error: (error) => {

      }
    });
    window.location.reload();
  }


  logout() {
    this.authService.logout();
  }
}
