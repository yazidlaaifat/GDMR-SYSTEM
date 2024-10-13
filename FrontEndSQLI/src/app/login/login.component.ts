import { Component } from '@angular/core';
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { AuthService } from "../Services/auth.service";
import { Router } from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) {
  }

  login() {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {

        this.authService.setToken(response.jwt);
        this.authService.getCurrentUser().subscribe({
          next: user => {
            const role = this.authService.getUserRole();
            if (role === 'ADMIN') {
              this.router.navigate(['admin-dashboard']);
            } else if (role === 'MEDECIN') {
              this.router.navigate(['medecin-dashboard']);
            }  else if (role === 'CHARGERH') {
              this.router.navigate(['charge-rh-dashboard']);
            } else if (role === 'COLLABORATEUR') {
              this.router.navigate(['collaborateur-dashboard']);
            }else {
              alert('Unauthorized role');
            }
          },
          error: error => {

            alert('Login failed');
          }
        });
      },
      error: (error) => {

        alert('Login failed');
      }
    });
  }
}
