import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthService} from "../Services/auth.service";
import {HttpClient} from "@angular/common/http";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-chargerh-visits',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule
  ],
  templateUrl: './chargerh-visits.component.html',
  styleUrl: './chargerh-visits.component.css'
})
export class ChargerhVisitsComponent implements OnInit{
  currentUser: any = {};
  visits: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
  }
  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {

        this.currentUser = data;
        this.loadVisits();
      },
      error: error => {

      }
    });
  }


  loadVisits() {
    const userId = this.currentUser.idUser;  // Assuming the id is in 'idUser'
    this.http.get<any[]>(`http://localhost:8080/chargerh/my-visits?chargéRHId=${userId}`).subscribe({
      next: data => {
        this.visits = data;
      },
      error: error => {

      }
    });
  }






  logout() {
    this.authService.logout();
  }

}
