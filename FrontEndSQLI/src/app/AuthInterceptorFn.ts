import {HttpInterceptorFn} from "@angular/common/http";
import { inject} from "@angular/core";
import {AuthService} from "./Services/auth.service";

export const authInterceptorFn: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();
  if(token){
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

    return next(cloned);
  } else {

    return next(req);
  }
};
