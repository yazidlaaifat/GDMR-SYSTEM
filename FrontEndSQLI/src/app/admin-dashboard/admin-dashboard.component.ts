import { Component, OnInit } from '@angular/core';
import { CommonModule } from "@angular/common";
import {HttpClient} from "@angular/common/http";
import { Router } from "@angular/router";
import { FormsModule } from "@angular/forms";
import { AuthService } from "../Services/auth.service";

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  users: any[] = [];
  newUser: any = {};
  isUserListVisible = false;
  isCreateUserFormVisible = false;
  currentUser: any = {};

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.loadUsers();
    this.loadCurrentUser();
  }

  loadUsers() {

    this.http.get<any[]>('http://localhost:8080/admin/users').subscribe({
      next: data => {

        this.users = data;
      },
      error: error => {

      }
    });
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

  createUser() {

    this.http.post('http://localhost:8080/admin/create-user', this.newUser).subscribe({
      next: () => {

        this.loadUsers();
        this.closeCreateUserForm();
      },
      error: error => {

      }
    });
  }

  deleteUser(id: number) {
    if (id){

    this.http.delete(`http://localhost:8080/admin/delete-user/${id}`).subscribe({
      next: () => {

        this.loadUsers();
      },
      error: error => {

      }
    });
  } else {

    }
  }

  toggleUserList() {
    this.isUserListVisible = !this.isUserListVisible;
    this.isCreateUserFormVisible = false;
  }

  showCreateUserForm() {
    this.isCreateUserFormVisible = true;
    this.isUserListVisible = false;
  }

  closeCreateUserForm() {
    this.isCreateUserFormVisible = false;
  }

  logout() {
    this.authService.logout();
  }


}
