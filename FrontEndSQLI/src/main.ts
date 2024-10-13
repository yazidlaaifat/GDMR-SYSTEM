import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { AppComponent } from './app/app.component';
import { importProvidersFrom } from '@angular/core';
import { routes } from './app/app.routes';
import { provideRouter } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {authInterceptorFn} from "./app/AuthInterceptorFn";

console.log('Registering providers...');

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient(withInterceptors([authInterceptorFn])), // Use withInterceptors
    importProvidersFrom(CommonModule, DatePipe),
    provideAnimationsAsync()
  ]
}).catch(err => console.error(err));

console.log('Bootstrap complete.');
