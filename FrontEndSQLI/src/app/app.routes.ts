import { Routes } from '@angular/router';
import {ActiveVisitsListComponent} from "./active-visits-list/active-visits-list.component";
import {DossierMedicalComponent} from "./dossier-medical/dossier-medical.component";
import {AddAntecedantComponent} from "./add-antecedant/add-antecedant.component";
import {LoginComponent} from "./login/login.component";
import {AdminDashboardComponent} from "./admin-dashboard/admin-dashboard.component";
import {AuthGuard} from "./AuthGuard";
import {CollaborateurDashboardComponent} from "./collaborateur-dashboard/collaborateur-dashboard.component";
import {MedecinDashboardComponent} from "./medecin-dashboard/medecin-dashboard.component";
import {ChargerhDashboardComponent} from "./chargerh-dashboard/chargerh-dashboard.component";
import {MedecinProfileComponent} from "./medecin-profile/medecin-profile.component";
import {ChargerhCreneauComponent} from "./chargerh-creneau/chargerh-creneau.component";
import {ChargerhDoctorsComponent} from "./chargerh-doctors/chargerh-doctors.component";
import {ChargerhCollaboratorsComponent} from "./chargerh-collaborators/chargerh-collaborators.component";
import {ChargerhVisitsComponent} from "./chargerh-visits/chargerh-visits.component";
import {MedecinScheduleComponent} from "./medecin-schedule/medecin-schedule.component";
import {MedecinVisitsComponent} from "./medecin-visits/medecin-visits.component";
import {CollaborateurScheduleComponent} from "./collaborateur-schedule/collaborateur-schedule.component";
import {CollaborateurProfileComponent} from "./collaborateur-profile/collaborateur-profile.component";
import {CollaborateurVisitsComponent} from "./collaborateur-visits/collaborateur-visits.component";

export const routes: Routes = [
  {path : 'login', component: LoginComponent},
  { path: 'admin-dashboard', component: AdminDashboardComponent, canActivate: [AuthGuard], data: {role: 'ADMIN'} },
  { path: 'collaborateur-dashboard', component: CollaborateurDashboardComponent, canActivate: [AuthGuard], data: {role: 'COLLABORATEUR'} },
  { path: 'collaborateur-profile', component: CollaborateurProfileComponent, canActivate: [AuthGuard], data: {role: 'COLLABORATEUR'} },
  { path: 'collaborateur-schedule', component: CollaborateurScheduleComponent, canActivate: [AuthGuard], data: {role: 'COLLABORATEUR'} },
  { path: 'collaborateur-visits', component: CollaborateurVisitsComponent, canActivate: [AuthGuard], data: {role: 'COLLABORATEUR'} },
  { path: 'medecin-dashboard', component: MedecinDashboardComponent, canActivate: [AuthGuard], data: {role: 'MEDECIN'} },
  { path: 'medecin-schedule', component: MedecinScheduleComponent, canActivate: [AuthGuard], data: {role: 'MEDECIN'} },
  { path: 'medecin-visits', component: MedecinVisitsComponent, canActivate: [AuthGuard], data: {role: 'MEDECIN'} },
  {path : "activevisits-list",component : ActiveVisitsListComponent, canActivate: [AuthGuard], data: {role: 'MEDECIN'}},
  {path : "dossier-medical/:collaborateurId",component : DossierMedicalComponent, canActivate: [AuthGuard], data: {role: 'MEDECIN'}},
  {path : "add-antecedant",component : AddAntecedantComponent, canActivate: [AuthGuard], data: {role: 'MEDECIN'}},
  {path : "medecin-profile",component : MedecinProfileComponent, canActivate: [AuthGuard], data: {role: 'MEDECIN'}},
  { path: 'charge-rh-dashboard', component: ChargerhDashboardComponent, canActivate: [AuthGuard], data: {role: 'CHARGERH'} },
  { path: 'charge-rh-creneau', component: ChargerhCreneauComponent, canActivate: [AuthGuard], data: {role: 'CHARGERH'} },
  { path: 'charge-rh-doctors', component: ChargerhDoctorsComponent, canActivate: [AuthGuard], data: {role: 'CHARGERH'} },
  { path: 'charge-rh-collaborateurs', component: ChargerhCollaboratorsComponent, canActivate: [AuthGuard], data: {role: 'CHARGERH'} },
  { path: 'charge-rh-visits', component: ChargerhVisitsComponent, canActivate: [AuthGuard], data: {role: 'CHARGERH'} },
  { path: '**', redirectTo: '/login'}
];

