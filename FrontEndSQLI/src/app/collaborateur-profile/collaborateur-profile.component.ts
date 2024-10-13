import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink,Router} from "@angular/router";
import {AuthService} from "../Services/auth.service";
import {HttpClient} from "@angular/common/http";
import {CommonModule, DatePipe} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-collaborateur-profile',
  standalone: true,
  imports: [
    RouterLink,
    DatePipe,
    CommonModule,
    FormsModule
  ],
  templateUrl: './collaborateur-profile.component.html',
  styleUrl: './collaborateur-profile.component.css'
})
export class CollaborateurProfileComponent implements OnInit{
  currentUser: any = {};
  collaborateur: any = {};
  dossierMedical: any = {};
  showForm: boolean = false;
  editing: boolean = false;

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
        this.loadProfile();
      },
      error: error => {

      }
    });
  }


  loadProfile() {
    this.http.get<any>(`http://localhost:8080/collaborateur/profile/${this.currentUser.idUser}`).subscribe({
      next: (profileData) => {
        this.collaborateur = profileData;
        this.dossierMedical = profileData.dossierMedical;


        if (!this.dossierMedical || !this.dossierMedical.sexe || !this.dossierMedical.height || !this.dossierMedical.weight || !this.dossierMedical.groupeSanguin) {
          this.showForm = true;
        }
      },
      error: (error) => {

      }
    });
  }

  updateProfile() {
    const updatedProfile = {
      departement: this.collaborateur.departement,
      dossierMedical: this.dossierMedical
    };

    this.http.post<any>(`http://localhost:8080/collaborateur/updateProfile?collaborateurId=${this.currentUser.idUser}`, updatedProfile)
      .subscribe({
        next: (response) => {
          this.editing = false;
          this.showForm = false;
          this.router.navigate(['/collaborateur-profile']);
        },
        error: (error) => {

        }
      });
  }

  toggleEdit() {
    this.editing = !this.editing;
  }

  cancelEdit() {
    this.editing = false;
  }


  logout() {
    this.authService.logout();
  }

}
