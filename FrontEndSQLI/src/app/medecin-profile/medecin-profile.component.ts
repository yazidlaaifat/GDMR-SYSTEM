import { Component, OnInit } from '@angular/core';
import {CommonModule} from "@angular/common";
import {HttpClient} from "@angular/common/http";
import {Router ,RouterLink} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {AuthService} from "../Services/auth.service";

@Component({
  selector: 'app-medecin-profile',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './medecin-profile.component.html',
  styleUrl: './medecin-profile.component.css'
})
export class MedecinProfileComponent implements OnInit{
  currentUser: any = {};
  showingForm: boolean = false;

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.loadCurrentUser();
  }

  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {

        this.currentUser = data;
        if (this.currentUser && this.currentUser.idUser) {
          this.fetchMedecinProfile(this.currentUser.idUser);
        } else {

        }
      },
      error: error => {

      }
    });
  }

  fetchMedecinProfile(medecinId: number){
    this.http.get<any>(`http://localhost:8080/medecin/${medecinId}`).subscribe({
      next: medecin => {
        this.currentUser = { ...this.currentUser, ...medecin };
      },
      error: error => {

      }
    });
  }

  profileFilled(){
    return this.currentUser.siteTravail && this.currentUser.specialite && this.currentUser.qualification && this.currentUser.experience;
  }

  showForm(){
    this.showingForm = true;
  }

  hideForm() {
    this.showingForm = false;
  }

  updateProfile(){
    this.http.put(`http://localhost:8080/medecin/update/${this.currentUser.idUser}`, this.currentUser).subscribe({
      next: data => {
        this.currentUser = data;
        this.showingForm = false;
      },
      error: error => {

      }
    });
    window.location.reload();
  }


  logout(){
    this.authService.logout();
    this.router.navigate(['login']);
  }

}
