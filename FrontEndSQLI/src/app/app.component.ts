import { Component } from '@angular/core';
import { RouterOutlet, Router, NavigationEnd, NavigationStart } from '@angular/router';
import { CommonModule } from '@angular/common';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'FrontEndSQLI';
  isLoginPage = false;

  constructor(private router: Router) {
    // Handle both NavigationStart and NavigationEnd to cover all navigation scenarios
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart || event instanceof NavigationEnd)
    ).subscribe(event => {
      if (event instanceof NavigationStart) {
        // While navigating, we can assume the layout should not be shown
        this.isLoginPage = false;
      } else if (event instanceof NavigationEnd) {
        // After navigation ends, we check if the current URL is the login page
        this.isLoginPage = event.url === '/login';
      }
    });
  }

  logout() {
    // Implement logout logic here
    this.router.navigate(['/login']);
  }
}
