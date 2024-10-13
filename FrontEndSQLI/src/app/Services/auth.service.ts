import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {JwtHelperService} from "@auth0/angular-jwt";
import {Observable} from "rxjs";
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService{
  private apiUrl = 'http://localhost:8080';
  private jwtHelper = new JwtHelperService();
  private token: string | null = null;
  private userRole: string | null = null;

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/authenticate`,{ username, password}).pipe(
      tap(response => {

        this.token = response.jwt;

        if (typeof this.token === "string") {
          localStorage.setItem('token', this.token);
        }
        this.getCurrentUser().subscribe();
      })
    );
  }


  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return token ? !this.jwtHelper.isTokenExpired(token) : false;
  }

  getToken(): string | null {
    const token = localStorage.getItem('token');

    return token;
  }

  setToken(token: string): void{

    localStorage.setItem('token',token);
  }

  removeToken(): void{
    localStorage.removeItem('token');
  }

  getCurrentUser(): Observable<any>{
    return this.http.get<any>(`${this.apiUrl}/current-user`, {
      headers: {
        'Authorization': `Bearer ${this.getToken()}`
      }
    }).pipe(
      tap(user => {
        this.userRole = user.role;
        localStorage.setItem('userRole', user.role);
      })
    );
  }

  getUserRole(): string | null {
    return this.userRole || localStorage.getItem('userRole');
  }

  logout(): void{
    this.token = null;
    this.userRole = null;
    localStorage.removeItem('token');
    localStorage.removeItem('userRole');
    this.router.navigate(['/login']);
  }
}
