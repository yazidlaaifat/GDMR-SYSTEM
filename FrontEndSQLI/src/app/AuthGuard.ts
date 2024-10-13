import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {AuthService} from "./Services/auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate{

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{
    const token = this.authService.getToken();
    const userRole = this.authService.getUserRole();
    const expectedRole = route.data['role'];

    if (token && userRole === expectedRole) {
      return true;
    }

    this.router.navigate(['login']);
    return false;
  }
}
